package main.java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class that allows convertion of editor to talking coach
 */
public class DirectoryCreator {
	private File directory;
	private ArrayList<Module> modules;
	private ArrayList<Language> languages;

	public DirectoryCreator(File newdirectory, ArrayList<Module> newmodules, ArrayList<Language> newlanguages) {
		directory = newdirectory;
		modules = newmodules;
		languages = newlanguages;
	}
	/**
	 * Method that converts the code to javascript
	 */
	public void print(){
		//Build.folder - - this never changes so does not need to be rewritten
		//home.folder
		//modules
		writeHomeModules();
		//languages
		writeHomeLanguages();
		//images.folder
		writeImages();
		//modules.folder
		writeModules();
		//TemplateData.folder - - this never changes so does not need to be rewritten
		//coachstyle.css - - this never changes so does not need to be rewritten. could do tho

		//index.html
		writeIndex();
		//output.txt

	}

	/**
	 * Writes the index.html file
	 */
	private void writeIndex() {
		try {
			File dir = new File(directory.getCanonicalPath()+"//index.html");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);

			printGeneralIndex(out);
			printLanguageSelection(out);
			printDisplayCurrentLanguage(out);
			printSpeakFunction(out);
			printLoadHomeScreen(out);
			//now load every module and its functions
			for(int i=0;i<modules.size();i++){
				printModule(modules.get(i),out);
			}
			//
			printGeneralEnding(out);

			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes a module out into files for the coach
	 * @param m
	 * 		module to write
	 * @param out
	 * 		writer to write it with
	 */
	private void printModule(Module m, BufferedWriter out) {
		//loadmodule
		printLoadModule(m, out);
		//generalquestions
		printGeneralModuleQuestions(m,out);
		//questions
		printQuestions(m,out);
		//responses +> next question
		printResponses(m,out);
	}

	/**
	 * Writes responses out into files for the coach
	 * @param m
	 * 		model the responses belong to
	 * @param out
	 * 		writer to write it with
	 */
	private void printResponses(Module m, BufferedWriter out) {
		try {
			for (int i = 0; i < m.getQuestions().size(); i++) {
				Question curQuestion = m.getQuestions().get(i);
				for (int k = 0; k < curQuestion.getOptions().size(); k++) {
					Option curOption = curQuestion.getOptions().get(k);

					out.write("function " + m.getTitle().get(0) + "vraag" + i + "option" + k + "() {\n" +
							"\t\t\tif (performedanswers.indexOf(\"q" + i + "o" + k + "\") != -1) {\n" +
							"\t\t\t\treturn;\n" +
							"\t\t\t}\n" +
							"\t\t\tperformedanswers.push(\"q" + i + "o" + k + "\");\n");
					if (curOption.getLink().getID() != -1) {
						out.write("\t\t\tvar xhttp = new XMLHttpRequest();\n" +
								"\t\t\txhttp.onreadystatechange = function() {\n" +
								"\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
								"\t\t\t\t\tdocument.getElementById(\"modulecontainer\").innerHTML += this.responseText;\n" +
								"\t\t\tdisplayCurrentLanguage();\n" +

                                "\t\t\t\t}\n" +
                                "\t\t\t};\n" +
                                "\t\t\txhttp.open(\"GET\", \"modules/" + m.getTitle().get(0) + "/response-question" + i + "-option" + k + ".txt\", true);\n" +
                                "\t\t\txhttp.send();\n" +
                                "\t\t\t\tspeak(" + stringsToArray(curOption.getResponse()) + ");\n" +

                                "\t\t\t" + m.getTitle().get(0) + "vraag" + curOption.getLink().getID() + "()\n");

					} else {
						out.write("\t\t\tloadHomeScreen()\n");
					}
					out.write("\t\t};\n");

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes questions out into files for the coach
	 * @param m
	 * 		model the questions belong to
	 * @param out
	 * 		writer to write it with
	 */
	private void printQuestions(Module m, BufferedWriter out) {
		try {
			for(int i=0;i<m.getQuestions().size();i++){
				out.write("function "+m.getTitle().get(0)+"vraag"+i+"() {\n" +
						"\t\t\tsleep(1000).then(() => {\n" +
						"\t\t\t\tvar xhttp = new XMLHttpRequest();\n" +
						"\t\t\t\txhttp.onreadystatechange = function() {\n" +
						"\t\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
						"\t\t\t\t\t\tdocument.getElementById(\"modulecontainer\").innerHTML += this.responseText;\n" +
						"\t\t\tdisplayCurrentLanguage();\n" +

                        "\t\t\t\t\t}\n" +
                        "\t\t\t\t};\n" +
                        "\t\t\t\txhttp.open(\"GET\", \"modules/"+m.getTitle().get(0)+"/question"+i+".txt\", true);\n" +
                        "\t\t\t\txhttp.send();\n" +
                        "\t\t\t\tspeak("+ stringsToArray(m.getQuestions().get(i).getBody()) +");\n" +
                        "\t\t\t})\n" +
						"\t\t}\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Writes general questions out into files for the coach
	 * @param m
	 * 		model the general questions belong to
	 * @param out
	 * 		writer to write it with
	 */
	private void printGeneralModuleQuestions(Module m, BufferedWriter out) {
		try {
			for (int i = 0; i < m.getGquestions().size(); i++) {
				out.write("\t\tfunction " + m.getTitle().get(0) + "hoofdvraag" + i + "() {\n");
				out.write("\t\tspeak("+ stringsToArray(m.getGquestions().get(i).getAnswer()) +")\n");
				out.write("\t\t};\n");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes responses out into files for the coach
	 * @param m
	 * 		model the responses belong to
	 * @param out
	 * 		writer to write it with
	 */
	private void printLoadModule(Module m, BufferedWriter out) {
		try {
			out.write("function load"+m.getTitle().get(0)+"Module() {\n" +
					"\t\t\tvar xhttp = new XMLHttpRequest();\n" +
					"\t\t\txhttp.onreadystatechange = function() {\n" +
					"\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
					"\t\t\t\t\tdocument.getElementById(\"modulecontainer\").innerHTML = this.responseText;\n" +
					"\t\t\tdisplayCurrentLanguage();\n" +

                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\txhttp.open(\"GET\", \"modules/"+m.getTitle().get(0)+"/question0.txt\", true);\n" +
                    "\t\t\txhttp.send();\n" +
                    "\t\t\tvar xhttp = new XMLHttpRequest();\n" +
                    "\t\t\txhttp.onreadystatechange = function() {\n" +
                    "\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
                    "\t\t\t\t\tdocument.getElementById(\"rightsideQuestions\").innerHTML = this.responseText;\n" +
                    "\t\t\tdisplayCurrentLanguage();\n" +

                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\txhttp.open(\"GET\", \"modules/"+ m.getTitle().get(0) +"/questions.txt\", true);\n" +
                    "\t\t\txhttp.send();\n" +
                    "\t\t\tspeak("+ stringsToArray(m.getQuestions().get(0).getBody()) +");\n" +
					"\t\t}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * converts an array of strings to a single string
	 * @param body
	 * 		the array of strings
	 * @return
	 * 		the single string
	 */
	private String stringsToArray(ArrayList<String> body) {
		String res = "[";
		for(int i=0;i<body.size();i++){
			res+="\"";
			res+=body.get(i);
			res+="\"";
			if(i!=body.size()-1){
				res+=", ";
			}
		}
		res +="]";
		return res;
	}

	/**
	 * Writes the ending of the coachfiles
	 * @param out
	 * 		the writer to write with
	 */
	private void printGeneralEnding(BufferedWriter out) {
		try {
			out.write("\n" +
					"\t\twindow.onload = loadHomeScreen();\n" +
					"\t\t\tdisplayCurrentLanguage();\n" +

                    "\t</script>\n" +
                    "</body>\n" +
                    "\n" +
					"</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates the file that allows loading the homescreen
	 * @param out
	 * 		the writer to write with
	 */
	private void printLoadHomeScreen(BufferedWriter out) {
		try {
			out.write("function loadHomeScreen() {\n" +
					"\t\t\tperformedanswers = new Array();\n" +
					"\t\t\tvar xhttp = new XMLHttpRequest();\n" +
					"\t\t\txhttp.onreadystatechange = function() {\n" +
					"\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
					"\t\t\t\t\tdocument.getElementById(\"modulecontainer\").innerHTML = this.responseText;\n" +
					"\t\t\tdisplayCurrentLanguage();\n" +

                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\txhttp.open(\"GET\", \"home/modules.txt\", true);\n" +
                    "\t\t\txhttp.send();\n" +
                    "\n" +
                    "\t\t\tvar xhttp = new XMLHttpRequest();\n" +
                    "\t\t\txhttp.onreadystatechange = function() {\n" +
                    "\t\t\t\tif (this.readyState == 4 && this.status == 200) {\n" +
                    "\t\t\t\t\tdocument.getElementById(\"rightsideQuestions\").innerHTML = this.responseText;\n" +
                    "\t\t\tdisplayCurrentLanguage();\n" +

                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\txhttp.open(\"GET\", \"home/languages.txt\", true);\n" +
                    "\t\t\txhttp.send();\n" +
					"\t\t}\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * creates the script for speech for the coach
	 * @param out
	 * 		writer to write with
	 */
	private void printSpeakFunction(BufferedWriter out) {
		try {
			out.write("function speak(q){\n");
			out.write("switch (currentLanguage) {\n");
			for(int i=0;i<languages.size();i++){
				out.write("\t\t\t\tcase \""+languages.get(i)+"\":\n");
				out.write("\t\t\t\t\tCoach.SendMessage('TalkingCoach', 'convertToSpeach', q["+i+"]);\n");
				out.write("\t\t\t\t\tbreak;\n");
			}
			out.write("\t}\n");
			out.write("}\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates the display for the current language
	 * @param out
	 * 		writer to write with
	 */
	private void printDisplayCurrentLanguage(BufferedWriter out) {
		try {
			out.write("function displayCurrentLanguage(){\n");
			out.write("switch (currentLanguage) {\n");
			for(int i=0;i<languages.size();i++){
				out.write("\t\t\t\tcase \""+languages.get(i)+"\":\n");
				out.write("\t\t\t\t\tlanguage"+i+"();\n");
				out.write("\t\t\t\t\tbreak;\n");
			}
			out.write("\t}\n");
			out.write("}\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates the language selection
	 * @param out
	 * 		writer to write with
	 */
	private void printLanguageSelection(BufferedWriter out) {
		try {
			for(int i=0;i<languages.size();i++){
				out.write("function language"+ i +"() {\n");
				out.write("currentLanguage = \"" + languages.get(i)+"\";\n");
				for(int k=0;k<languages.size();k++){
					out.write("\t\t\tvar elems"+languages.get(k)+" = document.getElementsByClassName(\""+languages.get(k)+"\");\n");
				}
				out.write("\t\t\tfor (var i = 0; i < elems"+languages.get(0)+".length; i++) {\n");
				for(int k=0;k<languages.size();k++) {
					if(k!=i){
						out.write("\t\t\t\telems"+languages.get(k)+"[i].style.display = \"none\";\n");
					}
				}
				out.write("\t\t\t\telems"+languages.get(i)+"[i].style.display = \"inline-block\";\n");
				out.write("\t\t\t}\n" +
						"\t\t}\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates the general index
	 * @param out
	 * 		writer to write with
	 */
	private void printGeneralIndex(BufferedWriter out) {
		try {
			out.write("<!DOCTYPE HTML>\n" +
					"<html>\n" +
					"\n" +
					"<head>\n" +
					"\n" +
					"\t<meta charset=\"utf-8\">\n" +
					"\t<meta http-equiv=\"Content-Type\" content=\"Text/HTML; charset=utf-8\" />\n" +
					"\t<title>Virtuele Coach</title>\n" +
					"\t<link rel=\"stylesheet\" type=\"text/css\" href=\"coachstyle.css\">\n" +
					"\t<script src=\"TemplateData/UnityProgress.js\"></script>\n" +
					"\t<script src=\"TemplateData/textToSpeech.js\"></script>\n" +
					"\t<script src=\"Build/UnityLoader.js\"></script>\n" +
					"\t<script>\n" +
					"\t\tvar Coach = UnityLoader.instantiate(\"gameContainerId\", \"Build/coach.json\");\n" +
					"\t</script>\n" +
					"</head>\n" +
					"\n" +
					"<body>\n" +
					"\t<header></header>\n" +
					"\t<div class=\"core\">\n" +
					"\t\t<div class=\"rightside\">\n" +
					"\t\t\t<div id=\"gameContainerId\" style=\"width: 500px; height: 500px;\">\n" +
					"\t\t\t\t<!-- this is where the coach is -->\n" +
					"\t\t\t</div>\n" +
					"\t\t\t<div class=\"controlpanel\">\n" +
					"\t\t\t\t<input class=\"controlbuttons\" type=\"button\" onclick=stopTalk() value=\"Stop met praten\">\n" +
					"\t\t\t\t<input class=\"controlbuttons\" type=\"button\" onclick=changeBackground() value=\"Verander Achtergrond\" />\n" +
					"\t\t\t\t<input class=\"controlbuttons\" type=\"button\" onclick=changeCoach() value=\"Verander Persoon\" />\n" +
					"\t\t\t</div>\n" +
					"\t\t\t<div id=\"rightsideQuestions\">\n" +
					"\n" +
					"\t\t\t</div>\n" +
					"\t\t</div>\n" +
					"\t\t<div id=\"modulecontainer\">\n" +
					"\n" +
					"\t\t</div>\n" +
					"\n" +
					"\t</div>\n" +
					"\t<!-- core -->\n" +
					"\t<script>\n" +
					"\t\tvar performedanswers = new Array();\n" +
					"\t\tvar currentLanguage = \""+ languages.get(0) +"\";\n" +
					"\t\t/* general <functions></functions>*/\n" +
					"\t\tfunction sleep(time) {\n" +
					"\t\t\treturn new Promise((resolve) => setTimeout(resolve, time));\n" +
					"\t\t}\n" +
					"\n" +
					"\t\tfunction stopTalk() {\n" +
					"\t\t\tCoach.SendMessage('TalkingCoach', 'stopSpeach');\n" +
					"\t\t}\n" +
					"\n" +
					"\t\tfunction changeBackground() {\n" +
					"\t\t\tCoach.SendMessage('TalkingCoach', 'changeBackround');\n" +
					"\t\t}\n" +
					"\n" +
					"\t\tfunction changeCoach() {\n" +
					"\t\t\tCoach.SendMessage('TalkingCoach', 'changeCoach');\n" +
					"\t\t}\n" +
					"\t\t/* end of general functions */\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the modules
	 */
	private void writeModules() {
		for (int i = 0; i < modules.size(); i++) {
			Module m = modules.get(i);
			//write general questions
			writeModulesGeneralQuestions(m);
			//write questions
			for(int k =0;k<m.getQuestions().size();k++){
				writeModulesQuestion(m,m.getQuestions().get(k),k);
				for(int l=0;l<m.getQuestions().get(k).getOptions().size();l++){
					writeModulesQuestionOption(m,m.getQuestions().get(k),m.getQuestions().get(k).getOptions().get(l),l);
				}
			}
			//write answers to options

		}
	}
	
/**
 * creates a file for a module, question, option combination
 * @param m
 * 		the module
 * @param q
 * 		the question
 * @param o
 * 		the option
 * @param optionIndex
 * 		index of the option
 */
	private void writeModulesQuestionOption(Module m, Question q, Option o,int optionIndex) {
		try {
			File dir = new File(directory.getCanonicalPath()+"//modules//" +m.getTitle().get(0)+"//response-question"+ q.getID() +"-option"+optionIndex+".txt");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);
			out.write("<div class=\"modulelinks\">\n");
			//write every language
			//-----
			for(int k=0;k<languages.size();k++){
				out.write("  <span class=\"" + languages.get(k) + "\">\n");
				out.write("    " + o.getResponse().get(k) +"\n");
				out.write("  </span>\n");
			}
			//-----
			out.write("</div>\n");
			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a file for a module, question combination
	 * @param m
	 * 		the module
	 * @param q
	 * 		the question
	 * @param indexOfQuestion
	 * 		index of the question
	 */
	private void writeModulesQuestion(Module m, Question q, int indexOfQuestion) {
		try {
			File dir = new File(directory.getCanonicalPath()+"//modules//"+ m.getTitle().get(0)+"//question" +q.getID()+".txt");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);
			out.write("<div class=\"modulelinks\">\n");
			//write every language for the question itself
			//-----
			for(int k=0;k<languages.size();k++){
				out.write("  <span class=\"" + languages.get(k) + "\">\n");
				out.write("    " + q.getBody().get(k) +"\n");
				out.write("  </span>\n");
			}
			//-----
			out.write("</div>\n");
			//now onto the options
			out.write("<div class=\"modulerechts\">\n");
			//write every option
			ArrayList<Option> options = q.getOptions();
			for(int i=0;i<options.size();i++){
				out.write("<span style=\"cursor: pointer;\" class=\"basicbuttons\" onclick=\"" +m.getTitle().get(0)+ "vraag"+indexOfQuestion+"option" + i +"()\">\n");
				//write every language for the option
				for(int k=0;k<languages.size();k++){
					out.write("  <span class=\"" + languages.get(k) + "\">\n");
					out.write("    " + options.get(i).getChoice().get(k) +"\n");
					out.write("  </span>\n");
				}
				out.write("</span>\n");
			}


			out.write("</div>\n");

			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates a file for the general questions of a module
	 * @param m
	 * 		the module
	 */
	private void writeModulesGeneralQuestions(Module m) {
		try {
			File dir = new File(directory.getCanonicalPath()+"//modules//"+ m.getTitle().get(0) +"//questions.txt");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);
			//write every general question
			//-----
			ArrayList<GQuestion> gq = m.getGquestions();
			for(int i=0;i<gq.size();i++){
				out.write("<div style=\"cursor: pointer;\" class=\"rightquestion\" onclick=\""+ m.getTitle().get(0)+"hoofdvraag" +i +"()\">\n");
				//for every language
				for(int k=0;k<languages.size();k++){
					out.write("  <span class=\"" + languages.get(k) + "\">\n");
					out.write("    " + gq.get(i).getQuestion().get(k) +"\n");
					out.write("  </span>\n");
				}
				out.write("</div>\n");
			}
			//-----
			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes down the images of every module
	 */
	private void writeImages() {
		try {
			//write every image
			//-----
			for(int i=0;i<modules.size();i++){
				Path path = Paths.get(modules.get(i).getPicture());
				if(Files.exists(path)){
					System.out.println(modules.get(i).getPicture());
					BufferedImage img = ImageIO.read(new File(modules.get(i).getPicture()));
					File output = new File(directory.getCanonicalPath()+ "//images//img" +modules.get(i).getTitle().get(0)+".png");
					output.getParentFile().mkdirs();
					ImageIO.write(img, "png", output);
				}
			}
			//-----
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a file containing the languages for on the home screen
	 */
	private void writeHomeLanguages() {
		try {
			File dir = new File(directory.getCanonicalPath()+"//home//languages.txt");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);
			//write every language
			//-----
			for(int i=0;i<languages.size();i++){
				out.write("<div style=\"cursor: pointer;\" class=\"rightquestion\" onclick=language" + i + "() >\n");
				out.write("  "+ languages.get(i).getName());
				out.write("</div>");
			}
			//-----
			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a list of modules for on the homescreen
	 */
	private void writeHomeModules() {
		try {
			File dir = new File(directory.getCanonicalPath()+"//home//modules.txt");
			dir.getParentFile().mkdirs();
			FileWriter fw = new FileWriter(dir);
			BufferedWriter out = new BufferedWriter(fw);
			//write every module
			//-----
			for(int i=0;i<modules.size();i++){
				out.write("<div style=\"cursor: pointer;\" class=\"module\"  onclick=load"+ modules.get(i).getTitle().get(0) +"Module()>\n");
				out.write("  <img src=\"images/"+ "img"+ modules.get(i).getTitle().get(0)+".png" +"\"></img>\n");
				//for every language
				for(int k=0;k<languages.size();k++){
					out.write("  <span class=\"" + languages.get(k) + "\">\n");
					out.write("    " + modules.get(i).getTitle().get(k) +"\n");
					out.write("  </span>\n");
				}
				out.write("</div>\n");
			}
			//-----
			out.close();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

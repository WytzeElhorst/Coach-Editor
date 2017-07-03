package main.java;

import java.io.BufferedWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {
	private int id;
	private ArrayList<String> body;
	private ArrayList<Option> options;

	public int getID() {
		return id;
	}

	public ArrayList<String> getBody() {
		return body;
	}

	public ArrayList<Option> getOptions() {
		return options;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setBody(ArrayList<String> body) {
		this.body = body;
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}

	/**
	 * @param i
	 * 		question id
	 * @param b
	 * 		text of the question
	 * @param opt
	 * 		list of options to the question
	 */
	public Question(int i, ArrayList<String> b, ArrayList<Option> opt) {
		id = i;
		body = b;
		options = opt;
	}

	/**
	 * Adds a text to the list of texts
	 * @param s
	 * 		the added text
	 */
	public void addBody(String s) {
		body.add(s);
	}
	
	/**
	 * Adds an option to the list of options
	 * @param curopt
	 * 		the option
	 */
	public void addOption(Option curopt) {
		options.add(curopt);
	}

	/**
	 * Turns the question into a String, if the question id is -1, returns 'Home'
	 */
	public String toString() {
		if (this.id == -1) {
			return "Home";
		}
		return "Question " + id;
	}

	/**
	 * Prints the question out onto a file
	 * @param out
	 * 		the writer to write with
	 * @param languages
	 * 		the list of languages
	 * @throws IOException
	 */
	public void Print(BufferedWriter out, ArrayList<Language> languages) throws IOException {
		out.write("<leftsidequestion>");
		out.newLine();
		out.write("<index>");
		out.newLine();
		out.write(this.getID() + "");
		out.newLine();
		out.write("</index>");
		out.newLine();
		out.write("<question>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			if (this.getBody().get(l) == "") {
				this.getBody().set(l, "inc");
			}
			out.write(this.getBody().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</question>");
		out.newLine();
		for (int o = 0; o < this.getOptions().size(); o++) {
			Option opt = this.getOptions().get(o);
			opt.Print(out, languages);
		}
		out.write("</leftsidequestion>");
		out.newLine();
	}

	/**
	 * Reads a question from a file
	 * @param sc
	 * 		the scanner it reads with
	 * @return
	 * 		the read question
	 */
	public static Question Read(Scanner sc) {
		Question quest = new Question(0,new ArrayList<String>(), new ArrayList<Option>());
		while(sc.hasNextLine()){
			String leftLine = sc.nextLine().trim();
			if(leftLine.contains("</leftsidequestion")){
				break;
			} else {
				if(leftLine.contains("<index>")){
					quest.setID(Integer.parseInt(sc.nextLine().trim()));
					assert sc.nextLine().contains("</index>");
				} else if(leftLine.contains("<question>")){
					while(sc.hasNextLine()){
						String questLine = sc.nextLine().trim();
						if(questLine.contains("</question")){
							break;
						} else {
							assert questLine.contains("<");//open language
							quest.addBody(sc.nextLine().trim());
							assert sc.nextLine().contains("</");// close language
							sc.nextLine();
						}
					}
				} else if(leftLine.contains("<answer>")){
					quest.addOption(Option.Read(sc));
				}
			}
		}
		return quest;
	}

	/**
	 * compares two questions to see if they're equal
	 * @param q
	 * 		the question to compare this with
	 * @return
	 * 		returns true if they are equal
	 */
	public boolean equals(Question q) {
		Boolean res = false;
		if (this.getID() == q.getID()) {
			if (this.getBody().size() == q.getBody().size()) {
				for (int i = 0; i < this.getBody().size(); i++) {
					if (!(this.body.get(i).equals(q.getBody().get(i)))) {
						return false;
					}
				}
			} else { 
				return false;
			}
			if ( this.getOptions().size() == q.getOptions().size()) {
				for (int i = 0; i < this.getOptions().size(); i++) {
					if (!(this.options.get(i).equals(q.getOptions().get(i)))) {
						return false;
					}
				}
			} else {
				return false;
			}
			res = true;
		}
		return res;
	}
}

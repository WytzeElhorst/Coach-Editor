package main.java;

import java.io.BufferedWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that represents an Option
 */
public class Option {
	private ArrayList<String> choice;
	private ArrayList<String> response;
	private int linkint;
	private Question link;
	
	/**
	 * @param c
	 * 		ArrayList of choices
	 * @param r
	 * 		ArrayList of responses
	 * @param q
	 * 		Question the option links to
	 */
	public Option(ArrayList<String> c, ArrayList<String> r, Question q) {
		choice = c;
		response = r;
		link = q;
		if (q != null) {
			linkint = q.getID();
		}
	}

	public ArrayList<String> getChoice() {
		return choice;
	}

	public ArrayList<String> getResponse() {
		return response;
	}

	public Question getLink() {
		return link;
	}

	public int getLinkint() {
		return linkint;
	}

	public void setLinkint(int linkint) {
		this.linkint = linkint;
	}

	public void setChoice(ArrayList<String> choice) {
		this.choice = choice;
	}

	public void setResponse(ArrayList<String> response) {
		this.response = response;
	}

	public void setLink(Question link) {
		this.link = link;
		if (link != null) {
			linkint = link.getID();
		}
	}
	/**
	 * Option creation only used for loading
	 * @param c	
	 * 		list of choices
	 * @param r	
	 * 		list of responses
	 * @param i 
	 * 		id of the linked question
	 */
	public Option(ArrayList<String> c, ArrayList<String> r, int i) {
		choice = c;
		response = r;
		linkint = i;
	}

	/**
	 * Adds a choice to the list of choices
	 * @param s
	 * 		the choice to be added
	 */
	public void addChoice(String s) {
		choice.add(s);
	}

	/**
	 * Adds a response to the list of responses
	 * @param s
	 * 		the response to be added
	 */
	public void addResponse(String s) {
		response.add(s);
	}

	/**
	 * Returns "Link to" plus the number of the question it links to.
	 * Will return "Link to Home" if the question it links to has an id of -1
	 */
	public String toString() {
		if (link.getID() == -1) {
			return "Link to Home";
		}
		return "Link to " + link.getID();
	}

	/**
	 * Writes down the option onto a file
	 * @param out 
	 * 		The writer to write with
	 * @param languages 
	 * 		list of languages
	 * @throws IOException
	 */
	public void Print(BufferedWriter out, ArrayList<Language> languages) throws IOException {
		out.write("<answer>");
		out.newLine();
		out.write("<value>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			if (this.getChoice().get(l) == "") {
				this.getChoice().set(l, "inc");
			}
			out.write(this.getChoice().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</value>");
		out.newLine();
		out.write("<respons>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			if (this.getResponse().get(l) == "") {
				this.getResponse().set(l, "inc");
			}
			out.write(this.getResponse().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</respons>");
		out.newLine();
		out.write("<nextquestionindex>");
		out.newLine();
		out.write(this.getLink().getID() + "");
		out.newLine();
		out.write("</nextquestionindex>");
		out.newLine();
		out.write("</answer>");
		out.newLine();
	}

	/**
	 * Reads an option from a file
	 * @param sc
	 * 		The scanner it reads with
	 * @return
	 * 		The read option
	 */
	public static Option Read(Scanner sc) {
		Option opt = new Option(new ArrayList<String>(), new ArrayList<String>(), null);
		while(sc.hasNextLine()){
			String answerLine = sc.nextLine().trim();
			if(answerLine.contains("</answer>")){
				break;
			} else {
				if(answerLine.contains("<value>")){
					while(sc.hasNextLine()) {
						String valueLine = sc.nextLine().trim();
						if (valueLine.contains("</value>")) {
							break;
						} else {
							assert valueLine.contains("<");//open language
							opt.addChoice(sc.nextLine().trim());
							assert sc.nextLine().contains("</");//close language
							sc.nextLine().trim();
						}
					}
				} else if(answerLine.contains("<respons>")){
					while(sc.hasNextLine()) {
						String responsLine = sc.nextLine().trim();
						if (responsLine.contains("</respons>")) {
							break;
						} else {
							assert responsLine.contains("<");//open language
							opt.addResponse(sc.nextLine().trim());
							assert sc.nextLine().contains("</");// close language
							sc.nextLine();
						}
					}
				} else if(answerLine.contains("<nextquestionindex>")){
					int nextQuestionID = Integer.parseInt(sc.nextLine().trim());
					opt.setLinkint(nextQuestionID);
					assert sc.nextLine().contains("</nextquestionindex>");
				}
			}
		}
		return opt;
	}

	/**
	 * Compares two Options to see if they're equal
	 * @param o 
	 * 		the Option to compare this one with
	 * @return
	 * 		returns true if they are equal
	 */
	public boolean equals(Option o) {
		Boolean res = false;
		if (this.linkint == o.linkint) {
			if ( this.getChoice().size() == o.getChoice().size()) {
				for (int i = 0; i < this.getChoice().size(); i++) {
					if (!(this.getChoice().get(i).equals(o.getChoice().get(i)))) {
						return false;
					}
				}
			} else {
				return false;
			}
			if ( this.getResponse().size() == o.getResponse().size()) {
				for (int i = 0; i < this.getResponse().size(); i++) {
					if (!(this.getResponse().get(i).equals(o.getResponse().get(i)))) {
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

package main.java;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that represents a general Question
 *
 */
public class GQuestion {
	private String curname;
	private ArrayList<String> question;
	private ArrayList<String> answer;
	
	/**
	 * @param q 
	 * 		ArrayList of questions
	 * @param a 
	 * 		ArrayList of answers
	 */
	public GQuestion(ArrayList<String> q, ArrayList<String> a) {
		question = q;
		answer = a;
	}
	
	public String getCurname() {
		return curname;
	}

	public void setCurname(String curname) {
		this.curname = curname;
	}
	
	public ArrayList<String> getQuestion() {
		return question;
	}

	public ArrayList<String> getAnswer() {
		return answer;
	}

	public void setQuestion(ArrayList<String> question) {
		this.question = question;
	}

	public void setAnswer(ArrayList<String> answer) {
		this.answer = answer;
	}
	
	public void addQuestion(String s) {
        question.add(s);
    }

    public void addResponse(String s) {
        answer.add(s);
    }

	public String toString() {
		return curname;
	}
	
	/**
	 * Writes down the general question onto a file
	 * @param out 
	 * 		The writer to write with
	 * @param languages 
	 * 		list of languages
	 * @throws IOException
	 */
	public void Print(BufferedWriter out, ArrayList<Language> languages) throws IOException {
		out.write("<rightsidequestion>");
		out.newLine();
		out.write("<question>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			out.write(this.getQuestion().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</question>");
		out.newLine();
		out.write("<respons>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			out.write(this.getAnswer().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</respons>");
		out.newLine();
		out.write("</rightsidequestion>");
		out.newLine();
	}
	
	/**
	 * Reads a general question from a file
	 * @param sc
	 * 		The scanner it reads with
	 * @return
	 * 		The read general question
	 */
	public static GQuestion Read(Scanner sc) {
		GQuestion gquest = new GQuestion(new ArrayList<String>(), new ArrayList<String>());
		while(sc.hasNextLine()){
			String rightLine = sc.nextLine();
			if(rightLine.contains("</rightsidequestion>")){
				break;
			} else if(rightLine.contains("<question>")) {
				while(sc.hasNextLine()) {
					String questionLine = sc.nextLine();
					if (questionLine.contains("</question")) {
						break;
					} else {
						assert(questionLine.contains("<")); //open language
						gquest.addQuestion(sc.nextLine().trim());
						assert(sc.nextLine().contains("</"));//close language
						sc.nextLine();
					}
				}
			} else if(rightLine.contains("<respons>")) {
				while(sc.hasNextLine()){
					String respLine = sc.nextLine();
					if(respLine.contains("</respons>")){
						break;
					} else {
						assert(respLine.contains("<"));//open language
						gquest.addResponse(sc.nextLine().trim());
						assert(sc.nextLine().contains("</"));//close language
						sc.nextLine();
					}
				}
			}
		}
		return gquest;
	}
	
	/**
	 * Compares two GQuestions to see if they're equal
	 * @param o 
	 * 		the GQuestion to compare this one with
	 * @return
	 * 		returns true if they are equal
	 */
	public boolean equals(GQuestion o) {
			if ( this.getAnswer().size() == o.getAnswer().size()) {
				for (int i = 0; i < this.getAnswer().size(); i++) {
					if (!(this.getAnswer().get(i).equals(o.getAnswer().get(i)))) {
						return false;
					}
				}
			} else {
				return false;
			}
			if ( this.getQuestion().size() == o.getQuestion().size()) {
				for (int i = 0; i < this.getQuestion().size(); i++) {
					if (!(this.getQuestion().get(i).equals(o.getQuestion().get(i)))) {
						return false;
					}
				}
			} else {
				return false;
			}
		return true;
	}
}

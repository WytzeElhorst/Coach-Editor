package main.java;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents a module
 */
public class Module {
	private String curtitle;
	private ArrayList<String> titles;
	private ArrayList<Question> questions;
	private ArrayList<GQuestion> gquestions;
	private String Picture;

	/**
	 * @param t
	 * 		ArrayList containing the titles
	 * @param q
	 * 		ArrayList containing the Questions
	 * @param g
	 * 		ArrayList containing the GQuestions
	 */
	public Module(ArrayList<String> t, ArrayList<Question> q, ArrayList<GQuestion> g) {
		titles = t;
		questions = q;
		gquestions = g;
	}

	public ArrayList<String> getTitle() {
		return titles;
	}

	public void setTitle(ArrayList<String> s) {
		titles = s;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> q) {
		questions = q;
	}

	public void setCurTitle(String s) {
		curtitle = s;
	}

	public String toString(){
		return curtitle;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public ArrayList<GQuestion> getGquestions() {
		return gquestions;
	}

	public void setGquestions(ArrayList<GQuestion> gquestions) {
		this.gquestions = gquestions;
	}
	
	/**
	 * Adds a title to the list of titles
	 * @param s
	 * 		title that is added
	 */
	public void addTitle(String s) {
		titles.add(s);
	}
	
	/**
	 * Adds a question to the list of titles
	 * @param s
	 * 		question that is added
	 */
	public void addQuestion(Question curquest) {
		questions.add(curquest);
	}
	
	/**
	 * Adds a gquestion to the list of titles
	 * @param s
	 * 		gquestion that is added
	 */
	public void addGeneralQuestion(GQuestion curgquest) {
		gquestions.add(curgquest);
	}
	
	/**
	 * Writes down the module onto a file
	 * @param out 
	 * 		The writer to write with
	 * @param languages 
	 * 		list of languages
	 * @throws IOException
	 */
	public void Print(BufferedWriter out, ArrayList<Language> languages) throws IOException {
		out.write("<module>");
		out.newLine();
		out.write("<moduleimagesrc>");
		out.newLine();
		out.write(this.getPicture());
		out.newLine();
		out.write("</moduleimagesrc>");
		out.newLine();
		out.write("<moduletitle>");
		out.newLine();
		for (int l = 0; l < languages.size(); l++) {
			out.write("<" + languages.get(l).getName() + ">");
			out.newLine();
			out.write(this.getTitle().get(l));
			out.newLine();
			out.write("</" + languages.get(l).getName() + ">");
			out.newLine();
		}
		out.write("</moduletitle>");
		out.newLine();
		for (int k = 0; k < this.getQuestions().size(); k++) {
			//Print question stuff
			Question quest = this.getQuestions().get(k);
			quest.Print(out, languages);
		}
		for (int k = 0; k < this.getGquestions().size(); k++) {
			//Print GQuestion stuff
			GQuestion gq = this.getGquestions().get(k);
			gq.Print(out, languages);
		}
		out.write("</module>");
		out.newLine();
	}

	/**
	 * Compares two Modules to see if they're equal
	 * @param q 
	 * 		the module to compare this one with
	 * @return
	 * 		returns true if they are equal
	 */
	public boolean equals(Module q) {
		if (this.getTitle().size() == q.getTitle().size()) {
			for (int i = 0; i < this.getTitle().size(); i++) {
				if (!(this.getTitle().get(i).equals(q.getTitle().get(i)))) {
					return false;
				}
			}
		} else { 
			return false;
		}
		if ( this.getQuestions().size() == q.getQuestions().size()) {
			for (int i = 0; i < this.getQuestions().size(); i++) {
				if (!(this.getQuestions().get(i).equals(q.getQuestions().get(i)))) {
					return false;
				}
			}
		} else {
			return false;
		}
		if ( this.getGquestions().size() == q.getGquestions().size()) {
			for (int i = 0; i < this.getGquestions().size(); i++) {
				if (!(this.getGquestions().get(i).equals(q.getGquestions().get(i)))) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}

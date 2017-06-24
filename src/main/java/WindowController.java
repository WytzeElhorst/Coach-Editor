package main.java;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * Class that stores the values of the current session and handles the loading and saving of files
 * @author Wytze
 *
 */
public class WindowController {
	public QuestionController qc = new QuestionController(this);
	public OptionController oc = new OptionController(this);
	public ModuleController mc = new ModuleController(this);
	public LanguageController lc = new LanguageController(this);
	public GQuestionController gc = new GQuestionController(this);
	public ArrayList<Module> modules = new ArrayList<Module>();
	public int curlang = 0;
	public Option curopt;
	public Module curmod;
	public GQuestion curgquest;
	public Question curquest;
	public Question linkquest;
	public ArrayList<Language> languages = new ArrayList<Language>();
	public JFileChooser fc;

	public WindowController() {
	}
	
	/**
	 * Calls for all buttoneffects to be added to the buttons on the GUI
	 */
	public void addButtonEffects() {
		qc.QuestionButtons();
		oc.OptionButtons();
		mc.ModuleButtons();
		lc.LanguageButtons();
		gc.GQuestionButtons();
		Window.loadBox.addActionListener(loadBox());
		fc = new JFileChooser();
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener that allows loading and saving an xml file from the loadbox
	 */
	public ActionListener loadBox() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Window.loadBox.getSelectedItem().equals("Save")) {
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fc.setFileFilter(new TxtFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(Window.directory);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File directory = new File(fc.getSelectedFile(), "//outputcoach//");
                        DirectoryCreator dc = new DirectoryCreator(directory, modules, languages);
                        dc.print();
						File output = new File(fc.getSelectedFile(), "//output.txt");
						Print(output);
					}
				}
				if (Window.loadBox.getSelectedItem().equals("Load")) {
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fc.setFileFilter(new TxtFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showOpenDialog(Window.directory);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File input = fc.getSelectedFile();
						load(input);
						System.out.println(input.getAbsolutePath());
					}
				}
				Window.loadBox.setSelectedIndex(0);
			}
		};
	}

	/**
	 * Attempts to load the selected file
	 * @param output
	 * 		the selected file
	 */
	public void load(File output) {
		try {
			modules.removeAll(modules);
			languages.removeAll(languages);
			Window.modelg.clear();
			Window.modell.clear();
			Window.modelm.clear();
			Window.modelo.clear();
			Window.modelq.clear();
			Scanner sc = new Scanner(output);
			while(sc.hasNextLine()){
				String firstLine = sc.nextLine();
				if(firstLine.contains("<core>")){
					System.out.println("Started reading in the file...");
				} else if(firstLine.contains("<module>")){
					loadModule(sc);
				} else if(firstLine.contains("</core>")){
					System.out.println("Finished reading in the file. Closing scanner...");
				} else {
					System.out.println("Error, the line was not recognized.");
				}
			}

			sc.close();
			for (int i = 0; i < languages.size(); i++) {
				Window.modell.addElement(languages.get(i));
			}
			System.out.println("Scanner closed.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Attempts to read a module from a file
	 * @param sc
	 * 		the scanner it reads with
	 */
	private void loadModule(Scanner sc){
		curmod = new Module(new ArrayList<String>(), new ArrayList<Question>(), new ArrayList<GQuestion>());
		int count = 0;
		while(sc.hasNextLine()){
			String currentLine = sc.nextLine();
			if(currentLine.contains("</module>")){
				break;
			} else if(currentLine.contains("<moduleimagesrc>")){
				String filePathOfModuleImage = sc.nextLine().trim();
				curmod.setPicture(filePathOfModuleImage);
				assert(sc.nextLine().contains("</moduleimagesrc"));
			} else if(currentLine.contains("<moduletitle>")){
				while(sc.hasNextLine()){
					String titleLine = sc.nextLine().trim();
					if(titleLine.contains("</moduletitle>")){
						count++;
						break;
					} else {
						assert titleLine.contains("<");//for example: <dutch>
						Language l = new Language(titleLine.replace('<', ' ').replace('>', ' ').trim());
						if (count == 0) {
							languages.add(l);
						}
						curmod.addTitle(sc.nextLine().trim());
						assert sc.nextLine().contains("</");//for example: </dutch>
						sc.nextLine();
					}
				}
			} else if(currentLine.contains("<leftsidequestion>")){
				curmod.addQuestion(Question.Read(sc));
			} else if(currentLine.contains("<rightsidequestion>")){

				curmod.addGeneralQuestion(GQuestion.Read(sc));
			}
		}
		for (int i = 0; i < curmod.getQuestions().size(); i++) {
			Question q = curmod.getQuestions().get(i);
			for (int k = 0; k < q.getOptions().size(); k++) {
				if (q.getOptions().get(k).getLinkint() != -1) {
					q.getOptions().get(k).setLink(curmod.getQuestions().get(q.getOptions().get(k).getLinkint()));
				} else {
					q.getOptions().get(k).setLink(qc.getMainMenu());
				}
			}
		}
		modules.add(curmod);
	}
	
	/**
	 * Prints out all data of the current session into a file
	 * @param output
	 * 		File to write onto
	 */
	public void Print(File output) {
		try {
			FileWriter fstream = new FileWriter(output);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("<core>");
			out.newLine();
			for (int i = 0; i < modules.size(); i++) {
				modules.get(i).Print(out, languages);
			}
			out.write("</core>");
			out.newLine();
			out.close();
			fstream.close();
		} catch (IOException e) {
			System.out.print("Loading failed, invalid format.");
			e.printStackTrace();
		} 		
	}
	
	/**
	 * @return
	 * 		Adds a WindowListener to the window that will ask if you want to save when you exit the application.
	 */
	public WindowListener windowControl() {
		return new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e){
				int result = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Confirm" ,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				if(result == JOptionPane.YES_OPTION){
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					fc.setFileFilter(new TxtFilter());
					fc.setAcceptAllFileFilterUsed(false);
					int returnVal = fc.showSaveDialog(Window.directory);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File directory = new File(fc.getSelectedFile(), "//outputcoach//");
                        DirectoryCreator dc = new DirectoryCreator(directory, modules, languages);
                        dc.print();
						File output = new File(fc.getSelectedFile(), "//output.txt");
						Print(output);
					}
					System.exit(0);
				}else{
					System.exit(0);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

		};
	}
}

package main.java;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;

/**
 * Class that gives functionality to the question panel
 */
public class QuestionController {
	private Question MainMenu = new Question(-1, null, null);
	private WindowController wc;
	
	/**
	 * @param w
	 * 		The WindowController this class uses for values
	 */
	public QuestionController(WindowController w) {
		wc = w;
	}

	/**
	 * @return
	 * 		returns the question that links to the main menu
	 */
	public Question getMainMenu() {
		return MainMenu;
	}

	/**
	 * adds actions all buttons and lists on the question panel
	 */
	public void QuestionButtons() {
		Window.btnNewButton_1.addActionListener(newQuestion());
		Window.btnRemoveQ.addActionListener(removeQuestion());
		Window.textArea.addKeyListener(questionText());
		Window.listq.addMouseListener(questionList());
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener which created a new question
	 */
	public ActionListener newQuestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> l = new ArrayList<String>();
				for (int i = 0; i < wc.languages.size(); i++) {
					l.add("");
				}
				Question quest = new Question(0, l, new ArrayList<Option>());
				if (!wc.curmod.getQuestions().isEmpty()) {
					quest.setID(wc.curmod.getQuestions().get(wc.curmod.getQuestions().size() - 1).getID() + 1);
				}
				wc.curmod.getQuestions().add(quest);
				Window.modelq.addElement(quest);
				Window.comboBox.addItem(quest);
			}
		};
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener which removes the currently selected question
	 */
	public ActionListener removeQuestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(wc.curmod == null) && !(wc.curquest == null)) {
					wc.curmod.getQuestions().remove(wc.curquest);
					Window.modelq.clear();
					Window.comboBox.removeAllItems();
					Window.comboBox.addItem(MainMenu);
					for (int i = 0; i < wc.curmod.getQuestions().size(); i++) {
						Window.modelq.addElement(wc.curmod.getQuestions().get(i));
						Window.comboBox.addItem(wc.curmod.getQuestions().get(i));
						ArrayList<Option> opt = new ArrayList<Option>();
						for (int k = 0; k < wc.curmod.getQuestions().get(i).getOptions().size(); k++) {
							if (!(wc.curmod.getQuestions().get(i).getOptions().get(k).getLink().equals(wc.curquest))) {
								opt.add(wc.curmod.getQuestions().get(i).getOptions().get(k));
							}
						}
						wc.curmod.getQuestions().get(i).setOptions(opt);
					}
					wc.curquest = null;
					disableQuestionButtons();
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener which allows the selection of questions from the list by clicking on it's index
	 */
	public MouseAdapter questionList() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					if (index != -1) {
						if (!(Window.listq.isSelectionEmpty())) {
							Window.modelo.clear();
							wc.curquest = Window.listq.getSelectedValue();
							enableQuestionButtons();
							for (int i = 0; i < wc.curquest.getOptions().size(); i++) {
								Window.modelo.addElement(wc.curquest.getOptions().get(i));
							}
							Window.textArea.setText(wc.curquest.getBody().get(wc.curlang));
							wc.linkquest = null;
						}
					}
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		returns a keylistener that saves the text typed into the text area to the currently selected question
	 */
	public KeyListener questionText() {
		return new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (wc.curquest != null) {
					wc.curquest.getBody().set(wc.curlang, Window.textArea.getText() + e.getKeyChar());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		};
	}

	/**
	 * Disables all buttons for which a question is needed
	 */
	public void disableQuestionButtons() {
		Window.btnEditAnswer.setEnabled(false);
		Window.btnAdd.setEnabled(false);
		Window.btnNewButton.setEnabled(false);
		Window.btnRemoveQ.setEnabled(false);
		Window.textArea.setText("");
		Window.textArea_1.setText("");
		Window.comboBox.setSelectedIndex(-1);
		Window.txtChoice.setText("");
		Window.modelo.clear();
		wc.curquest = null;
		wc.curopt = null;
		wc.linkquest = null;
	}

	/**
	 * Enables all buttons for which a question is needed
	 */
	public void enableQuestionButtons() {
		Window.btnAdd.setEnabled(true);
		Window.btnRemoveQ.setEnabled(true);
	}

}

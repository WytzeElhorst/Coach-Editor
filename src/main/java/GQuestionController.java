package main.java;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
/**
 * Class that gives functionality to the GQuestion panel
 *
 */
public class GQuestionController {
	private WindowController wc;
	
	/**
	 * @param w
	 * 		The WindowController this class uses for values
	 */
	public GQuestionController(WindowController w) {
		wc = w;
	}
	/**
	 * Adds actions all buttons and lists on the GQuestion panel
	 */
	public void GQuestionButtons() {
		Window.list_g.addMouseListener(gQuestionList());
		Window.btnAddGQ.addActionListener(newGQuestion());
		Window.btnEditGQ.addActionListener(editGQuestion());
		Window.btnRemoveGQ.addActionListener(removeGQuestion());
	}
	/**
	 * @return
	 * 		returns a mouse adaptor that allows selection of GQuestions from the list by clicking on them
	 */
	public MouseAdapter gQuestionList() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					if (index != -1) {
						if (!(Window.list_g.isSelectionEmpty())) {
							wc.curgquest = Window.list_g.getSelectedValue();
							Window.txtQuestion.setText(wc.curgquest.getQuestion().get(wc.curlang));
							Window.txtrAnswer.setText(wc.curgquest.getAnswer().get(wc.curlang));
							Window.btnEditGQ.setEnabled(true);
							Window.btnRemoveGQ.setEnabled(true);
						}
					}
				}
			}
		};
	}


	/**
	 * @return
	 * 		Returns an actionlistener that creates a new GQuestion
	 */
	public ActionListener newGQuestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Window.txtQuestion.getText().equals("")) && !(Window.txtrAnswer.getText().equals(""))) {
					ArrayList<String> q = new ArrayList<String>();
					ArrayList<String> a = new ArrayList<String>();
					for (int i = 0; i < wc.languages.size(); i++) {
						q.add(Window.txtQuestion.getText());
						a.add(Window.txtrAnswer.getText());
					}

					GQuestion quest = new GQuestion(q, a);
					if (! (wc.curmod == null)) {
						wc.curmod.getGquestions().add(quest);
					}
					quest.setCurname(Window.txtQuestion.getText());
					Window.modelg.addElement(quest);
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		returns an actionlistener that edits the currently selected GQuestion
	 */
	public ActionListener editGQuestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Window.txtQuestion.getText().equals("")) && !(Window.txtrAnswer.getText().equals(""))) {
					if (wc.curmod != null) {
						wc.curgquest.getQuestion().set(wc.curlang, Window.txtQuestion.getText());
						wc.curgquest.getAnswer().set(wc.curlang, Window.txtrAnswer.getText());
						wc.curgquest.setCurname(Window.txtQuestion.getText());
					}
					Window.modelg.clear();
					for (int i = 0; i < wc.curmod.getGquestions().size(); i++) {
						Window.modelg.addElement(wc.curmod.getGquestions().get(i));
					}
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		returns an actionlistener that removes the currently selected GQuestion
	 */
	public ActionListener removeGQuestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wc.curgquest != null && wc.curmod != null) {
					wc.curmod.getGquestions().remove(wc.curgquest);
					Window.modelg.clear();
					for (int i = 0; i < wc.curmod.getGquestions().size(); i++) {
						Window.modelg.addElement(wc.curmod.getGquestions().get(i));
					}
					Window.btnRemoveGQ.setEnabled(false);
					Window.btnEditGQ.setEnabled(false);
					wc.curgquest = null;
				}
			}
		};
	}

}

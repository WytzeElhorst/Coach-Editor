package main.java;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;

/**
 * Class that gives functionality to the option panel
 */
public class OptionController {
private WindowController wc;
	
/**
 * @param w
 * 		The WindowController this class uses for values
 */
	public OptionController(WindowController w) {
		wc = w;
	}
	
	/**
	 * adds actions all buttons and lists on the option panel
	 */
	public void OptionButtons() {
		Window.btnAdd.addActionListener(addAnswer());
		Window.btnNewButton.addActionListener(removeOptionAction());
		Window.list_o.addMouseListener(answerList());
		Window.comboBox.addActionListener(selectlinkquestion());
		Window.btnEditAnswer.addActionListener(editAnswer());
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener which allows the selection of the question the current option links to
	 */
	public ActionListener selectlinkquestion() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Window.listq.isSelectionEmpty())) {
					wc.linkquest = (Question) Window.comboBox.getSelectedItem();
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener that adds the option to the currently selected question
	 */
	public ActionListener addAnswer() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(wc.linkquest == null)) {
					ArrayList<String> choices = new ArrayList<String>();
					ArrayList<String> answers = new ArrayList<String>();
					for (int i = 0; i < wc.languages.size(); i++) {
						choices.add(Window.txtChoice.getText());
						answers.add(Window.textArea_1.getText());
					}
					Option opt = new Option(choices, answers, wc.linkquest);
					wc.curquest.getOptions().add(opt);
					Window.modelo.addElement(opt);
					Window.btnEditAnswer.setEnabled(true);
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener that edits the currently selected option
	 */
	public ActionListener editAnswer() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(wc.linkquest == null) && wc.curopt != null) {
					Window.modelo.clear();
					wc.curopt.getChoice().set(wc.curlang, Window.txtChoice.getText());
					wc.curopt.getResponse().set(wc.curlang, Window.textArea_1.getText());
					wc.curopt.setLink((Question) Window.comboBox.getSelectedItem());
					for (int i = 0; i < wc.curquest.getOptions().size(); i++) {
						Window.modelo.addElement(wc.curquest.getOptions().get(i));
					}
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener that removes the currently selected option
	 */
	public ActionListener removeOptionAction(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Window.list_o.isSelectionEmpty())) {
					Option opt = Window.list_o.getSelectedValue();
					Window.btnEditAnswer.setEnabled(false);
					Window.btnNewButton.setEnabled(false);
					Window.modelo.remove(Window.list_o.getSelectedIndex());
					wc.curquest.getOptions().remove(opt);
					Window.textArea_1.setText("");
					Window.txtChoice.setText("");
					Window.comboBox.setSelectedIndex(-1);
					wc.linkquest = null;
					wc.curopt = null;
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener which allows the selection of options from the list by clicking on it's index
	 */
	public MouseAdapter answerList() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					if (index != -1) {
						if (!(Window.list_o.isSelectionEmpty())) {
							wc.curopt = Window.list_o.getSelectedValue();
							Window.textArea_1.setText(wc.curopt.getResponse().get(wc.curlang));
							Window.txtChoice.setText(wc.curopt.getChoice().get(wc.curlang));
							Window.comboBox.setSelectedIndex(wc.curopt.getLink().getID() + 1);
							Window.btnEditAnswer.setEnabled(true);
							Window.btnNewButton.setEnabled(true);
							wc.linkquest = wc.curopt.getLink();
						}
					}
				}
			}
		};
	}
}

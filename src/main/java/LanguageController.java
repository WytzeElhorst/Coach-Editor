package main.java;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 * Class that gives functionality to the language panel
 */
public class LanguageController {
	private WindowController wc;

	/**
	 * @param w
	 * 		The WindowController this class uses for values
	 */
	public LanguageController(WindowController w) {
		wc = w;
	}
	
	/**
	 * adds actions all buttons and lists on the language panel
	 */
	public void LanguageButtons() {
		Window.btnAddLanguage.addActionListener(newLanguage());
		Window.btnRemoveL.addActionListener(removeLanguage());
		Window.list.addMouseListener(languageList());
		Window.btnNewButton_2.addActionListener(renameLanguage());
	}
	
	/**
	 * @return
	 * 		Returns a MouseAdapter allows selecting a language from the list by clicking on its index.
	 */
	public MouseAdapter languageList() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					if (index != -1) {
						if (!(Window.list.isSelectionEmpty())) {
							wc.curlang = Window.list.getSelectedIndex();
							enableLanguageButtons();
							Window.modelm.clear();
							for (int i = 0; i < wc.modules.size(); i++) {
								wc.modules.get(i).setCurTitle(wc.modules.get(i).getTitle().get(wc.curlang));
								Window.modelm.addElement(wc.modules.get(i));
							}
							if (wc.curmod != null) {
								Window.modelg.clear();
								for (int i = 0; i < wc.curmod.getGquestions().size(); i++) {
									wc.curmod.getGquestions().get(i).setCurname(wc.curmod.getGquestions().get(i).getQuestion().get(wc.curlang));
									Window.modelg.addElement(wc.curmod.getGquestions().get(i));
								}
							}
							if (wc.curgquest != null) {
								Window.txtrAnswer.setText(wc.curgquest.getAnswer().get(wc.curlang));
								Window.txtQuestion.setText(wc.curgquest.getQuestion().get(wc.curlang));

							}
							if (!(wc.curquest == null)) {
								Window.textArea.setText(wc.curquest.getBody().get(wc.curlang));
								if (wc.curopt != null) {
									Window.txtChoice.setText(wc.curopt.getChoice().get(wc.curlang));
									Window.textArea_1.setText(wc.curopt.getResponse().get(wc.curlang));
								}
							}
						}
					}
				}

			}
		};
	}

	/**
	 * @return
	 * 		Returns an actionlistener that creates a new language
	 */
	public ActionListener newLanguage() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Language lang = new Language(Window.txtLangName.getText());
				wc.languages.add(lang);
				Window.modell.addElement(lang);
				for (int i = 0; i < wc.modules.size(); i++) {
					wc.modules.get(i).getTitle().add("Nameless Module");
					for (int k = 0; k < wc.modules.get(i).getGquestions().size(); k++) {
						wc.modules.get(i).getGquestions().get(k).getAnswer().add("New Answer");
						wc.modules.get(i).getGquestions().get(k).getQuestion().add("New Question");
					}
					for (int k = 0; k < wc.modules.get(i).getQuestions().size(); k++) {
						wc.modules.get(i).getQuestions().get(k).getBody().add("");
						for (int q = 0; q < wc.modules.get(i).getQuestions().get(k).getOptions().size(); q++) {
							wc.modules.get(i).getQuestions().get(k).getOptions().get(q).getChoice().add("new Choice");
							wc.modules.get(i).getQuestions().get(k).getOptions().get(q).getResponse().add("new Response");						
						}
					}
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		Returns an actionlistener that renames the currently selected Language
	 */
	public ActionListener renameLanguage() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wc.languages.get(wc.curlang).setName(Window.txtLangName.getText());
				Window.modell.clear();
				for (int i = 0; i < wc.languages.size(); i++) {
					Window.modell.addElement(wc.languages.get(i));
				}
			}
		};
	}
	/**
	 * @return
	 * 		Returns an actionlistener that removes the currently selected Language
	 */
	public ActionListener removeLanguage() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wc.languages.remove(wc.curlang);
				Window.modell.clear();
				for (int i = 0; i < wc.languages.size(); i++) {
					Window.modell.addElement(wc.languages.get(i));
				}
				disableLanguageButtons();
				for (int i = 0; i < wc.modules.size(); i++) {
					wc.modules.get(i).getTitle().remove(wc.curlang);
					for (int k = 0; k < wc.modules.get(i).getGquestions().size(); k++) {
						wc.modules.get(i).getGquestions().get(k).getAnswer().remove(wc.curlang);
						wc.modules.get(i).getGquestions().get(k).getQuestion().remove(wc.curlang);
					}
					for (int k = 0; k < wc.modules.get(i).getQuestions().size(); k++) {
						wc.modules.get(i).getQuestions().get(k).getBody().remove(wc.curlang);
						for (int q = 0; q < wc.modules.get(i).getQuestions().get(k).getOptions().size(); q++) {
							wc.modules.get(i).getQuestions().get(k).getOptions().get(q).getChoice().remove(wc.curlang);
							wc.modules.get(i).getQuestions().get(k).getOptions().get(q).getResponse().remove(wc.curlang);
						}
					}
				}
				wc.curlang = 0;
			}
		};
	}
	/**
	 * Disables all buttons for which a language is needed
	 */
	public void disableLanguageButtons() {
		Window.btnRemoveL.setEnabled(false);
		Window.btnNewModule.setEnabled(false);
		Window.btnNewButton_2.setEnabled(false);
		wc.mc.disableModuleButtons();
	}

	/**
	 * Enables all buttons for which a language is needed
	 */
	public void enableLanguageButtons() {
		Window.btnNewModule.setEnabled(true);
		Window.btnRemoveL.setEnabled(true);
		Window.btnNewButton_2.setEnabled(true);
	}
}

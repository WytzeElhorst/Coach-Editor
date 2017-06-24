package main.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JList;

/**
 * Class that gives functionality to the module panel
 */
public class ModuleController {
	private WindowController wc;
	
	/**
	 * @param w
	 * 		The WindowController this class uses for values
	 */
	public ModuleController(WindowController w) {
		wc = w;
	}
	
	/**
	 * adds actions all buttons and lists on the module panel
	 */
	public void ModuleButtons() {
		Window.btnNewModule.addActionListener(newModule());
		Window.btnRemoveM.addActionListener(removeModule());
		Window.btnRenameModule.addActionListener(renameModule());
		Window.btnAddImage.addActionListener(loadImage());
		Window.list_m.addMouseListener(moduleList());
	}
	
	/**
	 * @return
	 * 		returns an Actionlistener that allows the selection of an image and will store it's path
	 */
	public ActionListener loadImage() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (wc.curmod != null) {
					wc.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					wc.fc.setFileFilter(new ImageFilter());
					wc.fc.setAcceptAllFileFilterUsed(false);
					int returnVal = wc.fc.showOpenDialog(Window.directory);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File input = wc.fc.getSelectedFile();
						wc.curmod.setPicture(input.getAbsolutePath());
						Window.lblNoImage.setText(input.getName());
					}
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener which created a new module
	 */
	public ActionListener newModule() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Window.txtModuleName.equals(""))) {
					ArrayList<String> l = new ArrayList<String>();
					for (int i = 0; i < wc.languages.size(); i++) {
						l.add(Window.txtModuleName.getText());
					}
					Module mod = new Module(l, new ArrayList<Question>(), new ArrayList<GQuestion>());
					mod.setCurTitle(Window.txtModuleName.getText());
					wc.modules.add(mod);
					Window.modelm.addElement(mod);
				}
			}
		};
	}
	
	/**
	 * @return
	 * 		Returns an ActionListener which renames the currently selected module
	 */
	public ActionListener renameModule() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(wc.curmod == null) && (!(Window.txtModuleName.equals("")))) {
					wc.curmod.setCurTitle(Window.txtModuleName.getText());
					wc.curmod.getTitle().set(wc.curlang, wc.curmod.toString());
					Window.modelm.clear();
					for (int i = 0; i < wc.modules.size(); i++) {
						wc.modules.get(i).setCurTitle(wc.modules.get(i).getTitle().get(wc.curlang));
						Window.modelm.addElement(wc.modules.get(i));
					}
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener which removes the currently selected module
	 */
	public ActionListener removeModule() {
		return new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if (!(wc.curmod == null)) {
					wc.modules.remove(wc.curmod);
					Window.modelm.clear();
					for (int i = 0; i < wc.modules.size(); i++) {
						Window.modelm.addElement(wc.modules.get(i));
					}
					wc.curmod = null;
					disableModuleButtons();
				}
			}
		};
	}

	/**
	 * @return
	 * 		Returns an ActionListener which allows the selection of modules from the list by clicking on it's index
	 */
	public MouseAdapter moduleList() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<?> listm = (JList<?>)evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = listm.locationToIndex(evt.getPoint());
					if (index != -1) {
						if (!(Window.list_m.isSelectionEmpty())) {
							Window.modelq.clear();
							Window.modelg.clear();
							Window.comboBox.removeAllItems();
							Window.comboBox.addItem(wc.qc.getMainMenu());
							wc.curmod = Window.list_m.getSelectedValue();
							enableModuleButtons();
							if (wc.curmod.getPicture() != null) {
								Window.lblNoImage.setText(wc.curmod.getPicture().split("\\\\")[wc.curmod.getPicture().split("\\\\").length - 1]);
							} else {
								Window.lblNoImage.setText("No Image");
							}
							for (int i = 0; i < wc.curmod.getQuestions().size(); i++) {
								Window.modelq.addElement(wc.curmod.getQuestions().get(i));
								Window.comboBox.addItem(wc.curmod.getQuestions().get(i));
							}
							for (int i = 0; i < wc.curmod.getGquestions().size(); i++) {
								wc.curmod.getGquestions().get(i).setCurname(wc.curmod.getGquestions().get(i).getQuestion().get(wc.curlang));
								Window.modelg.addElement(wc.curmod.getGquestions().get(i));
							}
						}
					}
				}
			}
		};
	}
	
	/**
	 * Disables all buttons for which a module is needed
	 */
	public void disableModuleButtons() {
		Window.btnNewButton_1.setEnabled(false);
		Window.btnRemoveM.setEnabled(false);
		Window.btnRenameModule.setEnabled(false);
		Window.btnAddImage.setEnabled(false);
		Window.btnAddGQ.setEnabled(false);
		Window.btnRemoveGQ.setEnabled(false);
		Window.lblNoImage.setText("No Image");
		wc.curgquest = null;
		wc.curmod = null;
		wc.qc.disableQuestionButtons();
	}

	/**
	 * Enables all buttons for which a module is needed
	 */
	public void enableModuleButtons() {
		Window.btnNewButton_1.setEnabled(true);
		Window.btnRemoveM.setEnabled(true);
		Window.btnRenameModule.setEnabled(true);
		Window.btnAddImage.setEnabled(true);
		Window.btnAddGQ.setEnabled(true);
		wc.qc.disableQuestionButtons();
	}
}

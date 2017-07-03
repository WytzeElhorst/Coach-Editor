package main.java;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

/**
 * Class that sets up the GUI
 */
public class Window {
	public static JFrame frmGui;
	public static JTextField txtChoice;
	public static JTextField txtModuleName;
	public static JTextField txtQuestion;
	public static DefaultListModel<Question> modelq = new DefaultListModel<Question>();
	public static DefaultListModel<Language> modell = new DefaultListModel<Language>();
	public static DefaultListModel<Option> modelo = new DefaultListModel<Option>();
	public static DefaultListModel<Module> modelm = new DefaultListModel<Module>();
	public static DefaultListModel<GQuestion> modelg = new DefaultListModel<GQuestion>();
	public static JTextField txtLangName;
	private JScrollPane scrollPane = new JScrollPane();
	private JScrollPane scrollPane_1 = new JScrollPane();
	private JScrollPane scrollPane_2 = new JScrollPane();
	private JScrollPane scrollPane_4 = new JScrollPane();
	private JScrollPane scrollPane_5 = new JScrollPane();
	public final static JList<Question> listq = new JList<Question>(modelq);
	public final static JList<Module> list_m = new JList<Module>(modelm);
	public final static JList<Language> list = new JList<Language>(modell);
	public final static JList<GQuestion> list_g = new JList<GQuestion>(modelg);
	public final static JList<Option> list_o = new JList<Option>(modelo);
	final JLabel lblGeneralQuestions = new JLabel("General Questions");
	final JLabel lblText = new JLabel("Text");
	final JLabel lblName = new JLabel("Module Name");
	final JLabel lblModules = new JLabel("Modules");
	final JLabel lblQuestions = new JLabel("Questions");
	final JLabel lblLanguageName = new JLabel("Language Name");
	final JLabel lblChoice = new JLabel("Choice");
	final JLabel lblResponse = new JLabel("Response");
	final JPanel panel = new JPanel();
	public final static JLabel lblLinksTo = new JLabel("Links to");
	public final static JLabel lblNoImage = new JLabel("No Image");
	public final static JButton btnNewButton_1 = new JButton("New Question");
	public final static JButton btnNewModule = new JButton("New Module");
	public final static JButton btnRenameModule = new JButton("Rename Module");
	public final static JButton btnAddLanguage = new JButton("Add Language");
	public final static JButton btnRemoveQ = new JButton("Remove Question");
	public final static JButton btnRemoveM = new JButton("Remove Module");
	public final static JButton btnRemoveL = new JButton("Remove Language");
	public final static JButton btnAdd = new JButton("Add Answer");
	public final static JButton btnEditAnswer = new JButton("Edit answer");
	public final static JButton btnAddImage = new JButton("Add Image");
	public final static JButton btnAddGQ = new JButton("Add GQuestion");
	public final static JButton btnRemoveGQ = new JButton("Remove GQuestion");
	public final static JButton btnEditGQ = new JButton("Edit GQuestion");
	public final static JButton btnNewButton = new JButton("Remove Answer");
	public final static JTextArea textArea_1 = new JTextArea();
	public final static JTextArea textArea = new JTextArea();
	public final static JTextArea txtrAnswer = new JTextArea();
	public final static JOptionPane optionPane = new JOptionPane("Do you want to save?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
	public final static JComboBox<Question> comboBox = new JComboBox<Question>();
	public final static JComboBox<String> loadBox = new JComboBox<String>();
	public final static JPanel directory = new JPanel();
	public final static JButton btnNewButton_2 = new JButton("Rename Language");
	 JFrame  frame= new JFrame("frame"); 
	private final JPanel panel_4 = new JPanel();
	private final JPanel panel_6 = new JPanel();
	public WindowController wc = new WindowController();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window Window = new Window();
					Window.frmGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmGui = new JFrame();
		frmGui.setResizable(false);
		frmGui.setTitle("Talking Coach Editor");
		frmGui.setBounds(300, 150, 1324, 787);
		frmGui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		btnAddGQ.setEnabled(false);
		btnRemoveGQ.setEnabled(false);
		btnEditGQ.setEnabled(false);
		btnRenameModule.setEnabled(false);
		btnNewModule.setEnabled(false);
		btnAddImage.setEnabled(false);
		btnRemoveM.setEnabled(false);
		btnNewButton_1.setEnabled(false);
		btnRemoveQ.setEnabled(false);
		loadBox.addItem("Load or Save");
		loadBox.addItem("Load");
		loadBox.addItem("Save");
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		textArea_1.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		
		txtrAnswer.setLineWrap(true);
		txtrAnswer.setWrapStyleWord(true);
		txtrAnswer.getDocument().putProperty("filterNewlines", Boolean.TRUE);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_1 = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frmGui.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(loadBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 255, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 334, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(loadBox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 416, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Window.class.getResource("/assets/logo.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(label)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 327, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 272, Short.MAX_VALUE)
		);
		panel_6.setLayout(gl_panel_6);

		//Lists
		listq.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listq);
		
		
				scrollPane_1.setViewportView(textArea);
		
				panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				
						txtChoice = new JTextField();
						txtChoice.setColumns(10);
						
								JScrollPane scrollPane_3 = new JScrollPane();
										
										JLabel lblAnswers = new JLabel("Answers");
										btnAdd.setEnabled(false);
										btnEditAnswer.setEnabled(false);
								
										GroupLayout gl_panel = new GroupLayout(panel);
										gl_panel.setHorizontalGroup(
											gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
																.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
															.addGap(30)
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																	.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblResponse)
																		.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(lblChoice)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(txtChoice, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(lblLinksTo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.RELATED)
																			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_panel.createSequentialGroup()
																			.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
																			.addPreferredGap(ComponentPlacement.RELATED)))
																	.addGap(9))
																.addGroup(gl_panel.createSequentialGroup()
																	.addComponent(btnEditAnswer, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
																	.addGap(18)
																	.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
														.addGroup(gl_panel.createSequentialGroup()
															.addContainerGap()
															.addComponent(lblAnswers)))
													.addContainerGap())
										);
										gl_panel.setVerticalGroup(
											gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(lblAnswers)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblChoice)
																.addGroup(gl_panel.createSequentialGroup()
																	.addComponent(txtChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																	.addGap(14)
																	.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																		.addComponent(lblLinksTo)
																		.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
															.addGap(13)
															.addComponent(lblResponse)
															.addGap(8)
															.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
														.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnNewButton)
														.addComponent(btnAdd)
														.addComponent(btnEditAnswer))
													.addGap(249))
										);
										btnNewButton.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
											}
										});
										btnNewButton.setEnabled(false);
										scrollPane_3.setViewportView(list_o);
										
												scrollPane_2.setViewportView(textArea_1);
												panel.setLayout(gl_panel);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuestions)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveQ, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblText)
								.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))))
					.addGap(20))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblQuestions)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveQ, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblText)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);

		list_m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_4.setViewportView(list_m);

		txtModuleName = new JTextField();
		txtModuleName.setText("New Module");
		txtModuleName.setColumns(10);

		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtModuleName, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(lblName)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(btnRenameModule)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewModule))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(btnAddImage, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveM))
						.addComponent(lblNoImage)
						.addComponent(lblModules))
					.addContainerGap())
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addComponent(lblModules)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtModuleName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRenameModule, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewModule, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoveM)
						.addComponent(btnAddImage))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNoImage)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);

		JScrollPane scrollPane_6 = new JScrollPane();

		txtQuestion = new JTextField();
		txtQuestion.setText("Question");
		txtQuestion.setColumns(10);

		JScrollPane scrollPane_7 = new JScrollPane();

		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addContainerGap(23, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtQuestion)
								.addComponent(lblGeneralQuestions, Alignment.LEADING)
								.addComponent(scrollPane_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
						.addGap(20))
				.addGroup(gl_panel_3.createSequentialGroup()
						.addGap(23)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
										.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnEditGQ, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
												.addGroup(gl_panel_3.createSequentialGroup()
														.addComponent(btnAddGQ, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnRemoveGQ, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
										.addGap(21))))
				);
		gl_panel_3.setVerticalGroup(
				gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(lblGeneralQuestions)
						.addGap(8)
						.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtQuestion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRemoveGQ)
								.addComponent(btnAddGQ))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditGQ)
						.addContainerGap(247, Short.MAX_VALUE))
				);

		txtrAnswer.setText("Answer");
		scrollPane_7.setViewportView(txtrAnswer);
		scrollPane_6.setViewportView(list_g);
		panel_3.setLayout(gl_panel_3);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_5.setViewportView(list);

		txtLangName = new JTextField();
		txtLangName.setText("language");
		txtLangName.setColumns(10);
		btnRemoveL.setEnabled(false);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnAddLanguage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRemoveL, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addComponent(txtLangName))
						.addComponent(lblLanguageName))
					.addGap(12)
					.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblLanguageName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtLangName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddLanguage, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveL, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		frmGui.getContentPane().setLayout(groupLayout);
		
		wc.addButtonEffects();
		frmGui.addWindowListener(wc.windowControl());
		

	}
}


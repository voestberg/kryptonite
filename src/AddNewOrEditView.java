import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class AddNewOrEditView {

	JFrame frame;
	JButton okButton, cancelButton, passGenButton;
	JTextField placeField, usernameField, passwordField, emailField, commentField, dateField;
	private JPanel inputPanel, buttonPanel;
	private JSplitPane jsp;
	private int row;
		
	public AddNewOrEditView(String module, String[] columnData, int row) {
		this.row = row;
		initModuleObjects(module);
		setPlacementOfObjects();
		createModuleTextFields(columnData);
		createModuleButtons();
		addFrameComponents();
	}
	
	public void display() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void close() {
		frame.dispose();
	}
	
	void addNewListener(ActionListener listenerForAddNewButtons) {
		okButton.addActionListener(listenerForAddNewButtons);
		cancelButton.addActionListener(listenerForAddNewButtons);
		passGenButton.addActionListener(listenerForAddNewButtons);
	}
	
	public int getRow() {
		return row;
	}
	
	public void passGenWindow() {
		JOptionPane.showConfirmDialog(null, "No yet implemented", "Password Generator", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void initModuleObjects(String module) {
		frame 		= new JFrame(module);
		inputPanel 	= new JPanel();
		buttonPanel = new JPanel();
		jsp 		= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	}
	
	private void setPlacementOfObjects() {
		frame.setSize(400, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		WindowCenterCalculation wcc	= new WindowCenterCalculation(frame);
		int screenHeight = wcc.getHeight();
		int screenWidth = wcc.getWidth();
		frame.setLocation(screenHeight, screenWidth);
	}
	
	private void createModuleButtons() {
		
		buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buttonPanel.setLayout(new GridBagLayout());
		
		okButton = makeButton("OK");
		cancelButton = makeButton("Cancel");
		passGenButton = makeButton("Password Generator");
		
		setButtonPositionInGrid(passGenButton, 0, 1, 0);
		setButtonPositionInGrid(okButton, 0, 2, 0);
		setButtonPositionInGrid(cancelButton, 1, 2, 0);
	}

	private void addFrameComponents() {
		jsp.add(inputPanel);
		jsp.add(buttonPanel);
		frame.add(jsp);
	}
	
	private void setButtonPositionInGrid(JButton btn, int x, int y, int weight) {
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.weighty = weight;
			gbc.fill = GridBagConstraints.BOTH;
		buttonPanel.add(btn, gbc);
	}
		
	private JButton makeButton(String buttonName) {
		JButton btn = new JButton(buttonName);
			btn.setPreferredSize(new Dimension(150,40));
		return btn;
	}
	
	private void createModuleTextFields(String[] columnData) {
		
		inputPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		inputPanel.setLayout(new GridBagLayout());
		
		if (columnData == null) {
			addNewChosen();
		} else if (columnData != null) {
			editChosen(columnData);
		}
		
		setTextFieldPositionInGrid(placeField, 0, 1, 0);
		setTextFieldPositionInGrid(usernameField, 0, 2, 0);
		setTextFieldPositionInGrid(passwordField, 0, 3, 0);
		setTextFieldPositionInGrid(emailField, 0, 4, 0);
		setTextFieldPositionInGrid(commentField, 0, 5, 0);
		setTextFieldPositionInGrid(dateField, 0, 6, 0);
	}
	
	private void addNewChosen() {
		placeField = makeTextField("Place");
		usernameField = makeTextField("Username");
		passwordField = makeTextField("Password");
		emailField = makeTextField("Email");
		commentField = makeTextField("Comment");
		dateField = makeTextField("Date");
	}
	
	private void editChosen(String[] columnData) {
		System.out.println(columnData[2]);
		placeField = makeTextField(columnData[0]);
		usernameField = makeTextField(columnData[1]);
		passwordField = makeTextField(columnData[2]);
		emailField = makeTextField(columnData[3]);
		commentField = makeTextField(columnData[4]);
		dateField = makeTextField(columnData[5]);
	}
	
	private void setTextFieldPositionInGrid(JTextField jtf, int x, int y, int weight) {
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.weighty = weight;
			gbc.fill = GridBagConstraints.BOTH;
		inputPanel.add(jtf, gbc);
	}
	
	private JTextField makeTextField(String displayText) {
		JTextField txtField = new JTextField(30);
			txtField.setText(displayText);
		return txtField;
	}

}

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MainWindowView {

	DefaultTableModel dtm;
	JTable table;
	JButton addNewButton, editButton, deleteButton, copyUserButton, copyPassButton, copyEmailButton;
	private JFrame mainFrame;
	private JPanel buttonPanel, tablePanel;
	private JSplitPane sp;
	private String[] columnNames = new String[] {"Place", "Username", "Password", "Email", "Comment", "Date"};
	
	public MainWindowView() {
		initMainWindowObjects();
		setPlacementOfObjects();
		createMainWindowButtons();
		addFrameComponents();
	}
	
	public void display() {
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	private void initMainWindowObjects() {
		mainFrame 	= new JFrame("MainWindow");
		buttonPanel = new JPanel();
		tablePanel 	= new JPanel();
		sp 			= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	}
	
	private void setPlacementOfObjects() {
		mainFrame.setSize(1000,1000);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		WindowCenterCalculation wcc	= new WindowCenterCalculation(mainFrame);
		int screenHeight = wcc.getHeight();
		int screenWidth = wcc.getWidth();
		mainFrame.setLocation(screenHeight, screenWidth);
	}
		
	private void createMainWindowButtons() {
		buttonPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buttonPanel.setLayout(new GridBagLayout());
		
			addNewButton = makeButton("Add New");
			editButton = makeButton("Edit");
			deleteButton = makeButton("Delete");
			copyUserButton = makeButton("Copy Username");
			copyPassButton = makeButton("Copy Password");
			copyEmailButton = makeButton("Copy Email");
			
			setButtonPositionInGrid(addNewButton, 0, 1, 0);
			setButtonPositionInGrid(editButton, 1, 1, 0);
			setButtonPositionInGrid(deleteButton, 2, 1, 0);
			setButtonPositionInGrid(copyUserButton, 0, 2, 0);
			setButtonPositionInGrid(copyPassButton, 1, 2, 0);
			setButtonPositionInGrid(copyEmailButton, 2, 2, 0);
	}
		
	private void addFrameComponents() {
		sp.add(buttonPanel);
		sp.add(tablePanel);
		mainFrame.add(sp);
	}
	
	private JButton makeButton(String buttonName) {
		JButton btn = new JButton(buttonName);
			btn.setPreferredSize(new Dimension(150,40));
		return btn;
	}
	
	private void setButtonPositionInGrid(JButton btn, int x, int y, int weight) {
		GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.weighty = weight;
			gbc.fill = GridBagConstraints.BOTH;
		buttonPanel.add(btn, gbc);
	}
	
	public void createTable(Object[][] dataFromTxt) {
		dtm 	= new DefaultTableModel(dataFromTxt, columnNames);
		table 	= new JTable(dtm);
		table.setPreferredScrollableViewportSize(new Dimension(800, 800));
		table.setFillsViewportHeight(true);
		tablePanel.add(new JScrollPane(table));
	}
	
	void addMainWindowListener(ActionListener listenerForMainWindowButtons) {
		addNewButton.addActionListener(listenerForMainWindowButtons);
		editButton.addActionListener(listenerForMainWindowButtons);
		deleteButton.addActionListener(listenerForMainWindowButtons);
		copyUserButton.addActionListener(listenerForMainWindowButtons);
		copyPassButton.addActionListener(listenerForMainWindowButtons);
		copyEmailButton.addActionListener(listenerForMainWindowButtons);
	}

	
//METHODS USED TO ADD, EDIT OR DELETE DATA IN THE JTABLE-----------------------
	
	void updateRowInTable(String[] updatedData) {
		for (int i = 0; i < updatedData.length; i++) {
			table.getModel().setValueAt(updatedData[i], table.getSelectedRow(), i);
		}
	}
	
	void addRowInTable(Object[][] dataFromTxt, String[] newRowData ) {
	
		dtm.addRow(new Object[] {newRowData[0],
								newRowData[1],
								newRowData[2],
								newRowData[3],
								newRowData[4],
								newRowData[5]});
	}
	
	boolean notifyViewDelete() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you really want to (hurt me) delete me?", "Delete post confirmation", JOptionPane.OK_CANCEL_OPTION);
		//OK
		if (choice == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	void deleteRowInTable(Object[][] dataFromTxt) {
		dtm.removeRow(table.getSelectedRow());
	}
	
	void copyPopup(int column) {
		JOptionPane.showMessageDialog(null, table.getValueAt(table.getSelectedRow(), column));
	}
	
}

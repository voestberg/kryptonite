import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindowController {

	private MainWindowModel mainWinModel;
	private MainWindowView mainWinView;
	
	public MainWindowController(MainWindowModel mainWinModel, MainWindowView mainWinView) {
		this.mainWinModel = mainWinModel;
		this.mainWinView = mainWinView;
		this.mainWinView.addMainWindowListener(new MainWindowButtons());
		this.mainWinView.createTable(mainWinModel.prepareTableData());
	}

	public void notifyViewAddNewRow() {
		mainWinView.addRowInTable(mainWinModel.prepareTableData(), mainWinModel.getNewRowData());
	}
	
	public void notifyViewUpdateRow() {
		mainWinView.updateRowInTable(mainWinModel.getNewRowData());
	}
	
	public void setNewRowDataFromAddOrEdit(String[] inputData) {
		mainWinModel.setNewRowData(inputData);
	}
	
	//Gets the data from the view to be send to the model
	private String[] getChosenColumnData() {
	
		int row = mainWinView.table.getSelectedRow();
		if (row == -1) {
			row = 0;
		} 
		int column = mainWinView.table.getColumnCount();
		String[] columnData = new String[6];
			for(int i = 0; i < column; i++) {
				columnData[i] = (String) mainWinView.table.getValueAt(row, i);
			}
		return columnData;
	}
	
	private MainWindowController getMainWinControllerObj() {
		return this;
	}		
		
	private class MainWindowButtons implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == mainWinView.addNewButton) {
				AddNewOrEditView ane = new AddNewOrEditView("Add New", null, 0);
				AddNewModel anm = new AddNewModel();
				AddNewController anc = new AddNewController(anm, ane, getMainWinControllerObj());
				ane.display();
				
			}
			else if (event.getSource() == mainWinView.editButton) {		
				AddNewOrEditView ane = new AddNewOrEditView("Edit", getChosenColumnData(), mainWinView.table.getSelectedRow());
				EditModel em = new EditModel();
				EditController ec = new EditController(em, ane, getMainWinControllerObj());
				ane.display();
			}
			else if (event.getSource() == mainWinView.deleteButton) {
				boolean checkRemoval = mainWinView.notifyViewDelete();
					if (checkRemoval == true) {
						mainWinModel.notifyModelDelete(mainWinView.table.getSelectedRow(), getChosenColumnData());
						mainWinView.deleteRowInTable(mainWinModel.prepareTableData());
					}
			}
			else if (event.getSource() == mainWinView.copyUserButton) {
				mainWinView.copyPopup(1);
			}
			else if (event.getSource() == mainWinView.copyPassButton) {
				mainWinView.copyPopup(2);
			}
			else if (event.getSource() == mainWinView.copyEmailButton) {
				mainWinView.copyPopup(3);
			}
		}
		
	}
	

}

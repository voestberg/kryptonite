import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddNewController {

	private AddNewModel anModel;
	private AddNewOrEditView anView;
	private MainWindowController mainWinCont;
	
	public AddNewController(AddNewModel anModel, AddNewOrEditView anView, MainWindowController mainWinCont) {
		this.anModel = anModel;
		this.anView = anView;
		this.anView.addNewListener(new AddNewButtons());
		this.mainWinCont = mainWinCont;
	}
	
	private class AddNewButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == anView.okButton) {
				
				String[] inputData = new String[] 
									{	
									anView.placeField.getText(),
									anView.usernameField.getText(),
									anView.passwordField.getText(),
									anView.emailField.getText(),
									anView.commentField.getText(),
									anView.dateField.getText()
									};
				anModel.setInputData(inputData);
				anModel.save();
				mainWinCont.setNewRowDataFromAddOrEdit(inputData);
				mainWinCont.notifyViewAddNewRow();
				anView.close();
			}
			else if (event.getSource() == anView.cancelButton) {
				anView.close();
			}
			else if (event.getSource() == anView.passGenButton) {
				anView.passGenWindow();
			}
			
		}
		
	}
	
}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EditController {

	private EditModel edModel;
	private AddNewOrEditView edView;
	private MainWindowController mainWinCont;
	
	public EditController(EditModel edModel, AddNewOrEditView edView, MainWindowController mainWinCont) {
		this.edModel = edModel;
		this.edView = edView;
		this.edView.addNewListener(new EditButtons());
		this.mainWinCont = mainWinCont;
	}
	
	private class EditButtons implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == edView.okButton) {
				
				String[] inputData = new String[] 
									{	
									edView.placeField.getText(),
									edView.usernameField.getText(),
									edView.passwordField.getText(),
									edView.emailField.getText(),
									edView.commentField.getText(),
									edView.dateField.getText()
									};
				edModel.setInputData(edView.getRow(), inputData);
				edModel.save();
				mainWinCont.setNewRowDataFromAddOrEdit(inputData);
				mainWinCont.notifyViewUpdateRow();
				edView.close();
			}
			else if (event.getSource() == edView.cancelButton) {
				edView.close();
			}
			else if (event.getSource() == edView.passGenButton) {
				edView.passGenWindow();
			}
		}
		
		
	}
	
}

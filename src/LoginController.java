import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginController {

	private LoginModel loginModel;
	private LoginView loginView;
	
	public LoginController(LoginModel loginModel, LoginView loginView) {
		this.loginModel = loginModel;
		this.loginView = loginView;
		this.loginView.addLoginButtonsListener(new LoginListener());
	}
	
	private class LoginListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == loginView.loginButton) {
				if (loginView.getUsernameInput().length() == 0 || loginView.getPasswordInput().length() == 0) {
					loginView.ERROR_noInput();
				} else {
					boolean userExists = loginModel.loginChecker(loginView.getUsernameInput()+loginView.getPasswordInput());
					
					if (userExists == false) {
						loginView.ERROR_wrongUserInformation();
					} else {
						MainWindowView mainWinView = new MainWindowView();
						MainWindowModel mainWinModel = new MainWindowModel();
						MainWindowController mainWinController = new MainWindowController(mainWinModel, mainWinView);
						mainWinView.display();
						loginView.close();
					}
				}
			}
			else if (event.getSource() == loginView.registerButton) {
				loginView.registerWindow();
			}
		}
	}
	
}

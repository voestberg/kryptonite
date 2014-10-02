
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;

public class Remyind  {
	
	private static void remyinderInit() {
		
		LoginView loginView = new LoginView();
		LoginModel loginModel = new LoginModel();
		LoginController loginController = new LoginController(loginModel, loginView);
		loginView.display();
		
	}
	
	private static void mainWindowInit() {
		
		MainWindowView mainWinView = new MainWindowView();
		MainWindowModel mainWinModel = new MainWindowModel();
		MainWindowController mainWinController = new MainWindowController(mainWinModel, mainWinView);
		mainWinView.display();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		/* Nuvarande login: 
		 *  username: ville
		 *  pass: 12346
		 */
		remyinderInit();
		
		//mainWindowInit();
	
		/*
		TESTA KRYPTERING ->
		Encryptor e = new Encryptor();
		String orginal = "StringToBeEncrypted";
		
		String en = e.encrypt(orginal);
		String de = e.decrypt(en);
		
		System.out.println("Orginal: " + orginal);
		System.out.println("Encypt: " + en);
		System.out.println("Decrypt: " + de);
		*/

	}
	
	
}

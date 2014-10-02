import java.security.NoSuchAlgorithmException;


public class LoginModel {

	private PostHandler ph;
	private Encryptor ec;
	
	public LoginModel() {
		ph = new PostHandler();
		ec = new Encryptor(ph.getLoginData());
	}
	
	public boolean loginChecker(String userInput) {
		
		boolean userExists = false;
		String txtFileLoginData = ph.getLoginData();
			userExists = compareLoginInfo(userInput, txtFileLoginData);
		return userExists;
	}
	
	private boolean compareLoginInfo(String userInput, String txtFileLoginData) {
		
		boolean compareCheck = false;
			try {
				String userInputHashed = ec.hashThisString(userInput);
					if (userInputHashed.equals(txtFileLoginData)) {
						compareCheck = true;
					} 
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		return compareCheck;
	}

}

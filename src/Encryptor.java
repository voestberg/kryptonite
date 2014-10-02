import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryptor {

	private String encryptedStr;
	private String decryptedStr;
	private String lib ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%&/,.;<+?";
	private int[] keys = {};
	private String userLoginData;
	//private PostHandler ph;
	
	
	public Encryptor(String userLoginData) {
		this.encryptedStr = "";
		this.decryptedStr = "";
		//ph = new PostHandler();
		this.userLoginData = userLoginData;
		setKeys(calcKeysFromUserLogin());
	}
	
	public String encrypt(String uncryptedStr) {
		
		int libPos = 0;
		int keyPos = 0;
		int keys[] = getKeys();
		
		for (int i = 0; i < uncryptedStr.length(); i++) {	
			for (int j = 0; j < lib.length(); j++) {
	
					if (keyPos >= keys.length) { keyPos =  0; }
					
					if (uncryptedStr.charAt(i) == lib.charAt(j)) {
						
						int check = j + keys[keyPos];
						
							if (check >= lib.length()) {
								int x = lib.length() - j;
								libPos = keys[keyPos] - x;
							} else {
								libPos = check;
							}
						keyPos++;
					}
			}
			encryptedStr += lib.charAt(libPos);
		}
		return encryptedStr;
	}
	
	public String decrypt(String encryptedStr) {
		
		int libPos = 0;
		int keyPos = 0;
		int keys[] = getKeys();
		
		for (int i = 0; i < encryptedStr.length(); i++) {
			for (int j = 0; j < lib.length(); j++) {
				
				if (keyPos >= keys.length) { keyPos = 0; }
				
				if (encryptedStr.charAt(i) == lib.charAt(j)) {
					
					int check = j - keys[keyPos];
					
						if (check < 0) {
							int x = keys[keyPos] - j;
							libPos = lib.length() - x;
						} else {
							libPos = check;
						}
					keyPos++;
				}
			}
			decryptedStr += lib.charAt(libPos);
		}
		return decryptedStr;
	}
	
	public String hashThisString(String stringToBeHashed) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		StringBuffer sb = new StringBuffer();
		
			md.update(stringToBeHashed.getBytes());
			byte byteData[] = md.digest();
			
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
			stringToBeHashed = sb.toString();
		return stringToBeHashed;
	}
	
	public void setUserLoginData(String userLoginData) {
		this.userLoginData = userLoginData;
	}
	
	private String getUserLoginData() {
		return userLoginData;
	}
	
	private int[] getKeys() {
		return keys;
	}
	
	private void setKeys(int[] keys) {
		this.keys = keys;
	}
	private int[] calcKeysFromUserLogin() {
		
		int[] keys = new int[5];
		int loginLength = getUserLoginData().length();
		
		keys[0] = getFirstNumber(loginLength);
		keys[1] = getSecondNumber(loginLength);
		keys[2] = getThirdNumber(loginLength);
		keys[3] = getFourthNumber(loginLength);
		keys[4] = getFifthNumber(loginLength);
		//System.out.println(keys[0] +" "+ keys[1]+" "+ keys[2]+" "+ keys[3]+" "+ keys[4]);
		
		return keys;
	}
	
	private int getFirstNumber(int loginLength) {
		
		int n1 = loginLength % 5;
			if (n1 == 0) {
				n1 = loginLength % 4;
			}
			n1 *= 3;
		return n1;
	}
	
	private int getSecondNumber(int loginLength) {
		
		int n2 = loginLength % 4;
			if (n2 == 0) {
				n2 = loginLength % 3;
			}
			n2 += 10;
		return n2;
	}
	
	private int getThirdNumber(int loginLength) {
		
		int n3 = loginLength % 3;
			if (n3 == 0) {
				n3 = loginLength % 2;
			}
			n3 *= 5;
		return n3;
	}
	
	private int getFourthNumber(int loginLength) {
		return loginLength / 3;
	}
	
	private int getFifthNumber(int loginLength) {
		return 21;
	}
	
}

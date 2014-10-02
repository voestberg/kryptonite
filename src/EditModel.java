
public class EditModel {

	String updatedStr;
	String original = "";
	String inputDataStr;
	PostHandler ph;
	
	public EditModel() {
		ph = new PostHandler();
		ph.initStrSorting();
		ph.getStrPostsToObj();
	}
	
	public void setInputData(int row, String[] inputData) {
		
		String tmpStr = "";
		
		for (int i = 0; i < 6; i++) {
			Encryptor en = new Encryptor(ph.getLoginData());
			if (i == 0) {
				tmpStr += "<" + en.encrypt(inputData[i]) + "<";
			} else {
				tmpStr += en.encrypt(inputData[i]) + "<";
			}
		}
		this.inputDataStr = tmpStr;
		prepareOldString(row, inputDataStr);
	}

	
	public void save() {
		TxtFileHandler tfh = new TxtFileHandler();
		tfh.saveTxtFile(updatedStr);
	}

	private void prepareOldString(int row, String data) {
		
		ph.initStrSorting();
		ph.getStrPostsToObj();
		Object[] postIndexObj = ph.getPostIndexInObj();
		int start = (int) postIndexObj[row];
		int nextRow = row+1;
		
		int end = (int) postIndexObj[nextRow];
		
		String fullDataStr = ph.getFullDataStr();
		String loginData = ph.getLoginData();
		
		String firstPart = sortOutFirstPart(start, fullDataStr);
		String chosenPart = sortOutChosenStr(start, end, fullDataStr);
		String secPart = sortOutSecPart(end, fullDataStr);
		String combinedStr = combineParts(loginData, firstPart, data, secPart);
		System.out.println(firstPart);
		System.out.println(chosenPart);
		System.out.println(secPart);
		
		updatedStr = combinedStr;
	}
	
	private String sortOutFirstPart(int start, String fullDataStr) {
		
		String tmpFirstPart = "";
		int strStart = ph.getLoginData().length();
			for (int i = strStart; i < start; i++) {
				tmpFirstPart += fullDataStr.charAt(i);
			}
		return tmpFirstPart;
	}
	
	private String sortOutChosenStr(int start, int end, String fullDataStr) {
		
		String tmpChosenStr = "";
		for (int i = start; i <= end; i++) {
			tmpChosenStr += fullDataStr.charAt(i);
		}
		return tmpChosenStr;
	}
	
	private String sortOutSecPart(int end, String fullDataStr) {
		
		String tmpSecPart = "";
			for (int i = end+1; i <= fullDataStr.length()-1; i++) {
				tmpSecPart += fullDataStr.charAt(i);
			}
		return tmpSecPart;
	}
	
	private String combineParts(String loginData, String firstPart, String data, String secPart) {
		
		String tmpCombinedStr = loginData+firstPart+data+secPart;
		return tmpCombinedStr;
	}
}

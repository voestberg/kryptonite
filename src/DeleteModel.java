
public class DeleteModel {

	String updatedStr;
	String fileName = "kryptonite";
	String original = "";
	String inputDataStr;
	PostHandler ec;
	
	public DeleteModel() {
		ec = new PostHandler();
		ec.initStrSorting();
		ec.getStrPostsToObj();
	}
	
	public void save() {
		TxtFileHandler tfh = new TxtFileHandler();
		tfh.saveTxtFile(updatedStr);
	}
	
	public void partToBeDeleted(int row, String[] rowData) {
		this.inputDataStr = rowData[0] + "<" + rowData[1] + "<" + rowData[2] + "<" + rowData[3] + "<" + rowData[4] + "<" + rowData[5];
		prepareOldString(row, inputDataStr);
	}
	
	private void prepareOldString(int row, String data) {
		ec.initStrSorting();
		ec.getStrPostsToObj();
		Object[] postIndexObj = ec.getPostIndexInObj();
		int start = (int) postIndexObj[row];
		int nextRow = row+1;
		
		int end = (int) postIndexObj[nextRow];
		
		String fullDataStr = ec.getFullDataStr();
		String loginData = ec.getLoginData();
		System.out.println(fullDataStr);
		
		String firstPart = sortOutFirstPart(start, fullDataStr);
		String chosenPart = sortOutChosenStr(start, end, fullDataStr);
		String secPart = sortOutSecPart(end, fullDataStr);
		String combinedStr = combineParts(loginData, firstPart, secPart);
		System.out.println(combinedStr);
		
		updatedStr = combinedStr;
	
	}
	
	private String sortOutFirstPart(int start, String fullDataStr) {
		
		String tmpFirstPart = "";
		int strStart = ec.getLoginData().length();
			for (int i = strStart; i <= start; i++) {
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
	
	private String combineParts(String loginData, String firstPart, String secPart) {
		
		String tmpCombinedStr = loginData+firstPart+secPart;
		return tmpCombinedStr;
		
	}
	
}

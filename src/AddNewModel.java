
public class AddNewModel {

	private PostHandler ph;
	private String inputDataStr;
	
	public AddNewModel() {
		ph = new PostHandler();
		ph.initStrSorting();
		ph.getStrPostsToObj();
	}
	
	public void setInputData(String[] inputData) {
		
		String tmpStr = "";
		
		for (int i = 0; i < 6; i++) {
			Encryptor en = new Encryptor(ph.getLoginData());
			tmpStr += en.encrypt(inputData[i]) + "<";
		}
		this.inputDataStr = tmpStr;
	}
	
	public void save() {
		String newStr = ph.getFullDataStr() + getInputData();
		TxtFileHandler tfh = new TxtFileHandler();
		tfh.saveTxtFile(newStr);
	}

	private String getInputData() {
		return inputDataStr;
	}
	
}

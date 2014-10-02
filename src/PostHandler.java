
public class PostHandler {

	private TxtFileHandler txt;
	private String fullDataStr;
	private String loginDataStr = "";
	
	private int postIndex;
	private int tableObjLength;
	private String fullPostsStr;
	Object[] postIndexInObj;
	
	public PostHandler() {
		txt = new TxtFileHandler();
		txt.readTxtFile();
		this.fullDataStr = txt.getTxtFileString();
		this.loginDataStr = setLoginData(getFullDataStr());
	}
	
	public void initStrSorting() {
		setStartIndexForPosts(getFullDataStr());
		sortOutFullPostsStr();
		calcTableObjectLength();
	}
		
	public String getLoginData() {
		return loginDataStr;
	}
	
	public String getFullDataStr() {
		return fullDataStr;
	}
	
	public Object[] getPostIndexInObj() {
		return postIndexInObj;
	}
	
	public String getFullPostsStr() {
		return fullPostsStr;
	}
	
	public int getTableObjectLength() {
		return tableObjLength;
	}
	
	public Object[][] getStrPostsToObj() {
		
		int length = getTableObjectLength();
		
		Object[][] tableObjPosts = new Object[length][6];
		postIndexInObj = new Object[length];
		
			for (int i = 0; i < tableObjPosts.length; i++) {
				
				setPostIndexInObj(i);
				
				for (int j = 0; j < 6; j++) {
					tableObjPosts[i][j] = getNextPostInStr();
				}
			}
		return tableObjPosts;
	}
	
	private void setPostIndex(int index) {
		this.postIndex = index;
	}
	
	private int getPostIndex() {
		return postIndex;
	}
		
	private void setPostIndexInObj(int n) {
		postIndexInObj[n] = getPostIndex();
	}
	
	private void setTableObjectLength(int tableObjLength) {
		this.tableObjLength = tableObjLength;
	}
	
	private void setFullPostsStr(String fullPostsStr) {
		this.fullPostsStr = fullPostsStr;
	}
	
	private void setStartIndexForPosts(String fullDataStr) {
		int tmpStartIndex = 0;
			while (fullDataStr.charAt(tmpStartIndex) != '<') {
				tmpStartIndex++; 
			}
		setPostIndex(tmpStartIndex);
	}
	
	private void sortOutFullPostsStr() {
		
		String tmpFullDataStr = getFullDataStr();
		String fullPostsStr = "";
		int index = getPostIndex();
			while (index != tmpFullDataStr.length()) {
				fullPostsStr += tmpFullDataStr.charAt(index);
				index++;
			}
		setFullPostsStr(fullPostsStr);
	}

	private String getNextPostInStr() {
		
		Encryptor en = new Encryptor(getLoginData());
		String fullDataStr = getFullDataStr();
		String tmpPost = "";
		int tmpPostIndex = getPostIndex();
		
			while (fullDataStr.charAt(tmpPostIndex+1) != '<') {
				tmpPost += fullDataStr.charAt(tmpPostIndex+1);
				tmpPostIndex++;
			}
			setPostIndex(tmpPostIndex+1);
		return en.decrypt(tmpPost);
	}
	
	private void calcTableObjectLength() {
		
		String tmpFullPostsStr = getFullPostsStr();
		int tmpLength = 0;
			for(int i = 0; i < tmpFullPostsStr.length(); i++) {
				if(tmpFullPostsStr.charAt(i) == '<') {
					tmpLength++;
				}
			}
			tmpLength = tmpLength / 6;
		setTableObjectLength(tmpLength);
	}

	private String setLoginData(String fullDataStr) {
		
		int strIndex = 0;
			while (fullDataStr.charAt(strIndex) != '<') {
				loginDataStr += fullDataStr.charAt(strIndex);
				strIndex++;
			}
		return loginDataStr;
	}
}

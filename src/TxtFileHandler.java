import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class TxtFileHandler {
	
	private String txtFileString;
	private static final String fileName = "kryptonite";
	
	public void readTxtFile() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
			this.txtFileString = br.readLine();
			br.close();
		} catch (Exception e) {
			System.out.println("ERROR in readTxtFile: " + e.getMessage());
		}
	}
	
	public void saveTxtFile(String userInfo) {
		
		try {
			FileWriter fw = new FileWriter(fileName + ".txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(userInfo);
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getTxtFileString() {
		return txtFileString;
	}
	
}

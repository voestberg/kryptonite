
public interface MainWindow {

	public Object[][] prepareTableData();
	public void notifyModelDelete(int row, String[] rowData);
	public void notifyCopyUsername();
	public void notifyCopyPassword();
	public void notifyCopyEmail();
	
}

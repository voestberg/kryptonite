
public class MainWindowModel implements MainWindow {
	
	private String[] newRowData;

	public Object[][] prepareTableData() {
		PostHandler ec = new PostHandler();
		ec.initStrSorting();
		Object[][] tableData = ec.getStrPostsToObj();
		return tableData;
	}
	
	public void setNewRowData(String[] newRowData) {
		this.newRowData = newRowData;
	}
	
	public String[] getNewRowData() {
		return newRowData;
	}
	
	@Override
	public void notifyModelDelete(int row, String[] rowData) {
		DeleteModel dm = new DeleteModel();
		dm.partToBeDeleted(row, rowData);
		dm.save();	
	}

	@Override
	public void notifyCopyUsername() {
		
	}

	@Override
	public void notifyCopyPassword() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyCopyEmail() {
		// TODO Auto-generated method stub
		
	}
}

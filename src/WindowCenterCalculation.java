import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


/*
 * Class only used for GUI components to make them in the middle of the screen
 * */

public class WindowCenterCalculation {

	private JFrame tmpFrame;
	private int height = 0;
	private int width = 0;
	
	public WindowCenterCalculation(JFrame tmpFrame) {
		this.tmpFrame = tmpFrame;
		calcScreenHeight();
		calcScreenWidth();
	}
		
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	private JFrame getFrame() {
		return tmpFrame;
	}
	
	private void setHeight(int height) {
		this.height = height;
	}
	
	private void setWidth(int width) {
		this.width = width;
	}
		
	private void calcScreenHeight() {
		final Toolkit tk = Toolkit.getDefaultToolkit();
		final Dimension screen = tk.getScreenSize();
		final int height = (screen.height - getFrame().getHeight() / 2);
		setHeight(height);
	}
	
	private void calcScreenWidth() {
		final Toolkit tk = Toolkit.getDefaultToolkit();
		final Dimension screen = tk.getScreenSize();
		final int width = (screen.width - getFrame().getWidth() / 2);
		setWidth(width);
	}

}


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginView {

	JButton loginButton;
	JButton registerButton;
	private JFrame loginFrame;
	private JTextField userTextField;
	private JPasswordField passTextField;
	
	public LoginView() {
		initLoginObjects();
		setPlacementOfObjects();
		setLoginComponents();
	}
	
	public void display() {
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
	}
	
	public void close() {
		loginFrame.dispose();
	}
	
	public String getUsernameInput() {
		return userTextField.getText();
	}
	
	public String getPasswordInput() {
		return new String(passTextField.getPassword());
	}
	
	public void ERROR_noInput() {
		JOptionPane.showConfirmDialog(null, "Please fill in your login information", "Login failed - Oops(?)", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void ERROR_wrongUserInformation() {
		JOptionPane.showConfirmDialog(null, "Wrong user information", "Login failed - Oops(?)", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void registerWindow() {
		//TODO implement register
		JOptionPane.showConfirmDialog(null, "No yet implemented", "Register failed - Oops(?)", JOptionPane.PLAIN_MESSAGE);
	}
	
	void addLoginButtonsListener(ActionListener listenerForLoginButtons) {
		loginButton.addActionListener(listenerForLoginButtons);
		registerButton.addActionListener(listenerForLoginButtons);
	}
	
	private void initLoginObjects() {
		loginFrame 	= new JFrame("Login");
	}
	
	private void setPlacementOfObjects() {
		WindowCenterCalculation wcc = new WindowCenterCalculation(loginFrame);
		final int screenHeight = wcc.getHeight();
		final int screenWidth = wcc.getWidth();
		loginFrame.setSize(300, 150);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLocation(screenWidth, screenHeight);
	}
	
	private void setLoginComponents() {
		loginFrame.setLayout(null);
		createUsernameLabel();
		createPasswordLabel();
		createUsernameTextInput();
		createPasswordTextInput();
		createRegisterButton();
		createLoginButton();
	}
	
	private void createUsernameLabel() {
		JLabel userLabel = new JLabel ("Username");
		userLabel.setBounds(10, 10, 90, 25); // x, y, width, height
		loginFrame.add(userLabel);
	}
	
	private void createPasswordLabel() {
		JLabel passwordLabel = new JLabel ("Password");
		passwordLabel.setBounds(10, 40, 90, 25);
		loginFrame.add(passwordLabel);
	}
	
	private void createUsernameTextInput() {
		userTextField = new JTextField(20);
		userTextField.setBounds(100, 10, 160, 25);
		loginFrame.add(userTextField);
	}
	
	private void createPasswordTextInput() {
		passTextField = new JPasswordField(20);
		passTextField.setBounds(100, 40, 160, 25);
		loginFrame.add(passTextField);
	}
	
	private void createLoginButton() {
		loginButton = new JButton("Login");
		loginButton.setBounds(180, 80, 80, 25);
		loginFrame.add(loginButton);
	}
	
	private void createRegisterButton() {
		registerButton = new JButton("Register");
		registerButton.setBounds(10, 80, 100, 25);
		loginFrame.add(registerButton);
	}
	
}

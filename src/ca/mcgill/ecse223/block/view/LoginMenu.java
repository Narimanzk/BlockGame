package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class LoginMenu {

	private JFrame frmWasdwa;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMenu window = new LoginMenu();
					window.frmWasdwa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWasdwa = new JFrame();
		frmWasdwa.setTitle("Login");
		frmWasdwa.setResizable(false);
		frmWasdwa.setBounds(100, 100, 450, 300);
		frmWasdwa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWasdwa.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogin.setBounds(185, 71, 55, 38);
		frmWasdwa.getContentPane().add(lblLogin);
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setText("Username");
		txtpnUsername.setBounds(158, 109, 126, 20);
		frmWasdwa.getContentPane().add(txtpnUsername);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		pwdPassword.setBounds(158, 141, 126, 20);
		frmWasdwa.getContentPane().add(pwdPassword);
	}
}

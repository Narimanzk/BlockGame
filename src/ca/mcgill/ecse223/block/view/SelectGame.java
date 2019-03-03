package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class SelectGame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectGame window = new SelectGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Game"}));
		comboBox.setBounds(158, 32, 114, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnEditGame = new JButton("Edit Game");
		btnEditGame.setBounds(158, 108, 114, 23);
		frame.getContentPane().add(btnEditGame);
		
		JButton btnDeleteGame = new JButton("Delete Game");
		btnDeleteGame.setBounds(158, 132, 114, 23);
		frame.getContentPane().add(btnDeleteGame);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(0, 0, 89, 23);
		frame.getContentPane().add(btnMainMenu);
	}
}

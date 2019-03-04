package ca.mcgill.ecse223.block.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class Block223Page{
	public JFrame frame;


	public Block223Page() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("Block 223");

		
		//TODO: set visible!!!
		
		/////////////////////////////////////////////////////////////////
		//Login page:
		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "name_26471782456177");

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(226, 221, 79, 29);
		//When you click the login button, this happens.
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		loginPanel.setLayout(null);
		loginPanel.add(btnLogin);

		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setText("Password");
		txtrPassword.setBounds(222, 186, 81, 23);
		loginPanel.add(txtrPassword);

		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setText("Username");
		txtrUsername.setBounds(222, 153, 81, 23);
		loginPanel.add(txtrUsername);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(238, 122, 66, 16);
		loginPanel.add(lblLogin);
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Main Menu page
		JPanel mainMenu = new JPanel();
		frame.getContentPane().add(mainMenu, "name_33113224405399");
		
		//////Add/Edit game button////////////
		JButton btnAddEditGame = new JButton("Add/Edit Game");
		btnAddEditGame.setBounds(160, 128, 170, 29);
		//When you click the Edit game button, this happens
		btnAddEditGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mainMenu.setLayout(null);
		mainMenu.add(btnAddEditGame);

		
		//////Load Game button//////
		JButton btnLoad = new JButton("Load Game");
		//When you click the Add game button, this happens
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoad.setBounds(160, 158, 170, 29);
		mainMenu.add(btnLoad);
		
		//////Logout button//////
		JButton btnLogout = new JButton("Logout");
		//When you click the Add game button, this happens
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.setBounds(160, 188, 170, 29);
		mainMenu.add(btnLogout);
		/////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////
		//Add/Edit Game page:
		JPanel AddEditGameMenu = new JPanel();
		frame.getContentPane().add(AddEditGameMenu, "name_34257085377978");
		AddEditGameMenu.setLayout(null);
		
		//Button to create a game. Should take the name from the NewGameName text Area.
		//This will ALSO CALL: selectGame(name from NewGameName text Area)
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateGame.setBounds(45, 178, 157, 29);
		AddEditGameMenu.add(btnCreateGame);
		
		//Text field for the game name.
		JTextArea NewGameName = new JTextArea();
		NewGameName.setText("Enter Game Name");
		NewGameName.setBounds(55, 150, 134, 16);
		AddEditGameMenu.add(NewGameName);
		
		//Spinner, should have all the game names!
		JSpinner spinner = new JSpinner();
		spinner.setBounds(294, 145, 105, 26);
		AddEditGameMenu.add(spinner);
		
		//Select game button, should take the name from the spinner.
		JButton SelectGame = new JButton("Select Game");
		SelectGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SelectGame.setBounds(266, 178, 157, 29);
		AddEditGameMenu.add(SelectGame);
		
		//"Back" button, should bring us back to the main menu.
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack.setBounds(6, 6, 69, 29);
		AddEditGameMenu.add(btnBack);
		
		/////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////
		//Add Game menu:
		JPanel AddGameSpecifyDetails = new JPanel();
		frame.getContentPane().add(AddGameSpecifyDetails, "name_35765440027354");
		AddGameSpecifyDetails.setLayout(null);
		
		JTextArea txtrNumberOfLevels = new JTextArea();
		txtrNumberOfLevels.setBounds(162, 45, 194, 16);
		txtrNumberOfLevels.setText("Number of Levels");
		AddGameSpecifyDetails.add(txtrNumberOfLevels);
		
		JTextArea txtrNumBlocksPerLvl = new JTextArea();
		txtrNumBlocksPerLvl.setText("Number of Blocks Per Level");
		txtrNumBlocksPerLvl.setBounds(162, 75, 194, 16);
		AddGameSpecifyDetails.add(txtrNumBlocksPerLvl);
		
		JTextArea txtrMinBallSpd = new JTextArea();
		txtrMinBallSpd.setText("Minimum ball speed");
		txtrMinBallSpd.setBounds(162, 105, 194, 16);
		AddGameSpecifyDetails.add(txtrMinBallSpd);
		
		JTextArea txtrBallSpeedIncrease = new JTextArea();
		txtrBallSpeedIncrease.setText("Ball speed Increase Factor");
		txtrBallSpeedIncrease.setBounds(162, 135, 194, 16);
		AddGameSpecifyDetails.add(txtrBallSpeedIncrease);
		
		JTextArea txtrMaxPaddleLength = new JTextArea();
		txtrMaxPaddleLength.setText("Max paddle length");
		txtrMaxPaddleLength.setBounds(162, 165, 194, 16);
		AddGameSpecifyDetails.add(txtrMaxPaddleLength);
		
		JTextArea txtrMinimumPaddleLength = new JTextArea();
		txtrMinimumPaddleLength.setText("Minimum paddle length");
		txtrMinimumPaddleLength.setBounds(162, 195, 194, 16);
		AddGameSpecifyDetails.add(txtrMinimumPaddleLength);
		
		JLabel lblDefineAGame = new JLabel("Define a game!");
		lblDefineAGame.setBounds(209, 17, 185, 16);
		AddGameSpecifyDetails.add(lblDefineAGame);
		
		//First need to call selectGame with the game name
		//Should take all these guys^ as parameters, and then call tudor's method:  setGameDetails
		JButton btnDefine = new JButton("Define");
		btnDefine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDefine.setBounds(197, 223, 117, 29);
		AddGameSpecifyDetails.add(btnDefine);
		
		JPanel EditGameMenu = new JPanel();
		frame.getContentPane().add(EditGameMenu, "name_39858955777561");
		
		/////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////
		
		//Update Game Menu//
		JPanel UpdateGameMenu = new JPanel();
		EditGameMenu.add(UpdateGameMenu);
		UpdateGameMenu.setLayout(null);
		
		
		JTextArea txtrNewGameName = new JTextArea();
		txtrNewGameName.setText("New Game Name");
		txtrNewGameName.setBounds(162, 45, 194, 16);
		UpdateGameMenu.add(txtrNewGameName);
		
		JTextArea NrLevels = new JTextArea();
		NrLevels.setText("Number of Levels");
		NrLevels.setBounds(162, 75, 194, 16);
		UpdateGameMenu.add(NrLevels);
		
		JTextArea NrBlocksPerLvl = new JTextArea();
		NrBlocksPerLvl.setText("Number of Blocks Per Level");
		NrBlocksPerLvl.setBounds(162, 105, 194, 16);
		UpdateGameMenu.add(NrBlocksPerLvl);
		
		JTextArea MinBallSpd = new JTextArea();
		MinBallSpd.setText("Minimum ball speed");
		MinBallSpd.setBounds(162, 135, 194, 16);
		UpdateGameMenu.add(MinBallSpd);
		
		JTextArea BallSpdIncFactor = new JTextArea();
		BallSpdIncFactor.setText("Ball speed Increase Factor");
		BallSpdIncFactor.setBounds(162, 165, 194, 16);
		UpdateGameMenu.add(BallSpdIncFactor);
		
		JTextArea MaxPaddleLngth = new JTextArea();
		MaxPaddleLngth.setText("Max paddle length");
		MaxPaddleLngth.setBounds(162, 195, 194, 16);
		UpdateGameMenu.add(MaxPaddleLngth);
		
		JTextArea MinPaddleLngth = new JTextArea();
		MinPaddleLngth.setText("Minimum paddle length");
		MinPaddleLngth.setBounds(162, 225, 194, 16);
		UpdateGameMenu.add(MinPaddleLngth);
		
		JLabel lblUpdateAGame = new JLabel("Update a game!");
		lblUpdateAGame.setBounds(209, 17, 185, 16);
		UpdateGameMenu.add(lblUpdateAGame);
		
		
		//Should take all these parameters^ and then update the game using UpdateGame
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(197, 255, 117, 29);
		UpdateGameMenu.add(btnUpdate);

	
		
	}
}

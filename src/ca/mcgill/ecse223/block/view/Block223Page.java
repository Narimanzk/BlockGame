package ca.mcgill.ecse223.block.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.*;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Player;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Block223Page{
	public JFrame frame;
	// UI elements
	private JTextArea txtrNewGameName;
	private JTextArea NrLevels;
	private JComboBox<String> gameList;
	private JComboBox<String> playableGameList;
	private JComboBox<Integer> blockList;
	private JComboBox<Integer> blockList2;
	private JLabel errorMessage;
	private JTextField txtRedValue;
	private JTextField txtGreenValue;
	private JTextField txtBlueValue;
	private JTextField txtPoints;
	private JTextField txtRedEdit;
	private JTextField txtGreenEdit;
	private JTextField txtBlueEdit;
	private JTextField txtPointsEdit;
	private JTextField txtLevel;
	private JTextField txtHorizontalGridPosition;
	private JTextField txtVerticalGridPosition;
	private JTextField txtCurrentHorizontalGrid;
	private JTextField txtCurrentVerticalGrid;
	private JTextField txtNewHorizontalGrid;
	private JTextField txtNewVerticalGrid;
	private JTextField txtHorGridPos;
	private JTextField txtVerGridPos;




	// data elements
	private String error = null;

	public Block223Page() {
		initialize();
		//refreshData();
	}

	private void initialize() {
		
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setVerticalAlignment(SwingConstants.BOTTOM);
		errorMessage.setForeground(Color.RED);
		blockList2 = new JComboBox<Integer>();
		gameList = new JComboBox<String>();
		playableGameList = new JComboBox<String>();
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 527, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("Block 223");
		frame.getContentPane().add(errorMessage);

		/////////////////////////////////////////////////////////////////
		//Login page:
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(loginPanel, "name_26471782456177");

		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setBounds(222, 168, 81, 23);
		loginPanel.add(txtrPassword);

		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setBounds(222, 130, 81, 23);
		loginPanel.add(txtrUsername);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Mshtakan", Font.BOLD | Font.ITALIC, 27));
		lblLogin.setBounds(222, 72, 81, 46);
		loginPanel.add(lblLogin);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(222, 200, 81, 32);
		loginPanel.setLayout(null);
		loginPanel.add(btnLogin);

		//Should bring us to the register page.
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(222, 236, 81, 32);
		loginPanel.add(btnRegister);

		JLabel label_4 = new JLabel("Username:");
		label_4.setFont(new Font("Mshtakan", Font.PLAIN, 15));
		label_4.setBounds(85, 130, 125, 16);
		loginPanel.add(label_4);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Mshtakan", Font.PLAIN, 15));
		lblPassword.setBounds(85, 168, 121, 16);
		loginPanel.add(lblPassword);

		loginPanel.setVisible(true);

		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Main Menu page
		JPanel mainMenu = new JPanel();
		mainMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(mainMenu, "name_33113224405399");

		//////Add/Edit game button////////////
		JButton btnAddEditGame = new JButton("Game Menu");
		btnAddEditGame.setBounds(160, 128, 170, 29);
		mainMenu.setLayout(null);
		mainMenu.add(btnAddEditGame);


		//////Load Game button//////
		JButton btnLoad = new JButton("Load Game");
		//When you click the Add game button, this happens
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LOAD DOES NOTHING AS OF DELIVERABLE 3
			}
		});

		btnLoad.setBounds(160, 158, 170, 29);
		mainMenu.add(btnLoad);

		//////Logout button//////
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(160, 188, 170, 29);
		mainMenu.add(btnLogout);

		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setFont(new Font("Mshtakan", Font.BOLD | Font.ITALIC, 27));
		lblMainMenu.setBounds(170, 23, 146, 78);
		mainMenu.add(lblMainMenu);
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Add/Edit Game page:
		JPanel AddEditGameMenu = new JPanel();
		AddEditGameMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(AddEditGameMenu, "name_34257085377978");
		AddEditGameMenu.setLayout(null);

		//Text field for the game name.
		JTextArea NewGameName = new JTextArea();
		NewGameName.setBounds(55, 150, 134, 16);
		AddEditGameMenu.add(NewGameName);

		//Button to create a game. Should take the name from the NewGameName text Area.
		//This will ALSO CALL: selectGame(name from NewGameName text Area)
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.setBounds(45, 178, 157, 29);
		AddEditGameMenu.add(btnCreateGame);
		//JComboBox, should have all the game names!
		gameList.setBounds(276, 146, 146, 27);
		AddEditGameMenu.add(gameList);

		//Select game button, should take the name from the combo box^.
		JButton btnSelectGame = new JButton("Select Game");
		btnSelectGame.setBounds(265, 185, 157, 29);
		AddEditGameMenu.add(btnSelectGame);

		JButton btnDeleteGame = new JButton("Delete Game");
		btnDeleteGame.setBounds(265, 219, 157, 29);
		AddEditGameMenu.add(btnDeleteGame);


		JButton backAEGamePage = new JButton("Back");
		backAEGamePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				AddEditGameMenu.setVisible(false);
				mainMenu.setVisible(true);
			}
		});
		backAEGamePage.setBounds(6, 6, 100, 29);
		AddEditGameMenu.add(backAEGamePage);
		/////////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////////

		//General game menu
		//This menu should have the buttons to: 
		//1. update game details
		//2. Affect blocks within a game
		//3. Affect blocks within a level
		//4. Save changes.

		JPanel GeneralGameMenu = new JPanel();
		GeneralGameMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(GeneralGameMenu, "name_46221789659840");
		GeneralGameMenu.setLayout(null);
		errorMessage.setVisible(true);

		JButton btnUpdateGameDtails = new JButton("Update Game");
		btnUpdateGameDtails.setBounds(160, 110, 214, 29);
		GeneralGameMenu.add(btnUpdateGameDtails);

		JButton btnChangeBlocksInGame = new JButton("Add/Delete/Update a Block");


		btnChangeBlocksInGame.setBounds(160, 140, 214, 29);
		GeneralGameMenu.add(btnChangeBlocksInGame);


		//Edit blocks in a level. 
		//This will bring to a new menu, that will allow us to select level, and then position,move,remove blocks.
		JButton btnEditBlocksWithin = new JButton("Edit Blocks Within a Level");
		btnEditBlocksWithin.setBounds(160, 170, 214, 29);
		GeneralGameMenu.add(btnEditBlocksWithin);


		//Save Changes ( Call the save method)
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(160, 200, 214, 29);
		GeneralGameMenu.add(btnSaveChanges);
		
		JLabel lblSpecificGame = new JLabel("Specific Game");
		lblSpecificGame.setFont(new Font("Mshtakan", Font.BOLD | Font.ITALIC, 27));
		lblSpecificGame.setBounds(175, 6, 270, 78);
		GeneralGameMenu.add(lblSpecificGame);
		
		JButton btnPublishGame = new JButton("Publish");
		btnPublishGame.setBounds(160, 232, 214, 29);
		GeneralGameMenu.add(btnPublishGame);
		


		JButton backGenGamePage = new JButton("Back");
		backGenGamePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				GeneralGameMenu.setVisible(false);
				mainMenu.setVisible(true);
			}
		});
		backAEGamePage.setBounds(6, 6, 60, 29);
		AddEditGameMenu.add(backAEGamePage);

		JLabel lblNewLabel = new JLabel("Game Name:");
		lblNewLabel.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblNewLabel.setBounds(55, 122, 133, 16);
		AddEditGameMenu.add(lblNewLabel);
		
		JLabel lblGameMenu = new JLabel("Game Menu");
		lblGameMenu.setFont(new Font("Mshtakan", Font.BOLD | Font.ITALIC, 27));
		lblGameMenu.setBounds(172, 20, 146, 78);
		AddEditGameMenu.add(lblGameMenu);
		
		JLabel lblAvailableGames = new JLabel("Available Games:");
		lblAvailableGames.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblAvailableGames.setBounds(289, 122, 133, 16);
		AddEditGameMenu.add(lblAvailableGames);
		
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Add Game menu:
		JPanel AddGameSpecifyDetails = new JPanel();
		AddGameSpecifyDetails.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(AddGameSpecifyDetails, "name_35765440027354");
		AddGameSpecifyDetails.setLayout(null);

		JTextArea txtrNumberOfLevels = new JTextArea();
		txtrNumberOfLevels.setBounds(273, 45, 83, 16);
		AddGameSpecifyDetails.add(txtrNumberOfLevels);

		JTextArea txtrNumBlocksPerLvl = new JTextArea();
		txtrNumBlocksPerLvl.setBounds(273, 75, 83, 16);
		AddGameSpecifyDetails.add(txtrNumBlocksPerLvl);

		JTextArea txtrMinBallSpdX = new JTextArea();
		txtrMinBallSpdX.setBounds(273, 104, 83, 16);
		AddGameSpecifyDetails.add(txtrMinBallSpdX);

		JTextArea txtrMinimumBallSpeedY = new JTextArea();
		txtrMinimumBallSpeedY.setBounds(273, 135, 83, 16);
		AddGameSpecifyDetails.add(txtrMinimumBallSpeedY);

		JTextArea txtrBallSpeedIncrease = new JTextArea();
		txtrBallSpeedIncrease.setBounds(273, 165, 83, 16);
		AddGameSpecifyDetails.add(txtrBallSpeedIncrease);

		JTextArea txtrMaxPaddleLength = new JTextArea();
		txtrMaxPaddleLength.setBounds(273, 195, 83, 16);
		AddGameSpecifyDetails.add(txtrMaxPaddleLength);

		JTextArea txtrMinimumPaddleLength = new JTextArea();
		txtrMinimumPaddleLength.setBounds(273, 225, 83, 16);
		AddGameSpecifyDetails.add(txtrMinimumPaddleLength);

		JLabel lblDefineAGame = new JLabel("Define a game!");
		lblDefineAGame.setFont(new Font("Mshtakan", Font.BOLD, 19));
		lblDefineAGame.setBounds(162, 17, 185, 16);
		AddGameSpecifyDetails.add(lblDefineAGame);

		JButton backSpecDetailsPage = new JButton("Back");
		backSpecDetailsPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				AddGameSpecifyDetails.setVisible(false);
				GeneralGameMenu.setVisible(true);
			}
		});
		backSpecDetailsPage.setBounds(6, 13, 100, 29);
		AddGameSpecifyDetails.add(backSpecDetailsPage);
		//First need to call selectGame with the game name
		//Should take all these guys^ as parameters, and then call tudor's method:  setGameDetails
		JButton btnDefine = new JButton("Define");
		btnDefine.setBounds(197, 248, 117, 29);
		AddGameSpecifyDetails.add(btnDefine);
		
		JLabel lblNewLabel_2 = new JLabel("Number of Levels:");
		lblNewLabel_2.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(67, 45, 140, 16);
		AddGameSpecifyDetails.add(lblNewLabel_2);
		
		JLabel lblNumberOfBlocks = new JLabel("Number of Blocks Per Level:");
		lblNumberOfBlocks.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblNumberOfBlocks.setBounds(67, 75, 194, 16);
		AddGameSpecifyDetails.add(lblNumberOfBlocks);
		
		JLabel lblMinimumBallSpeed = new JLabel("Minimum Ball Speed X:");
		lblMinimumBallSpeed.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblMinimumBallSpeed.setBounds(67, 104, 194, 16);
		AddGameSpecifyDetails.add(lblMinimumBallSpeed);
		
		JLabel lblMinimumBallSpeed_1 = new JLabel("Minimum Ball Speed Y:");
		lblMinimumBallSpeed_1.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblMinimumBallSpeed_1.setBounds(67, 135, 194, 16);
		AddGameSpecifyDetails.add(lblMinimumBallSpeed_1);
		
		JLabel lblBallSpeedIncrease = new JLabel("Ball Speed Increase Factor:");
		lblBallSpeedIncrease.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblBallSpeedIncrease.setBounds(67, 165, 194, 16);
		AddGameSpecifyDetails.add(lblBallSpeedIncrease);
		
		JLabel lblMaxPaddleLength = new JLabel("Max Paddle Length");
		lblMaxPaddleLength.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblMaxPaddleLength.setBounds(67, 195, 194, 16);
		AddGameSpecifyDetails.add(lblMaxPaddleLength);
		
		JLabel lblMinimumPaddleLength = new JLabel("Minimum Paddle Length: ");
		lblMinimumPaddleLength.setFont(new Font("Mshtakan", Font.PLAIN, 13));
		lblMinimumPaddleLength.setBounds(67, 225, 194, 16);
		AddGameSpecifyDetails.add(lblMinimumPaddleLength);

		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////

		//Update Game Menu//
		JPanel UpdateGameMenu = new JPanel();
		UpdateGameMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(UpdateGameMenu, "name_44591434156371");
		UpdateGameMenu.setLayout(null);

		txtrNewGameName = new JTextArea();
		txtrNewGameName.setText("New Game Name");
		txtrNewGameName.setBounds(162, 45, 194, 16);
		UpdateGameMenu.add(txtrNewGameName);

		NrLevels = new JTextArea();
		NrLevels.setText("Number of Levels");
		NrLevels.setBounds(162, 75, 194, 16);
		UpdateGameMenu.add(NrLevels);

		JTextArea NrBlocksPerLvl = new JTextArea();
		NrBlocksPerLvl.setText("Number of Blocks Per Level");
		NrBlocksPerLvl.setBounds(162, 105, 194, 16);
		UpdateGameMenu.add(NrBlocksPerLvl);

		JTextArea MinBallSpdX = new JTextArea();
		MinBallSpdX.setText("Minimum ball speed X");
		MinBallSpdX.setBounds(162, 135, 194, 16);
		UpdateGameMenu.add(MinBallSpdX);

		JTextArea MinBallSpdY = new JTextArea();
		MinBallSpdY.setText("Minimum ball speed Y");
		MinBallSpdY.setBounds(162, 165, 194, 16);
		UpdateGameMenu.add(MinBallSpdY);

		JTextArea BallSpdIncFactor = new JTextArea();
		BallSpdIncFactor.setText("Ball speed Increase Factor");
		BallSpdIncFactor.setBounds(162, 195, 194, 16);
		UpdateGameMenu.add(BallSpdIncFactor);

		JTextArea MaxPaddleLngth = new JTextArea();
		MaxPaddleLngth.setText("Max paddle length");
		MaxPaddleLngth.setBounds(162, 225, 194, 16);
		UpdateGameMenu.add(MaxPaddleLngth);

		JTextArea MinPaddleLngth = new JTextArea();
		MinPaddleLngth.setText("Minimum paddle length");
		MinPaddleLngth.setBounds(162, 255, 194, 16);
		UpdateGameMenu.add(MinPaddleLngth);

		JLabel lblUpdateAGame = new JLabel("Update a game!");
		lblUpdateAGame.setBounds(209, 17, 185, 16);
		UpdateGameMenu.add(lblUpdateAGame);


		//Should take all these parameters^ and then update the game using UpdateGame
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(197, 283, 117, 29);
		UpdateGameMenu.add(btnUpdate);

		JButton backUpdateMenu = new JButton("Back");
		backUpdateMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				UpdateGameMenu.setVisible(false);
				GeneralGameMenu.setVisible(true);
			}
		});
		backUpdateMenu.setBounds(6, 6, 100, 29);
		UpdateGameMenu.add(backUpdateMenu);

		blockList = new JComboBox<Integer>();
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Play menu
		//has play button
		JPanel PlayMenu = new JPanel();
		PlayMenu.setLayout(null);
		PlayMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(PlayMenu, "name_138462746885867");

		playableGameList.setBounds(276, 146, 146, 27);
		PlayMenu.add(playableGameList);

		JButton btnPlay = new JButton("PLAY");
		btnPlay.setBounds(160, 63, 214, 47);
		PlayMenu.add(btnPlay);
		
		JLabel playableGamesListLabel = new JLabel("Playable Games");
		playableGamesListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playableGamesListLabel.setBounds(276, 122, 146, 16);
		PlayMenu.add(playableGamesListLabel);
		
		JButton btnSelectPlayableGame = new JButton("Select Game");
		btnSelectPlayableGame.setBounds(276, 183, 146, 29);
		PlayMenu.add(btnSelectPlayableGame);

		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////

		//Edit Block in game
		//We want to have add block, remove block, and update block
		JPanel EditBlockInGame = new JPanel();
		EditBlockInGame.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(EditBlockInGame, "name_45874181750810");
		EditBlockInGame.setLayout(null);

		JLabel lbladdBlock = new JLabel("Add a block:");
		lbladdBlock.setFont(new Font("Mshtakan", Font.BOLD, 18));
		lbladdBlock.setBounds(30, 37, 144, 16);
		EditBlockInGame.add(lbladdBlock);

		txtRedValue = new JTextField();
		txtRedValue.setBounds(55, 65, 90, 26);
		EditBlockInGame.add(txtRedValue);
		txtRedValue.setColumns(10);

		txtGreenValue = new JTextField();
		txtGreenValue.setColumns(10);
		txtGreenValue.setBounds(55, 105, 92, 26);
		EditBlockInGame.add(txtGreenValue);

		txtBlueValue = new JTextField();
		txtBlueValue.setColumns(10);
		txtBlueValue.setBounds(55, 149, 92, 26);
		EditBlockInGame.add(txtBlueValue);

		txtPoints = new JTextField();
		txtPoints.setColumns(10);
		txtPoints.setBounds(55, 187, 92, 26);
		EditBlockInGame.add(txtPoints);

		JLabel lblSelectABlock = new JLabel("Select a Block");
		lblSelectABlock.setFont(new Font("Mshtakan", Font.BOLD, 18));
		lblSelectABlock.setBounds(229, 37, 126, 16);
		EditBlockInGame.add(lblSelectABlock);

		JLabel lblBlockId = new JLabel("ID:");
		lblBlockId.setBounds(229, 70, 27, 16);
		EditBlockInGame.add(lblBlockId);


		//Call delete block on the id from the spinner.
		JButton btnDeleteBlock = new JButton("Delete Block");
		btnDeleteBlock.setBounds(360, 65, 103, 29);
		EditBlockInGame.add(btnDeleteBlock);

		txtRedEdit = new JTextField();
		txtRedEdit.setColumns(10);
		txtRedEdit.setBounds(275, 91, 80, 26);
		EditBlockInGame.add(txtRedEdit);

		txtGreenEdit = new JTextField();
		txtGreenEdit.setColumns(10);
		txtGreenEdit.setBounds(275, 120, 80, 26);
		EditBlockInGame.add(txtGreenEdit);

		txtBlueEdit = new JTextField();
		txtBlueEdit.setColumns(10);
		txtBlueEdit.setBounds(275, 149, 80, 26);
		EditBlockInGame.add(txtBlueEdit);

		txtPointsEdit = new JTextField();
		txtPointsEdit.setColumns(10);
		txtPointsEdit.setBounds(275, 187, 80, 26);
		EditBlockInGame.add(txtPointsEdit);
		JButton btnUpdateBlock = new JButton("Update Block");

		btnUpdateBlock.setBounds(229, 221, 130, 29);
		EditBlockInGame.add(btnUpdateBlock);

		JButton btnAddBlock = new JButton("Add Block");

		btnAddBlock.setBounds(17, 221, 128, 39);
		EditBlockInGame.add(btnAddBlock);

		blockList.setBounds(275, 66, 79, 27);
		EditBlockInGame.add(blockList);


		JButton backEditBlock = new JButton("Back");
		backEditBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				EditBlockInGame.setVisible(false);
				GeneralGameMenu.setVisible(true);
			}
		});
		backEditBlock.setBounds(6, 6, 100, 29);
		EditBlockInGame.add(backEditBlock);

		JLabel lblRed = new JLabel("Red:");
		lblRed.setForeground(new Color(255, 0, 0));
		lblRed.setBounds(17, 70, 27, 16);
		EditBlockInGame.add(lblRed);

		JLabel lblGreen = new JLabel("Green:");
		lblGreen.setForeground(new Color(0, 128, 0));
		lblGreen.setBounds(17, 110, 61, 16);
		EditBlockInGame.add(lblGreen);

		JLabel lblBlue = new JLabel("Blue:");
		lblBlue.setForeground(new Color(0, 0, 255));
		lblBlue.setBounds(17, 154, 41, 16);
		EditBlockInGame.add(lblBlue);

		JLabel lblPts = new JLabel("Pts:");
		lblPts.setBounds(17, 192, 34, 16);
		EditBlockInGame.add(lblPts);

		JLabel label = new JLabel("Red:");
		label.setForeground(Color.RED);
		label.setBounds(229, 98, 27, 16);
		EditBlockInGame.add(label);

		JLabel label_1 = new JLabel("Green:");
		label_1.setForeground(new Color(0, 128, 0));
		label_1.setBounds(229, 125, 61, 16);
		EditBlockInGame.add(label_1);

		JLabel label_2 = new JLabel("Blue:");
		label_2.setForeground(Color.BLUE);
		label_2.setBounds(229, 154, 41, 16);
		EditBlockInGame.add(label_2);

		JLabel label_3 = new JLabel("Pts:");
		label_3.setBounds(229, 192, 34, 16);
		EditBlockInGame.add(label_3);

		//Delete Block
		btnDeleteBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.deleteBlock(Integer.valueOf(blockList.getSelectedItem().toString()));
				} catch (NumberFormatException e1) {
					error+=e1.getMessage();
				} catch (InvalidInputException e1) {
					error+=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					EditBlockInGame.setVisible(false);
					AddEditGameMenu.setVisible(true);
				}
			}
		});

		//EditBlock
		btnUpdateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.updateBlock(Integer.valueOf(blockList.getSelectedItem().toString()), Integer.valueOf(txtRedValue.getText()), Integer.valueOf(txtGreenValue.getText()),
							Integer.valueOf(txtBlueValue.getText()), Integer.valueOf(txtPoints.getText()));
				} catch (NumberFormatException e1) {
					error+=e1.getMessage();
				} catch (InvalidInputException e1) {
					error+=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					EditBlockInGame.setVisible(false);
					AddEditGameMenu.setVisible(true);
				}
			}
		});

		//Add a block.
		btnAddBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.addBlock(Integer.valueOf(txtRedValue.getText()), Integer.valueOf(txtGreenValue.getText()),
							Integer.valueOf(txtBlueValue.getText()), Integer.valueOf(txtPoints.getText()));
				} catch (NumberFormatException e1) {
					error+= e1.getMessage();
				} catch (InvalidInputException e1) {
					error+= e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					EditBlockInGame.setVisible(false);
					AddEditGameMenu.setVisible(true);
				}
			}
		});
		/////////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////////
		//Register Menu
		JPanel RegisterMenu = new JPanel();
		RegisterMenu.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(RegisterMenu, "name_46244369728357");
		RegisterMenu.setLayout(null);

		JTextArea txtrNewUsername = new JTextArea();
		txtrNewUsername.setBounds(207, 90, 105, 16);
		txtrNewUsername.setText("");
		RegisterMenu.add(txtrNewUsername);

		JTextArea txtrNewPassword = new JTextArea();
		txtrNewPassword.setBounds(207, 120, 105, 16);
		RegisterMenu.add(txtrNewPassword);

		JTextArea txtrAdminPassword = new JTextArea();
		txtrAdminPassword.setBounds(207, 150, 105, 16);
		RegisterMenu.add(txtrAdminPassword);

		// Clicking this button should call register, using the username/normal pass/admin pass
		JButton btnRegistration = new JButton("Register");
		btnRegistration.setBounds(207, 180, 105, 29);
		RegisterMenu.add(btnRegistration);

		JLabel lblWelcomeToBlock = new JLabel("Welcome to Block 223!");
		lblWelcomeToBlock.setFont(new Font("PT Serif", Font.BOLD | Font.ITALIC, 26));
		lblWelcomeToBlock.setBounds(124, 26, 303, 41);
		RegisterMenu.add(lblWelcomeToBlock);

		JButton backRegisterMenu = new JButton("Back");
		backRegisterMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				RegisterMenu.setVisible(false);
				loginPanel.setVisible(true);
			}
		});
		backRegisterMenu.setBounds(6, 6, 100, 29);
		RegisterMenu.add(backRegisterMenu);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Mshtakan", Font.PLAIN, 15));
		lblUsername.setBounds(72, 90, 121, 16);
		RegisterMenu.add(lblUsername);

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Mshtakan", Font.PLAIN, 15));
		lblNewPassword.setBounds(72, 120, 101, 16);
		RegisterMenu.add(lblNewPassword);

		JLabel lblNewLabel_1 = new JLabel("Admin Password:");
		lblNewLabel_1.setFont(new Font("Mshtakan", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(72, 150, 123, 16);
		RegisterMenu.add(lblNewLabel_1);
		/////////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////////	

		//Edit block within level Menu
		JPanel EditBlockWithinLevel = new JPanel();
		EditBlockWithinLevel.setBackground(new Color(255, 228, 225));
		frame.getContentPane().add(EditBlockWithinLevel, "name_48329674973804");
		EditBlockWithinLevel.setLayout(null);

		txtLevel = new JTextField();
		txtLevel.setBounds(459, 22, 27, 26);
		txtLevel.setText("1");
		EditBlockWithinLevel.add(txtLevel);
		txtLevel.setColumns(10);

		JLabel lblBlockId_1 = new JLabel("ID:");
		lblBlockId_1.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblBlockId_1.setBounds(40, 54, 61, 16);
		EditBlockWithinLevel.add(lblBlockId_1);

		txtHorizontalGridPosition = new JTextField();
		txtHorizontalGridPosition.setBounds(116, 74, 78, 26);
		EditBlockWithinLevel.add(txtHorizontalGridPosition);
		txtHorizontalGridPosition.setColumns(10);

		txtVerticalGridPosition = new JTextField();
		txtVerticalGridPosition.setColumns(10);
		txtVerticalGridPosition.setBounds(116, 99, 78, 26);
		EditBlockWithinLevel.add(txtVerticalGridPosition);

		JButton btnPositionBlock = new JButton("Position Block");


		btnPositionBlock.setBounds(56, 123, 117, 29);
		EditBlockWithinLevel.add(btnPositionBlock);

		txtCurrentHorizontalGrid = new JTextField();
		txtCurrentHorizontalGrid.setColumns(10);
		txtCurrentHorizontalGrid.setBounds(421, 74, 94, 26);
		EditBlockWithinLevel.add(txtCurrentHorizontalGrid);

		txtCurrentVerticalGrid = new JTextField();
		txtCurrentVerticalGrid.setColumns(10);
		txtCurrentVerticalGrid.setBounds(421, 99, 94, 26);
		EditBlockWithinLevel.add(txtCurrentVerticalGrid);

		txtNewHorizontalGrid = new JTextField();
		txtNewHorizontalGrid.setColumns(10);
		txtNewHorizontalGrid.setBounds(421, 123, 94, 26);
		EditBlockWithinLevel.add(txtNewHorizontalGrid);

		txtNewVerticalGrid = new JTextField();
		txtNewVerticalGrid.setColumns(10);
		txtNewVerticalGrid.setBounds(421, 147, 94, 26);
		EditBlockWithinLevel.add(txtNewVerticalGrid);

		JButton btnMoveBlock = new JButton("Move Block");
		btnMoveBlock.setBounds(347, 172, 117, 29);
		EditBlockWithinLevel.add(btnMoveBlock);

		txtHorGridPos = new JTextField();
		txtHorGridPos.setColumns(10);
		txtHorGridPos.setBounds(116, 193, 78, 26);
		EditBlockWithinLevel.add(txtHorGridPos);

		txtVerGridPos = new JTextField();
		txtVerGridPos.setColumns(10);
		txtVerGridPos.setBounds(116, 217, 78, 26);
		EditBlockWithinLevel.add(txtVerGridPos);

		JButton btnRemoveBlock= new JButton("Remove Block");

		btnRemoveBlock.setBounds(56, 248, 117, 29);
		EditBlockWithinLevel.add(btnRemoveBlock);

		JLabel lblBlockEditor = new JLabel("Block Editor");
		lblBlockEditor.setFont(new Font("Mshtakan", Font.BOLD | Font.ITALIC, 27));
		lblBlockEditor.setBounds(168, 6, 193, 32);
		EditBlockWithinLevel.add(lblBlockEditor);

		blockList2 = new JComboBox<Integer>();
		blockList2.setBounds(108, 50, 86, 27);
		EditBlockWithinLevel.add(blockList2);

		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(418, 27, 61, 16);
		EditBlockWithinLevel.add(lblLevel);

		JButton backEditBlockInLvl = new JButton("Back");
		backEditBlockInLvl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				EditBlockWithinLevel.setVisible(false);
				GeneralGameMenu.setVisible(true);
			}
		});
		backEditBlockInLvl.setBounds(6, 6, 100, 29);
		EditBlockWithinLevel.add(backEditBlockInLvl);
		
		JLabel lblMoveABlock = new JLabel("Move a Block:");
		lblMoveABlock.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblMoveABlock.setBounds(347, 54, 133, 16);
		EditBlockWithinLevel.add(lblMoveABlock);
		
		JLabel lblPositionABlock = new JLabel("Position a Block:");
		lblPositionABlock.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblPositionABlock.setBounds(40, 32, 133, 16);
		EditBlockWithinLevel.add(lblPositionABlock);
		
		JLabel lblCurrXPosition = new JLabel("Curr X Position:");
		lblCurrXPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblCurrXPosition.setBounds(296, 79, 117, 16);
		EditBlockWithinLevel.add(lblCurrXPosition);
		
		JLabel lblCurrYPosition = new JLabel("Curr Y Position:");
		lblCurrYPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblCurrYPosition.setBounds(296, 104, 133, 16);
		EditBlockWithinLevel.add(lblCurrYPosition);
		
		JLabel lblNewXPosition = new JLabel("New X Position:");
		lblNewXPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblNewXPosition.setBounds(296, 127, 133, 16);
		EditBlockWithinLevel.add(lblNewXPosition);
		
		JLabel lblNewYPosition = new JLabel("New Y Position:");
		lblNewYPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblNewYPosition.setBounds(296, 152, 133, 16);
		EditBlockWithinLevel.add(lblNewYPosition);
		
		JLabel lblRemoveABlock = new JLabel("Remove a Block:");
		lblRemoveABlock.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblRemoveABlock.setBounds(40, 168, 133, 16);
		EditBlockWithinLevel.add(lblRemoveABlock);
		
		JLabel lblXPosition = new JLabel("X Position:");
		lblXPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblXPosition.setBounds(40, 82, 117, 16);
		EditBlockWithinLevel.add(lblXPosition);
		
		JLabel lblYPosition = new JLabel("Y Position:");
		lblYPosition.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		lblYPosition.setBounds(40, 104, 84, 16);
		EditBlockWithinLevel.add(lblYPosition);
		
		JLabel label_5 = new JLabel("X Position:");
		label_5.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		label_5.setBounds(40, 198, 117, 16);
		EditBlockWithinLevel.add(label_5);
		
		JLabel label_6 = new JLabel("Y Position:");
		label_6.setFont(new Font("Mshtakan", Font.PLAIN, 16));
		label_6.setBounds(40, 222, 84, 16);
		EditBlockWithinLevel.add(label_6);
		

		////////////////////////////////////////////////////////////////////////////////
		//ALL BUTTONS:

		//To register:
		btnRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.register(txtrNewUsername.getText(), txtrNewPassword.getText(), txtrAdminPassword.getText());
				} catch (InvalidInputException e1) {
					error+=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					RegisterMenu.setVisible(false);
					loginPanel.setVisible(true);
				}
			}
		});

		//When you click the login button, this happens.
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = null;
				try {
					Block223Controller.login(txtrUsername.getText(),txtrPassword.getText());
				}	catch (InvalidInputException err) {
					error = err.getMessage();
				}
				refreshData();
				txtrUsername.setText("");
				txtrPassword.setText("");
				if (error == null || error.length() == 0) {
					if(Block223Application.getCurrentUserRole() instanceof Admin) {
						loginPanel.setVisible(false);
						mainMenu.setVisible(true);

					}
					else {
						loginPanel.setVisible(false);
						PlayMenu.setVisible(true);

					}
				}
			}
		});
		//Bring us to the play paeg:
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText("");
				GeneralGameMenu.setVisible(false);
				Block223PlayModeView bpmv = new Block223PlayModeView();
			}
		});

		//Bring us to the registration page:
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				loginPanel.setVisible(false);
				RegisterMenu.setVisible(true);
			}
		});

		//When you click the Edit game button, this happens
		btnAddEditGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				mainMenu.setVisible(false);
				AddEditGameMenu.setVisible(true);
			}
		});

		//When you click the Logout button, this happens
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Block223Controller.logout();
				Block223Application.resetBlock223();
				error = "";
				errorMessage.setText(error);
				mainMenu.setVisible(false);
				loginPanel.setVisible(true);
			}
		});

		//Button to define a game
		btnDefine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.setGameDetails(Integer.valueOf(txtrNumberOfLevels.getText()), Integer.valueOf(txtrNumBlocksPerLvl.getText()), 
							Integer.valueOf(txtrMinBallSpdX.getText()), Integer.valueOf(txtrMinimumBallSpeedY.getText()), Double.valueOf(txtrBallSpeedIncrease.getText())
							, Integer.valueOf(txtrMaxPaddleLength.getText()), Integer.valueOf(txtrMinimumPaddleLength.getText()));

				} catch (NumberFormatException e1) {
					// 
					error+=e1.getMessage();
				} catch (InvalidInputException e1) {
					error+=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					AddGameSpecifyDetails.setVisible(false);
					GeneralGameMenu.setVisible(true);
				}
			}
		});

		//Update a game
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.updateGame(txtrNewGameName.getText(),Integer.valueOf(NrLevels.getText()), Integer.valueOf(NrBlocksPerLvl.getText()), 
							Integer.valueOf(MinBallSpdX.getText()), Integer.valueOf(MinBallSpdY.getText()), Double.valueOf(BallSpdIncFactor.getText())
							, Integer.valueOf(MaxPaddleLngth.getText()), Integer.valueOf(MinPaddleLngth.getText()));
				} catch (NumberFormatException e1) {
					error+=e1.getMessage();
				} catch (InvalidInputException e1) {
					error+=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					UpdateGameMenu.setVisible(false);
					GeneralGameMenu.setVisible(true);
				}
			}
		});


		//When you click "create game", this happens
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.createGame(NewGameName.getText());
				} catch (InvalidInputException e1) {
					error=e1.getMessage();
				} catch (RuntimeException e1) {
					error=e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					error = "";
					try {
						Block223Controller.selectGame(NewGameName.getText());
					} catch (InvalidInputException e1) {
						error=e1.getMessage();
					}
				}
				refreshData();
				if (error.length() == 0) {
					AddEditGameMenu.setVisible(false);
					AddGameSpecifyDetails.setVisible(true);
				}
			}
		});

		//When you click select game, this happens
		btnSelectGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.selectGame(gameList.getSelectedItem().toString());
				} catch (InvalidInputException e1) {
					error+= e1.getMessage();
				}
				refreshData();
				//If this went in smoothly:
				if (error.length() == 0) {
					AddEditGameMenu.setVisible(false);
					GeneralGameMenu.setVisible(true);
				}
			}
		});
		//select playable game button
		btnSelectPlayableGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {//idk how to use id so we'll go by name
					Block223Controller.selectPlayableGame(playableGameList.getSelectedItem().toString(), -1);
				} catch (InvalidInputException e1) {
					error+= e1.getMessage();
				}
				refreshData();
			}
		});

		//Travel to the edit block within a level menu.
		btnEditBlocksWithin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				GeneralGameMenu.setVisible(false);
				EditBlockWithinLevel.setVisible(true);
			}
		});

		//Travel to the update game menu
		btnUpdateGameDtails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				GeneralGameMenu.setVisible(false);
				UpdateGameMenu.setVisible(true);
			}
		});

		//Travel to the edit block within a game menu.
		btnChangeBlocksInGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				errorMessage.setText(error);
				GeneralGameMenu.setVisible(false);
				EditBlockInGame.setVisible(true);
			}
		});
		//Delete a game
		btnDeleteGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.deleteGame(gameList.getSelectedItem().toString());
				} catch (InvalidInputException e1) {
					error += e1.getMessage();
				}
				refreshData();
			}
		});

		//Save changes made during the session.
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.saveGame();
				} catch (InvalidInputException e1) {
					error+= e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					GeneralGameMenu.setVisible(false);
					mainMenu.setVisible(true);
				}
			}
		});
		//publish a game
		btnPublishGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.publishGame();
					Block223Controller.saveGame();

				} catch (InvalidInputException e1) {
					error+= e1.getMessage();
				}
				refreshData();
				if (error.length() == 0) {
					GeneralGameMenu.setVisible(false);
					mainMenu.setVisible(true);
				}
			}
		});

		//Position a block
		btnPositionBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.positionBlock(Integer.valueOf(blockList2.getSelectedItem().toString()), Integer.valueOf(txtLevel.getText()),
							Integer.valueOf(txtHorizontalGridPosition.getText()), Integer.valueOf(txtVerticalGridPosition.getText()));
				} catch (NumberFormatException e1) {
					error = e1.getMessage();
				} catch (InvalidInputException e1) {
					error = e1.getMessage();
				}
				refreshData();
			}
		});

		//Move a block
		btnMoveBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.moveBlock(Integer.valueOf(txtLevel.getText()), 
							Integer.valueOf(txtCurrentHorizontalGrid.getText()), 
							Integer.valueOf(txtCurrentVerticalGrid.getText()), 
							Integer.valueOf(txtNewHorizontalGrid.getText()), 
							Integer.valueOf(txtNewVerticalGrid.getText()));
				} catch (NumberFormatException e1) {
					error += e1.getMessage();
				} catch (InvalidInputException e1) {
					error += e1.getMessage();
				}
				refreshData();
			}
		});

		//Remove a block
		btnRemoveBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					Block223Controller.removeBlock(Integer.valueOf(txtLevel.getText()), Integer.valueOf(txtHorGridPos.getText()), Integer.valueOf(txtVerGridPos.getText()));
				} catch (NumberFormatException e1) {
					error += e1.getMessage();
				} catch (InvalidInputException e1) {
					error += e1.getMessage();
				}
				refreshData();
			}
		});
	}

	protected void refreshData() {
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			System.out.println("User mode: " + Block223Controller.getUserMode().getMode().toString());
			//games list
			if (Block223Controller.getUserMode().getMode() == TOUserMode.Mode.Design){

				gameList.removeAllItems();
				try {
					for (TOGame game : Block223Controller.getDesignableGames()) {
						System.out.println(game.getName());
						gameList.addItem(game.getName());
					}
				} catch (InvalidInputException e) {
					e.printStackTrace();
				};

				gameList.setSelectedIndex(-1);

				blockList.removeAllItems();
				//Make the exact same list twice, to be used in two different parents.
				blockList2.removeAllItems();

				if (Block223Application.getCurrentGame() != null) {
					try {
						for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
							blockList.addItem(block.getId());
						}
					} catch (InvalidInputException e) {
						e.printStackTrace();
					};
					blockList.setSelectedIndex(-1);


					try {
						for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
							blockList2.addItem(block.getId());
						}
					} catch (InvalidInputException e) {
						e.printStackTrace();
					};
					blockList2.setSelectedIndex(-1);
				}
			}
			//playable games list
			if (Block223Controller.getUserMode().getMode() == TOUserMode.Mode.Play){
				playableGameList.removeAllItems();
				try {
					for (TOPlayableGame playableGame : Block223Controller.getPlayableGames()) {
						playableGameList.addItem(playableGame.getName());
					}
				} catch (InvalidInputException e) {
					e.printStackTrace();
				}
				gameList.setSelectedIndex(-1);


			}

		}

	}
}




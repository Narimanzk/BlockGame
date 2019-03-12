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
import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;
import ca.mcgill.ecse223.block.controller.TOGame;
import ca.mcgill.ecse223.block.controller.TOUserMode;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class Block223Page{
	public JFrame frame;
	// UI elements
	private JTextArea txtrNewGameName;
	private JTextArea NrLevels;
	private JComboBox<String> gameList;
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

		blockList = new JComboBox<Integer>();
		blockList2 = new JComboBox<Integer>();
		gameList = new JComboBox<String>();
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setTitle("Block 223");
		frame.getContentPane().add(errorMessage);

		/////////////////////////////////////////////////////////////////
		//Login page:
		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel, "name_26471782456177");

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

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(222, 221, 79, 29);
		loginPanel.setLayout(null);
		loginPanel.add(btnLogin);

		//Should bring us to the register page.
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(222, 264, 79, 23);
		loginPanel.add(btnRegister);

		loginPanel.setVisible(true);

		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Main Menu page
		JPanel mainMenu = new JPanel();

		frame.getContentPane().add(mainMenu, "name_33113224405399");

		//////Add/Edit game button////////////
		JButton btnAddEditGame = new JButton("Add/Edit Game");
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
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////
		//Add/Edit Game page:
		JPanel AddEditGameMenu = new JPanel();
		frame.getContentPane().add(AddEditGameMenu, "name_34257085377978");
		AddEditGameMenu.setLayout(null);

		//Text field for the game name.
		JTextArea NewGameName = new JTextArea();
		NewGameName.setText("Enter Game Name");
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
		JButton SelectGame = new JButton("Select Game");
		SelectGame.setBounds(266, 178, 157, 29);
		AddEditGameMenu.add(SelectGame);

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
		backAEGamePage.setBounds(6, 6, 60, 29);
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
		frame.getContentPane().add(GeneralGameMenu, "name_46221789659840");
		GeneralGameMenu.setLayout(null);
		errorMessage.setVisible(true);

		JButton btnUpdateGameDtails = new JButton("Update Game");
		btnUpdateGameDtails.setBounds(160, 70, 214, 29);
		GeneralGameMenu.add(btnUpdateGameDtails);

		JButton btnChangeBlocksInGame = new JButton("Add/Delete/Update a Block");


		btnChangeBlocksInGame.setBounds(160, 100, 214, 29);
		GeneralGameMenu.add(btnChangeBlocksInGame);


		//Edit blocks in a level. 
		//This will bring to a new menu, that will allow us to select level, and then position,move,remove blocks.
		JButton btnEditBlocksWithin = new JButton("Edit Blocks Within a Level");
		btnEditBlocksWithin.setBounds(160, 130, 214, 29);
		GeneralGameMenu.add(btnEditBlocksWithin);


		//Save Changes ( Call the save method)
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(160, 160, 214, 29);
		GeneralGameMenu.add(btnSaveChanges);


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

		JTextArea txtrMinBallSpdX = new JTextArea();
		txtrMinBallSpdX.setText("Minimum ball speed X");
		txtrMinBallSpdX.setBounds(162, 104, 194, 16);
		AddGameSpecifyDetails.add(txtrMinBallSpdX);

		JTextArea txtrMinimumBallSpeedY = new JTextArea();
		txtrMinimumBallSpeedY.setText("Minimum ball speed Y");
		txtrMinimumBallSpeedY.setBounds(162, 135, 194, 16);
		AddGameSpecifyDetails.add(txtrMinimumBallSpeedY);

		JTextArea txtrBallSpeedIncrease = new JTextArea();
		txtrBallSpeedIncrease.setText("Ball speed Increase Factor");
		txtrBallSpeedIncrease.setBounds(162, 165, 194, 16);
		AddGameSpecifyDetails.add(txtrBallSpeedIncrease);

		JTextArea txtrMaxPaddleLength = new JTextArea();
		txtrMaxPaddleLength.setText("Max paddle length");
		txtrMaxPaddleLength.setBounds(162, 195, 194, 16);
		AddGameSpecifyDetails.add(txtrMaxPaddleLength);

		JTextArea txtrMinimumPaddleLength = new JTextArea();
		txtrMinimumPaddleLength.setText("Minimum paddle length");
		txtrMinimumPaddleLength.setBounds(162, 225, 194, 16);
		AddGameSpecifyDetails.add(txtrMinimumPaddleLength);

		JLabel lblDefineAGame = new JLabel("Define a game!");
		lblDefineAGame.setBounds(209, 17, 185, 16);
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
		backSpecDetailsPage.setBounds(6, 6, 60, 29);
		AddGameSpecifyDetails.add(backSpecDetailsPage);
		//First need to call selectGame with the game name
		//Should take all these guys^ as parameters, and then call tudor's method:  setGameDetails
		JButton btnDefine = new JButton("Define");
		btnDefine.setBounds(197, 248, 117, 29);
		AddGameSpecifyDetails.add(btnDefine);

		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////

		//Update Game Menu//
		JPanel UpdateGameMenu = new JPanel();
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
		backUpdateMenu.setBounds(6, 6, 60, 29);
		UpdateGameMenu.add(backUpdateMenu);
		/////////////////////////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////

		//Edit Block in game
		//We want to have add block, remove block, and update block
		JPanel EditBlockInGame = new JPanel();
		frame.getContentPane().add(EditBlockInGame, "name_45874181750810");
		EditBlockInGame.setLayout(null);

		JLabel lbladdBlock = new JLabel("Add a block:");
		lbladdBlock.setBounds(30, 37, 144, 16);
		EditBlockInGame.add(lbladdBlock);

		txtRedValue = new JTextField();
		txtRedValue.setText("Red Value");
		txtRedValue.setBounds(17, 65, 130, 26);
		EditBlockInGame.add(txtRedValue);
		txtRedValue.setColumns(10);

		txtGreenValue = new JTextField();
		txtGreenValue.setText("Green Value");
		txtGreenValue.setColumns(10);
		txtGreenValue.setBounds(17, 105, 130, 26);
		EditBlockInGame.add(txtGreenValue);

		txtBlueValue = new JTextField();
		txtBlueValue.setText("Blue Value");
		txtBlueValue.setColumns(10);
		txtBlueValue.setBounds(17, 149, 130, 26);
		EditBlockInGame.add(txtBlueValue);

		txtPoints = new JTextField();
		txtPoints.setText("Points");
		txtPoints.setColumns(10);
		txtPoints.setBounds(17, 187, 130, 26);
		EditBlockInGame.add(txtPoints);

		JLabel lblSelectABlock = new JLabel("Select a Block");
		lblSelectABlock.setBounds(254, 37, 101, 16);
		EditBlockInGame.add(lblSelectABlock);

		JLabel lblBlockId = new JLabel("Block ID:");
		lblBlockId.setBounds(229, 70, 61, 16);
		EditBlockInGame.add(lblBlockId);


		//Call delete block on the id from the spinner.
		JButton btnDeleteBlock = new JButton("Delete Block");
		btnDeleteBlock.setBounds(360, 65, 103, 29);
		EditBlockInGame.add(btnDeleteBlock);

		txtRedEdit = new JTextField();
		txtRedEdit.setText("Red Value");
		txtRedEdit.setColumns(10);
		txtRedEdit.setBounds(225, 91, 130, 26);
		EditBlockInGame.add(txtRedEdit);

		txtGreenEdit = new JTextField();
		txtGreenEdit.setText("Green Value");
		txtGreenEdit.setColumns(10);
		txtGreenEdit.setBounds(225, 120, 130, 26);
		EditBlockInGame.add(txtGreenEdit);

		txtBlueEdit = new JTextField();
		txtBlueEdit.setText("Blue Value");
		txtBlueEdit.setColumns(10);
		txtBlueEdit.setBounds(225, 149, 130, 26);
		EditBlockInGame.add(txtBlueEdit);

		txtPointsEdit = new JTextField();
		txtPointsEdit.setText("Points");
		txtPointsEdit.setColumns(10);
		txtPointsEdit.setBounds(225, 187, 130, 26);
		EditBlockInGame.add(txtPointsEdit);
		JButton btnUpdateBlock = new JButton("Update Block");

		btnUpdateBlock.setBounds(229, 221, 103, 29);
		EditBlockInGame.add(btnUpdateBlock);

		JButton btnAddBlock = new JButton("Add Block");

		btnAddBlock.setBounds(17, 221, 117, 29);
		EditBlockInGame.add(btnAddBlock);

		blockList.setBounds(293, 66, 61, 27);
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
		backEditBlock.setBounds(6, 6, 60, 29);
		EditBlockInGame.add(backEditBlock);
		/////////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////////
		//Register Menu
		JPanel RegisterMenu = new JPanel();
		frame.getContentPane().add(RegisterMenu, "name_46244369728357");
		RegisterMenu.setLayout(null);

		JTextArea txtrNewUsername = new JTextArea();
		txtrNewUsername.setBounds(207, 90, 105, 16);
		txtrNewUsername.setText("New Username");
		RegisterMenu.add(txtrNewUsername);

		JTextArea txtrNewPassword = new JTextArea();
		txtrNewPassword.setBounds(207, 120, 105, 16);
		txtrNewPassword.setText("New Password");
		RegisterMenu.add(txtrNewPassword);

		JTextArea txtrAdminPassword = new JTextArea();
		txtrAdminPassword.setText("Admin Password");
		txtrAdminPassword.setBounds(207, 150, 105, 16);
		RegisterMenu.add(txtrAdminPassword);

		// Clicking this button should call register, using the username/normal pass/admin pass
		JButton btnRegistration = new JButton("Register");
		btnRegistration.setBounds(207, 180, 105, 29);
		RegisterMenu.add(btnRegistration);

		JLabel lblWelcomeToBlock = new JLabel("Welcome to Block 223!");
		lblWelcomeToBlock.setBounds(191, 50, 204, 16);
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
		backRegisterMenu.setBounds(6, 6, 60, 29);
		RegisterMenu.add(backRegisterMenu);
		/////////////////////////////////////////////////////////////////


		/////////////////////////////////////////////////////////////////	

		//Edit block within level Menu
		JPanel EditBlockWithinLevel = new JPanel();
		frame.getContentPane().add(EditBlockWithinLevel, "name_48329674973804");
		EditBlockWithinLevel.setLayout(null);

		txtLevel = new JTextField();
		txtLevel.setBounds(459, 22, 27, 26);
		txtLevel.setText("1");
		EditBlockWithinLevel.add(txtLevel);
		txtLevel.setColumns(10);

		JLabel lblPositionABlock = new JLabel("Position a Block");
		lblPositionABlock.setBounds(30, 32, 158, 16);
		EditBlockWithinLevel.add(lblPositionABlock);

		JLabel lblBlockId_1 = new JLabel("Block ID:");
		lblBlockId_1.setBounds(30, 54, 61, 16);
		EditBlockWithinLevel.add(lblBlockId_1);

		txtHorizontalGridPosition = new JTextField();
		txtHorizontalGridPosition.setText("Horizontal Grid Position");
		txtHorizontalGridPosition.setBounds(20, 74, 174, 26);
		EditBlockWithinLevel.add(txtHorizontalGridPosition);
		txtHorizontalGridPosition.setColumns(10);

		txtVerticalGridPosition = new JTextField();
		txtVerticalGridPosition.setText("Vertical Grid Position");
		txtVerticalGridPosition.setColumns(10);
		txtVerticalGridPosition.setBounds(20, 99, 174, 26);
		EditBlockWithinLevel.add(txtVerticalGridPosition);

		JButton btnPositionBlock = new JButton("Position Block");


		btnPositionBlock.setBounds(30, 127, 117, 29);
		EditBlockWithinLevel.add(btnPositionBlock);

		JLabel lblMoveBlock = new JLabel("Move a Block");
		lblMoveBlock.setBounds(212, 54, 86, 16);
		EditBlockWithinLevel.add(lblMoveBlock);

		txtCurrentHorizontalGrid = new JTextField();
		txtCurrentHorizontalGrid.setText("Current Horizontal Grid Position");
		txtCurrentHorizontalGrid.setColumns(10);
		txtCurrentHorizontalGrid.setBounds(206, 74, 219, 26);
		EditBlockWithinLevel.add(txtCurrentHorizontalGrid);

		txtCurrentVerticalGrid = new JTextField();
		txtCurrentVerticalGrid.setText("Current Vertical Grid Position");
		txtCurrentVerticalGrid.setColumns(10);
		txtCurrentVerticalGrid.setBounds(206, 99, 219, 26);
		EditBlockWithinLevel.add(txtCurrentVerticalGrid);

		txtNewHorizontalGrid = new JTextField();
		txtNewHorizontalGrid.setText("New Horizontal Grid Position");
		txtNewHorizontalGrid.setColumns(10);
		txtNewHorizontalGrid.setBounds(206, 123, 219, 26);
		EditBlockWithinLevel.add(txtNewHorizontalGrid);

		txtNewVerticalGrid = new JTextField();
		txtNewVerticalGrid.setText("New Vertical Grid Position");
		txtNewVerticalGrid.setColumns(10);
		txtNewVerticalGrid.setBounds(206, 147, 219, 26);
		EditBlockWithinLevel.add(txtNewVerticalGrid);

		JButton btnMoveBlock = new JButton("Move Block");
		btnMoveBlock.setBounds(251, 172, 117, 29);
		EditBlockWithinLevel.add(btnMoveBlock);

		JLabel lblDeleteABlock = new JLabel("Remove a Block");
		lblDeleteABlock.setBounds(30, 177, 158, 16);
		EditBlockWithinLevel.add(lblDeleteABlock);

		txtHorGridPos = new JTextField();
		txtHorGridPos.setText("Horizontal Grid Position");
		txtHorGridPos.setColumns(10);
		txtHorGridPos.setBounds(20, 193, 174, 26);
		EditBlockWithinLevel.add(txtHorGridPos);

		txtVerGridPos = new JTextField();
		txtVerGridPos.setText("Vertical Grid Position");
		txtVerGridPos.setColumns(10);
		txtVerGridPos.setBounds(20, 217, 174, 26);
		EditBlockWithinLevel.add(txtVerGridPos);

		JButton btnRemoveBlock= new JButton("Remove Block");

		btnRemoveBlock.setBounds(30, 247, 117, 29);
		EditBlockWithinLevel.add(btnRemoveBlock);

		JLabel lblBlockEditor = new JLabel("Block Editor");
		lblBlockEditor.setBounds(168, 6, 143, 32);
		EditBlockWithinLevel.add(lblBlockEditor);

		JFormattedTextField frmtdtxtfldMask = new JFormattedTextField();
		frmtdtxtfldMask.setText("Mask");
		frmtdtxtfldMask.setBounds(290, 248, 100, 26);
		EditBlockWithinLevel.add(frmtdtxtfldMask);

		blockList2 = new JComboBox<Integer>();
		blockList2.setBounds(95, 50, 86, 27);
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
		backEditBlockInLvl.setBounds(6, 6, 60, 29);
		EditBlockWithinLevel.add(backEditBlockInLvl);

		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, "name_63047693751053");
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
					loginPanel.setVisible(false);
					mainMenu.setVisible(true);
				}
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
		SelectGame.addActionListener(new ActionListener() {
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
			System.out.println(Block223Controller.getUserMode().getMode().toString());
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
		}

	}
}




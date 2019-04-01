package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.HallOfFameEntry;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.PlayedBlockAssignment;
import ca.mcgill.ecse223.block.model.PlayedGame;
import ca.mcgill.ecse223.block.model.PlayedGame.PlayStatus;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
/*
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.BlockAssignment;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Player;
import ca.mcgill.ecse223.block.model.User;
import ca.mcgill.ecse223.block.model.UserRole;
 */
import ca.mcgill.ecse223.block.persistence.Block223Persistence;
import ca.mcgill.ecse223.block.view.Block223PlayModeInterface;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole userRole = Block223Application.getCurrentUserRole();
		Game aGame = findGame(name);
		if (name == null || name.length() == 0)
			throw new InvalidInputException("The name of a game must be specified.".trim());

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to create a game.".trim());

		if (aGame != null)
			throw new InvalidInputException("The name of a game must be unique.".trim());

		Game game = new Game(name, 1, (Admin) userRole, 1, 1, 1, 10, 10, block223);
		block223.addGame(game);
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole userRole = Block223Application.getCurrentUserRole();
		Game aGame = Block223Application.getCurrentGame();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to define game settings.".trim());
		if (aGame == null)
			throw new InvalidInputException("A game must be selected to define game settings.".trim());

		if (aGame.getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can define its game settings.".trim());

		if (nrLevels < 1 || nrLevels > 99)
			throw new InvalidInputException("The number of levels must be between 1 and 99.".trim());

		if (minBallSpeedX == 0 && minBallSpeedY == 0)
			throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
		if (minBallSpeedX < 0)
			throw new InvalidInputException("The minimum speed of the ball must be greater than zero.".trim());

		if (minBallSpeedY < 0)
			throw new InvalidInputException("Admin privileges are required to create a game.".trim());

		if (ballSpeedIncreaseFactor <= 0)
			throw new InvalidInputException("The speed increase factor of the ball must be greater than zero.".trim());

		if (maxPaddleLength <= 0 || maxPaddleLength > 390)
			throw new InvalidInputException(
					"The maximum length of the paddle must be greater than zero and less than or equal to 390.".trim());

		if (minPaddleLength <= 0)
			throw new InvalidInputException("The minimum length of the paddle must be greater than zero.".trim());

		if (nrBlocksPerLevel <= 0)
			throw new InvalidInputException("The number of blocks per level must be greater than zero.".trim());
		// sketch ass code to check if max # blocks is less than any assigned blocks per
		// level
		int blockCounter = 0;
		for (Level level : aGame.getLevels()) {
			int maxBlockPerLevel = 0;
			for (BlockAssignment blockAssignment : level.getBlockAssignments()) {
				maxBlockPerLevel++;
			}
			blockCounter = Math.max(maxBlockPerLevel, blockCounter);
			maxBlockPerLevel = 0;
		}
		if (blockCounter > nrBlocksPerLevel)
			throw new InvalidInputException(
					"The maximum number of blocks per level cannot be less than the number of existing blocks in a level."
							.trim());
		// set game
		aGame.setNrBlocksPerLevel(nrBlocksPerLevel);
		// set ball
		Ball ball = aGame.getBall();
		ball.setMinBallSpeedX(minBallSpeedX);
		ball.setMinBallSpeedY(minBallSpeedY);
		ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		// set paddle
		Paddle paddle = aGame.getPaddle();
		paddle.setMaxPaddleLength(maxPaddleLength);
		paddle.setMinPaddleLength(minPaddleLength);
		List<Level> levels = aGame.getLevels();
		int size = levels.size();
		while (nrLevels > size) {
			aGame.addLevel();
			size = levels.size();
		}
		while (nrLevels < size) {
			Level level = aGame.getLevel(size - 1);
			level.delete();
			size = levels.size();
		}
	}

	public static void deleteGame(String name) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.".trim());
		}
		if (!Block223Application.getCurrentGame().getAdmin().equals(Block223Application.getCurrentUserRole())) {
			throw new InvalidInputException("Only the admin who created the game can delete the game.".trim());
		}
		Game game = findGame(name);
		
		if(game != null) {
			if (game.getPublished()) {
				throw new InvalidInputException("A published game cannot be deleted.");
			}
		}
		else {
			
		}


		if (game != null) {
			Block223 block223 = game.getBlock223();
			game.delete();
			try {
				Block223Persistence.save(block223);
			} catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}

	public static void selectGame(String name) throws InvalidInputException {

		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.".trim());
		}

		Game game = findGame(name);

		if (game == null) {
			throw new InvalidInputException(("A game with name " + name + " does not exist.").trim());
		}

		if (game.getPublished()) {
			throw new InvalidInputException("A published game cannot be changed.");
		}

		if (!game.getAdmin().equals(Block223Application.getCurrentUserRole())) {
			throw new InvalidInputException("Only the admin who created the game can select the game.".trim());
		}

		Block223Application.setCurrentGame(game);

	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {

		Game game = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to define game settings.".trim());
		if (game == null)
			throw new InvalidInputException("A game must be selected to define game settings.".trim());
		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can define its game settings.".trim());
		if (name == null || name.length() == 0)
			throw new InvalidInputException("The name of a game must be specified.".trim());
		if(game.isPublished())
			throw new InvalidInputException("A published game cannot be updated.".trim());
		String currentName = game.getName();
		Game aGame = findGame(name);
		if (aGame != null && !aGame.equals(game) && !name.contentEquals(currentName))
			throw new InvalidInputException("The name of a game must be unique.".trim());
		if (!currentName.equals(name))
				game.setName(name);
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor,
						maxPaddleLength, minPaddleLength);
			
		
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		if (red > 255 || red < 0) {
			throw new InvalidInputException("Red must be between 0 and 255.".trim());
		}
		if (green > 255 || green < 0) {
			throw new InvalidInputException("Green must be between 0 and 255.".trim());
		}
		if (blue > 255 || blue < 0) {
			throw new InvalidInputException("Blue must be between 0 and 255.".trim());
		}
		if (green > 255 || green < 0) {
			throw new InvalidInputException("Green must be between 0 and 255.".trim());
		}
		if (points > 1000 || points < 1) {
			throw new InvalidInputException("Points must be between 1 and 1000.".trim());
		}
		Game aGame = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to add a block.".trim());
		if (aGame == null)
			throw new InvalidInputException("A game must be selected to add a block.".trim());
		if (aGame.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can add a block.".trim());

		List<Block> blocks = aGame.getBlocks();
		for (Block block : blocks) {
			if (block.getBlue() == blue && block.getRed() == red && block.getGreen() == green)
				throw new InvalidInputException("A block with the same color already exists for the game.");
		}

		aGame.addBlock(red, green, blue, points);

	}

	public static void deleteBlock(int id) throws InvalidInputException {

		Block223 block223 = Block223Application.getBlock223();
		Game aGame = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to delete a block.".trim());
		if (aGame == null)
			throw new InvalidInputException("A game must be selected to delete a block.".trim());
		if (aGame.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can delete a block.".trim());

		Block toDelete = findBlock(aGame, id);

		if (toDelete == null) {
			return;
		}

		toDelete.delete();
		// BlockApplication.getCurrentGame().removeBlock(toDelete);// calling custom
		// getBlock method
	}

	private static Block findBlock(Game aGame, int id) {
		List<Block> blocks = aGame.getBlocks();
		for (Block aBlock : blocks) {
			if (aBlock.getId() == id) {
				return aBlock;
			}
		}
		return null;
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {

		Game aGame = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to update a block.".trim());

		if (red > 255 || red < 0)
			throw new InvalidInputException("Red must be between 0 and 255.".trim());
		if (green > 255 || green < 0)
			throw new InvalidInputException("Green must be between 0 and 255.".trim());
		if (blue > 255 || blue < 0)
			throw new InvalidInputException("Blue must be between 0 and 255.".trim());
		if (green > 255 || green < 0)
			throw new InvalidInputException("Green must be between 0 and 255.".trim());
		if (points > 1000 || points < 1)
			throw new InvalidInputException("Points must be between 1 and 1000.".trim());

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information.");

		Game game = Block223Application.getCurrentGame();
		if (game == null)
			throw new InvalidInputException("A game must be selected to update a block.");

		if (aGame.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can update a block.");

		Block foundBlock = null;
		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			if (block.getBlue() == blue && block.getRed() == red && block.getGreen() == green)
				throw new InvalidInputException("A block with the same color already exists for the game.");
			if (id == block.getId()) {
				foundBlock = block;
			}
		}

		if (foundBlock == null) {
			throw new InvalidInputException("The block does not exist. ");
		}

		foundBlock.setRed(red);
		foundBlock.setBlue(blue);
		foundBlock.setGreen(green);
		foundBlock.setPoints(points);

	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to position a block.");

		Block223 block223 = Block223Application.getBlock223();
		if (game == null)
			throw new InvalidInputException("A game must be selected to position a block.");

		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can position a block.");

		Level aLevel = null;
		try {
			aLevel = game.getLevel(level - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game. ");
		}

		int nrBlocksPerLevel = game.getNrBlocksPerLevel();
		if (aLevel.numberOfBlockAssignments() >= nrBlocksPerLevel) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + nrBlocksPerLevel
					+ ") allowed for this game.");
		}

		if (findBlockAssigment(aLevel, gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException(
					"A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
		}

		Block foundBlock = null;
		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			if (id == block.getId()) {
				foundBlock = block;
				break;
			}
		}

		if (foundBlock == null) {
			throw new InvalidInputException("The block does not exist. ");
		}
		try {
			BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel,
					foundBlock, game);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		// Error checking:
		Game game = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		Block223 block223 = Block223Application.getBlock223();
		// Needs to be an admin.
		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to move a block.".trim());
		// Game needs to be set.
		if (game == null) {
			throw new InvalidInputException("A game must be selected to move a block.".trim());
		}
		// The admin must be the admin of the current game.
		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can move a block.".trim());

		if (level <= 0 || level > (game.getLevels().size())) {
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
		}
		Level editLevel;
		editLevel = game.getLevel(level - 1);

		BlockAssignment moveBlock = findBlockAssigment(editLevel, oldGridHorizontalPosition, oldGridVerticalPosition);

		// Throw error if there's no block at x and y
		if (moveBlock == null)
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/"
					+ oldGridVerticalPosition + ".");

		// Throw error if there's already a block at x and y
		if (findBlockAssigment(editLevel, newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + "/"
					+ newGridVerticalPosition + ".");
		}
		int maxNumberHorizontalBlocks = (Game.PLAY_AREA_SIDE + Game.COLUMNS_PADDING - 2 * Game.WALL_PADDING)
				/ (Block.SIZE + Game.COLUMNS_PADDING);
		if (newGridHorizontalPosition <= 0 || newGridHorizontalPosition > maxNumberHorizontalBlocks) {
			throw new InvalidInputException(
					"The horizontal position must be between 1 and " + maxNumberHorizontalBlocks + ".");
		}

		int maxNumberVerticalBlocks = (Game.PLAY_AREA_SIDE + Game.ROW_PADDING - Paddle.PADDLE_WIDTH
				- Paddle.VERTICAL_DISTANCE - Ball.BALL_DIAMETER - Game.WALL_PADDING) / (Block.SIZE + Game.ROW_PADDING);
		if (newGridVerticalPosition <= 0 || newGridVerticalPosition > maxNumberVerticalBlocks) {
			throw new InvalidInputException(
					"The vertical position must be between 1 and " + maxNumberVerticalBlocks + ".");
		}
		moveBlock.setGridHorizontalPosition(newGridHorizontalPosition);
		moveBlock.setGridVerticalPosition(newGridVerticalPosition);

	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		// Error checking:
		// Needs to be an admin.
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to remove a block.".trim());
		// Game needs to be set.
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to remove a block.".trim());
		}
		// The admin must be the admin of the current game.
		if (!Block223Application.getCurrentGame().getAdmin().equals(Block223Application.getCurrentUserRole()))
			throw new InvalidInputException("Only the admin who created the game can remove a block.".trim());
		Game game = Block223Application.getCurrentGame();
		try {
			Level editLevel = game.getLevel(level - 1);
			BlockAssignment moveBlock = findBlockAssigment(editLevel, gridHorizontalPosition, gridVerticalPosition);
			if (moveBlock != null) {
				moveBlock.delete();
				;
			}
		} catch (RuntimeException e) {
			return;
		}
	}

	public static void saveGame() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to save a game.".trim());
		if (Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to save it.".trim());
		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can save it.".trim());

		try {

			Block223Persistence.save(block223);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (userRole != null)
			throw new InvalidInputException("Cannot register a new user while a user is logged in.".trim());

		if (playerPassword == null || "".equals(playerPassword.trim())) {
			throw new InvalidInputException("The player password needs to be specified.");
		}
		
		if (playerPassword.equals(adminPassword))
			throw new InvalidInputException("The passwords have to be different.".trim());

		Block223 block223 = Block223Application.getBlock223();
		
		if (username == null || "".equals(username.trim())) {
			throw new InvalidInputException("The username must be specified.");
		}

		List<User> users = block223.getUsers();
		boolean check = false;
		for (User tempUser : users) {
			if (tempUser.getUsername().equals(username)) {
				check = true;
			}
		}
		if (Block223Application.getBlock223().getUsers().contains(User.getWithUsername(username))) {
			throw new InvalidInputException("The username has already been taken.");
		}
		
		
		try {
			Player player = new Player(playerPassword, block223);
			User user = new User(username, block223, player);

			if (adminPassword != null && adminPassword != "") {
				Admin admin = new Admin(adminPassword, block223);
				user.addRole(admin);
			}
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		Block223Persistence.save(block223);

	}

	public static void login(String username, String password) throws InvalidInputException {

		Block223 block223 = Block223Application.resetBlock223();
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole != null)
			throw new InvalidInputException("Cannot login a user while a user is already logged in.".trim());

		if (username.trim().equals("")) {
			throw new InvalidInputException("The username must be specified.".trim());
		}
		
		if (password.trim().equals("")) {
			throw new InvalidInputException("The password must be specified.".trim());
		}
		if (User.getWithUsername(username) == null)
			throw new InvalidInputException("The username and password do not match.".trim());

		
		User user = User.getWithUsername(username);
		List<UserRole> roles = user.getRoles();
		
		for (UserRole aRole : roles) {
			String rolePassword = aRole.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(aRole);
			}
		}
		
		if (Block223Application.getCurrentUserRole() == null)
			throw new InvalidInputException("The username and password do not match.");
		
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
	}

	// play mode TODO

	// TUDZ
	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {

		Game game = findGame(name);
		Block223 block223 = Block223Application.getBlock223();
		UserRole player = Block223Application.getCurrentUserRole();
		PlayedGame pgame = null;
		String username = User.findUsername(player);

		if(!(player instanceof Player))
			throw new InvalidInputException("Player privileges are required to play a game.");

		if(game != null) {
			pgame = new PlayedGame(username, game, block223);
			pgame.setPlayer((Player) player);
		}
		else {
			pgame = block223.findPlayableGame(id);

		}
		if(pgame == null && game == null)
			throw new InvalidInputException("The game does not exist.");
		if(game == null && player != pgame.getPlayer()) 
			throw new InvalidInputException("Only the player that started a game can continue the game.");
		Block223Application.setCurrentPlayableGame(pgame);
	}

	// TUDZ
	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {
		PlayedGame aGame = Block223Application.getCurrentPlayableGame();

		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole == null)
			throw new InvalidInputException("Player privileges are required to play a game.");
		if (aGame == null)
			throw new InvalidInputException("A game must be selected to play it.");
		if ((userRole instanceof Admin) && aGame.getPlayer() != null)
			throw new InvalidInputException("Player privileges are required to play a game.");
		if ((userRole instanceof Admin) && aGame.getGame().getAdmin() != userRole)
			throw new InvalidInputException("Only the admin of a game can test the game.");
		if (userRole instanceof Player && aGame.getPlayer() == null)
			throw new InvalidInputException("Admin privileges are required to test a game.");

		aGame.play();
		String userInputs = ui.takeInputs();

		
		while (aGame.getPlayStatus() == PlayStatus.Moving) {
			userInputs = ui.takeInputs();
			updatePaddlePosition(userInputs);
			aGame.move();

			if (userInputs.contains(" ")) {
				aGame.pause();
			}
			
//			try {
//				userInputs.wait((long) aGame.getWaitTime());
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			ui.refresh();
		}
		if (aGame.getPlayStatus() == PlayStatus.GameOver) {
			Block223Application.setCurrentPlayableGame(null);
		} else {
			Block223 block223 = Block223Application.getBlock223();
			Block223Persistence.save(block223);
		}
	}

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to publish a game\n");
		if(Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to publish it\n");
		if(!Block223Application.getCurrentUserRole().equals(Block223Application.getCurrentGame().getAdmin()))
			throw new InvalidInputException("Only the admin who created the game can publish it\n");
		if(Block223Application.getCurrentGame().getBlocks().size()<1)
			throw new InvalidInputException("At least one block must be defined for a game to be published\n");
		

		Game game = Block223Application.getCurrentGame();
		UserRole admin = Block223Application.getCurrentUserRole();
		String username = User.findUsername(admin);
		Block223 block223 = Block223Application.getBlock223();
		PlayedGame pgame = new PlayedGame(username,game,block223);
		pgame.setPlayer(null);
		Block223Application.setCurrentPlayableGame(pgame);
		startGame(ui);
		
		
	}



	public static void publishGame() throws InvalidInputException {

		if(!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to publish a game\n");
		if(Block223Application.getCurrentGame() == null)
			throw new InvalidInputException("A game must be selected to publish it\n");
		if(!Block223Application.getCurrentUserRole().equals(Block223Application.getCurrentGame().getAdmin()))
			throw new InvalidInputException("Only the admin who created the game can publish it\n");
		if(Block223Application.getCurrentGame().getBlocks().size()<1)
			throw new InvalidInputException("At least one block must be defined for a game to be published\n");

		
		
		
		
				
		Game game = Block223Application.getCurrentGame();
		game.setPublished(true);
		
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();

		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.".trim());
		}

		List<TOGame> result = new ArrayList<TOGame>();
		List<Game> games = block223.getGames();
		for (Game game : games) {
			Admin gameAdmin = game.getAdmin();
			if (gameAdmin.equals(userRole) && !game.isPublished()) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
						game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
						game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
						game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {

		UserRole userRole = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information.".trim());
		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information.".trim());
		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can access its information.".trim());

		TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(),
				game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(),
				game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(),
				game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {

		UserRole userRole = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can access its information.");

		List<TOBlock> result = new ArrayList<TOBlock>();

		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(),
					block.getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {

		UserRole userRole = Block223Application.getCurrentUserRole();
		Game game = Block223Application.getCurrentGame();

		if (!(userRole instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (game.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can access its information.");

		Block foundBlock = null;
		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			if (id == block.getId()) {
				foundBlock = block;
			}
		}

		if (foundBlock == null) {
			throw new InvalidInputException("The block does not exist. ");
		}

		TOBlock to = new TOBlock(foundBlock.getId(), foundBlock.getRed(), foundBlock.getGreen(), foundBlock.getBlue(),
				foundBlock.getPoints());

		return to;
	}

	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		Game game = Block223Application.getCurrentGame();
		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can access its information.");

		List<TOGridCell> result = new ArrayList<TOGridCell>();

		int numLevels = game.getLevels().size();
		if (level > numLevels)
			throw new InvalidInputException("Level " + level + " does not exist for the game.");
		if (level < 1)
			throw new InvalidInputException("Level " + level + " does not exist for the game.");

		Level alevel = game.getLevel(level - 1);
		List<BlockAssignment> assignments = alevel.getBlockAssignments();

		for (BlockAssignment assignment : assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(),
					assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
					assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
			result.add(to);
		}
		return result;
	}

	public static TOUserMode getUserMode() {
		UserRole userRole = Block223Application.getCurrentUserRole();
		TOUserMode to = null;
		if (userRole == null) {
			to = new TOUserMode(Mode.None);
		}
		if (userRole instanceof Admin) {
			to = new TOUserMode(Mode.Design);
		}
		if (userRole instanceof Player) {
			to = new TOUserMode(Mode.Play);
		}

		return to;
	}

	// play mode TODO

	// TUDZ
	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole player = Block223Application.getCurrentUserRole();
		List<TOPlayableGame> result = new ArrayList<TOPlayableGame>();
		List<Game> games = block223.getGames();

		if (!(player instanceof Player))
			throw new InvalidInputException("Player privileges are required to play a game.");

		for (Game game : games) {
			boolean published = game.isPublished();
			if (published) {
				TOPlayableGame to = new TOPlayableGame(game.getName(), -1, 0);
				result.add(to);
			}
		}

		List<PlayedGame> games2 = block223.getPlayedGames();

		for (PlayedGame game : games2) {
			TOPlayableGame to = new TOPlayableGame(game.getGame().getName(), game.getId(), game.getCurrentLevel());
			result.add(to);
		}
		return result;
	}

	// TUDZ
	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
		UserRole player = Block223Application.getCurrentUserRole();
		PlayedGame pgame = Block223Application.getCurrentPlayableGame();

		if (player == null)
			throw new InvalidInputException("Player privileges are required to play a game.");
		if (pgame == null)
			throw new InvalidInputException("A game must be selected to play it.");
		if (player instanceof Admin && pgame.getPlayer() != null)
			throw new InvalidInputException("Player privileges are required to play a game.");
		if (player instanceof Admin && pgame.getGame().getAdmin() != player)
			throw new InvalidInputException("Only the admin of a game can test the game.");
		if (player instanceof Player && pgame.getPlayer() == null)
			throw new InvalidInputException("Admin privileges are required to test a game.");

		boolean paused = pgame.getPlayStatus() == PlayStatus.Ready || pgame.getPlayStatus() == PlayStatus.Paused;

		TOCurrentlyPlayedGame result = new TOCurrentlyPlayedGame(pgame.getGame().getName(), paused, pgame.getScore(),
				pgame.getLives(), pgame.getCurrentLevel(), pgame.getPlayername(), pgame.getCurrentBallX(),
				pgame.getCurrentBallY(), pgame.getCurrentPaddleLength(), pgame.getCurrentPaddleX());

		List<PlayedBlockAssignment> blocks = pgame.getBlocks();

		for (PlayedBlockAssignment pblock : blocks) {
			TOCurrentBlock to = new TOCurrentBlock(pblock.getBlock().getRed(), pblock.getBlock().getGreen(),
					pblock.getBlock().getBlue(), pblock.getBlock().getPoints(), pblock.getX(), pblock.getY(), result);
		}
		return result;
	}

	// Charles L
	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a gameï¿½s hall of fame.".trim());
		if (Block223Application.getCurrentPlayableGame() == null)
			throw new InvalidInputException("A game must be selected to view its hall of fame.".trim());

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		Game game = pgame.getGame();

		TOHallOfFame result = new TOHallOfFame(game.getName());

		if (start < 1)
			start = 1;
		if (end > game.numberOfHallOfFameEntries())
			end = game.numberOfHallOfFameEntries();
		start = game.numberOfHallOfFameEntries() - start;
		end = game.numberOfHallOfFameEntries() - end;

		for (int index = start; index >= end; index--) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(index + 1, game.getHallOfFameEntry(index).getPlayername(),
					game.getHallOfFameEntry(index).getScore(), result);
		}

		return result;
	}

	// Charles L
	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		if (!(Block223Application.getCurrentUserRole() instanceof Player))
			throw new InvalidInputException("Player privileges are required to access a gameï¿½s hall of fame.".trim());
		if (Block223Application.getCurrentPlayableGame() == null)
			throw new InvalidInputException("A game must be selected to view its hall of fame.".trim());

		PlayedGame pgame = Block223Application.getCurrentPlayableGame();
		Game game = pgame.getGame();

		TOHallOfFame result = new TOHallOfFame(game.getName());

		HallOfFameEntry mostRecent = game.getMostRecentEntry();
		int indexR = game.indexOfHallOfFameEntry(mostRecent);

		int start = indexR + numberOfEntries / 2;
		if (start > game.numberOfHallOfFameEntries() - 1)
			start = game.numberOfHallOfFameEntries() - 1;
		int end = start - numberOfEntries + 1;
		if (end < 0)
			end = 0;

		for (int index = start; index >= end; index--) {
			TOHallOfFameEntry to = new TOHallOfFameEntry(index + 1, game.getHallOfFameEntry(index).getPlayername(),
					game.getHallOfFameEntry(index).getScore(), result);
		}

		return result;
	}

	/////////// PRIVATE METHODS ///////////
	// Narry
	private static Game findGame(String name) {
		Game foundGame = null;
		for (Game game : Block223Application.getBlock223().getGames()) {
			if (game.getName().equals(name)) {
				return game;
			}
		}
		return foundGame;
	}

	// MATT:
	private static BlockAssignment findBlockAssigment(Level editLevel, int oldGridHorizontalPosition,
			int oldGridVerticalPosition) {
		for (BlockAssignment curBlock : editLevel.getBlockAssignments()) {
			if (curBlock.getGridHorizontalPosition() == oldGridHorizontalPosition
					&& curBlock.getGridVerticalPosition() == oldGridVerticalPosition) {
				return curBlock;
			}
		}
		return null;
	}

	// TUDZ
	private static void updatePaddlePosition(String userInputs) {
		PlayedGame aGame = Block223Application.getCurrentPlayableGame();
		int r = 0, l = 0;
		for (int i = 0; i < userInputs.length(); i++) {
			char c = userInputs.charAt(i);
			if (c == ' ')
				break;
			if (c == 'l')
				l++;
			if (c == 'r')
				r++;
		}
		double newPos = aGame.getCurrentPaddleX() + (PlayedGame.PADDLE_MOVE_LEFT * l) + (PlayedGame.PADDLE_MOVE_RIGHT * r);
		if (newPos > 370) {
			newPos = 370;
		}
		if (newPos < 0) {
			newPos = 0;
			
		}
		aGame.setCurrentPaddleX(newPos);
	}

}

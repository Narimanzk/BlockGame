package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
import ca.mcgill.ecse223.block.model.*;
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
			throw new InvalidInputException("Admin privileges are required to create a game.".trim());
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
			throw new InvalidInputException("The maximum length of the paddle must be greater than zero and less than or equal to 390.".trim());

		if (minPaddleLength <= 0)
			throw new InvalidInputException("The minimum length of the paddle must be greater than zero.".trim());

		if (nrBlocksPerLevel <= 0)
			throw new InvalidInputException("The number of blocks per level must be greater than zero.".trim());
		//sketch ass code to check if max # blocks is less than any assigned blocks per level
		int blockCounter = 0;
		for(Level level : aGame.getLevels()) {
			int maxBlockPerLevel = 0;
			for(BlockAssignment blockAssignment : level.getBlockAssignments()) {
				maxBlockPerLevel++;
			}
			blockCounter = Math.max(maxBlockPerLevel, blockCounter);
			maxBlockPerLevel = 0;
		}
		if(blockCounter > nrBlocksPerLevel)
			throw new InvalidInputException("The maximum number of blocks per level cannot be less than the number of existing blocks in a level.".trim());
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
		if(name == null || name.length() == 0)
			throw new InvalidInputException("The name of a game must be specified.".trim());

		String currentName = game.getName();
		Game aGame = findGame(name);
		if (aGame != null) 
			throw new InvalidInputException("The name of a game must be unique.".trim());

		if (!currentName.equals(name)) {
			try {
				game.setName(name);
			} catch (RuntimeException e) {
				if (e.getMessage().equals("Name is not unique")) {
					throw new InvalidInputException("The name of a game must be unique.".trim());

				}
			}
		}
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
		if(aGame == null)
			throw new InvalidInputException("A game must be selected to add a block.".trim());
		if(aGame.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can add a block.".trim());

		aGame.addBlock(red, green, blue, points);
		
	}

	public static void deleteBlock(int id) throws InvalidInputException {
		
		Block223 block223 = Block223Application.getBlock223();
		Game aGame = Block223Application.getCurrentGame();
		UserRole userRole = Block223Application.getCurrentUserRole();

		if (!(userRole instanceof Admin)) 
			throw new InvalidInputException("Admin privileges are required to delete a block.".trim());
		if(aGame == null)
			throw new InvalidInputException("A game must be selected to delete a block.".trim());
		if(aGame.getAdmin() != userRole)
			throw new InvalidInputException("Only the admin who created the game can delete a block.".trim());
		
		List<Block> blocks = aGame.getBlocks();
		Block toDelete = null;
		for (Block aBlock : blocks) {
			if (aBlock.getId() == id) {
				toDelete = aBlock;
				break;
			}
		}
		if (toDelete == null) {
			throw new InvalidInputException("Block does not exist.");
		}
		
		toDelete.delete();
		//BlockApplication.getCurrentGame().removeBlock(toDelete);// calling custom getBlock method
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {

		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		Game game = Block223Application.getCurrentGame();
		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");

		Block foundBlock = null;
		List<Block> blocks = game.getBlocks();
		for (Block block : blocks) {
			if (block.getBlue() == blue && block.getRed() == red && block.getGreen() == green)
				throw new InvalidInputException("A block with the same colour already exists for the game. ");
			if (id == block.getId()) {
				foundBlock = block;
			}
		}

		if (foundBlock == null) {
			throw new InvalidInputException("The block does not exist. ");
		}

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

		foundBlock.setRed(red);
		foundBlock.setBlue(blue);
		foundBlock.setGreen(green);
		foundBlock.setPoints(points);

	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to access game information. ");

		Block223 block223 = Block223Application.getBlock223();
		Game game = Block223Application.getCurrentGame();
		if (game == null)
			throw new InvalidInputException("A game must be selected to access its information. ");

		if (Block223Application.getCurrentGame().getAdmin() != Block223Application.getCurrentUserRole())
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");

		Level aLevel = null;
		try {
			aLevel = game.getLevel(level - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level" + level + "does not exist for the game. ");
		}

		int nrBlocksperLevel = (Game.PLAY_AREA_SIDE - Game.ROW_PADDING) * (Game.PLAY_AREA_SIDE - Game.COLUMNS_PADDING)
				/ 400;
		if (aLevel.numberOfBlockAssignments() == nrBlocksperLevel) {
			throw new InvalidInputException("The number of blocks has reached the maximum number (" + nrBlocksperLevel
					+ ") allowed for this game.");
		}

		if (findBlockAssigment(aLevel, gridHorizontalPosition, gridVerticalPosition) != null) {
			throw new InvalidInputException(
					"A block already exists at position " + gridHorizontalPosition + "/" + gridVerticalPosition + ".");
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

		Block223 block223 = Block223Application.getBlock223();
		// Needs to be an admin.
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to move a block.".trim());
		// Game needs to be set.
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to move a block.".trim());
		}
		// The admin must be the admin of the current game.
		if (!Block223Application.getCurrentGame().getAdmin().equals(Block223Application.getCurrentUserRole()))
			throw new InvalidInputException("Only the admin who created the game can move a block.".trim());

		Game game = Block223Application.getCurrentGame();

		Level editLevel;
		try {
			editLevel = game.getLevel(level-1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException(e.getMessage());
		}

		BlockAssignment moveBlock = findBlockAssigment(editLevel, oldGridHorizontalPosition, oldGridVerticalPosition);

		// Throw error if there's no block at x and y
		if (moveBlock == null)
			throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition + "/"
					+ oldGridVerticalPosition + ".");

		// Throw error if there's already a block at x and y
		if (findBlockAssigment(editLevel, newGridHorizontalPosition, newGridVerticalPosition) != null) {
			throw new InvalidInputException(
					"A block already exists at location " + newGridHorizontalPosition + "/" + newGridVerticalPosition + ".");
		}
		try {
			//This is weird because if only the vertical position doesn't work it'll set X anyway
			//But prof wants it to catch in the umple before the "set" call, so it has to be this way.
			moveBlock.setGridHorizontalPosition(newGridHorizontalPosition);
			moveBlock.setGridVerticalPosition(newGridVerticalPosition);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
					Block223 block223 = Block223Application.getBlock223();
		// Error checking:
		// Needs to be an admin.
		if (!(Block223Application.getCurrentUserRole() instanceof Admin))
			throw new InvalidInputException("Admin privileges are required to move a block.".trim());
		// Game needs to be set.
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to move a block.".trim());
		}
		// The admin must be the admin of the current game.
		if (!Block223Application.getCurrentGame().getAdmin().equals(Block223Application.getCurrentUserRole()))
			throw new InvalidInputException("Only the admin who created the game can move a block.".trim());
		Game game = Block223Application.getCurrentGame();
		try {
			Level editLevel = game.getLevel(level-1);
			BlockAssignment moveBlock = findBlockAssigment(editLevel, gridHorizontalPosition, gridVerticalPosition);
			if (moveBlock != null) {
				moveBlock.delete();;
			}
		}
		catch (RuntimeException e) {
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
			throw new InvalidInputException("Cannot register a new user while a user is already logged in.".trim());

		if (playerPassword.equals(adminPassword))
			throw new InvalidInputException("The passwords have to be different.".trim());


		Block223 block223 = Block223Application.getBlock223();
		try {
			Player player = new Player(playerPassword, block223);
			User user = new User(username, block223, player);

			if (adminPassword != null && adminPassword != "") {
				Admin admin = new Admin(adminPassword, block223);
				user.addRole(admin);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		Block223Persistence.save(block223);

	}

	public static void login(String username, String password) throws InvalidInputException {

		Block223 block223 = Block223Application.getBlock223();
		UserRole userRole = Block223Application.getCurrentUserRole();
		if (userRole != null)
			throw new InvalidInputException("Cannot login a user while a user is already logged in.".trim());
		if (User.getWithUsername(username) == null)
			throw new InvalidInputException("The username and password do not match.".trim());


		Block223Application.resetBlock223();
		User user = User.getWithUsername(username);
		List<UserRole> roles = user.getRoles();
		for (UserRole aRole : roles) {
			String rolePassword = aRole.getPassword();
			if (rolePassword.equals(password)) {
				Block223Application.setCurrentUserRole(aRole);
			}
		}
		if (Block223Application.getCurrentUserRole() == null)
			throw new InvalidInputException("The username and password do not match\n");
	}

	public static void logout() {
				Block223Application.setCurrentUserRole(null);
	}

	// play mode TODO

	public static void selectPlayableGame(String name, int id) throws InvalidInputException  {

	}

	public static void startGame(Block223PlayModeInterface ui) throws InvalidInputException {

	}

	public static void testGame(Block223PlayModeInterface ui) throws InvalidInputException {
	}

	public static void publishGame () throws InvalidInputException {
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
			if (gameAdmin.equals(userRole)) {
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
			throw new InvalidInputException( "A game must be selected to access its information.".trim());
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
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");

		List<TOGridCell> result = new ArrayList<TOGridCell>();

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

	public static List<TOPlayableGame> getPlayableGames() throws InvalidInputException {
		return null;
	}

	public static TOCurrentlyPlayedGame getCurrentPlayableGame() throws InvalidInputException {
		return null;

	}

	public static TOHallOfFame getHallOfFame(int start, int end) throws InvalidInputException {
		return null;

	}

	public static TOHallOfFame getHallOfFameWithMostRecentEntry(int numberOfEntries) throws InvalidInputException {
		return null;

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

}
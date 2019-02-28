package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.BlockApplication;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Block;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin userRole = getCurrentUserRole(block223);// custom made function, probly not good
		if (name == null || name == "") {
			error += "Name cannot be empty\n";
		}
		if (findGame(name) != null) {
			error += "Game name already in use\n";
		}
		if (userRole == null) {
			error += "User has to be Admin\n";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = new Game(name, 1, userRole, 1, 1, 1, 10, 10, block223);

	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void deleteGame(String name) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		if(BlockApplication.getCurrentUserRole == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentGame().getCurrentUserRole != BlockApplication.getCurrentUserRole) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		Game game = findGame(name);
		if (game != null) {
			Block223 block223 = BlockApplication.getBlock223();
			game.delete();
			try {
				Block223Persistence.save(block223);
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		if(BlockApplication.getCurrentUserRole == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentGame().getCurrentUserRole != BlockApplication.getCurrentUserRole) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = findGame(name);
		if(game == null) {
			error += "A game with name"+ name + "does not exist.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		BlockApplication.setCurrentGame(game);
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		if(BlockApplication.getCurrentUserRole == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentGame == null) {
			error += "A game must be selected to access information.";
		}
		if(BlockApplication.getCurrentGame().getCurrentUserRole != BlockApplication.getCurrentUserRole) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = BlockApplication.getCurrentGame();
		String currentName = game.getName();
		if(!currentName.equals(name)) {
			try {
				game.setName(name);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				if (error.equals("Name is not unique")) {
					error = "The name of a game must be unique.";
				}
				throw new InvalidInputException(error);
			}
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);

	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		// TODO : exceptions
		// Question: How can I check if a block in the currentGame already has the same
		// value of red, blue and green?
		String error = "";

		if (red > 255 || red < 0)
			error = "Red must be between 0 and 255. ";
		if (green > 255 || green < 0)
			error = error + "Green must be between 0 and 255. ";
		if (blue > 255 || blue < 0)
			error = error + "Blue must be between 0 and 255. ";
		if (green > 255 || green < 0)
			error = error + "Green must be between 0 and 255. ";
		if (points > 1000 || points < 1)
			error = error + "Points must be between 1 and 1000.";

		if (error.length() > 0)
			throw new InvalidInputException(error.trim());

		Game game = BlockApplication.getCurrentGame();

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

		foundBlock.setRed(red);
		foundBlock.setBlue(blue);
		foundBlock.setGreen(green);
		foundBlock.setPoints(points);

	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		//TODO: exception after getLevel()
		String error = "";
		Game game = BlockApplication.getCurrentGame();
		Level aLevel = null;
		try {
			aLevel = game.getLevel(level);
		}
		catch (IndexOutOfBoundsException e)
		{
			throw new InvalidInputException("Level" + level + "does not exist for the game. ");
		}
		
		if(aLevel.numberOfBlockAssignments() == ???)
		{
			
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
			BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, alevel, block, game);
		}
		
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin admin = BlockApplication.getCurrentUserRole();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		ArrayList<TOGame> result = new ArrayList<TOGame>();
		for(Game game: block223.getGames()) {
			if(game.getAdmin().equals(admin)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		if(BlockApplication.getCurrentUserRole == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentGame == null) {
			error += "A game must be selected to access information.";
		}
		if(BlockApplication.getCurrentGame().getCurrentUserRole != BlockApplication.getCurrentUserRole) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		Game game = BlockApplication.getCurrentGame();
		TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		return null;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		return null;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		return null;
	}

	public static TOUserMode getUserMode() {
		return null;
	}

	private static Game findGame(String name) {
		Game foundGame = null;
		for (Game game : BlockApplication.getBlock223().getGames()) {
			if (game.getName().equals(name)) {
				foundGame = game;
				break;
			}
		}
		return foundGame;
	}

	// helper method made to get user role for other functions
	private static Admin getCurrentUserRole(Block223 block223) {
		Admin curUserRole = null;
		for (UserRole admin : block223.getUsers().get(0).getRoles()) {// 0 is sketch since idk how to see which user is
																		// current user
			if (admin.getClass() == Admin.class) {
				curUserRole = (Admin) admin;
				break;
			}
		}
		return curUserRole;
	}

	private static Block findBlock(int id) {

	}
}
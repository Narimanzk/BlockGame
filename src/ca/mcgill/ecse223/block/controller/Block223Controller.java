package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.BlockApplication;
import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	//TUDOR
	public static void createGame(String name) throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin userRole = (Admin) BlockApplication.getCurrentUserRole();
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
	//TUDOR
	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin userRole = (Admin) BlockApplication.getCurrentUserRole();
		
		if(nrLevels < 1 || nrLevels > 99) 
			error += "The number of levels must be between 1 and 99\n";
		if(nrBlocksPerLevel <= 0)
			error += "The number of blocks per level must be greater than zero\n";
		if(minBallSpeedX <= 0)
			error += "The minimum speed of the ball mut be greater than zero\n";
		if(minBallSpeedY <= 0)
			error += "The minimum speed of the ball mut be greater than zero\n";
		if(ballSpeedIncreaseFactor <= 0)
			error += "The speed increase factor of the ball mut be greater than zero\n";
		if(maxPaddleLength <= 0 || maxPaddleLength > 400) 
			error += "THe maximum length of the paddle be greater than zero and less than or equal to 400";
		if(minPaddleLength <= 0 ) 
			error += "THe minimum length of the paddle be greater than zero";
	
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		//set game
		Game aGame = BlockApplication.getCurrentGame();
		aGame.setNrBlocksPerLevel(nrBlocksPerLevel);
		//set ball
		Ball ball = aGame.getBall();
		ball.setMinBallSpeedX(minBallSpeedX);
		ball.setMinBallSpeedY(minBallSpeedY);
		ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
		//set paddle
		Paddle paddle = aGame.getPaddle();
		paddle.setMaxPaddleLength(maxPaddleLength);
		paddle.setMinPaddleLength(minPaddleLength);
		List<Level> levels = aGame.getLevels();
		int size = levels.size();
		while(nrLevels > size) {
			aGame.addLevel();
			size = levels.size();
		}
		while(nrLevels < size) {
			Level level = aGame.getLevel(size-1);
			level.delete();
			size = levels.size();
		}
			
		
	}
	//Narry
	public static void deleteGame(String name) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		Admin admin =(Admin) BlockApplication.getCurrentUserRole();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentUserRole() != BlockApplication.getCurrentUserRole()) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		Game game = findGame(name);
		if (game != null) {
			Block223 block223 = game.getBlock223();
			game.delete();
			try {
				Block223Persistence.save(block223);
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	}
	//Narry
	public static void selectGame(String name) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		Admin admin =(Admin) BlockApplication.getCurrentUserRole();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentUserRole() != BlockApplication.getCurrentUserRole()) {//condition is wrong probably
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
	//Narry
	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		Admin admin =(Admin) BlockApplication.getCurrentUserRole();
		Game game = BlockApplication.getCurrentGame();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(game == null) {
			error += "A game must be selected to access information.";
		}
		if(BlockApplication.getCurrentUserRole() != BlockApplication.getCurrentUserRole()) {//condition is wrong probably
			error += "Only the admin who created the game can select the game.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
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
		
		/*if(aLevel.numberOfBlockAssignments() == ???)
		{
			
		}
			
			*/
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
		
			BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, aLevel, foundBlock, game);
		
		
		
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}
	//TUDOR
	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
		//TODO add exceptions
		Block223 block223 = BlockApplication.getBlock223();
		Player player = new Player(playerPassword, block223);
		User user = new User(username, block223, player);
		
		if(adminPassword != null && adminPassword != "") {
			Admin admin = new Admin(adminPassword, block223);
			user.addRole(admin);
		}
		Block223Persistence.save(block223);

	}
	//TUDOR
	public static void login(String username, String password) throws InvalidInputException {
		//TODO add exceptions
		BlockApplication.resetBlock223();
		User user = User.getWithUsername(username);
		List<UserRole> roles = user.getRoles();
		
		for(UserRole aRole : roles) {
			String rolePassword = aRole.getPassword();
			if(rolePassword == password) {
				BlockApplication.setCurrentUserRole(aRole);
			}
		}
	}
	//TUDOR
	public static void logout() { //Tudor did this God function
		BlockApplication.setCurrentUserRole(null);
	}

	// ****************************
	// Query methods
	// ****************************
	//Narry
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		String error = "";
		Block223 block223 = BlockApplication.getBlock223();
		Admin admin =(Admin) BlockApplication.getCurrentUserRole();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		ArrayList<TOGame> result = new ArrayList<TOGame>();
		List<Game>games = block223.getGames();
		for(Game game: games) {
			if(game.getAdmin().equals(admin)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		return result;
	}
	//Narry
	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		//TODO : exceptions to be checked
		String error = "";
		Admin admin =(Admin) BlockApplication.getCurrentUserRole();
		if(admin == null) {
			error += "Admin privileges are required to access game information.";
		}
		if(BlockApplication.getCurrentGame() == null) {
			error += "A game must be selected to access information.";
		}
		if(BlockApplication.getCurrentUserRole() != BlockApplication.getCurrentUserRole()) {//condition is wrong probably
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
	//Narry
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


	private static Block findBlock(int id) {
		return null;
	}
}
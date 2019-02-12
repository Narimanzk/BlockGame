package ca.mcgill.ecse223.block.controller;

import ca.mcgill.ecse223.block.application.BlockApplication;
import ca.mcgill.ecse223.block.model.*;

public class BlockController {

	public BlockController() {

	}
	
	//add game TODO
	public static void addGame(String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea, Admin aAdmin,
			int aMinBallSpeedXForBall, int aMinBallSpeedYForBall, double aBallSpeedIncreaseFactorForBall,
			int aMaxPaddleLengthForPaddle, int aMinPaddleLengthForPaddle) throws InvalidInputException {
		String error = "";
		
		if(aNrBlocksPerLevel > 50)//arbitrary # i picked, need to check actual max
			error += "Cannot have more than 50 blocks per level";
		if(aWidthPlayArea < 200)
			error += "Min game width is 200";
		if(aWidthPlayArea > 500)
			error += "Max game width is 500";
		if(aHeightPlayArea < 200)
			error += "Min game height is 200";
		if(aHeightPlayArea > 500)
			error += "Max game height is 500";
		if(aMinBallSpeedXForBall < 1)//arbitrary # i picked, need to check actual min
			error += "Min X ball speed is 1";
		if(aMinBallSpeedYForBall < 1)//arbitrary # i picked, need to check actual min
			error += "Min Y ball speed is 1";
		if(aMaxPaddleLengthForPaddle > aWidthPlayArea)
			error += "Max paddle length cannot exceed game width";
		if(aMinPaddleLengthForPaddle < 5)
			error += "Min paddle lendth is 5";
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Block223 block223 = BlockApplication.getBlock223();
		//create game, dont need to do block223.addGame since its already added from the game constructor (in theory)
		try {
			Game newGame = new Game(aName, aNrBlocksPerLevel, aWidthPlayArea, aHeightPlayArea, aAdmin, aMinBallSpeedXForBall,
					 aMinBallSpeedYForBall, aBallSpeedIncreaseFactorForBall, aMaxPaddleLengthForPaddle, aMinPaddleLengthForPaddle, block223);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		
	}
	
	//define game settings TODO
	public static void changeGameSettings(String gameName, String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea,
			int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor,
			int aMaxPaddleLength, int aMinPaddleLength) throws InvalidInputException {
		String error = "";
		
		if(aNrBlocksPerLevel > 50)//arbitrary # i picked, need to check actual max
			error += "Cannot have more than 50 blocks per level";
		if(aWidthPlayArea < 200)
			error += "Min game width is 200";
		if(aWidthPlayArea > 500)
			error += "Max game width is 500";
		if(aHeightPlayArea < 200)
			error += "Min game height is 200";
		if(aHeightPlayArea > 500)
			error += "Max game height is 500";
		if(aMinBallSpeedX < 1)//arbitrary # i picked, need to check actual min
			error += "Min X ball speed is 1";
		if(aMinBallSpeedY < 1)//arbitrary # i picked, need to check actual min
			error += "Min Y ball speed is 1";
		if(aMaxPaddleLength > aWidthPlayArea)
			error += "Max paddle length cannot exceed game width";
		if(aMinPaddleLength < 5)
			error += "Min paddle lendth is 5";
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		//get game to change
		Block223 block223 = BlockApplication.getBlock223();
		try {
			Game gameToChange = Game.getWithName(gameName);
			
			//change game properties
			gameToChange.setNrBlocksPerLevel(aNrBlocksPerLevel);
			gameToChange.setWidthPlayArea(aWidthPlayArea);
			gameToChange.setHeightPlayArea(aHeightPlayArea);
			gameToChange.setName(aName);
			
			//change ball properties
			gameToChange.getBall().setBallSpeedIncreaseFactor(aBallSpeedIncreaseFactor);
			gameToChange.getBall().setMinBallSpeedX(aMinBallSpeedX);
			gameToChange.getBall().setMinBallSpeedY(aMinBallSpeedY);
			
			//change paddle properties
			gameToChange.getPaddle().setMaxPaddleLength(aMaxPaddleLength);
			gameToChange.getPaddle().setMinPaddleLength(aMinPaddleLength);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
	
	//delete game TODO
	public static void deleteGame(String gameName){
		Block223 block223 = BlockApplication.getBlock223();
		Game game = Game.getWithName(gameName);
		if(game != null)
			game.delete();
		
	}
	
	//update game TODO 
	public static void updateGame(String gameName, String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea,
			int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor,
			int aMaxPaddleLength, int aMinPaddleLength) throws InvalidInputException {
		
		//cheeky technique
		
		Game gameToChange = Game.getWithName(gameName);
		String name = aName;
		int numBlocks = aNrBlocksPerLevel;
		int width = aWidthPlayArea;
		int height = aHeightPlayArea;
		int xSpeed = aMinBallSpeedX;
		int ySpeed = aMinBallSpeedY;
		double speedFactor = aBallSpeedIncreaseFactor;
		int maxPaddleLength = aMaxPaddleLength;
		int minPaddleLength = aMinPaddleLength;

		if(aName == ""){
			name = gameToChange.getName();
		}
	
		if(aNrBlocksPerLevel == 0) {
			numBlocks = gameToChange.getNrBlocksPerLevel();
		}

		if(aWidthPlayArea == 0) {
			width = gameToChange.getWidthPlayArea();
		}

		if(aHeightPlayArea == 0) {
			height = gameToChange.getHeightPlayArea();
		}

		if(aMinBallSpeedX == 0) {
			xSpeed = gameToChange.getBall().getMinBallSpeedX();
		}

		if(aMinBallSpeedY == 0) {
			ySpeed = gameToChange.getBall().getMinBallSpeedY();
		}
		if(aBallSpeedIncreaseFactor == 0) {
			speedFactor = gameToChange.getBall().getBallSpeedIncreaseFactor();
		}

		if(aMaxPaddleLength == 0) {
			maxPaddleLength = gameToChange.getPaddle().getMaxPaddleLength();
		}

		if(aMinPaddleLength == 0) {
			minPaddleLength = gameToChange.getPaddle().getMinPaddleLength();
		}

		
		changeGameSettings(gameName, name, numBlocks, width, height, xSpeed, ySpeed, speedFactor, maxPaddleLength, minPaddleLength);

	}
	
	//add block to game to use in level TODO
	public static void addBlockToGame(String gameName, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {
		//get game
		String error = "";
		
		if(aRed > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aRed < 0)
			error += "RGB min value is 0";
		if(aGreen > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aGreen < 0)
			error += "RGB min value is 0";
		if(aBlue > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aBlue < 0)
			error += "RGB min value is 0";
		if(aPoints < 0)
			error += "Points min value is 0";
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		Block223 block223 = BlockApplication.getBlock223();
		try {
			Game gameToChange = Game.getWithName(gameName);
			//add block to game from block constructor
			gameToChange.addBlock(aRed, aGreen, aBlue, aPoints);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		
	}
	
	//delete block from game so it cant be used in level TODO
	public static void deleteBlockFromGame(String gameName, int blockId) throws InvalidInputException {
		//get game
		Block223 block223 = BlockApplication.getBlock223();
		try {
			Game gameToChange = Game.getWithName(gameName);
			gameToChange.removeBlock(getBlock(gameName, blockId));//calling custom getBlock method
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
	
	//update block in game TODO
	public static void updateBlock(String gameName, int blockId, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {
		String error = "";
		
		if(aRed > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aRed < 0)
			error += "RGB min value is 0";
		if(aGreen > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aGreen < 0)
			error += "RGB min value is 0";
		if(aBlue > 255)//arbitrary # i picked, need to check actual max
			error += "RGB max value is 255";
		if(aBlue < 0)
			error += "RGB min value is 0";
		if(aPoints < 0)
			error += "Points min value is 0";
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			Block blockToChange = getBlock(gameName, blockId);
			blockToChange.setRed(aRed);
			blockToChange.setGreen(aGreen);
			blockToChange.setBlue(aBlue);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
	//position a block at a grid location in level TODO
	public static void positionBlock(String gameName, int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock) throws InvalidInputException {
		String error = "";
		
		if(aLevel == null)
			error += "Level must be defined";
		if(aBlock == null)
			error += "Block must be defined";
		if(aGridHorizontalPosition < 0)
			error += "Grid X min position is 0";
		if(aGridHorizontalPosition > 20)//this is sketch in case of placing it a 15 and game window is too small, going up to only like 12
			error += "Grid X max position is 20";
		if(aGridVerticalPosition < 0)
			error += "Grid Y min position is 0";
		if(aGridVerticalPosition > 20)//this is sketch in case of placing it a 15 and game window is too small, going up to only like 12
			error += "Grid Y max position is 20";

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}

		//get game
		Block223 block223 = BlockApplication.getBlock223();
		try {
			Game gameToChange = Game.getWithName(gameName);
			//assign block to a position in a level
			gameToChange.addBlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}
	
	//move block from one grid location to another in a level TODO
	public static void moveBlock(BlockAssignment blockAssignment, int aGridHorizontalPosition, int aGridVerticalPosition) throws InvalidInputException {
		String error = "";
		
		if(blockAssignment == null)
			error += "BlockAssignment must be defined";
		if(aGridHorizontalPosition < 0)
			error += "Grid X min position is 0";
		if(aGridHorizontalPosition > 20)//this is sketch in case of placing it a 15 and game window is too small, going up to only like 12
			error += "Grid X max position is 20";
		if(aGridVerticalPosition < 0)
			error += "Grid Y min position is 0";
		if(aGridVerticalPosition > 20)//this is sketch in case of placing it a 15 and game window is too small, going up to only like 12
			error += "Grid Y max position is 20";

		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
		//get list of blocks assigned to game
			blockAssignment.setGridHorizontalPosition(aGridHorizontalPosition);
			blockAssignment.setGridVerticalPosition(aGridVerticalPosition);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

		
		
	}
	
	//remove block from level TODO
	public static void removeBlockFromLevel(int blockId , Level level) throws InvalidInputException {
		//loop through list and check if ID matches
		//cleaner way of iterating through
		try {
			for(BlockAssignment aBlockAssignment : level.getBlockAssignments()) {
				if(aBlockAssignment.getBlock().getId() == blockId) {
					level.removeBlockAssignment(aBlockAssignment);
				}
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}
	
	//save game TODO
	public static void saveGame() {
		
	}
	
	//log in/log out as player/admin TODO
	private static Block getBlock(String gameName, int blockId) {
		Game gameToChange = Game.getWithName(gameName);
		Block foundBlock = null;
		for(Block aBlock : gameToChange.getBlocks()) {
			if(aBlock.getId() == blockId) {
				foundBlock = aBlock;
			}
		}
		return foundBlock;

	}
}

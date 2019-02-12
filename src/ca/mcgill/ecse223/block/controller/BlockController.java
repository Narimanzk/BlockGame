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
	public static void changeGameSettings(int gameIndex, String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea,
			int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor,
			int aMaxPaddleLength, int aMinPaddleLength) throws InvalidInputException {
		
		//get game to change
		Block223 block223 = BlockApplication.getBlock223();
		Game gameToChange = block223.getGame(gameIndex);
		
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
	
	//delete game TODO
	public static void deleteGame(int gameIndex) {
		Block223 block223 = BlockApplication.getBlock223();
		block223.getGame(gameIndex).delete();
		
	}
	
	//update game TODO wtf is this supposed to do
	public static void updateGame() throws InvalidInputException {
		
	}
	
	//add block to game to use in level TODO
	public static void addBlockToGame(int gameIndex, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {
		//get game
		Block223 block223 = BlockApplication.getBlock223();
		Game gameToChange = block223.getGame(gameIndex);
		//add block to game from block constructor
		Block newBlock = gameToChange.addBlock(aRed, aGreen, aBlue, aPoints);
		gameToChange.addBlock(newBlock);
	}
	
	//delete block from game so it cant be used in level TODO
	public static void deleteBlockFromGame(int gameIndex, int blockId) throws InvalidInputException {
		//get game
		Block223 block223 = BlockApplication.getBlock223();

		Game gameToChange = block223.getGame(gameIndex);
		//loop through list and check if ID matches
		//cleaner way of iterating through blocks
		for(Block aBlock : gameToChange.getBlocks()) {
			if(aBlock.getId() == blockId) {
				gameToChange.removeBlock(aBlock);
			}
		}
	}
	
	//update block in game TODO wtf is this supposed to do
	public static void updateBlock() throws InvalidInputException {
		
	}
	
	//position a block at a grid location in level TODO
	public static void positionBlock(int gameIndex, int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock) throws InvalidInputException {
		//get game
		Block223 block223 = BlockApplication.getBlock223();

		Game gameToChange = block223.getGame(gameIndex);
		//assign block to a position in a level
		gameToChange.addBlockAssignment(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock);

	}
	
	//move block from one grid location to another in a level TODO
	public static void moveBlock(BlockAssignment blockAssignment, int aGridHorizontalPosition, int aGridVerticalPosition) throws InvalidInputException {
		//get game
//		Game gameToChange = Block223.getGame(gameIndex);

		//get list of blocks assigned to game
		blockAssignment.setGridHorizontalPosition(aGridHorizontalPosition);
		blockAssignment.setGridVerticalPosition(aGridVerticalPosition);
		
		
	}
	
	//remove block from level TODO
	public static void removeBlockFromLevel(int blockId , Level level) throws InvalidInputException {
		//loop through list and check if ID matches
		//cleaner way of iterating through
		for(BlockAssignment aBlockAssignment : level.getBlockAssignments()) {
			if(aBlockAssignment.getBlock().getId() == blockId) {
				level.removeBlockAssignment(aBlockAssignment);
			}
		}
	}
	
	//save game TODO
	public static void saveGame() {
		
	}
	
	//log in/log out as player/admin TODO
	
}

package ca.mcgill.ecse223.block.controller;

import java.util.List;

import ca.mcgill.ecse223.block.model.*;

public class BlockController {
	private static Block223 Block223;

	public BlockController() {
		this.Block223 = new Block223();

	}
	
	//add game TODO
	public static void addGame(String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea, Admin aAdmin,
			int aMinBallSpeedXForBall, int aMinBallSpeedYForBall, double aBallSpeedIncreaseFactorForBall,
			int aMaxPaddleLengthForPaddle, int aMinPaddleLengthForPaddle) throws InvalidInputException {
		
		//create game, dont need to do block223.addGame since its already added from the game constructor (in theory)
		Game newGame = new Game(aName, aNrBlocksPerLevel, aWidthPlayArea, aHeightPlayArea, aAdmin, aMinBallSpeedXForBall,
				 aMinBallSpeedYForBall, aBallSpeedIncreaseFactorForBall, aMaxPaddleLengthForPaddle, aMinPaddleLengthForPaddle, Block223);
	}
	
	//define game settings TODO
	public static void changeGameSettings(int gameIndex, String aName, int aNrBlocksPerLevel, int aWidthPlayArea, int aHeightPlayArea,
			int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor,
			int aMaxPaddleLength, int aMinPaddleLength) throws InvalidInputException {
		
		//get game to change
		Game gameToChange = Block223.getGame(gameIndex);
		
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
		Block223.getGame(gameIndex).delete();
		
	}
	
	//update game TODO wtf is this supposed to do
	public static void updateGame() throws InvalidInputException {
		
	}
	
	//add block to game to use in level TODO
	public static void addBlockToGame(int gameIndex, int aRed, int aGreen, int aBlue, int aPoints) throws InvalidInputException {
		//get game
		Game gameToChange = Block223.getGame(gameIndex);
		//add block to game from block constructor
		gameToChange.addBlock(aRed, aGreen, aBlue, aPoints);
		
	}
	
	//delete block from game so it cant be used in level TODO
	public static void deleteBlockFromGame(int gameIndex, int blockId) throws InvalidInputException {
		Game gameToChange = Block223.getGame(gameIndex);
		//get list of blocks assigned to game
		List<Block> myBlocks = gameToChange.getBlocks();
		//loop through list and check if ID matches
		for(int i = 0; i < myBlocks.size(); i++) {
			if(myBlocks.get(i).getId() == blockId) {//if ID matches remove it from game
				gameToChange.removeBlock(myBlocks.get(i));
			}
		}
	}
	
	//update block in game TODO
	public static void updateBlock() throws InvalidInputException {
		
	}
	
	//position a block at a grid location in level TODO
	public static void positionBlock() throws InvalidInputException {
		
	}
	
	//move block from one grid location to another in a level TODO
	public static void moveBlock() throws InvalidInputException {
		
	}
	
	//remove block from level TODO
	public static void removeBlockFromLevel() throws InvalidInputException {
		
	}
	
	//save game TODO
	public static void saveGame() {
		
	}
	
	//log in/log out as player/admin TODO
	
}

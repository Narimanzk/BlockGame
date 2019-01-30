package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 51 "Block223.ump"
public class BlockType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockType Attributes
  private int points;
  private int colour;
  private int sideLength;

  //BlockType Associations
  private List<Block> blocks;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockType(int aPoints, int aColour, Game aGame)
  {
    points = aPoints;
    colour = aColour;
    sideLength = 20;
    blocks = new ArrayList<Block>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create blockType due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setColour(int aColour)
  {
    boolean wasSet = false;
    colour = aColour;
    wasSet = true;
    return wasSet;
  }

  public boolean setSideLength(int aSideLength)
  {
    boolean wasSet = false;
    sideLength = aSideLength;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  /**
   * data type to be changed later
   */
  public int getColour()
  {
    return colour;
  }

  public int getSideLength()
  {
    return sideLength;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(int aX, int aY, Game aGame, Level aLevel)
  {
    return new Block(aX, aY, aGame, this, aLevel);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    BlockType existingBlockType = aBlock.getBlockType();
    boolean isNewBlockType = existingBlockType != null && !this.equals(existingBlockType);
    if (isNewBlockType)
    {
      aBlock.setBlockType(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a blockType
    if (!this.equals(aBlock.getBlockType()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(Block aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeBlockType(this);
    }
    game.addBlockType(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=blocks.size(); i > 0; i--)
    {
      Block aBlock = blocks.get(i - 1);
      aBlock.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlockType(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "colour" + ":" + getColour()+ "," +
            "sideLength" + ":" + getSideLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
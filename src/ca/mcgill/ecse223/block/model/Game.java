package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 33 "Block223.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String name;
  private int minSpeed;
  private int maxSpeed;
  private int minLength;
  private int maxLength;
  private float speedFactor;
  private int height;
  private int width;
  private int numLevels;

  //Game Associations
  private Admin admin;
  private List<BlockType> blockTypes;
  private List<Block> blocks;
  private List<Level> levels;
  private Ball ball;
  private Paddle paddle;
  private HallOfFame hallOfFame;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, int aNumLevels, Admin aAdmin, Ball aBall, Paddle aPaddle, HallOfFame aHallOfFame, Block223 aBlock223)
  {
    name = aName;
    minSpeed = aMinSpeed;
    maxSpeed = aMaxSpeed;
    minLength = aMinLength;
    maxLength = aMaxLength;
    speedFactor = aSpeedFactor;
    height = aHeight;
    width = aWidth;
    numLevels = aNumLevels;
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blockTypes = new ArrayList<BlockType>();
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    if (aBall == null || aBall.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aBall");
    }
    ball = aBall;
    if (aPaddle == null || aPaddle.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aPaddle");
    }
    paddle = aPaddle;
    if (aHallOfFame == null || aHallOfFame.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aHallOfFame");
    }
    hallOfFame = aHallOfFame;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  public Game(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, int aNumLevels, Admin aAdmin, int aCurrentSpeedForBall, int aLengthForPaddle, String aNameForHallOfFame, Block223 aBlock223)
  {
    name = aName;
    minSpeed = aMinSpeed;
    maxSpeed = aMaxSpeed;
    minLength = aMinLength;
    maxLength = aMaxLength;
    speedFactor = aSpeedFactor;
    height = aHeight;
    width = aWidth;
    numLevels = aNumLevels;
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
    blockTypes = new ArrayList<BlockType>();
    blocks = new ArrayList<Block>();
    levels = new ArrayList<Level>();
    ball = new Ball(aCurrentSpeedForBall, this);
    paddle = new Paddle(aLengthForPaddle, this);
    hallOfFame = new HallOfFame(aNameForHallOfFame, this);
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create game due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinSpeed(int aMinSpeed)
  {
    boolean wasSet = false;
    minSpeed = aMinSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxSpeed(int aMaxSpeed)
  {
    boolean wasSet = false;
    maxSpeed = aMaxSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinLength(int aMinLength)
  {
    boolean wasSet = false;
    minLength = aMinLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxLength(int aMaxLength)
  {
    boolean wasSet = false;
    maxLength = aMaxLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedFactor(float aSpeedFactor)
  {
    boolean wasSet = false;
    speedFactor = aSpeedFactor;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLevels(int aNumLevels)
  {
    boolean wasSet = false;
    numLevels = aNumLevels;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getMinSpeed()
  {
    return minSpeed;
  }

  public int getMaxSpeed()
  {
    return maxSpeed;
  }

  public int getMinLength()
  {
    return minLength;
  }

  public int getMaxLength()
  {
    return maxLength;
  }

  public float getSpeedFactor()
  {
    return speedFactor;
  }

  /**
   * int minHeight = 200;
   * int minWidth = 200;
   * int maxHeight = 500;
   * int maxWidth = 500;
   */
  public int getHeight()
  {
    return height;
  }

  public int getWidth()
  {
    return width;
  }

  public int getNumLevels()
  {
    return numLevels;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public BlockType getBlockType(int index)
  {
    BlockType aBlockType = blockTypes.get(index);
    return aBlockType;
  }

  public List<BlockType> getBlockTypes()
  {
    List<BlockType> newBlockTypes = Collections.unmodifiableList(blockTypes);
    return newBlockTypes;
  }

  public int numberOfBlockTypes()
  {
    int number = blockTypes.size();
    return number;
  }

  public boolean hasBlockTypes()
  {
    boolean has = blockTypes.size() > 0;
    return has;
  }

  public int indexOfBlockType(BlockType aBlockType)
  {
    int index = blockTypes.indexOf(aBlockType);
    return index;
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
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockType addBlockType(int aPoints, int aColour)
  {
    return new BlockType(aPoints, aColour, this);
  }

  public boolean addBlockType(BlockType aBlockType)
  {
    boolean wasAdded = false;
    if (blockTypes.contains(aBlockType)) { return false; }
    Game existingGame = aBlockType.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlockType.setGame(this);
    }
    else
    {
      blockTypes.add(aBlockType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockType(BlockType aBlockType)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockType, as it must always have a game
    if (!this.equals(aBlockType.getGame()))
    {
      blockTypes.remove(aBlockType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockTypeAt(BlockType aBlockType, int index)
  {  
    boolean wasAdded = false;
    if(addBlockType(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockTypeAt(BlockType aBlockType, int index)
  {
    boolean wasAdded = false;
    if(blockTypes.contains(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockTypeAt(aBlockType, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(int aX, int aY, BlockType aBlockType, Level aLevel)
  {
    return new Block(aX, aY, this, aBlockType, aLevel);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Game existingGame = aBlock.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aBlock.setGame(this);
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
    //Unable to remove aBlock, as it must always have a game
    if (!this.equals(aBlock.getGame()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Level addLevel(int aNumber, boolean aIsRandom)
  {
    return new Level(aNumber, aIsRandom, this);
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (!this.equals(aLevel.getGame()))
    {
      levels.remove(aLevel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removeGame(this);
    }
    block223.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
    while (blockTypes.size() > 0)
    {
      BlockType aBlockType = blockTypes.get(blockTypes.size() - 1);
      aBlockType.delete();
      blockTypes.remove(aBlockType);
    }
    
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = null;
    if (existingHallOfFame != null)
    {
      existingHallOfFame.delete();
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "minSpeed" + ":" + getMinSpeed()+ "," +
            "maxSpeed" + ":" + getMaxSpeed()+ "," +
            "minLength" + ":" + getMinLength()+ "," +
            "maxLength" + ":" + getMaxLength()+ "," +
            "speedFactor" + ":" + getSpeedFactor()+ "," +
            "height" + ":" + getHeight()+ "," +
            "width" + ":" + getWidth()+ "," +
            "numLevels" + ":" + getNumLevels()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
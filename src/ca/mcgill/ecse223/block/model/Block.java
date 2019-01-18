package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 68 "Block223.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private int x;
  private int y;

  //Block Associations
  private Game game;
  private BlockType blockType;
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(int aX, int aY, Game aGame, BlockType aBlockType, Level aLevel)
  {
    x = aX;
    y = aY;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create block due to game");
    }
    boolean didAddBlockType = setBlockType(aBlockType);
    if (!didAddBlockType)
    {
      throw new RuntimeException("Unable to create block due to blockType");
    }
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(int aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(int aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public BlockType getBlockType()
  {
    return blockType;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
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
      existingGame.removeBlock(this);
    }
    game.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockType(BlockType aBlockType)
  {
    boolean wasSet = false;
    if (aBlockType == null)
    {
      return wasSet;
    }

    BlockType existingBlockType = blockType;
    blockType = aBlockType;
    if (existingBlockType != null && !existingBlockType.equals(aBlockType))
    {
      existingBlockType.removeBlock(this);
    }
    blockType.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLevel(Level aLevel)
  {
    boolean wasSet = false;
    if (aLevel == null)
    {
      return wasSet;
    }

    Level existingLevel = level;
    level = aLevel;
    if (existingLevel != null && !existingLevel.equals(aLevel))
    {
      existingLevel.removeBlock(this);
    }
    level.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlock(this);
    }
    BlockType placeholderBlockType = blockType;
    this.blockType = null;
    if(placeholderBlockType != null)
    {
      placeholderBlockType.removeBlock(this);
    }
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockType = "+(getBlockType()!=null?Integer.toHexString(System.identityHashCode(getBlockType())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}
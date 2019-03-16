/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 29 "../../../../../Block223Play.ump"
public class GameInPlay extends Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameInPlay Attributes
  private int levelIndex;

  //GameInPlay Associations
  private List<BlockInPlay> blocksOnTheField;
  private Play play;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameInPlay(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223, HallOfFame aHallOfFame, int aLevelIndex, Play aPlay)
  {
    super(aName, aNrBlocksPerLevel, aAdmin, aBall, aPaddle, aBlock223, aHallOfFame);
    levelIndex = aLevelIndex;
    blocksOnTheField = new ArrayList<BlockInPlay>();
    if (aPlay == null || aPlay.getGame() != null)
    {
      throw new RuntimeException("Unable to create GameInPlay due to aPlay");
    }
    play = aPlay;
  }

  public GameInPlay(String aName, int aNrBlocksPerLevel, Admin aAdmin, Ball aBall, Paddle aPaddle, Block223 aBlock223, HallOfFame aHallOfFame, int aLevelIndex, boolean aIsTestForPlay, BallInPlay aBallForPlay, PaddleInPlay aPaddleForPlay)
  {
    super(aName, aNrBlocksPerLevel, aAdmin, aBall, aPaddle, aBlock223, aHallOfFame);
    levelIndex = aLevelIndex;
    blocksOnTheField = new ArrayList<BlockInPlay>();
    play = new Play(aIsTestForPlay, aBallForPlay, aPaddleForPlay, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevelIndex(int aLevelIndex)
  {
    boolean wasSet = false;
    levelIndex = aLevelIndex;
    wasSet = true;
    return wasSet;
  }

  public int getLevelIndex()
  {
    return levelIndex;
  }
  /* Code from template association_GetMany */
  public BlockInPlay getBlocksOnTheField(int index)
  {
    BlockInPlay aBlocksOnTheField = blocksOnTheField.get(index);
    return aBlocksOnTheField;
  }

  public List<BlockInPlay> getBlocksOnTheField()
  {
    List<BlockInPlay> newBlocksOnTheField = Collections.unmodifiableList(blocksOnTheField);
    return newBlocksOnTheField;
  }

  public int numberOfBlocksOnTheField()
  {
    int number = blocksOnTheField.size();
    return number;
  }

  public boolean hasBlocksOnTheField()
  {
    boolean has = blocksOnTheField.size() > 0;
    return has;
  }

  public int indexOfBlocksOnTheField(BlockInPlay aBlocksOnTheField)
  {
    int index = blocksOnTheField.indexOf(aBlocksOnTheField);
    return index;
  }
  /* Code from template association_GetOne */
  public Play getPlay()
  {
    return play;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocksOnTheField()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockInPlay addBlocksOnTheField(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame, LevelInPlay aLevelInPlay)
  {
    return new BlockInPlay(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, aGame, this, aLevelInPlay);
  }

  public boolean addBlocksOnTheField(BlockInPlay aBlocksOnTheField)
  {
    boolean wasAdded = false;
    if (blocksOnTheField.contains(aBlocksOnTheField)) { return false; }
    GameInPlay existingGameInPlay = aBlocksOnTheField.getGameInPlay();
    boolean isNewGameInPlay = existingGameInPlay != null && !this.equals(existingGameInPlay);
    if (isNewGameInPlay)
    {
      aBlocksOnTheField.setGameInPlay(this);
    }
    else
    {
      blocksOnTheField.add(aBlocksOnTheField);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlocksOnTheField(BlockInPlay aBlocksOnTheField)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlocksOnTheField, as it must always have a gameInPlay
    if (!this.equals(aBlocksOnTheField.getGameInPlay()))
    {
      blocksOnTheField.remove(aBlocksOnTheField);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlocksOnTheFieldAt(BlockInPlay aBlocksOnTheField, int index)
  {  
    boolean wasAdded = false;
    if(addBlocksOnTheField(aBlocksOnTheField))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocksOnTheField()) { index = numberOfBlocksOnTheField() - 1; }
      blocksOnTheField.remove(aBlocksOnTheField);
      blocksOnTheField.add(index, aBlocksOnTheField);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlocksOnTheFieldAt(BlockInPlay aBlocksOnTheField, int index)
  {
    boolean wasAdded = false;
    if(blocksOnTheField.contains(aBlocksOnTheField))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocksOnTheField()) { index = numberOfBlocksOnTheField() - 1; }
      blocksOnTheField.remove(aBlocksOnTheField);
      blocksOnTheField.add(index, aBlocksOnTheField);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlocksOnTheFieldAt(aBlocksOnTheField, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=blocksOnTheField.size(); i > 0; i--)
    {
      BlockInPlay aBlocksOnTheField = blocksOnTheField.get(i - 1);
      aBlocksOnTheField.delete();
    }
    Play existingPlay = play;
    play = null;
    if (existingPlay != null)
    {
      existingPlay.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "levelIndex" + ":" + getLevelIndex()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "play = "+(getPlay()!=null?Integer.toHexString(System.identityHashCode(getPlay())):"null");
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 35 "../../../../../Block223Play.ump"
public class LevelInPlay extends Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LevelInPlay Associations
  private List<BlockInPlay> blocksOnTheField;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LevelInPlay(Game aGame)
  {
    super(aGame);
    blocksOnTheField = new ArrayList<BlockInPlay>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocksOnTheField()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockInPlay addBlocksOnTheField(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame, GameInPlay aGameInPlay)
  {
    return new BlockInPlay(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, aGame, aGameInPlay, this);
  }

  public boolean addBlocksOnTheField(BlockInPlay aBlocksOnTheField)
  {
    boolean wasAdded = false;
    if (blocksOnTheField.contains(aBlocksOnTheField)) { return false; }
    LevelInPlay existingLevelInPlay = aBlocksOnTheField.getLevelInPlay();
    boolean isNewLevelInPlay = existingLevelInPlay != null && !this.equals(existingLevelInPlay);
    if (isNewLevelInPlay)
    {
      aBlocksOnTheField.setLevelInPlay(this);
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
    //Unable to remove aBlocksOnTheField, as it must always have a levelInPlay
    if (!this.equals(aBlocksOnTheField.getLevelInPlay()))
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
    super.delete();
  }

}
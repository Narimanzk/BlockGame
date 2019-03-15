/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 25 "../../../../../Block223Play.ump"
public class GameInPlay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GameInPlay Associations
  private List<BlockInPlay> blocksOnTheField;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GameInPlay()
  {
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
  public BlockInPlay addBlocksOnTheField(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame)
  {
    return new BlockInPlay(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, aGame, this);
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
  }

}
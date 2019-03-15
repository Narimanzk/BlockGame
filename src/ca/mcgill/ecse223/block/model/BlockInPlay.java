/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 22 "../../../../../Block223Play.ump"
public class BlockInPlay extends BlockAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockInPlay Associations
  private GameInPlay gameInPlay;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockInPlay(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame, GameInPlay aGameInPlay)
  {
    super(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, aGame);
    boolean didAddGameInPlay = setGameInPlay(aGameInPlay);
    if (!didAddGameInPlay)
    {
      throw new RuntimeException("Unable to create blocksOnTheField due to gameInPlay");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public GameInPlay getGameInPlay()
  {
    return gameInPlay;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGameInPlay(GameInPlay aGameInPlay)
  {
    boolean wasSet = false;
    if (aGameInPlay == null)
    {
      return wasSet;
    }

    GameInPlay existingGameInPlay = gameInPlay;
    gameInPlay = aGameInPlay;
    if (existingGameInPlay != null && !existingGameInPlay.equals(aGameInPlay))
    {
      existingGameInPlay.removeBlocksOnTheField(this);
    }
    gameInPlay.addBlocksOnTheField(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    GameInPlay placeholderGameInPlay = gameInPlay;
    this.gameInPlay = null;
    if(placeholderGameInPlay != null)
    {
      placeholderGameInPlay.removeBlocksOnTheField(this);
    }
    super.delete();
  }

}
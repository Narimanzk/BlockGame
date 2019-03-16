/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 25 "../../../../../Block223Play.ump"
public class BlockInPlay extends BlockAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockInPlay Associations
  private GameInPlay gameInPlay;
  private LevelInPlay levelInPlay;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockInPlay(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame, GameInPlay aGameInPlay, LevelInPlay aLevelInPlay)
  {
    super(aGridHorizontalPosition, aGridVerticalPosition, aLevel, aBlock, aGame);
    boolean didAddGameInPlay = setGameInPlay(aGameInPlay);
    if (!didAddGameInPlay)
    {
      throw new RuntimeException("Unable to create blocksOnTheField due to gameInPlay");
    }
    boolean didAddLevelInPlay = setLevelInPlay(aLevelInPlay);
    if (!didAddLevelInPlay)
    {
      throw new RuntimeException("Unable to create blocksOnTheField due to levelInPlay");
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
  /* Code from template association_GetOne */
  public LevelInPlay getLevelInPlay()
  {
    return levelInPlay;
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
  /* Code from template association_SetOneToMany */
  public boolean setLevelInPlay(LevelInPlay aLevelInPlay)
  {
    boolean wasSet = false;
    if (aLevelInPlay == null)
    {
      return wasSet;
    }

    LevelInPlay existingLevelInPlay = levelInPlay;
    levelInPlay = aLevelInPlay;
    if (existingLevelInPlay != null && !existingLevelInPlay.equals(aLevelInPlay))
    {
      existingLevelInPlay.removeBlocksOnTheField(this);
    }
    levelInPlay.addBlocksOnTheField(this);
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
    LevelInPlay placeholderLevelInPlay = levelInPlay;
    this.levelInPlay = null;
    if(placeholderLevelInPlay != null)
    {
      placeholderLevelInPlay.removeBlocksOnTheField(this);
    }
    super.delete();
  }

}
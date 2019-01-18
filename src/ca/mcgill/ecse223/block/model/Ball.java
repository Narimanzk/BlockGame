package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 46 "Block223.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int currentSpeed;
  private int diameter;

  //Ball Associations
  private Block223 block223;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aCurrentSpeed, Block223 aBlock223, Game aGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create ball due to block223");
    }
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aCurrentSpeed, Block223 aBlock223, String aNameForGame, int aMinSpeedForGame, int aMaxSpeedForGame, int aMinLengthForGame, int aMaxLengthForGame, float aSpeedFactorForGame, int aHeightForGame, int aWidthForGame, int aNumLevelsForGame, Admin aAdminForGame, Paddle aPaddleForGame, HallOfFame aHallOfFameForGame, Block223 aBlock223ForGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create ball due to block223");
    }
    game = new Game(aNameForGame, aMinSpeedForGame, aMaxSpeedForGame, aMinLengthForGame, aMaxLengthForGame, aSpeedFactorForGame, aHeightForGame, aWidthForGame, aNumLevelsForGame, aAdminForGame, this, aPaddleForGame, aHallOfFameForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentSpeed(int aCurrentSpeed)
  {
    boolean wasSet = false;
    currentSpeed = aCurrentSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiameter(int aDiameter)
  {
    boolean wasSet = false;
    diameter = aDiameter;
    wasSet = true;
    return wasSet;
  }

  public int getCurrentSpeed()
  {
    return currentSpeed;
  }

  public int getDiameter()
  {
    return diameter;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingBlock223.removeBall(this);
    }
    block223.addBall(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeBall(this);
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "currentSpeed" + ":" + getCurrentSpeed()+ "," +
            "diameter" + ":" + getDiameter()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
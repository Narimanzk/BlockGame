package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 56 "Block223.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int currentSpeed;
  private int diameter;

  //Ball Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aCurrentSpeed, Game aGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aCurrentSpeed, String aNameForGame, int aMinSpeedForGame, int aMaxSpeedForGame, int aMinLengthForGame, int aMaxLengthForGame, float aSpeedFactorForGame, int aHeightForGame, int aWidthForGame, int aNumLevelsForGame, Admin aAdminForGame, Paddle aPaddleForGame, HallOfFame aHallOfFameForGame, Block223 aBlock223ForGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
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
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
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
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
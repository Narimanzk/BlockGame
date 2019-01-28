/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 45 "Block223.ump"
public class Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private float currentSpeed;
  private int diameter;
  private float degrees;

  //Ball Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(float aCurrentSpeed, float aDegrees, Game aGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
    degrees = aDegrees;
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(float aCurrentSpeed, float aDegrees, String aNameForGame, int aMinSpeedForGame, int aMaxSpeedForGame, int aMinLengthForGame, int aMaxLengthForGame, float aSpeedFactorForGame, int aHeightForGame, int aWidthForGame, User aUserForGame, Paddle aPaddleForGame, HallOfFame aHallOfFameForGame, Block223 aBlock223ForGame)
  {
    currentSpeed = aCurrentSpeed;
    diameter = 10;
    degrees = aDegrees;
    game = new Game(aNameForGame, aMinSpeedForGame, aMaxSpeedForGame, aMinLengthForGame, aMaxLengthForGame, aSpeedFactorForGame, aHeightForGame, aWidthForGame, aUserForGame, this, aPaddleForGame, aHallOfFameForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentSpeed(float aCurrentSpeed)
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

  public boolean setDegrees(float aDegrees)
  {
    boolean wasSet = false;
    degrees = aDegrees;
    wasSet = true;
    return wasSet;
  }

  public float getCurrentSpeed()
  {
    return currentSpeed;
  }

  public int getDiameter()
  {
    return diameter;
  }

  public float getDegrees()
  {
    return degrees;
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
            "diameter" + ":" + getDiameter()+ "," +
            "degrees" + ":" + getDegrees()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
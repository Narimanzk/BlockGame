/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 1 "../../../../../Block223Play.ump"
public class BallInPlay extends Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BallInPlay Attributes
  private int xCurSpeed;
  private int yCurSpeed;
  private int xCurPos;
  private int yCurPos;

  //BallInPlay Associations
  private Play play;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BallInPlay(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Game aGame, int aXCurSpeed, int aYCurSpeed, int aXCurPos, int aYCurPos, Play aPlay)
  {
    super(aMinBallSpeedX, aMinBallSpeedY, aBallSpeedIncreaseFactor, aGame);
    xCurSpeed = aXCurSpeed;
    yCurSpeed = aYCurSpeed;
    xCurPos = aXCurPos;
    yCurPos = aYCurPos;
    if (aPlay == null || aPlay.getBall() != null)
    {
      throw new RuntimeException("Unable to create BallInPlay due to aPlay");
    }
    play = aPlay;
  }

  public BallInPlay(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Game aGame, int aXCurSpeed, int aYCurSpeed, int aXCurPos, int aYCurPos, boolean aIsTestForPlay, PaddleInPlay aPaddleForPlay, GameInPlay aGameForPlay)
  {
    super(aMinBallSpeedX, aMinBallSpeedY, aBallSpeedIncreaseFactor, aGame);
    xCurSpeed = aXCurSpeed;
    yCurSpeed = aYCurSpeed;
    xCurPos = aXCurPos;
    yCurPos = aYCurPos;
    play = new Play(aIsTestForPlay, this, aPaddleForPlay, aGameForPlay);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXCurSpeed(int aXCurSpeed)
  {
    boolean wasSet = false;
    xCurSpeed = aXCurSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setYCurSpeed(int aYCurSpeed)
  {
    boolean wasSet = false;
    yCurSpeed = aYCurSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setXCurPos(int aXCurPos)
  {
    boolean wasSet = false;
    xCurPos = aXCurPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setYCurPos(int aYCurPos)
  {
    boolean wasSet = false;
    yCurPos = aYCurPos;
    wasSet = true;
    return wasSet;
  }

  public int getXCurSpeed()
  {
    return xCurSpeed;
  }

  public int getYCurSpeed()
  {
    return yCurSpeed;
  }

  public int getXCurPos()
  {
    return xCurPos;
  }

  public int getYCurPos()
  {
    return yCurPos;
  }
  /* Code from template association_GetOne */
  public Play getPlay()
  {
    return play;
  }

  public void delete()
  {
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
            "xCurSpeed" + ":" + getXCurSpeed()+ "," +
            "yCurSpeed" + ":" + getYCurSpeed()+ "," +
            "xCurPos" + ":" + getXCurPos()+ "," +
            "yCurPos" + ":" + getYCurPos()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "play = "+(getPlay()!=null?Integer.toHexString(System.identityHashCode(getPlay())):"null");
  }
}
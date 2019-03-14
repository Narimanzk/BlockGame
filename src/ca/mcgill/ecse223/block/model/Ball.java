/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 9 "../../../../../Block223Persistence.ump"
// line 18 "../../../../../Block223Play.ump"
// line 135 "../../../../../Block223.ump"
public class Ball implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int BALL_DIAMETER = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int xSpeed;
  private int ySpeed;
  private int xPos;
  private int yPos;
  private int minBallSpeedX;
  private int minBallSpeedY;
  private double ballSpeedIncreaseFactor;

  //Ball Associations
  private Play play;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aXSpeed, int aYSpeed, int aXPos, int aYPos, int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Play aPlay, Game aGame)
  {
    xSpeed = aXSpeed;
    ySpeed = aYSpeed;
    xPos = aXPos;
    yPos = aYPos;
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    if (aPlay == null || aPlay.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aPlay");
    }
    play = aPlay;
    if (aGame == null || aGame.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aGame");
    }
    game = aGame;
  }

  public Ball(int aXSpeed, int aYSpeed, int aXPos, int aYPos, int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, String aNameForPlay, int aNrBlocksPerLevelForPlay, Paddle aPaddleForPlay, Player aPlayerForPlay, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    xSpeed = aXSpeed;
    ySpeed = aYSpeed;
    xPos = aXPos;
    yPos = aYPos;
    minBallSpeedX = aMinBallSpeedX;
    minBallSpeedY = aMinBallSpeedY;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    play = new Play(aNameForPlay, aNrBlocksPerLevelForPlay, this, aPaddleForPlay, aPlayerForPlay);
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, this, aPaddleForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXSpeed(int aXSpeed)
  {
    boolean wasSet = false;
    xSpeed = aXSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setYSpeed(int aYSpeed)
  {
    boolean wasSet = false;
    ySpeed = aYSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setXPos(int aXPos)
  {
    boolean wasSet = false;
    xPos = aXPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setYPos(int aYPos)
  {
    boolean wasSet = false;
    yPos = aYPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedX(int aMinBallSpeedX)
  {
    boolean wasSet = false;
    minBallSpeedX = aMinBallSpeedX;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeedY(int aMinBallSpeedY)
  {
    boolean wasSet = false;
    minBallSpeedY = aMinBallSpeedY;
    wasSet = true;
    return wasSet;
  }

  public boolean setBallSpeedIncreaseFactor(double aBallSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    ballSpeedIncreaseFactor = aBallSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public int getXSpeed()
  {
    return xSpeed;
  }

  public int getYSpeed()
  {
    return ySpeed;
  }

  public int getXPos()
  {
    return xPos;
  }

  public int getYPos()
  {
    return yPos;
  }

  public int getMinBallSpeedX()
  {
    return minBallSpeedX;
  }

  public int getMinBallSpeedY()
  {
    return minBallSpeedY;
  }

  public double getBallSpeedIncreaseFactor()
  {
    return ballSpeedIncreaseFactor;
  }
  /* Code from template association_GetOne */
  public Play getPlay()
  {
    return play;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Play existingPlay = play;
    play = null;
    if (existingPlay != null)
    {
      existingPlay.delete();
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
            "xSpeed" + ":" + getXSpeed()+ "," +
            "ySpeed" + ":" + getYSpeed()+ "," +
            "xPos" + ":" + getXPos()+ "," +
            "yPos" + ":" + getYPos()+ "," +
            "minBallSpeedX" + ":" + getMinBallSpeedX()+ "," +
            "minBallSpeedY" + ":" + getMinBallSpeedY()+ "," +
            "ballSpeedIncreaseFactor" + ":" + getBallSpeedIncreaseFactor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "play = "+(getPlay()!=null?Integer.toHexString(System.identityHashCode(getPlay())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 12 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 6076580571713169673L ;

  
}
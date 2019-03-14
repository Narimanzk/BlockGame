/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 68 "../../../../../Block223Persistence.ump"
// line 24 "../../../../../Block223Play.ump"
// line 159 "../../../../../Block223.ump"
public class Paddle implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int PADDLE_WIDTH = 5;
  public static final int VERTICAL_DISTANCE = 30;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int xPos;
  private int yPos;
  private int curLength;
  private int maxPaddleLength;
  private int minPaddleLength;

  //Paddle Associations
  private Play play;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aXPos, int aYPos, int aCurLength, int aMaxPaddleLength, int aMinPaddleLength, Play aPlay, Game aGame)
  {
    xPos = aXPos;
    yPos = aYPos;
    curLength = aCurLength;
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    if (aPlay == null || aPlay.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aPlay");
    }
    play = aPlay;
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(int aXPos, int aYPos, int aCurLength, int aMaxPaddleLength, int aMinPaddleLength, String aNameForPlay, int aNrBlocksPerLevelForPlay, Ball aBallForPlay, Player aPlayerForPlay, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Block223 aBlock223ForGame)
  {
    xPos = aXPos;
    yPos = aYPos;
    curLength = aCurLength;
    maxPaddleLength = aMaxPaddleLength;
    minPaddleLength = aMinPaddleLength;
    play = new Play(aNameForPlay, aNrBlocksPerLevelForPlay, aBallForPlay, this, aPlayerForPlay);
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, this, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setCurLength(int aCurLength)
  {
    boolean wasSet = false;
    curLength = aCurLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public int getXPos()
  {
    return xPos;
  }

  public int getYPos()
  {
    return yPos;
  }

  public int getCurLength()
  {
    return curLength;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
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
            "xPos" + ":" + getXPos()+ "," +
            "yPos" + ":" + getYPos()+ "," +
            "curLength" + ":" + getCurLength()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "play = "+(getPlay()!=null?Integer.toHexString(System.identityHashCode(getPlay())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 71 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 4417469660219235016L ;

  
}
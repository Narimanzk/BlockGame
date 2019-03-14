/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 74 "../../../../../Block223Persistence.ump"
// line 30 "../../../../../Block223Play.ump"
// line 42 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int points;
  private int livesLeft;
  private String name;

  //Player Associations
  private Play play;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223, int aPoints, int aLivesLeft, String aName, Play aPlay)
  {
    super(aPassword, aBlock223);
    points = aPoints;
    livesLeft = aLivesLeft;
    name = aName;
    if (aPlay == null || aPlay.getPlayer() != null)
    {
      throw new RuntimeException("Unable to create Player due to aPlay");
    }
    play = aPlay;
  }

  public Player(String aPassword, Block223 aBlock223, int aPoints, int aLivesLeft, String aName, String aNameForPlay, int aNrBlocksPerLevelForPlay, Ball aBallForPlay, Paddle aPaddleForPlay)
  {
    super(aPassword, aBlock223);
    points = aPoints;
    livesLeft = aLivesLeft;
    name = aName;
    play = new Play(aNameForPlay, aNrBlocksPerLevelForPlay, aBallForPlay, aPaddleForPlay, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setLivesLeft(int aLivesLeft)
  {
    boolean wasSet = false;
    livesLeft = aLivesLeft;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  public int getLivesLeft()
  {
    return livesLeft;
  }

  public String getName()
  {
    return name;
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
            "points" + ":" + getPoints()+ "," +
            "livesLeft" + ":" + getLivesLeft()+ "," +
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "play = "+(getPlay()!=null?Integer.toHexString(System.identityHashCode(getPlay())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 77 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 6711395097444586117L ;

  
}
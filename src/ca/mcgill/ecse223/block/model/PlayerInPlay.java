/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 31 "../../../../../Block223Play.ump"
public class PlayerInPlay extends Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayerInPlay Attributes
  private int points;
  private int livesLeft;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayerInPlay(String aPassword, Block223 aBlock223, HallOfFame aHallOfFame, int aPoints, int aLivesLeft)
  {
    super(aPassword, aBlock223, aHallOfFame);
    points = aPoints;
    livesLeft = aLivesLeft;
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

  public int getPoints()
  {
    return points;
  }

  public int getLivesLeft()
  {
    return livesLeft;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "livesLeft" + ":" + getLivesLeft()+ "]";
  }
}
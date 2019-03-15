/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 31 "Block223Play.ump"
public class PlayerInPlay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayerInPlay Attributes
  private int points;
  private int livesLeft;
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayerInPlay(int aPoints, int aLivesLeft, String aName)
  {
    points = aPoints;
    livesLeft = aLivesLeft;
    name = aName;
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

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "livesLeft" + ":" + getLivesLeft()+ "," +
            "name" + ":" + getName()+ "]";
  }
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 15 "../../../../../Block223Play.ump"
public class PlayerInPlay extends Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayerInPlay Attributes
  private int points;
  private int livesLeft;

  //PlayerInPlay Associations
  private HallOfFame hallOfFame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayerInPlay(String aPassword, Block223 aBlock223, int aPoints, int aLivesLeft, HallOfFame aHallOfFame)
  {
    super(aPassword, aBlock223);
    points = aPoints;
    livesLeft = aLivesLeft;
    boolean didAddHallOfFame = setHallOfFame(aHallOfFame);
    if (!didAddHallOfFame)
    {
      throw new RuntimeException("Unable to create entry due to hallOfFame");
    }
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
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasSet = false;
    if (aHallOfFame == null)
    {
      return wasSet;
    }

    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = aHallOfFame;
    if (existingHallOfFame != null && !existingHallOfFame.equals(aHallOfFame))
    {
      existingHallOfFame.removeEntry(this);
    }
    hallOfFame.addEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    HallOfFame placeholderHallOfFame = hallOfFame;
    this.hallOfFame = null;
    if(placeholderHallOfFame != null)
    {
      placeholderHallOfFame.removeEntry(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "livesLeft" + ":" + getLivesLeft()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null");
  }
}
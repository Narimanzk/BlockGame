package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 23 "Block223.ump"
public class Player extends PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int points;
  private int lives;

  //Player Associations
  private List<HallOfFame> hallOfFames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223, int aPoints, int aLives)
  {
    super(aPassword, aBlock223);
    points = aPoints;
    lives = aLives;
    hallOfFames = new ArrayList<HallOfFame>();
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

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  public int getLives()
  {
    return lives;
  }
  /* Code from template association_GetMany */
  public HallOfFame getHallOfFame(int index)
  {
    HallOfFame aHallOfFame = hallOfFames.get(index);
    return aHallOfFame;
  }

  public List<HallOfFame> getHallOfFames()
  {
    List<HallOfFame> newHallOfFames = Collections.unmodifiableList(hallOfFames);
    return newHallOfFames;
  }

  public int numberOfHallOfFames()
  {
    int number = hallOfFames.size();
    return number;
  }

  public boolean hasHallOfFames()
  {
    boolean has = hallOfFames.size() > 0;
    return has;
  }

  public int indexOfHallOfFame(HallOfFame aHallOfFame)
  {
    int index = hallOfFames.indexOf(aHallOfFame);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHallOfFames()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasAdded = false;
    if (hallOfFames.contains(aHallOfFame)) { return false; }
    hallOfFames.add(aHallOfFame);
    if (aHallOfFame.indexOfPlayer(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aHallOfFame.addPlayer(this);
      if (!wasAdded)
      {
        hallOfFames.remove(aHallOfFame);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasRemoved = false;
    if (!hallOfFames.contains(aHallOfFame))
    {
      return wasRemoved;
    }

    int oldIndex = hallOfFames.indexOf(aHallOfFame);
    hallOfFames.remove(oldIndex);
    if (aHallOfFame.indexOfPlayer(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aHallOfFame.removePlayer(this);
      if (!wasRemoved)
      {
        hallOfFames.add(oldIndex,aHallOfFame);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHallOfFameAt(HallOfFame aHallOfFame, int index)
  {  
    boolean wasAdded = false;
    if(addHallOfFame(aHallOfFame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFames()) { index = numberOfHallOfFames() - 1; }
      hallOfFames.remove(aHallOfFame);
      hallOfFames.add(index, aHallOfFame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHallOfFameAt(HallOfFame aHallOfFame, int index)
  {
    boolean wasAdded = false;
    if(hallOfFames.contains(aHallOfFame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFames()) { index = numberOfHallOfFames() - 1; }
      hallOfFames.remove(aHallOfFame);
      hallOfFames.add(index, aHallOfFame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHallOfFameAt(aHallOfFame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<HallOfFame> copyOfHallOfFames = new ArrayList<HallOfFame>(hallOfFames);
    hallOfFames.clear();
    for(HallOfFame aHallOfFame : copyOfHallOfFames)
    {
      aHallOfFame.removePlayer(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "lives" + ":" + getLives()+ "]";
  }
}
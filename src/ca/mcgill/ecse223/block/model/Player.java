package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 12 "Block223.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int points;
  private String name;
  private int lives;
  private String password;

  //Player Associations
  private Block223 block223;
  private List<HallOfFame> hallOfFames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aPoints, String aName, int aLives, String aPassword, Block223 aBlock223)
  {
    points = aPoints;
    name = aName;
    lives = aLives;
    password = aPassword;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create player due to block223");
    }
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

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
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

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  public String getName()
  {
    return name;
  }

  public int getLives()
  {
    return lives;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
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
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removePlayer(this);
    }
    block223.addPlayer(this);
    wasSet = true;
    return wasSet;
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
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayer(this);
    }
    ArrayList<HallOfFame> copyOfHallOfFames = new ArrayList<HallOfFame>(hallOfFames);
    hallOfFames.clear();
    for(HallOfFame aHallOfFame : copyOfHallOfFames)
    {
      aHallOfFame.removePlayer(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "name" + ":" + getName()+ "," +
            "lives" + ":" + getLives()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
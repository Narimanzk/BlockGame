package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 51 "Block223.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Attributes
  private int points;
  private int colour;
  private int sideLength;

  //Block Associations
  private Block223 block223;
  private List<Level> levels;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(int aPoints, int aColour, Block223 aBlock223)
  {
    points = aPoints;
    colour = aColour;
    sideLength = 20;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create block due to block223");
    }
    levels = new ArrayList<Level>();
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

  public boolean setColour(int aColour)
  {
    boolean wasSet = false;
    colour = aColour;
    wasSet = true;
    return wasSet;
  }

  public boolean setSideLength(int aSideLength)
  {
    boolean wasSet = false;
    sideLength = aSideLength;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  /**
   * data type to be changed later
   */
  public int getColour()
  {
    return colour;
  }

  public int getSideLength()
  {
    return sideLength;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
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
      existingBlock223.removeBlock(this);
    }
    block223.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    levels.add(aLevel);
    if (aLevel.indexOfBlock(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLevel.addBlock(this);
      if (!wasAdded)
      {
        levels.remove(aLevel);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    if (!levels.contains(aLevel))
    {
      return wasRemoved;
    }

    int oldIndex = levels.indexOf(aLevel);
    levels.remove(oldIndex);
    if (aLevel.indexOfBlock(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLevel.removeBlock(this);
      if (!wasRemoved)
      {
        levels.add(oldIndex,aLevel);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeBlock(this);
    }
    ArrayList<Level> copyOfLevels = new ArrayList<Level>(levels);
    levels.clear();
    for(Level aLevel : copyOfLevels)
    {
      aLevel.removeBlock(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "colour" + ":" + getColour()+ "," +
            "sideLength" + ":" + getSideLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
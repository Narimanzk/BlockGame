/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "Block223Play.ump"
public class Play
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MIN_NR_LEVELS = 1;

  /**
   * this is somewhat redundant because the max multiplicity is enforced by Umple
   */
  public static final int MAX_NR_LEVELS = 99;

  /**
   * play area is now constant
   */
  public static final int PLAY_AREA_SIDE = 390;
  public static final int WALL_PADDING = 10;
  public static final int COLUMNS_PADDING = 5;
  public static final int ROW_PADDING = 2;
  private static Map<String, Play> playsByName = new HashMap<String, Play>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Play Attributes
  private String name;
  private int nrBlocksPerLevel;

  //Play Associations
  private List<Level> levels;
  private BallInPlay ball;
  private PaddleInPlay paddle;
  private PlayerInPlay playerInPlay;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Play(String aName, int aNrBlocksPerLevel, BallInPlay aBall, PaddleInPlay aPaddle, PlayerInPlay aPlayerInPlay, Level... allLevels)
  {
    nrBlocksPerLevel = aNrBlocksPerLevel;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    levels = new ArrayList<Level>();
    boolean didAddLevels = setLevels(allLevels);
    if (!didAddLevels)
    {
      throw new RuntimeException("Unable to create Play, must have 1 to 99 levels");
    }
    if (!setBall(aBall))
    {
      throw new RuntimeException("Unable to create Play due to aBall");
    }
    if (!setPaddle(aPaddle))
    {
      throw new RuntimeException("Unable to create Play due to aPaddle");
    }
    if (!setPlayerInPlay(aPlayerInPlay))
    {
      throw new RuntimeException("Unable to create Play due to aPlayerInPlay");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      playsByName.remove(anOldName);
    }
    playsByName.put(aName, this);
    return wasSet;
  }

  public boolean setNrBlocksPerLevel(int aNrBlocksPerLevel)
  {
    boolean wasSet = false;
    nrBlocksPerLevel = aNrBlocksPerLevel;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Play getWithName(String aName)
  {
    return playsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getNrBlocksPerLevel()
  {
    return nrBlocksPerLevel;
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
  /* Code from template association_GetOne */
  public BallInPlay getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public PaddleInPlay getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public PlayerInPlay getPlayerInPlay()
  {
    return playerInPlay;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfLevels()
  {
    return 99;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    if (numberOfLevels() < maximumNumberOfLevels())
    {
      levels.add(aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    if (!levels.contains(aLevel))
    {
      return wasRemoved;
    }

    if (numberOfLevels() <= minimumNumberOfLevels())
    {
      return wasRemoved;
    }

    levels.remove(aLevel);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setLevels(Level... newLevels)
  {
    boolean wasSet = false;
    ArrayList<Level> verifiedLevels = new ArrayList<Level>();
    for (Level aLevel : newLevels)
    {
      if (verifiedLevels.contains(aLevel))
      {
        continue;
      }
      verifiedLevels.add(aLevel);
    }

    if (verifiedLevels.size() != newLevels.length || verifiedLevels.size() < minimumNumberOfLevels() || verifiedLevels.size() > maximumNumberOfLevels())
    {
      return wasSet;
    }

    levels.clear();
    levels.addAll(verifiedLevels);
    wasSet = true;
    return wasSet;
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
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBall(BallInPlay aNewBall)
  {
    boolean wasSet = false;
    if (aNewBall != null)
    {
      ball = aNewBall;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPaddle(PaddleInPlay aNewPaddle)
  {
    boolean wasSet = false;
    if (aNewPaddle != null)
    {
      paddle = aNewPaddle;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPlayerInPlay(PlayerInPlay aNewPlayerInPlay)
  {
    boolean wasSet = false;
    if (aNewPlayerInPlay != null)
    {
      playerInPlay = aNewPlayerInPlay;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    playsByName.remove(getName());
    levels.clear();
    ball = null;
    paddle = null;
    playerInPlay = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "nrBlocksPerLevel" + ":" + getNrBlocksPerLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playerInPlay = "+(getPlayerInPlay()!=null?Integer.toHexString(System.identityHashCode(getPlayerInPlay())):"null");
  }
}
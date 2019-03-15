/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 180 "../../../../../Block223.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Associations
  private Game game;
  private List<PlayerInPlay> entries;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(Game aGame)
  {
    if (aGame == null || aGame.getHallOfFame() != null)
    {
      throw new RuntimeException("Unable to create HallOfFame due to aGame");
    }
    game = aGame;
    entries = new ArrayList<PlayerInPlay>();
  }

  public HallOfFame(String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, aPaddleForGame, aBlock223ForGame, this);
    entries = new ArrayList<PlayerInPlay>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayerInPlay getEntry(int index)
  {
    PlayerInPlay aEntry = entries.get(index);
    return aEntry;
  }

  public List<PlayerInPlay> getEntries()
  {
    List<PlayerInPlay> newEntries = Collections.unmodifiableList(entries);
    return newEntries;
  }

  public int numberOfEntries()
  {
    int number = entries.size();
    return number;
  }

  public boolean hasEntries()
  {
    boolean has = entries.size() > 0;
    return has;
  }

  public int indexOfEntry(PlayerInPlay aEntry)
  {
    int index = entries.indexOf(aEntry);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayerInPlay addEntry(String aPassword, Block223 aBlock223, int aPoints, int aLivesLeft, String aName)
  {
    return new PlayerInPlay(aPassword, aBlock223, aPoints, aLivesLeft, aName, this);
  }

  public boolean addEntry(PlayerInPlay aEntry)
  {
    boolean wasAdded = false;
    if (entries.contains(aEntry)) { return false; }
    HallOfFame existingHallOfFame = aEntry.getHallOfFame();
    boolean isNewHallOfFame = existingHallOfFame != null && !this.equals(existingHallOfFame);
    if (isNewHallOfFame)
    {
      aEntry.setHallOfFame(this);
    }
    else
    {
      entries.add(aEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEntry(PlayerInPlay aEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aEntry, as it must always have a hallOfFame
    if (!this.equals(aEntry.getHallOfFame()))
    {
      entries.remove(aEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEntryAt(PlayerInPlay aEntry, int index)
  {  
    boolean wasAdded = false;
    if(addEntry(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEntryAt(PlayerInPlay aEntry, int index)
  {
    boolean wasAdded = false;
    if(entries.contains(aEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEntries()) { index = numberOfEntries() - 1; }
      entries.remove(aEntry);
      entries.add(index, aEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEntryAt(aEntry, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
    for(int i=entries.size(); i > 0; i--)
    {
      PlayerInPlay aEntry = entries.get(i - 1);
      aEntry.delete();
    }
  }

}
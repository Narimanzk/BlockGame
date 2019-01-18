package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 62 "Block223.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Associations
  private List<Player> players;
  private Block223 block223;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(Block223 aBlock223, Game aGame)
  {
    players = new ArrayList<Player>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create hallOfFame due to block223");
    }
    if (aGame == null || aGame.getHallOfFame() != null)
    {
      throw new RuntimeException("Unable to create HallOfFame due to aGame");
    }
    game = aGame;
  }

  public HallOfFame(Block223 aBlock223, String aNameForGame, int aMinSpeedForGame, int aMaxSpeedForGame, int aMinLengthForGame, int aMaxLengthForGame, float aSpeedFactorForGame, int aHeightForGame, int aWidthForGame, int aNumLevelsForGame, Admin aAdminForGame, Ball aBallForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    players = new ArrayList<Player>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create hallOfFame due to block223");
    }
    game = new Game(aNameForGame, aMinSpeedForGame, aMaxSpeedForGame, aMinLengthForGame, aMaxLengthForGame, aSpeedFactorForGame, aHeightForGame, aWidthForGame, aNumLevelsForGame, aAdminForGame, aBallForGame, aPaddleForGame, this, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    players.add(aPlayer);
    if (aPlayer.indexOfHallOfFame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlayer.addHallOfFame(this);
      if (!wasAdded)
      {
        players.remove(aPlayer);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!players.contains(aPlayer))
    {
      return wasRemoved;
    }

    int oldIndex = players.indexOf(aPlayer);
    players.remove(oldIndex);
    if (aPlayer.indexOfHallOfFame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlayer.removeHallOfFame(this);
      if (!wasRemoved)
      {
        players.add(oldIndex,aPlayer);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
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
      existingBlock223.removeHallOfFame(this);
    }
    block223.addHallOfFame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Player> copyOfPlayers = new ArrayList<Player>(players);
    players.clear();
    for(Player aPlayer : copyOfPlayers)
    {
      aPlayer.removeHallOfFame(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeHallOfFame(this);
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

}
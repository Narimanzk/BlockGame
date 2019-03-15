/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 179 "../../../../../Block223.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Associations
  private Game game;
  private List<PlayerInPlay> players;

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
    players = new ArrayList<PlayerInPlay>();
  }

  public HallOfFame(String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame)
  {
    game = new Game(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, aPaddleForGame, aBlock223ForGame, this);
    players = new ArrayList<PlayerInPlay>();
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
  public PlayerInPlay getPlayer(int index)
  {
    PlayerInPlay aPlayer = players.get(index);
    return aPlayer;
  }

  public List<PlayerInPlay> getPlayers()
  {
    List<PlayerInPlay> newPlayers = Collections.unmodifiableList(players);
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

  public int indexOfPlayer(PlayerInPlay aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayerInPlay addPlayer(String aPassword, Block223 aBlock223, int aPoints, int aLivesLeft)
  {
    return new PlayerInPlay(aPassword, aBlock223, aPoints, aLivesLeft, this);
  }

  public boolean addPlayer(PlayerInPlay aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    HallOfFame existingHallOfFame = aPlayer.getHallOfFame();
    boolean isNewHallOfFame = existingHallOfFame != null && !this.equals(existingHallOfFame);
    if (isNewHallOfFame)
    {
      aPlayer.setHallOfFame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(PlayerInPlay aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a hallOfFame
    if (!this.equals(aPlayer.getHallOfFame()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(PlayerInPlay aPlayer, int index)
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

  public boolean addOrMovePlayerAt(PlayerInPlay aPlayer, int index)
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

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
    for(int i=players.size(); i > 0; i--)
    {
      PlayerInPlay aPlayer = players.get(i - 1);
      aPlayer.delete();
    }
  }

}
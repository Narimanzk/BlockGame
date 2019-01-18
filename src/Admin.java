/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 19 "Block223.ump"
public class Admin extends Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Associations
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(int aPoints, String aName, int aLives, String aPassword, Block223 aBlock223)
  {
    super(aPoints, aName, aLives, aPassword, aBlock223);
    games = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, int aNumLevels, Ball aBall, Paddle aPaddle, HallOfFame aHallOfFame, Block223 aBlock223)
  {
    return new Game(aName, aMinSpeed, aMaxSpeed, aMinLength, aMaxLength, aSpeedFactor, aHeight, aWidth, aNumLevels, this, aBall, aPaddle, aHallOfFame, aBlock223);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Admin existingAdmin = aGame.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aGame.setAdmin(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a admin
    if (!this.equals(aGame.getAdmin()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
    super.delete();
  }

}
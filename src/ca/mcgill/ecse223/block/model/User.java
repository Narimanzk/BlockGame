package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 7 "Block223.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;

  //User Associations
  private List<UserRole> userRoles;
  private Block223 block223;
  private List<Game> games;
  private List<HallOfFame> hallOfFames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, Block223 aBlock223, UserRole... allUserRoles)
  {
    name = aName;
    userRoles = new ArrayList<UserRole>();
    boolean didAddUserRoles = setUserRoles(allUserRoles);
    if (!didAddUserRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 userRoles");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create user due to block223");
    }
    games = new ArrayList<Game>();
    hallOfFames = new ArrayList<HallOfFame>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
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
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfUserRolesValid()
  {
    boolean isValid = numberOfUserRoles() >= minimumNumberOfUserRoles() && numberOfUserRoles() <= maximumNumberOfUserRoles();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUserRoles()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    if (numberOfUserRoles() >= maximumNumberOfUserRoles())
    {
      return wasAdded;
    }

    userRoles.add(aUserRole);
    if (aUserRole.indexOfUser(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUserRole.addUser(this);
      if (!wasAdded)
      {
        userRoles.remove(aUserRole);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMNToMany */
  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    if (!userRoles.contains(aUserRole))
    {
      return wasRemoved;
    }

    if (numberOfUserRoles() <= minimumNumberOfUserRoles())
    {
      return wasRemoved;
    }

    int oldIndex = userRoles.indexOf(aUserRole);
    userRoles.remove(oldIndex);
    if (aUserRole.indexOfUser(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUserRole.removeUser(this);
      if (!wasRemoved)
      {
        userRoles.add(oldIndex,aUserRole);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToMany */
  public boolean setUserRoles(UserRole... newUserRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedUserRoles = new ArrayList<UserRole>();
    for (UserRole aUserRole : newUserRoles)
    {
      if (verifiedUserRoles.contains(aUserRole))
      {
        continue;
      }
      verifiedUserRoles.add(aUserRole);
    }

    if (verifiedUserRoles.size() != newUserRoles.length || verifiedUserRoles.size() < minimumNumberOfUserRoles() || verifiedUserRoles.size() > maximumNumberOfUserRoles())
    {
      return wasSet;
    }

    ArrayList<UserRole> oldUserRoles = new ArrayList<UserRole>(userRoles);
    userRoles.clear();
    for (UserRole aNewUserRole : verifiedUserRoles)
    {
      userRoles.add(aNewUserRole);
      if (oldUserRoles.contains(aNewUserRole))
      {
        oldUserRoles.remove(aNewUserRole);
      }
      else
      {
        aNewUserRole.addUser(this);
      }
    }

    for (UserRole anOldUserRole : oldUserRoles)
    {
      anOldUserRole.removeUser(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
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
      existingBlock223.removeUser(this);
    }
    block223.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, Ball aBall, Paddle aPaddle, HallOfFame aHallOfFame, Block223 aBlock223)
  {
    return new Game(aName, aMinSpeed, aMaxSpeed, aMinLength, aMaxLength, aSpeedFactor, aHeight, aWidth, this, aBall, aPaddle, aHallOfFame, aBlock223);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    User existingUser = aGame.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aGame.setUser(this);
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
    //Unable to remove aGame, as it must always have a user
    if (!this.equals(aGame.getUser()))
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
    if (aHallOfFame.indexOfUser(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aHallOfFame.addUser(this);
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
    if (aHallOfFame.indexOfUser(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aHallOfFame.removeUser(this);
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
    ArrayList<UserRole> copyOfUserRoles = new ArrayList<UserRole>(userRoles);
    userRoles.clear();
    for(UserRole aUserRole : copyOfUserRoles)
    {
      aUserRole.removeUser(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeUser(this);
    }
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
    }
    ArrayList<HallOfFame> copyOfHallOfFames = new ArrayList<HallOfFame>(hallOfFames);
    hallOfFames.clear();
    for(HallOfFame aHallOfFame : copyOfHallOfFames)
    {
      aHallOfFame.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
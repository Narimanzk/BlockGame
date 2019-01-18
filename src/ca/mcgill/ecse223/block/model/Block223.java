package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 1 "Block223.ump"
public class Block223
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block223 Associations
  private List<Person> persons;
  private List<Game> games;
  private List<PersonRole> personRoles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {
    persons = new ArrayList<Person>();
    games = new ArrayList<Game>();
    personRoles = new ArrayList<PersonRole>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  /**
   * 1 <@>-* HallOfFame;
   * 1 <@>-* Ball;
   * 1 <@>-* BlockType;
   * 1 <@>-* Block;
   * 1 <@>-* Paddle;
   */
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
  public PersonRole getPersonRole(int index)
  {
    PersonRole aPersonRole = personRoles.get(index);
    return aPersonRole;
  }

  /**
   * 1 <@>-* Level;
   */
  public List<PersonRole> getPersonRoles()
  {
    List<PersonRole> newPersonRoles = Collections.unmodifiableList(personRoles);
    return newPersonRoles;
  }

  public int numberOfPersonRoles()
  {
    int number = personRoles.size();
    return number;
  }

  public boolean hasPersonRoles()
  {
    boolean has = personRoles.size() > 0;
    return has;
  }

  public int indexOfPersonRole(PersonRole aPersonRole)
  {
    int index = personRoles.indexOf(aPersonRole);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Person addPerson(String aName, PersonRole... allPersonRoles)
  {
    return new Person(aName, this, allPersonRoles);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    Block223 existingBlock223 = aPerson.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPerson.setBlock223(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a block223
    if (!this.equals(aPerson.getBlock223()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, int aNumLevels, Admin aAdmin, Ball aBall, Paddle aPaddle, HallOfFame aHallOfFame)
  {
    return new Game(aName, aMinSpeed, aMaxSpeed, aMinLength, aMaxLength, aSpeedFactor, aHeight, aWidth, aNumLevels, aAdmin, aBall, aPaddle, aHallOfFame, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Block223 existingBlock223 = aGame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aGame.setBlock223(this);
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
    //Unable to remove aGame, as it must always have a block223
    if (!this.equals(aGame.getBlock223()))
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
  public static int minimumNumberOfPersonRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PersonRole addPersonRole(String aPassword)
  {
    return new PersonRole(aPassword, this);
  }

  public boolean addPersonRole(PersonRole aPersonRole)
  {
    boolean wasAdded = false;
    if (personRoles.contains(aPersonRole)) { return false; }
    Block223 existingBlock223 = aPersonRole.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPersonRole.setBlock223(this);
    }
    else
    {
      personRoles.add(aPersonRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePersonRole(PersonRole aPersonRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aPersonRole, as it must always have a block223
    if (!this.equals(aPersonRole.getBlock223()))
    {
      personRoles.remove(aPersonRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonRoleAt(PersonRole aPersonRole, int index)
  {  
    boolean wasAdded = false;
    if(addPersonRole(aPersonRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersonRoles()) { index = numberOfPersonRoles() - 1; }
      personRoles.remove(aPersonRole);
      personRoles.add(index, aPersonRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonRoleAt(PersonRole aPersonRole, int index)
  {
    boolean wasAdded = false;
    if(personRoles.contains(aPersonRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersonRoles()) { index = numberOfPersonRoles() - 1; }
      personRoles.remove(aPersonRole);
      personRoles.add(index, aPersonRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonRoleAt(aPersonRole, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (persons.size() > 0)
    {
      Person aPerson = persons.get(persons.size() - 1);
      aPerson.delete();
      persons.remove(aPerson);
    }
    
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
    while (personRoles.size() > 0)
    {
      PersonRole aPersonRole = personRoles.get(personRoles.size() - 1);
      aPersonRole.delete();
      personRoles.remove(aPersonRole);
    }
    
  }

}
package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 18 "Block223.ump"
public class PersonRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonRole Attributes
  private String password;

  //PersonRole Associations
  private Block223 block223;
  private List<Person> persons;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonRole(String aPassword, Block223 aBlock223)
  {
    password = aPassword;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create personRole due to block223");
    }
    persons = new ArrayList<Person>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
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
      existingBlock223.removePersonRole(this);
    }
    block223.addPersonRole(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    persons.add(aPerson);
    if (aPerson.indexOfPersonRole(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPerson.addPersonRole(this);
      if (!wasAdded)
      {
        persons.remove(aPerson);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    if (!persons.contains(aPerson))
    {
      return wasRemoved;
    }

    int oldIndex = persons.indexOf(aPerson);
    persons.remove(oldIndex);
    if (aPerson.indexOfPersonRole(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPerson.removePersonRole(this);
      if (!wasRemoved)
      {
        persons.add(oldIndex,aPerson);
      }
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

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePersonRole(this);
    }
    ArrayList<Person> copyOfPersons = new ArrayList<Person>(persons);
    persons.clear();
    for(Person aPerson : copyOfPersons)
    {
      if (aPerson.numberOfPersonRoles() <= Person.minimumNumberOfPersonRoles())
      {
        aPerson.delete();
      }
      else
      {
        aPerson.removePersonRole(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
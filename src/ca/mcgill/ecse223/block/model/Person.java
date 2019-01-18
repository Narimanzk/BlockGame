package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 13 "Block223.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;

  //Person Associations
  private List<PersonRole> personRoles;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, Block223 aBlock223, PersonRole... allPersonRoles)
  {
    name = aName;
    personRoles = new ArrayList<PersonRole>();
    boolean didAddPersonRoles = setPersonRoles(allPersonRoles);
    if (!didAddPersonRoles)
    {
      throw new RuntimeException("Unable to create Person, must have 1 to 2 personRoles");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create person due to block223");
    }
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
  public PersonRole getPersonRole(int index)
  {
    PersonRole aPersonRole = personRoles.get(index);
    return aPersonRole;
  }

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
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfPersonRolesValid()
  {
    boolean isValid = numberOfPersonRoles() >= minimumNumberOfPersonRoles() && numberOfPersonRoles() <= maximumNumberOfPersonRoles();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersonRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfPersonRoles()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPersonRole(PersonRole aPersonRole)
  {
    boolean wasAdded = false;
    if (personRoles.contains(aPersonRole)) { return false; }
    if (numberOfPersonRoles() >= maximumNumberOfPersonRoles())
    {
      return wasAdded;
    }

    personRoles.add(aPersonRole);
    if (aPersonRole.indexOfPerson(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPersonRole.addPerson(this);
      if (!wasAdded)
      {
        personRoles.remove(aPersonRole);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMNToMany */
  public boolean removePersonRole(PersonRole aPersonRole)
  {
    boolean wasRemoved = false;
    if (!personRoles.contains(aPersonRole))
    {
      return wasRemoved;
    }

    if (numberOfPersonRoles() <= minimumNumberOfPersonRoles())
    {
      return wasRemoved;
    }

    int oldIndex = personRoles.indexOf(aPersonRole);
    personRoles.remove(oldIndex);
    if (aPersonRole.indexOfPerson(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPersonRole.removePerson(this);
      if (!wasRemoved)
      {
        personRoles.add(oldIndex,aPersonRole);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToMany */
  public boolean setPersonRoles(PersonRole... newPersonRoles)
  {
    boolean wasSet = false;
    ArrayList<PersonRole> verifiedPersonRoles = new ArrayList<PersonRole>();
    for (PersonRole aPersonRole : newPersonRoles)
    {
      if (verifiedPersonRoles.contains(aPersonRole))
      {
        continue;
      }
      verifiedPersonRoles.add(aPersonRole);
    }

    if (verifiedPersonRoles.size() != newPersonRoles.length || verifiedPersonRoles.size() < minimumNumberOfPersonRoles() || verifiedPersonRoles.size() > maximumNumberOfPersonRoles())
    {
      return wasSet;
    }

    ArrayList<PersonRole> oldPersonRoles = new ArrayList<PersonRole>(personRoles);
    personRoles.clear();
    for (PersonRole aNewPersonRole : verifiedPersonRoles)
    {
      personRoles.add(aNewPersonRole);
      if (oldPersonRoles.contains(aNewPersonRole))
      {
        oldPersonRoles.remove(aNewPersonRole);
      }
      else
      {
        aNewPersonRole.addPerson(this);
      }
    }

    for (PersonRole anOldPersonRole : oldPersonRoles)
    {
      anOldPersonRole.removePerson(this);
    }
    wasSet = true;
    return wasSet;
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
      existingBlock223.removePerson(this);
    }
    block223.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<PersonRole> copyOfPersonRoles = new ArrayList<PersonRole>(personRoles);
    personRoles.clear();
    for(PersonRole aPersonRole : copyOfPersonRoles)
    {
      aPersonRole.removePerson(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}
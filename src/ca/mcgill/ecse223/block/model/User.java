/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 80 "../../../../../Block223Persistence.ump"
// line 11 "../../../../../Block223.ump"
public class User implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;

  //User Associations
  private List<UserRole> roles;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, Block223 aBlock223, UserRole... allRoles)
  {
    // line 15 "../../../../../Block223.ump"
    if(getWithUsername(aUsername) != null){
       			throw new RuntimeException("The username has already been taken");
       		}
    // END OF UMPLE BEFORE INJECTION
    // line 20 "../../../../../Block223.ump"
    if (aUsername == null || aUsername.length() == 0) {
    	  		throw new RuntimeException("The username must be specified.");
    		}
    // END OF UMPLE BEFORE INJECTION
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username");
    }
    roles = new ArrayList<UserRole>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create User, must have 1 to 2 roles");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create user due to block223");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    // line 15 "../../../../../Block223.ump"
    if(getWithUsername(aUsername) != null){
       			throw new RuntimeException("The username has already been taken");
       		}
    // END OF UMPLE BEFORE INJECTION
    // line 20 "../../../../../Block223.ump"
    if (aUsername == null || aUsername.length() == 0) {
    	  		throw new RuntimeException("The username must be specified.");
    		}
    // END OF UMPLE BEFORE INJECTION
    String anOldUsername = getUsername();
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }

  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }

  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public UserRole getRole(int index)
  {
    UserRole aRole = roles.get(index);
    return aRole;
  }

  public List<UserRole> getRoles()
  {
    List<UserRole> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(UserRole aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }

  public Block223 getBlock223()
  {
    return block223;
  }

  public static int minimumNumberOfRoles()
  {
    return 1;
  }

  public static int maximumNumberOfRoles()
  {
    return 2;
  }

  public boolean addRole(UserRole aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    if (numberOfRoles() < maximumNumberOfRoles())
    {
      roles.add(aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeRole(UserRole aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    roles.remove(aRole);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean setRoles(UserRole... newRoles)
  {
    boolean wasSet = false;
    ArrayList<UserRole> verifiedRoles = new ArrayList<UserRole>();
    for (UserRole aRole : newRoles)
    {
      if (verifiedRoles.contains(aRole))
      {
        continue;
      }
      verifiedRoles.add(aRole);
    }

    if (verifiedRoles.size() != newRoles.length || verifiedRoles.size() < minimumNumberOfRoles() || verifiedRoles.size() > maximumNumberOfRoles())
    {
      return wasSet;
    }

    roles.clear();
    roles.addAll(verifiedRoles);
    wasSet = true;
    return wasSet;
  }

  public boolean addRoleAt(UserRole aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(UserRole aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }

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

  public void delete()
  {
    usersByUsername.remove(getUsername());
    roles.clear();
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    placeholderBlock223.removeUser(this);
  }

  // line 86 "../../../../../Block223Persistence.ump"
   public static  void reinitializeUsersByUsername(List<User> users){
    usersByUsername = new HashMap<String, User>();
    for (User user : users) {
      usersByUsername.put(user.getUsername(), user);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 83 ../../../../../Block223Persistence.ump
  private static final long serialVersionUID = 4789044649628261312L ;

  
}
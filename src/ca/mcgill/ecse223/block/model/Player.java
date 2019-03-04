/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;

// line 74 "../../../../../Block223Persistence.ump"
// line 40 "../../../../../Block223.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    // line 43 "../../../../../Block223.ump"
    if (aPassword == null || aPassword.length() == 0){
       			throw new RuntimeException("The player password needs to be specified.");
       		}
    // END OF UMPLE BEFORE INJECTION
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_Set_subclass */
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    // line 43 "../../../../../Block223.ump"
    if (aPassword == null || aPassword.length() == 0){
       			throw new RuntimeException("The player password needs to be specified.");
       		}
    // END OF UMPLE BEFORE INJECTION
      wasSet = super.setPassword(aPassword);
    return wasSet;
  }

  public void delete()
  {
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 77 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 6711395097444586117L ;

  
}
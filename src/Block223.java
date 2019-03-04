/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 31 "Block223Persistence.ump"
public class Block223 implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 37 "Block223Persistence.ump"
   public void reinitialize(){
    Game.reinitializeGamesByName(this.getGames());
    User.reinitializeUsersByName(this.getUsers());
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 34 "Block223Persistence.ump"
  private static final long serialVersionUID = -6594557003371779087L ;

  
}
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 72 "Block223Persistence.ump"
public class Level implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 78 "Block223Persistence.ump"
   public static  void reinitializeAutouniqueID(List<Block> blocks){
    Integer nextId = 0; 
    for (Block block : blocks) {
      if (block.getId() > nextId) {
        nextId = block.getId();
      }
    }
    nextId++;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 75 "Block223Persistence.ump"
  private static final long serialVersionUID = -7956760334392179806L ;

  
}
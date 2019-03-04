/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 48 "Block223Persistence.ump"
public class Game implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 54 "Block223Persistence.ump"
   public static  void reinitializeGamesByName(List<Game> games){
    gamesByName = new HashMap<String, Game>();
    for (Game game : games) {
      Block.reinitializeAutouniqueID(game.getBlocks());
      gamesByName.put(game.getName(), game);
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 51 "Block223Persistence.ump"
  private static final long serialVersionUID = 6884475053231046252L ;

  
}
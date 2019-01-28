package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 74 "Block223.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int number;
  private boolean isRandom;

  //Level Associations
  private List<Block> grid;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aNumber, boolean aIsRandom, Game aGame)
  {
    number = aNumber;
    isRandom = aIsRandom;
    grid = new ArrayList<Block>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create level due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRandom(boolean aIsRandom)
  {
    boolean wasSet = false;
    isRandom = aIsRandom;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public boolean getIsRandom()
  {
    return isRandom;
  }
  /* Code from template association_GetMany */
  public Block getGrid(int index)
  {
    Block aGrid = grid.get(index);
    return aGrid;
  }

  public List<Block> getGrid()
  {
    List<Block> newGrid = Collections.unmodifiableList(grid);
    return newGrid;
  }

  public int numberOfGrid()
  {
    int number = grid.size();
    return number;
  }

  public boolean hasGrid()
  {
    boolean has = grid.size() > 0;
    return has;
  }

  public int indexOfGrid(Block aGrid)
  {
    int index = grid.indexOf(aGrid);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGrid()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addGrid(int aX, int aY, Game aGame, BlockType aBlockType)
  {
    return new Block(aX, aY, aGame, aBlockType, this);
  }

  public boolean addGrid(Block aGrid)
  {
    boolean wasAdded = false;
    if (grid.contains(aGrid)) { return false; }
    Level existingLevel = aGrid.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aGrid.setLevel(this);
    }
    else
    {
      grid.add(aGrid);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGrid(Block aGrid)
  {
    boolean wasRemoved = false;
    //Unable to remove aGrid, as it must always have a level
    if (!this.equals(aGrid.getLevel()))
    {
      grid.remove(aGrid);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGridAt(Block aGrid, int index)
  {  
    boolean wasAdded = false;
    if(addGrid(aGrid))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrid()) { index = numberOfGrid() - 1; }
      grid.remove(aGrid);
      grid.add(index, aGrid);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGridAt(Block aGrid, int index)
  {
    boolean wasAdded = false;
    if(grid.contains(aGrid))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGrid()) { index = numberOfGrid() - 1; }
      grid.remove(aGrid);
      grid.add(index, aGrid);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGridAt(aGrid, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeLevel(this);
    }
    game.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=grid.size(); i > 0; i--)
    {
      Block aGrid = grid.get(i - 1);
      aGrid.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeLevel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "isRandom" + ":" + getIsRandom()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
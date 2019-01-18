package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 58 "Block223.ump"
public class Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int length;

  //Paddle Associations
  private Block223 block223;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(Block223 aBlock223, Game aGame)
  {
    length = 5;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create paddle due to block223");
    }
    if (aGame == null || aGame.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aGame");
    }
    game = aGame;
  }

  public Paddle(Block223 aBlock223, String aNameForGame, int aMinSpeedForGame, int aMaxSpeedForGame, int aMinLengthForGame, int aMaxLengthForGame, float aSpeedFactorForGame, int aHeightForGame, int aWidthForGame, int aNumLevelsForGame, Admin aAdminForGame, Ball aBallForGame, HallOfFame aHallOfFameForGame, Block223 aBlock223ForGame)
  {
    length = 5;
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create paddle due to block223");
    }
    game = new Game(aNameForGame, aMinSpeedForGame, aMaxSpeedForGame, aMinLengthForGame, aMaxLengthForGame, aSpeedFactorForGame, aHeightForGame, aWidthForGame, aNumLevelsForGame, aAdminForGame, aBallForGame, this, aHallOfFameForGame, aBlock223ForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(int aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public int getLength()
  {
    return length;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingBlock223.removePaddle(this);
    }
    block223.addPaddle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePaddle(this);
    }
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
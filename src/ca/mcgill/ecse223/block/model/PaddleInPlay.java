/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 8 "../../../../../Block223Play.ump"
public class PaddleInPlay extends Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PaddleInPlay Attributes
  private int xCurPos;
  private int yPos;
  private int curLength;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PaddleInPlay(int aMaxPaddleLength, int aMinPaddleLength, Game aGame, int aXCurPos, int aCurLength)
  {
    super(aMaxPaddleLength, aMinPaddleLength, aGame);
    xCurPos = aXCurPos;
    yPos = Game.PLAY_AREA_SIDE - 30;
    curLength = aCurLength;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXCurPos(int aXCurPos)
  {
    boolean wasSet = false;
    xCurPos = aXCurPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setYPos(int aYPos)
  {
    boolean wasSet = false;
    yPos = aYPos;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurLength(int aCurLength)
  {
    boolean wasSet = false;
    curLength = aCurLength;
    wasSet = true;
    return wasSet;
  }

  public int getXCurPos()
  {
    return xCurPos;
  }

  public int getYPos()
  {
    return yPos;
  }

  public int getCurLength()
  {
    return curLength;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "xCurPos" + ":" + getXCurPos()+ "," +
            "yPos" + ":" + getYPos()+ "," +
            "curLength" + ":" + getCurLength()+ "]";
  }
}
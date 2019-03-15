/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 24 "../../../../../Block223Play.ump"
public class PaddleInPlay extends Paddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PaddleInPlay Attributes
  private int xCurPos;
  private int yCurPos;
  private int curLength;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PaddleInPlay(int aMaxPaddleLength, int aMinPaddleLength, Game aGame, int aXCurPos, int aYCurPos, int aCurLength)
  {
    super(aMaxPaddleLength, aMinPaddleLength, aGame);
    xCurPos = aXCurPos;
    yCurPos = aYCurPos;
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

  public boolean setYCurPos(int aYCurPos)
  {
    boolean wasSet = false;
    yCurPos = aYCurPos;
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

  public int getYCurPos()
  {
    return yCurPos;
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
            "yCurPos" + ":" + getYCurPos()+ "," +
            "curLength" + ":" + getCurLength()+ "]";
  }
}
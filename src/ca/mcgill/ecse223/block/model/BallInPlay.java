/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 1 "../../../../../Block223Play.ump"
public class BallInPlay extends Ball
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BallInPlay Attributes
  private int xCurSpeed;
  private int yCurSpeed;
  private int xCurPos;
  private int yCurPos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BallInPlay(int aMinBallSpeedX, int aMinBallSpeedY, double aBallSpeedIncreaseFactor, Game aGame, int aXCurSpeed, int aYCurSpeed, int aXCurPos, int aYCurPos)
  {
    super(aMinBallSpeedX, aMinBallSpeedY, aBallSpeedIncreaseFactor, aGame);
    xCurSpeed = aXCurSpeed;
    yCurSpeed = aYCurSpeed;
    xCurPos = aXCurPos;
    yCurPos = aYCurPos;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXCurSpeed(int aXCurSpeed)
  {
    boolean wasSet = false;
    xCurSpeed = aXCurSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setYCurSpeed(int aYCurSpeed)
  {
    boolean wasSet = false;
    yCurSpeed = aYCurSpeed;
    wasSet = true;
    return wasSet;
  }

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

  public int getXCurSpeed()
  {
    return xCurSpeed;
  }

  public int getYCurSpeed()
  {
    return yCurSpeed;
  }

  public int getXCurPos()
  {
    return xCurPos;
  }

  public int getYCurPos()
  {
    return yCurPos;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "xCurSpeed" + ":" + getXCurSpeed()+ "," +
            "yCurSpeed" + ":" + getYCurSpeed()+ "," +
            "xCurPos" + ":" + getXCurPos()+ "," +
            "yCurPos" + ":" + getYCurPos()+ "]";
  }
}
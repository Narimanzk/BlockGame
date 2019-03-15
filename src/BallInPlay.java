/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 17 "Block223Play.ump"
public class BallInPlay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BallInPlay Attributes
  private int xSpeed;
  private int ySpeed;
  private int xPos;
  private int yPos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BallInPlay(int aXSpeed, int aYSpeed, int aXPos, int aYPos)
  {
    xSpeed = aXSpeed;
    ySpeed = aYSpeed;
    xPos = aXPos;
    yPos = aYPos;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setXSpeed(int aXSpeed)
  {
    boolean wasSet = false;
    xSpeed = aXSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setYSpeed(int aYSpeed)
  {
    boolean wasSet = false;
    ySpeed = aYSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setXPos(int aXPos)
  {
    boolean wasSet = false;
    xPos = aXPos;
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

  public int getXSpeed()
  {
    return xSpeed;
  }

  public int getYSpeed()
  {
    return ySpeed;
  }

  public int getXPos()
  {
    return xPos;
  }

  public int getYPos()
  {
    return yPos;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "xSpeed" + ":" + getXSpeed()+ "," +
            "ySpeed" + ":" + getYSpeed()+ "," +
            "xPos" + ":" + getXPos()+ "," +
            "yPos" + ":" + getYPos()+ "]";
  }
}
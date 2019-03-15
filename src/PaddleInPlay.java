/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 24 "Block223Play.ump"
public class PaddleInPlay
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PaddleInPlay Attributes
  private int xPos;
  private int yPos;
  private int curLength;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PaddleInPlay(int aXPos, int aYPos, int aCurLength)
  {
    xPos = aXPos;
    yPos = aYPos;
    curLength = aCurLength;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setCurLength(int aCurLength)
  {
    boolean wasSet = false;
    curLength = aCurLength;
    wasSet = true;
    return wasSet;
  }

  public int getXPos()
  {
    return xPos;
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
  {}


  public String toString()
  {
    return super.toString() + "["+
            "xPos" + ":" + getXPos()+ "," +
            "yPos" + ":" + getYPos()+ "," +
            "curLength" + ":" + getCurLength()+ "]";
  }
}
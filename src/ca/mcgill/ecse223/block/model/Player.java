package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 17 "Block223.ump"
public class Player extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int points;
  private int lives;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223, int aPoints, int aLives)
  {
    super(aPassword, aBlock223);
    points = aPoints;
    lives = aLives;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
    wasSet = true;
    return wasSet;
  }

  public int getPoints()
  {
    return points;
  }

  public int getLives()
  {
    return lives;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "points" + ":" + getPoints()+ "," +
            "lives" + ":" + getLives()+ "]";
  }
}
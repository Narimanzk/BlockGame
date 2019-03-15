/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 1 "../../../../../Block223StateMachine.ump"
public class Play
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Play Attributes
  private boolean isTest;

  //Play State Machines
  public enum Status { Idle, Playing, Paused, End }
  private Status status;

  //Play Do Activity Threads
  Thread doActivityStatusPlayingThread = null;
  Thread doActivityStatusPausedThread = null;
  Thread doActivityStatusEndThread = null;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Play(boolean aIsTest)
  {
    isTest = aIsTest;
    setStatus(Status.Idle);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsTest(boolean aIsTest)
  {
    boolean wasSet = false;
    isTest = aIsTest;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsTest()
  {
    return isTest;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsTest()
  {
    return isTest;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean StartGame()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Idle:
        // line 7 "../../../../../Block223StateMachine.ump"
        isTest = false; 
				initalizePaddlePosition(); //Place the paddle in the middle, 30 units above.
				dropBallFromStartingPoint();	//Drop the ball from the starting point.
        setStatus(Status.Playing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean TestGame()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Idle:
        // line 15 "../../../../../Block223StateMachine.ump"
        isTest = true;
				initalizePaddlePosition(); //Place the paddle in the middle, 30 units above.
				dropBallFromStartingPoint();	//Drop the ball from the starting point.
        setStatus(Status.Playing);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean Pause()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (spaceIsPressed())
        {
          exitStatus();
        // line 34 "../../../../../Block223StateMachine.ump"
          //set speeds to 0 as entry
          setStatus(Status.Paused);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean GameEnd()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (getIsTest()&&(GameInPlay.getBlocksOnTheField().size()==0||PlayerInPlay.getLivesLeft()==0))
        {
          exitStatus();
        // line 41 "../../../../../Block223StateMachine.ump"
          
          setStatus(Status.Idle);
          wasEventProcessed = true;
          break;
        }
        if (!getIsTest()&&(GameInPlay.getBlocksOnTheField().size()==0||PlayerInPlay.getLivesLeft()==0))
        {
          exitStatus();
        // line 46 "../../../../../Block223StateMachine.ump"
          
          setStatus(Status.End);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean BallOutOfBounds()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (isBlockOutOfBounds(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos))&&PlayerInPlay.getLivesLeft>1)
        {
          exitStatus();
        // line 52 "../../../../../Block223StateMachine.ump"
          deductLives();
          setStatus(Status.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isBlockOutOfBounds(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos))&&PlayerInPlay.getLivesLeft==1)
        {
          exitStatus();
        // line 61 "../../../../../Block223StateMachine.ump"
          deductLives();
          setStatus(Status.End);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean BallHitNothing()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (isBallInSpace(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos)))
        {
          exitStatus();
        // line 67 "../../../../../Block223StateMachine.ump"
          
          setStatus(Status.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean BallHitBlock()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (isABlockHit(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos)))
        {
          exitStatus();
        // line 71 "../../../../../Block223StateMachine.ump"
          //isBlockHit is a private method that we create
			
				numBlocks=numBlocks-1;
				redirectBall();
				removeBlockAssignment(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos))); //Points will be added within this method
          setStatus(Status.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean BallHitPaddleOrWall()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Playing:
        if (isaWallOrPaddleHit(BallInPlay.get(xCurPos),BallInPlay.get(yCurPos)))
        {
          exitStatus();
        // line 81 "../../../../../Block223StateMachine.ump"
          //Within the guard is a private method we created to determine if the ball hit a wall/paddle
			
			redirectBall();
          setStatus(Status.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean Unpause()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Paused:
        if (spaceIsPressed())
        {
          exitStatus();
        // line 94 "../../../../../Block223StateMachine.ump"
          unpauseGame();
          setStatus(Status.Playing);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case Playing:
        if (doActivityStatusPlayingThread != null) { doActivityStatusPlayingThread.interrupt(); }
        break;
      case Paused:
        if (doActivityStatusPausedThread != null) { doActivityStatusPausedThread.interrupt(); }
        break;
      case End:
        if (doActivityStatusEndThread != null) { doActivityStatusEndThread.interrupt(); }
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case Playing:
        doActivityStatusPlayingThread = new DoActivityThread(this,"doActivityStatusPlaying");
        break;
      case Paused:
        doActivityStatusPausedThread = new DoActivityThread(this,"doActivityStatusPaused");
        break;
      case End:
        doActivityStatusEndThread = new DoActivityThread(this,"doActivityStatusEnd");
        break;
    }
  }

  private void doActivityStatusPlaying()
  {
    try
    {
      // line 27 "../../../../../Block223StateMachine.ump"
      ballMoving(); 
			paddleMoving();
      Thread.sleep(1);
    }
    catch (InterruptedException e)
    {

    }
  }

  private void doActivityStatusPaused()
  {
    try
    {
      // line 89 "../../../../../Block223StateMachine.ump"
      pauseGame();
				saveGameState();
      Thread.sleep(1);
    }
    catch (InterruptedException e)
    {

    }
  }

  private void doActivityStatusEnd()
  {
    try
    {
      // line 99 "../../../../../Block223StateMachine.ump"
      updateHallOfFame(); //Update the hall of fame with stats.
      Thread.sleep(1);
    }
    catch (InterruptedException e)
    {

    }
  }

  private static class DoActivityThread extends Thread
  {
    Play controller;
    String doActivityMethodName;
    
    public DoActivityThread(Play aController,String aDoActivityMethodName)
    {
      controller = aController;
      doActivityMethodName = aDoActivityMethodName;
      start();
    }
    
    public void run()
    {
      if ("doActivityStatusPlaying".equals(doActivityMethodName))
      {
        controller.doActivityStatusPlaying();
      }
        else if ("doActivityStatusPaused".equals(doActivityMethodName))
      {
        controller.doActivityStatusPaused();
      }
        else if ("doActivityStatusEnd".equals(doActivityMethodName))
      {
        controller.doActivityStatusEnd();
      }
    }
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "isTest" + ":" + getIsTest()+ "]";
  }
}
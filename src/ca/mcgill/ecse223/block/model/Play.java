/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

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

  //Play Associations
  private BallInPlay ball;
  private PaddleInPlay paddle;
  private GameInPlay game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Play(boolean aIsTest, BallInPlay aBall, PaddleInPlay aPaddle, GameInPlay aGame)
  {
    isTest = aIsTest;
    if (aBall == null || aBall.getPlay() != null)
    {
      throw new RuntimeException("Unable to create Play due to aBall");
    }
    ball = aBall;
    if (aPaddle == null || aPaddle.getPlay() != null)
    {
      throw new RuntimeException("Unable to create Play due to aPaddle");
    }
    paddle = aPaddle;
    if (aGame == null || aGame.getPlay() != null)
    {
      throw new RuntimeException("Unable to create Play due to aGame");
    }
    game = aGame;
    setStatus(Status.Idle);
  }

  public Play(boolean aIsTest, int aMinBallSpeedXForBall, int aMinBallSpeedYForBall, double aBallSpeedIncreaseFactorForBall, Game aGameForBall, int aXCurSpeedForBall, int aYCurSpeedForBall, int aXCurPosForBall, int aYCurPosForBall, int aMaxPaddleLengthForPaddle, int aMinPaddleLengthForPaddle, Game aGameForPaddle, int aXCurPosForPaddle, int aCurLengthForPaddle, String aNameForGame, int aNrBlocksPerLevelForGame, Admin aAdminForGame, Ball aBallForGame, Paddle aPaddleForGame, Block223 aBlock223ForGame, HallOfFame aHallOfFameForGame, int aLevelIndexForGame)
  {
    isTest = aIsTest;
    ball = new BallInPlay(aMinBallSpeedXForBall, aMinBallSpeedYForBall, aBallSpeedIncreaseFactorForBall, aGameForBall, aXCurSpeedForBall, aYCurSpeedForBall, aXCurPosForBall, aYCurPosForBall, this);
    paddle = new PaddleInPlay(aMaxPaddleLengthForPaddle, aMinPaddleLengthForPaddle, aGameForPaddle, aXCurPosForPaddle, aCurLengthForPaddle, this);
    game = new GameInPlay(aNameForGame, aNrBlocksPerLevelForGame, aAdminForGame, aBallForGame, aPaddleForGame, aBlock223ForGame, aHallOfFameForGame, aLevelIndexForGame, this);
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
        // line 11 "../../../../../Block223StateMachine.ump"
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
        // line 19 "../../../../../Block223StateMachine.ump"
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
        // line 38 "../../../../../Block223StateMachine.ump"
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
        // line 45 "../../../../../Block223StateMachine.ump"
          
          setStatus(Status.Idle);
          wasEventProcessed = true;
          break;
        }
        if (!getIsTest()&&(GameInPlay.getBlocksOnTheField().size()==0||PlayerInPlay.getLivesLeft()==0))
        {
          exitStatus();
        // line 50 "../../../../../Block223StateMachine.ump"
          
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
        if (isBlockOutOfBounds(BallInPlay.getXCurPos(),allInPlay.getYCurPos())&&PlayerInPlay.getLivesLeft()>1)
        {
          exitStatus();
        // line 56 "../../../../../Block223StateMachine.ump"
          deductLives();
          setStatus(Status.Paused);
          wasEventProcessed = true;
          break;
        }
        if (isBlockOutOfBounds(BallInPlay.getXCurPos(),allInPlay.getYCurPos())&&PlayerInPlay.getLivesLeft()==1)
        {
          exitStatus();
        // line 65 "../../../../../Block223StateMachine.ump"
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
        if (isBallInSpace(BallInPlay.getXCurPos(),allInPlay.getYCurPos()))
        {
          exitStatus();
        // line 71 "../../../../../Block223StateMachine.ump"
          
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
        if (isABlockHit(BallInPlay.getXCurPos(),allInPlay.getYCurPos()))
        {
          exitStatus();
        // line 75 "../../../../../Block223StateMachine.ump"
          //isBlockHit is a private method that we create
			
				numBlocks=numBlocks-1;
				redirectBall();
				removeBlockAssignment(BallInPlay.getXCurPos(),BallInPlay.getYCurPos()); //Points will be added within this method
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
        if (isaWallOrPaddleHit(BallInPlay.getXCurPos(),allInPlay.getYCurPos()))
        {
          exitStatus();
        // line 85 "../../../../../Block223StateMachine.ump"
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
        // line 98 "../../../../../Block223StateMachine.ump"
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
  /* Code from template association_GetOne */
  public BallInPlay getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public PaddleInPlay getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public GameInPlay getGame()
  {
    return game;
  }

  private void doActivityStatusPlaying()
  {
    try
    {
      // line 31 "../../../../../Block223StateMachine.ump"
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
      // line 93 "../../../../../Block223StateMachine.ump"
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
      // line 104 "../../../../../Block223StateMachine.ump"
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
  {
    BallInPlay existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
    PaddleInPlay existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    GameInPlay existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isTest" + ":" + getIsTest()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}
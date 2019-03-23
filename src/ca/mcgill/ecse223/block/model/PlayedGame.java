/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 112 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to PlayedGame, Game, and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to PlayedGame, Game, and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 47 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
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

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
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
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
    wasSet = true;
    return wasSet;
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
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 55 "../../../../../Block223PlayMode.ump"
   private void bounceBall(){
    BouncePoint bp = getBounce();
	   double incomingY = bp.getY()- currentBallY;
	   double remainingY = ballDirectionY - incomingY;
	   double incomingX = bp.getX()- currentBallX;
	   double remainingX = ballDirectionX - incomingX;
	   if(bp.getDirection() == BouncePoint.BounceDirection.FLIP_Y) {
		   double newBallDirectionY = ballDirectionY *-1;
		   double newBallDirectionX = ballDirectionX + Math.abs(ballDirectionY*0.1);
		   setCurrentBallY(bp.getY()+remainingY/ballDirectionY * newBallDirectionY);
		   setCurrentBallX(bp.getX()+remainingX/ballDirectionX * newBallDirectionX);
		   setBallDirectionX(newBallDirectionX);
		   setBallDirectionY(newBallDirectionY);
	   }else if(bp.getDirection() == BouncePoint.BounceDirection.FLIP_X) {
		   double newBallDirectionX = ballDirectionX *-1;
		   double newBallDirectionY = ballDirectionY + Math.abs(ballDirectionX*0.1);
		   setCurrentBallY(bp.getY()+remainingY/ballDirectionY * newBallDirectionY);
		   setCurrentBallX(bp.getX()+remainingX/ballDirectionX * newBallDirectionX);
		   setBallDirectionX(newBallDirectionX);
		   setBallDirectionY(newBallDirectionY);
	   }else if(bp.getDirection() == BouncePoint.BounceDirection.FLIP_BOTH) {
		   double newBallDirectionX = ballDirectionX *-1;
		   double newBallDirectionY = ballDirectionY *-1;
		   setCurrentBallY(bp.getY()+remainingY/ballDirectionY * newBallDirectionY);
		   setCurrentBallX(bp.getX()+remainingX/ballDirectionX * newBallDirectionX);
		   setBallDirectionX(newBallDirectionX);
		   setBallDirectionY(newBallDirectionY);
	   }
	   if(bp.hasHitBlock()) {
		   bounce.setHitBlock(null);
	   }
  }

  // line 88 "../../../../../Block223PlayMode.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment aBlock){
    int size = aBlock.getBlock().SIZE;
	   int borderSize = size+20;
	   double leftX = aBlock.getX()-(size+10);
	   double bottomY = aBlock.getY()-10;
	   //find the bottom left corner of the block's boundary and make a rectangle2D with that
	   Rectangle2D blockBoundary = new Rectangle2D.Double(leftX,bottomY,borderSize,borderSize);
	   Point2D ballCoords = new Point2D.Double(currentBallX, currentBallY);
	   Point2D ballNewCoords = new Point2D.Double(currentBallX+ballDirectionX, currentBallY+ballDirectionY);
	   //make a Line2D object that represents the ball's path in the next tick
	   Line2D.Double ballPath = new Line2D.Double(ballCoords, ballNewCoords);

	   //First, does the path of the ball intersect with the border of the block in any way?
	   if(!blockBoundary.intersectsLine(ballPath)) {
		   return null;//if it doesn't, screw it, just return null.
	   }
	   //if it does, we need to find the exact point where they intersect
	   double rightX = leftX + borderSize;
	   double topY = bottomY + borderSize;
	   
	   //Define line segments that denote the edges of the block's border.
	   Line2D.Double top = new Line2D.Double(leftX,topY,rightX,topY);
	   Line2D.Double bottom = new Line2D.Double(leftX, bottomY, rightX, bottomY);
	   Line2D.Double right = new Line2D.Double(rightX, topY, rightX, bottomY);
	   Line2D.Double left = new Line2D.Double(leftX, topY, leftX, bottomY);
	   
	   ArrayList<Point2D> intersects = new ArrayList<Point2D>();
	   //If the path of the ball intersects with ANY of those defined lines
	   if(ballPath.intersectsLine(top)) {
		   //calculate the point of intersection and add that point to a list for processing.
		   intersects.add(getIntersection(ballPath,top));
	   }
	   if(ballPath.intersectsLine(bottom)) {
		   intersects.add(getIntersection(ballPath,bottom));
	   }
	   if(ballPath.intersectsLine(left)) {
		   intersects.add(getIntersection(ballPath,left));
	   }
	   if(ballPath.intersectsLine(right)) {
		   intersects.add(getIntersection(ballPath,right));
	   }
	   //With this list of points, find the one closest to the ball's current position
	   double minDistance = ballCoords.distance(intersects.get(0));
	   Point2D closestPoint=intersects.get(0);//set some initial values
	   for(int i=1;i<intersects.size();i++) {//and iterate through the rest of the list
		   double distance = ballCoords.distance(intersects.get(i));
		   if(distance<minDistance) {
			   closestPoint = intersects.get(i);
			   minDistance = distance;
		   }
	   }//we now have the closest intersect point! So how do we flip?
	   
	   if(closestPoint.getX()<rightX-10 && closestPoint.getX()>leftX+10) {
		   BouncePoint finalBP = new BouncePoint(closestPoint.getX(),closestPoint.getY(),BouncePoint.BounceDirection.FLIP_Y);
		   return finalBP;
	   }else if(closestPoint.getY()<topY-10 && closestPoint.getY()>bottomY+10) {
		   BouncePoint finalBP = new BouncePoint(closestPoint.getX(),closestPoint.getY(),BouncePoint.BounceDirection.FLIP_X);
		   return finalBP;
	   }else {
		   BouncePoint finalBP = new BouncePoint(closestPoint.getX(),closestPoint.getY(),BouncePoint.BounceDirection.FLIP_BOTH);
		   return finalBP;
	   }
  }

  // line 171 "../../../../../Block223PlayMode.ump"
   private boolean isCloser(BouncePoint bp, BouncePoint bounce){
    if(bp == null) {
		   return false;
	   }
	   if(bounce == null) {
		   return true;
	   }
	   double bpDist =Math.sqrt(Math.pow(Math.abs(currentBallX-bp.getX()),2)+Math.pow(Math.abs(currentBallY-bp.getY()),2));
	   double bounceDist = Math.sqrt(Math.pow(Math.abs(currentBallX-bounce.getX()),2)+Math.pow(Math.abs(currentBallY-bounce.getY()),2));;
	   if(bpDist<=bounceDist) {
		   return true;
	   }else {
		   return false;
	   }
  }

  // line 187 "../../../../../Block223PlayMode.ump"
   private void doSetup(){
    resetCurrentBallX();
	resetCurrentBallY();
	resetBallDirectionX();
	resetBallDirectionY();
	resetCurrentPaddleX();
	getGame();
	Level level = game.getLevel(currentLevel-1);
	List<BlockAssignment> assignments = level.getBlockAssignments();
	
	for(BlockAssignment a : assignments) {
		PlayedBlockAssignment pblock = new PlayedBlockAssignment(
				(Game.WALL_PADDING+Block.SIZE+Game.COLUMNS_PADDING) *(a.getGridHorizontalPosition()-1),
				(Game.WALL_PADDING+Block.SIZE+Game.ROW_PADDING) *(a.getGridVerticalPosition()-1), a.getBlock(), this);
	}
	
	while(numberOfBlocks() < game.getNrBlocksPerLevel()) {
		//lets not be random no idea how to chekc if grid is taken TODO
		PlayedBlockAssignment pblock = new PlayedBlockAssignment(0, 0, game.getRandomBlock(), this);
	}
  }


  /**
   * Guards
   */
  // line 32 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
	   setBounce(bp);
	   return (bp!=null);
  }

  // line 38 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    boolean outOfBounds = false;
		if (getLives() == 1) {
			outOfBounds = isOutOfBounds();
		}
		return outOfBounds;
  }

  // line 46 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    return (this.getCurrentBallY() + Ball.BALL_DIAMETER > 390);
  }

  // line 50 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = getGame();
	   int nrLevels = game.numberOfLevels();
	   setBounce(null);
	   if(nrLevels == currentLevel) {
		   int nrBlocks = numberOfBlocks();
		   if(nrBlocks == 1) {
			   PlayedBlockAssignment block = getBlock(0);
			   BouncePoint bp = calculateBouncePointBlock(block);
			   setBounce(bp);
			   return (bp != null);
		   }
	   }
    return false;
  }

  // line 66 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   if(nrBlocks==1) {
		   PlayedBlockAssignment block = getBlock(0);
		   BouncePoint bp = calculateBouncePointBlock(block);
		   setBounce(bp);
		   return (bp != null);
	   }
    return false;
  }

  // line 78 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = numberOfBlocks();
	   setBounce(null);
	   for(int i=0;i<nrBlocks;i++) {
		   PlayedBlockAssignment aBlock = getBlock(i);
		   BouncePoint bp = calculateBouncePointBlock(aBlock);
		   bounce = getBounce();
		   boolean closer = isCloser(bp, bounce);
		   if(closer) {
			   setBounce(bp);
		   }
	   }
    return (getBounce() != null);
  }

  // line 93 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
	   setBounce(bp);
	   return (bp != null);
  }

  // line 105 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }

  // line 109 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    setLives(getLives()-1);
		resetCurrentBallX();
		resetCurrentBallY();
		resetBallDirectionX();
		resetBallDirectionY();
		resetCurrentPaddleX();
  }

  // line 118 "../../../../../Block223States.ump"
   private void doHitBlock(){
    int score = getScore();
	   BouncePoint bounce = getBounce();
	   PlayedBlockAssignment pBlock = bounce.getHitBlock();
	   Block block = pBlock.getBlock();
	   int points = block.getPoints();
	   setScore(score+points);
	   pBlock.delete();
	   bounceBall();
  }

  // line 129 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    doHitBlock();
	   int level = getCurrentLevel();
	   setCurrentLevel(level+1);
	   setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength()-
			   (getGame().getPaddle().getMaxPaddleLength()-
					   getGame().getPaddle().getMinPaddleLength())/
			   (getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
	   setWaitTime(INITIAL_WAIT_TIME * Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel())-1));
  }

  // line 140 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    // TODO implement
  }

  // line 144 "../../../../../Block223States.ump"
   private void doGameOver(){
    Player p = getPlayer();
		if (p != null) {
			Game curGame = getGame();
			HallOfFameEntry hof = new HallOfFameEntry(score,playername,p,game,block223);
			curGame.setMostRecentEntry(hof);
		}
		delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 151 "../../../../../Block223PlayMode.ump"
  private static Point2D getIntersection (final Line2D.Double line1, final Line2D.Double line2) 
  {
    final double x1,y1, x2,y2, x3,y3, x4,y4;
       x1 = line1.x1; y1 = line1.y1; x2 = line1.x2; y2 = line1.y2;
       x3 = line2.x1; y3 = line2.y1; x4 = line2.x2; y4 = line2.y2;
       final double x = (
               (x2 - x1)*(x3*y4 - x4*y3) - (x4 - x3)*(x1*y2 - x2*y1)
               ) /
               (
               (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)
               );
       final double y = (
               (y3 - y4)*(x1*y2 - x2*y1) - (y1 - y2)*(x3*y4 - x4*y3)
               ) /
               (
               (x1 - x2)*(y3 - y4) - (y1 - y2)*(x3 - x4)
               );
       return new Point2D.Double(x, y);
  }
// line 115 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}
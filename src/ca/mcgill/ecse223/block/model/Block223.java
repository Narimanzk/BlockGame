package ca.mcgill.ecse223.block.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 2 "Block223.ump"
public class Block223
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block223 Associations
  private List<Player> players;
  private List<HallOfFame> hallOfFames;
  private List<Ball> balls;
  private List<Block> blocks;
  private List<Paddle> paddles;
  private List<Game> games;
  private List<Level> levels;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {
    players = new ArrayList<Player>();
    hallOfFames = new ArrayList<HallOfFame>();
    balls = new ArrayList<Ball>();
    blocks = new ArrayList<Block>();
    paddles = new ArrayList<Paddle>();
    games = new ArrayList<Game>();
    levels = new ArrayList<Level>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetMany */
  public HallOfFame getHallOfFame(int index)
  {
    HallOfFame aHallOfFame = hallOfFames.get(index);
    return aHallOfFame;
  }

  public List<HallOfFame> getHallOfFames()
  {
    List<HallOfFame> newHallOfFames = Collections.unmodifiableList(hallOfFames);
    return newHallOfFames;
  }

  public int numberOfHallOfFames()
  {
    int number = hallOfFames.size();
    return number;
  }

  public boolean hasHallOfFames()
  {
    boolean has = hallOfFames.size() > 0;
    return has;
  }

  public int indexOfHallOfFame(HallOfFame aHallOfFame)
  {
    int index = hallOfFames.indexOf(aHallOfFame);
    return index;
  }
  /* Code from template association_GetMany */
  public Ball getBall(int index)
  {
    Ball aBall = balls.get(index);
    return aBall;
  }

  public List<Ball> getBalls()
  {
    List<Ball> newBalls = Collections.unmodifiableList(balls);
    return newBalls;
  }

  public int numberOfBalls()
  {
    int number = balls.size();
    return number;
  }

  public boolean hasBalls()
  {
    boolean has = balls.size() > 0;
    return has;
  }

  public int indexOfBall(Ball aBall)
  {
    int index = balls.indexOf(aBall);
    return index;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetMany */
  public Paddle getPaddle(int index)
  {
    Paddle aPaddle = paddles.get(index);
    return aPaddle;
  }

  public List<Paddle> getPaddles()
  {
    List<Paddle> newPaddles = Collections.unmodifiableList(paddles);
    return newPaddles;
  }

  public int numberOfPaddles()
  {
    int number = paddles.size();
    return number;
  }

  public boolean hasPaddles()
  {
    boolean has = paddles.size() > 0;
    return has;
  }

  public int indexOfPaddle(Paddle aPaddle)
  {
    int index = paddles.indexOf(aPaddle);
    return index;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Player addPlayer(int aPoints, String aName, int aLives, String aPassword)
  {
    return new Player(aPoints, aName, aLives, aPassword, this);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    Block223 existingBlock223 = aPlayer.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPlayer.setBlock223(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a block223
    if (!this.equals(aPlayer.getBlock223()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHallOfFames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HallOfFame addHallOfFame(Game aGame)
  {
    return new HallOfFame(this, aGame);
  }

  public boolean addHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasAdded = false;
    if (hallOfFames.contains(aHallOfFame)) { return false; }
    Block223 existingBlock223 = aHallOfFame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aHallOfFame.setBlock223(this);
    }
    else
    {
      hallOfFames.add(aHallOfFame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasRemoved = false;
    //Unable to remove aHallOfFame, as it must always have a block223
    if (!this.equals(aHallOfFame.getBlock223()))
    {
      hallOfFames.remove(aHallOfFame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHallOfFameAt(HallOfFame aHallOfFame, int index)
  {  
    boolean wasAdded = false;
    if(addHallOfFame(aHallOfFame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFames()) { index = numberOfHallOfFames() - 1; }
      hallOfFames.remove(aHallOfFame);
      hallOfFames.add(index, aHallOfFame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHallOfFameAt(HallOfFame aHallOfFame, int index)
  {
    boolean wasAdded = false;
    if(hallOfFames.contains(aHallOfFame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHallOfFames()) { index = numberOfHallOfFames() - 1; }
      hallOfFames.remove(aHallOfFame);
      hallOfFames.add(index, aHallOfFame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHallOfFameAt(aHallOfFame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBalls()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Ball addBall(int aCurrentSpeed, Game aGame)
  {
    return new Ball(aCurrentSpeed, this, aGame);
  }

  public boolean addBall(Ball aBall)
  {
    boolean wasAdded = false;
    if (balls.contains(aBall)) { return false; }
    Block223 existingBlock223 = aBall.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aBall.setBlock223(this);
    }
    else
    {
      balls.add(aBall);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBall(Ball aBall)
  {
    boolean wasRemoved = false;
    //Unable to remove aBall, as it must always have a block223
    if (!this.equals(aBall.getBlock223()))
    {
      balls.remove(aBall);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBallAt(Ball aBall, int index)
  {  
    boolean wasAdded = false;
    if(addBall(aBall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBalls()) { index = numberOfBalls() - 1; }
      balls.remove(aBall);
      balls.add(index, aBall);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBallAt(Ball aBall, int index)
  {
    boolean wasAdded = false;
    if(balls.contains(aBall))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBalls()) { index = numberOfBalls() - 1; }
      balls.remove(aBall);
      balls.add(index, aBall);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBallAt(aBall, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(int aPoints, int aColour)
  {
    return new Block(aPoints, aColour, this);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Block223 existingBlock223 = aBlock.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aBlock.setBlock223(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a block223
    if (!this.equals(aBlock.getBlock223()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
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

  public boolean addOrMoveBlockAt(Block aBlock, int index)
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPaddles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Paddle addPaddle(Game aGame)
  {
    return new Paddle(this, aGame);
  }

  public boolean addPaddle(Paddle aPaddle)
  {
    boolean wasAdded = false;
    if (paddles.contains(aPaddle)) { return false; }
    Block223 existingBlock223 = aPaddle.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aPaddle.setBlock223(this);
    }
    else
    {
      paddles.add(aPaddle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePaddle(Paddle aPaddle)
  {
    boolean wasRemoved = false;
    //Unable to remove aPaddle, as it must always have a block223
    if (!this.equals(aPaddle.getBlock223()))
    {
      paddles.remove(aPaddle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPaddleAt(Paddle aPaddle, int index)
  {  
    boolean wasAdded = false;
    if(addPaddle(aPaddle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaddles()) { index = numberOfPaddles() - 1; }
      paddles.remove(aPaddle);
      paddles.add(index, aPaddle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePaddleAt(Paddle aPaddle, int index)
  {
    boolean wasAdded = false;
    if(paddles.contains(aPaddle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaddles()) { index = numberOfPaddles() - 1; }
      paddles.remove(aPaddle);
      paddles.add(index, aPaddle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPaddleAt(aPaddle, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aMinSpeed, int aMaxSpeed, int aMinLength, int aMaxLength, float aSpeedFactor, int aHeight, int aWidth, int aNumLevels, Admin aAdmin, Ball aBall, Paddle aPaddle, HallOfFame aHallOfFame)
  {
    return new Game(aName, aMinSpeed, aMaxSpeed, aMinLength, aMaxLength, aSpeedFactor, aHeight, aWidth, aNumLevels, aAdmin, aBall, aPaddle, aHallOfFame, this);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Block223 existingBlock223 = aGame.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aGame.setBlock223(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a block223
    if (!this.equals(aGame.getBlock223()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Level addLevel(int aNumber, boolean aIsRandom, Game aGame)
  {
    return new Level(aNumber, aIsRandom, this, aGame);
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    Block223 existingBlock223 = aLevel.getBlock223();
    boolean isNewBlock223 = existingBlock223 != null && !this.equals(existingBlock223);
    if (isNewBlock223)
    {
      aLevel.setBlock223(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a block223
    if (!this.equals(aLevel.getBlock223()))
    {
      levels.remove(aLevel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (players.size() > 0)
    {
      Player aPlayer = players.get(players.size() - 1);
      aPlayer.delete();
      players.remove(aPlayer);
    }
    
    while (hallOfFames.size() > 0)
    {
      HallOfFame aHallOfFame = hallOfFames.get(hallOfFames.size() - 1);
      aHallOfFame.delete();
      hallOfFames.remove(aHallOfFame);
    }
    
    while (balls.size() > 0)
    {
      Ball aBall = balls.get(balls.size() - 1);
      aBall.delete();
      balls.remove(aBall);
    }
    
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    while (paddles.size() > 0)
    {
      Paddle aPaddle = paddles.get(paddles.size() - 1);
      aPaddle.delete();
      paddles.remove(aPaddle);
    }
    
    while (games.size() > 0)
    {
      Game aGame = games.get(games.size() - 1);
      aGame.delete();
      games.remove(aGame);
    }
    
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
  }

}
import com.sun.jna.Pointer;

///The class implementing gameplay logic.
public class AI extends BaseAI {
  Player me = null;
  Pos[] spawnPoints = null; //0: northern, 1: eastern, 2: southern, 3: western
  
  
  
  public String username() {
    return "DING DONG WIIUUIIUU";
  }
  public String password() {
    return "password";
  }

  //This function is called each time it is your turn
  //Return true to end your turn, return false to ask the server for updated information
  public boolean run() {
    if (roundTurnNumber() <= 1) {
      int mapSize = mapHeight();
      me.placeTrap(mapSize/2-1 + playerID()*mapSize, 1, TrapTypes.SARCOPHAGUS);
    }
    else if (roundTurnNumber() <= 3) {
      me.purchaseThief(spawnPoints[0].x, spawnPoints[0].y, ThiefTypes.SLAVE);
    }
    
    for (int i = 0 ; i < thiefs.length; i++) {
      Thief thief = thiefs[i];
      if (thief.getOwner() == playerID())
        thief.move(thief.getX(), thief.getY()+1);
    }
    
    //me.pharaohTalk("Ha! That old scarab will break any day!");
    return true;
  }


  //This function is called once, before your first turn
  public void init() {
    me = BaseAI.players[playerID()];
    //me.pharaohTalk("So you decided to show up, eh? Bring it on!");

    int mapSize = mapHeight();
    spawnPoints = new Pos[4];
    spawnPoints[0] = new Pos(mapSize/2-1 + (1-playerID())*mapSize, 0);
    spawnPoints[1] = new Pos(mapSize-1 + (1-playerID())*mapSize, mapSize/2-1);
    spawnPoints[2] = new Pos(mapSize/2+1 + (1-playerID())*mapSize, mapSize-1);
    spawnPoints[3] = new Pos((1-playerID())*mapHeight(), mapSize/2+1);
    
  }

  //This function is called once, after your last turn
  public void end() {}
  
  
  public AI(Pointer c) {
    super(c);
  }
  
  //
  //HELPER STUFF
  //
  
  static class Pos {
    int x, y;
    Pos(int x, int y) { this.x = x; this.y = y; }
  }
  
  static class TrapTypes {
    public static final int SARCOPHAGUS = 0;
    public static final int SNAKE_PIT = 1;
    public static final int SWINGING_BLADE = 2;
    public static final int BOULDER = 3;
    public static final int SPIDER_WEB = 4;
    public static final int QUICKSAND = 5;
    public static final int OIL_VASES = 6;
    public static final int ARROW_WALL = 7;
    public static final int HEAD_WIRE = 8;
    public static final int MERCURY_PIT = 9;
    public static final int MUMMY = 10;
    public static final int FAKE_ROTATING_WALL = 11;
  };
  
  static class ThiefTypes {  
    public static final int BOMBER = 0;
    public static final int DIGGER = 1;
    public static final int NINJA = 2;
    public static final int GUIDE = 3;
    public static final int SLAVE = 4;
  };
  
  private Tile getTileAt(int x, int y) {
    return BaseAI.tiles[50*y + x];
    //return BaseAI.tiles[25*x + y];
  }
}

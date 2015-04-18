import com.sun.jna.Pointer;

///Represents a tile.
class Tile extends Mappable
{
  public static final int EMPTY = 0;
  public static final int SPAWN = 1;
  public static final int WALL = 2;
  
  public Tile(Pointer p)
  {
    super(p);
  }
  boolean validify()
  {
    if(iteration == BaseAI.iteration) return true;
    for(int i = 0; i < BaseAI.tiles.length; i++)
    {
      if(BaseAI.tiles[i].ID == ID)
      {
        ptr = BaseAI.tiles[i].ptr;
        iteration = BaseAI.iteration;
        return true;
      }
    }
    throw new ExistentialError();
  }

    //commands


    //getters

  ///Unique Identifier
  public int getId()
  {
    validify();
    return Client.INSTANCE.tileGetId(ptr);
  }
  ///X position of the object
  public int getX()
  {
    validify();
    return Client.INSTANCE.tileGetX(ptr);
  }
  ///Y position of the object
  public int getY()
  {
    validify();
    return Client.INSTANCE.tileGetY(ptr);
  }
  ///What type of tile this is. 0: empty, 1: spawn: 2: wall.
  public int getType()
  {
    validify();
    return Client.INSTANCE.tileGetType(ptr);
  }

}

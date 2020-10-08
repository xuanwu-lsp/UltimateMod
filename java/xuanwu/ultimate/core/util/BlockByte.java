package xuanwu.ultimate.core.util;

import java.io.Serializable;

public class BlockByte
  implements Serializable
{
  public int x;
  public int y;
  public int z;
  public int world;
  
  public BlockByte() {}
  
  public BlockByte(int x, int y, int z, int world)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    this.world = world;
  }
  
  public BlockByte(byte[] b)
  {
    Object obj = ByteBufUtil.toObject(b);
    BlockByte by = (BlockByte)obj;
    this.x = by.x;
    this.y = by.y;
    this.z = by.z;
    this.world = by.world;
  }
  
  public byte[] getByte()
  {
    return ByteBufUtil.toByte(this);
  }
}

package xuanwu.ultimate.core.util;

import java.io.IOException;
import net.minecraft.entity.Entity;

public class EntityByte
{
  public Entity entity;
  public double x;
  public double y;
  public double z;
  
  public EntityByte(Entity entity, double x, double y, double z)
  {
    this.entity = entity;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public EntityByte(byte[] b)
  {
    try
    {
      EntityByte by = (EntityByte)ByteBufUtil.getObject(b);
      this.entity = by.entity;
      this.x = by.x;
      this.y = by.y;
      this.z = by.z;
    }
    catch (ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public byte[] getByte()
  {
    return ByteBufUtil.toByte(this);
  }
}

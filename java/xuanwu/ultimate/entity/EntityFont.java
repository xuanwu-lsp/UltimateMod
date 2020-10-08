package xuanwu.ultimate.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFont
  extends Entity
  implements IEntityAdditionalSpawnData
{
  protected int age;
  public String font = "";
  protected int speed;
  
  public EntityFont(World world)
  {
    super(world);
  }
  
  public EntityFont(World world, String str, double x, double y, double z)
  {
    super(world);
    this.lastTickPosX = (this.posX = x);
    this.lastTickPosY = (this.posY = y);
    this.lastTickPosZ = (this.posZ = z);
    this.font = str;
  }
  
  protected void entityInit()
  {
    this.age = 0;
    this.speed = 500;
  }
  
  public void onEntityUpdate()
  {
    if (this.age++ > 50) {
      setDead();
    }
    this.posY += this.speed / 500.0D;
    if (this.speed > 1) {
      this.speed /= 2;
    } else if (this.speed == 1) {
      this.speed = 0;
    }
  }
  
  public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_) {}
  
  public void reSet(String font)
  {
    this.font = font;
    this.age = 0;
  }
  
  protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}
  
  protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
  
  public void writeSpawnData(ByteBuf buffer)
  {
    try
    {
      String msg = this.font;
      byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
      buffer.writeBytes(this.font.getBytes());
    }
    catch (Exception localException) {}
  }
  
  public void readSpawnData(ByteBuf buf)
  {
    try
    {
      String str;
      if (buf.hasArray())
      {
        str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
      }
      else
      {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(buf.readerIndex(), bytes);
        str = new String(bytes, 0, buf.readableBytes());
      }
      this.font = str;
    }
    catch (Exception localException) {}
  }
}

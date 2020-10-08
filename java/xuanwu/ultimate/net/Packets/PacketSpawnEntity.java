package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.util.ByteBufUtil;
import xuanwu.ultimate.core.util.EntityByte;

public class PacketSpawnEntity
  implements IMessage
{
  public EntityByte b;
  
  public PacketSpawnEntity() {}
  
  public PacketSpawnEntity(EntityByte b)
  {
    this.b = b;
  }
  
  public void fromBytes(ByteBuf buf)
  {
    this.b = ((EntityByte)ByteBufUtil.toObject(buf.array()));
  }
  
  public void toBytes(ByteBuf buf)
  {
    buf.writeBytes(this.b.getByte());
  }
  
  public static class Handler
    implements IMessageHandler<PacketSpawnEntity, IMessage>
  {
    public IMessage onMessage(PacketSpawnEntity pkt, MessageContext ctx)
    {
      Entity entity = pkt.b.entity;
      entity.setPosition(pkt.b.x, pkt.b.y, pkt.b.z);
      entity.worldObj.spawnEntityInWorld(entity);
      return null;
    }
  }
}

package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;

public class PacketRespawn
  implements IMessage
{
  private String player;
  
  public PacketRespawn(String player)
  {
    this.player = player;
  }
  
  public void fromBytes(ByteBuf buf)
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
      this.player = str;
    }
    catch (Exception localException) {}
  }
  
  public void toBytes(ByteBuf buf)
  {
    try
    {
      String msg = this.player;
      byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
      buf.writeBytes(this.player.getBytes());
    }
    catch (Exception localException) {}
  }
  
  public static class Handler
    implements IMessageHandler<PacketRespawn, IMessage>
  {
    public IMessage onMessage(PacketRespawn pkt, MessageContext ctx)
    {
      List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
      for (EntityPlayer player : players) {
        if (player.getDisplayName() == pkt.player) {
          player.setPosition(player.worldObj.getSpawnPoint().posX, player.worldObj.getSpawnPoint().posY, player.worldObj.getSpawnPoint().posZ);
        }
      }
      return null;
    }
  }
}

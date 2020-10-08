package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.ByteBufUtils;
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
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.gui.EnumConfig;

public class PacketSetConfig
  implements IMessage
{
  public int config;
  public PacketSetConfig() {}
  public PacketSetConfig(int config)
  {
    this.config = config;
  }
  
  public void fromBytes(ByteBuf buf)
  {
this.config = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf)
  {
buf.writeInt(this.config);
  }
  
  public static class Handler
    implements IMessageHandler<PacketSetConfig, IMessage>
  {
    public IMessage onMessage(PacketSetConfig pkt, MessageContext ctx)
    {
switch(pkt.config) {
case EnumConfig.RailGunBreakBlock:
	if(Data.Server.Config.BreakBlock) {
		Data.Server.Config.BreakBlock = false;
	}else {
		Data.Server.Config.BreakBlock = true;
	}
	break;
}
      return null;
    }
  }
}

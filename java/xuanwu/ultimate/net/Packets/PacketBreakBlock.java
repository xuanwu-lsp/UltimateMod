package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import xuanwu.ultimate.core.util.BlockByte;

public class PacketBreakBlock
  implements IMessage
{
  public int i;
  public void fromBytes(ByteBuf buf)
  {
this.i = buf.readInt();//这里读取数字,要保持顺序
  }
  
  public void toBytes(ByteBuf buf)
  {
buf.writeInt(1);//这里写入数字,要保持顺序
  }
  
  public static class Handler
    implements IMessageHandler<PacketBreakBlock, IMessage>
  {
    public IMessage onMessage(PacketBreakBlock pkt, MessageContext ctx)
    {
//这里是收到信息后执行的代码
      return null;
    }
  }
}

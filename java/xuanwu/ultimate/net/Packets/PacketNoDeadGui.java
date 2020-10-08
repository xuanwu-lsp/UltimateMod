package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import xuanwu.ultimate.GuiDead;

public class PacketNoDeadGui
  implements IMessage
{
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler
    implements IMessageHandler<PacketNoDeadGui, IMessage>
  {
    public IMessage onMessage(PacketNoDeadGui pkt, MessageContext ctx)
    {
      GuiScreen gui = Minecraft.getMinecraft().currentScreen;
      if (gui != null && 
        gui instanceof GuiGameOver)
      {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
        Minecraft.getMinecraft().thePlayer.respawnPlayer();
      }
      return null;
    }
  }
}

package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.TimerTask;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import xuanwu.ultimate.ClientUtil;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.ByteBufUtil;
import xuanwu.ultimate.item.ItemCBall;
import xuanwu.ultimate.listeners.TimeStop;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.register.Register;

@SideOnly(Side.CLIENT)
public class PacketTimeStop
  implements IMessage
{
  public boolean isStop;
  public PacketTimeStop() {}
  /*
   * true为开启时停,false为关闭时停
   */
  public PacketTimeStop(boolean isStop) {
	  this.isStop = isStop;
  }
  
  public void fromBytes(ByteBuf buf)
  {
this.isStop = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf)
  {
buf.writeBoolean(this.isStop);
  }
  
  public static class Handler
    implements IMessageHandler<PacketTimeStop, IMessage>
  {
    public IMessage onMessage(PacketTimeStop pkt, MessageContext ctx)
    {
if(pkt.isStop) {
	final ResourceLocation r = new ResourceLocation("shaders/post/desaturate.json");
    try {
    	(Minecraft.getMinecraft().entityRenderer.theShaderGroup = new ShaderGroup(Minecraft.getMinecraft().getTextureManager(), Minecraft.getMinecraft().getResourceManager(), Minecraft.getMinecraft().getFramebuffer(), r)).createBindFramebuffers(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    	Minecraft.getMinecraft().skipRenderWorld = true;
	Minecraft.getMinecraft().skipRenderWorld = true;
EntityFX[] particles = ClientUtil.GetAllParticle();
try {
for(int i = 0;i < particles.length;i++) {
try {
particles[i].getEntityData().setBoolean("SpawnBeforeTimeStop",true);
}catch(Exception exp) {}
}
}catch(Exception exp) {}
try {

    }
    catch (Exception ex) {}
	Minecraft.getMinecraft().skipRenderWorld = true;
    }catch(Exception ex) {}
}else {
	Minecraft.getMinecraft().entityRenderer.theShaderGroup=null;
	Minecraft.getMinecraft().skipRenderWorld = false;
}
      return null;
    }
    }
}

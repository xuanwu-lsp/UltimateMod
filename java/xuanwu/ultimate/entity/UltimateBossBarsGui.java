package xuanwu.ultimate.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import xuanwu.ultimate.entity.UltimateChicken;

public class UltimateBossBarsGui
  extends Gui
{
  private Minecraft mc;
  private static ResourceLocation texture = new ResourceLocation("ultimate", "textures/entities/chicken/bossbars/witherzilla.png");
  
  public UltimateBossBarsGui(Minecraft mc)
  {
    this.mc = mc;
  }
  
  @SubscribeEvent
  public void onRenderOverlay(RenderGameOverlayEvent event)
  {
    if (event.isCancelable()) {
      return;
    }
    int u = 0;
    int v = 0;
    String outstring = null;
    int color = 16382457;
    FontRenderer fr = this.mc.fontRenderer;
    
    int barWidth = 182;
    int namey = 10;
    int barHeight = 5;
    int y = 0;
    float fade = 1.0F;
    float gfHealth = 0.0F;
    boolean flag = true;
    
    Entity entity = null;
    EntityPlayer player = null;
    if (this.mc.playerController.enableEverythingIsScrewedUpMode()) {
      return;
    }
    player = this.mc.thePlayer;
    if (player == null) {
      return;
    }
    List list = player.worldObj.loadedEntityList;
    if ((list != null) && (!list.isEmpty())) {
      for (int j = 0; j < list.size(); j++)
      {
        entity = (Entity)list.get(j);
        if ((entity != null) && (!entity.isDead) && ((entity instanceof EntityLivingBase)))
        {
          EntityLivingBase e = (EntityLivingBase)entity;
          outstring = e.getCommandSenderName();
          gfHealth = e.getHealth() / e.getMaxHealth();
          flag = true;
          if (!e.isEntityAlive()) {
            fade = 0.25F;
          } else {
            fade = 1.0F;
          }
          if ((e instanceof UltimateChicken))
          {
            barWidth = 185;
            barHeight = 32;
            color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
            y = 46;
            namey = y + 61;
            fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
            texture = new ResourceLocation("thetitans", "textures/entities/titans/bossbars/witherzilla.png");
            ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            int width = res.getScaledWidth();
            int barWidthFilled = (int)(gfHealth * (barWidth + 1));
            int x = width / 2 - barWidth / 2;
            if (flag) {
              fr.drawStringWithShadow((int)e.getHealth() + "/" + (int)e.getMaxHealth(), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
            }
          }
          if (outstring == null) {
            return;
          }
          if (flag)
          {
            ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            int width = res.getScaledWidth();
            int barWidthFilled = (int)(gfHealth * (barWidth + 1));
            
            int x = width / 2 - barWidth / 2;
            this.mc.renderEngine.bindTexture(texture);
            
            GL11.glColor4f(fade, fade, fade, 1.0F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            x = width / 4 - barWidth / 4 - 12;
            barWidthFilled = (int)(gfHealth * (barWidth + 1));
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            drawTexturedModalRect(x, y, u, v, barWidth, barHeight);
            if (barWidthFilled > 0) {
              drawTexturedModalRect(x, y, u, v + barHeight, barWidthFilled, barHeight);
            }
            GL11.glDisable(3042);
            GL11.glScalef(0.6666667F, 0.6666667F, 0.6666667F);
            fr.drawStringWithShadow(outstring, width / 2 - fr.getStringWidth(outstring) / 2, namey, color);
          }
        }
      }
    }
  }
}

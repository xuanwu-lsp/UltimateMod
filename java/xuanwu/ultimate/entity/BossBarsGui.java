package xuanwu.ultimate.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import xuanwu.ultimate.core.ultimateCore;

public class BossBarsGui
  extends Gui
{
  private Minecraft mc;
  private static ResourceLocation texture = new ResourceLocation("ultimate", "textures/entities/chicken/bossbars/ultimatechicken.png");
  
  public BossBarsGui(Minecraft mc)
  {
    this.mc = mc;
  }
  
  @SubscribeEvent
  public void onRenderOverlay(RenderGameOverlayEvent event)
  {
    try
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
      if ((list != null) && (!list.isEmpty()))
      {
        for (int j = 0; j < list.size(); j++) {
          if ((entity instanceof UltimateChicken)) {
            entity = (Entity)list.get(j);
          }
        }
        UltimateChicken e = (UltimateChicken)entity;
        outstring = e.getCommandSenderName();
        gfHealth = 100.0F;
        fade = 1.0F;
        barWidth = 185;
        barHeight = 27;
        color = 12369084;
        y = 0;
        namey = y + 20;
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        GL11.glScalef(1.0F,1.0F,1.0F);
        int width = res.getScaledWidth();
        int barWidthFilled = (int)(gfHealth * (barWidth + 1));
        int x = width / 2 - barWidth / 2;
        fr.drawStringWithShadow((int)e.getHealth() + "/" + (int)e.getMaxHealth(), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, 16382457);
      }
    }
    catch (Exception exp)
    {
      ultimateCore.ChatPrint(exp.getMessage());
    }
  }
}

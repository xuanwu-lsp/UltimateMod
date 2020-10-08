package xuanwu.ultimate;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.Entity303;
import xuanwu.ultimate.entity.EntityAccelerator;
import xuanwu.ultimate.entity.EntityHanling;
import xuanwu.ultimate.entity.EntityHerobrine;
import xuanwu.ultimate.entity.EntityKongJvMoWang;
import xuanwu.ultimate.entity.EntityNotch;
import xuanwu.ultimate.entity.EntityNull;
import xuanwu.ultimate.entity.EntityPaojie;
import xuanwu.ultimate.entity.EntitySiLingQiShi;
import xuanwu.ultimate.entity.EntitySteve;

public class BossBar extends Gui{
	  private Minecraft mc;
	  public Random rand = new Random();
	  private static ResourceLocation texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/ultimatechicken.png");
	  public BossBar(Minecraft mc)
	  {
	    this.mc = mc;
	  }
	  @SubscribeEvent
	  public void onRenderOverlay(RenderGameOverlayEvent event)
	  {
		  if(event.type != RenderGameOverlayEvent.ElementType.ALL) {
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
	    boolean flag = false;
	    boolean hasColor = false;
	    float R = 0;
	    float G = 0;
	    float B = 0;
	    Entity entity = null;
	    EntityPlayer player = null;
	    player = this.mc.thePlayer;
	    if (player == null) {
	      return;
	    }
	    List list = player.worldObj.loadedEntityList;
	    if ((list != null) && (!list.isEmpty())) {
	        for (int j = 0; j < list.size(); j++)
	        {
	          entity = (Entity)list.get(j);
	          if ((entity instanceof DummyWither))
	          {
	        	  DummyWither e = (DummyWither)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 40;
	              namey = y + 61;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/ultimatechicken.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow((int)e.getHealth() + "/" + (int)e.getMaxHealth(), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if ((entity instanceof Entity303))
	          {
	        	  Entity303 e = (Entity303)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 256;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 30;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/303_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntitySiLingQiShi)
	          {
	        	  EntitySiLingQiShi e = (EntitySiLingQiShi)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/SiLingQiShi_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityKongJvMoWang)
	          {
	        	  EntityKongJvMoWang e = (EntityKongJvMoWang)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/KongJuMoWang_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityHerobrine)
	          {
	        	  EntityHerobrine e = (EntityHerobrine)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Him_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityNotch)
	          {
	        	  EntityNotch e = (EntityNotch)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Notch_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityNull)
	          {
	        	  EntityNull e = (EntityNull)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 45;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Null_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntitySteve)
	          {
	        	  EntitySteve e = (EntitySteve)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 15;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/EntitySteve.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityHanling)
	          {
	        	  EntityHanling e = (EntityHanling)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 5;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Hanling_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityPaojie)
	          {
	        	  EntityPaojie e = (EntityPaojie)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 10;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Paojie_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth()), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if (entity instanceof EntityAccelerator)
	          {
	        	  EntityAccelerator e = (EntityAccelerator)entity;
	        	  outstring = e.getCommandSenderName();
	        	  gfHealth = e.getHealth() / e.getMaxHealth();
	              barWidth = 185;
	              barHeight = 32;
	              color = 15728880 - (int)(MathHelper.cos(e.ticksExisted * 0.05F) * 1.572888E7F);
	              y = 0;
	              namey = y + 40;
	              fade = 1.0F + MathHelper.cos(e.ticksExisted * 0.2F) * 0.3F;
	              texture = new ResourceLocation("ultimate", "textures/entity/chicken/bossbars/Accelerator_BossBar.png");
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2;
	              flag = true;
	              fr.drawStringWithShadow(LudicrousText.makeFabulous(String.valueOf((int)e.getHealth() + "/" + (int)e.getMaxHealth())), width / 2 - fr.getStringWidth((int)e.getHealth() + "/" + (int)e.getMaxHealth()) / 2, namey + 10, color);
	          }
	          if(flag) {
	              ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
	              int width = res.getScaledWidth();
	              int barWidthFilled = (int)(gfHealth * (barWidth + 1));
	              int x = width / 2 - barWidth / 2+12;
	              this.mc.renderEngine.bindTexture(texture); 
	              if(hasColor) {
	            	  GL11.glColor4f(R,G,B, 0.5F);
	              }else {
	              GL11.glColor4f(fade, fade, fade, 1.0F);
	              }
	                GL11.glScalef(1.5F, 1.5F, 1.5F);
	                x = width / 4 - barWidth / 4+12;
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

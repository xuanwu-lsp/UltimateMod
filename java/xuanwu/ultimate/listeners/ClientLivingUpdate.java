package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.lwjgl.opengl.GL11;

import xuanwu.ultimate.ClientUtil;
import xuanwu.ultimate.GuiDead;
import xuanwu.ultimate.GuiMain;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.command.command;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.util.EventUtil;
import xuanwu.ultimate.render.FontRender2D;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.UltimateEntity;
import xuanwu.ultimate.core.ultimateCore;

@SideOnly(Side.CLIENT)
public class ClientLivingUpdate
{
	protected boolean State = false;
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void OnTick(RenderLivingEvent.Pre event) {
		if(ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
			if(isDeadGui(Minecraft.getMinecraft().currentScreen)) {
		onGuiOpen(new GuiOpenEvent(Minecraft.getMinecraft().currentScreen));
		event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void OnInitGui(GuiScreenEvent.InitGuiEvent.Pre event) {
		if(ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
			if(isDeadGui(event.gui)) {
		onGuiOpen(new GuiOpenEvent(event.gui));
		event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void OnInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
		if(ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
			if(isDeadGui(event.gui)) {
		onGuiOpen(new GuiOpenEvent(event.gui));
		event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void OnInitGui(ActionPerformedEvent.Pre event) {
		if(ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
			if(isDeadGui(event.gui)) {
		onGuiOpen(new GuiOpenEvent(event.gui));
		event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRender(RenderPlayerEvent.Pre event) {
		if(ultimateCore.isUltimate(event.entityPlayer)) {
			event.setCanceled(false);
			event.entityPlayer.deathTime = 0;
			event.entityPlayer.isDead = false;
			onGuiOpen(new GuiOpenEvent(Minecraft.getMinecraft().currentScreen));
			return;
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRenderLiving(RenderLivingEvent.Pre event) {
if(event.entity instanceof EntityPlayer) {
	if(ultimateCore.isUltimate((EntityPlayer)event.entity)) {
		onGuiOpen(new GuiOpenEvent(Minecraft.getMinecraft().currentScreen));
	}
	if(event.entity.UltimateDead) {
		event.setCanceled(true);
		return;
	}
}
	}
	public void onDeadScreen(GuiScreen screen) {
		UltimateMod.logger.info("There is an Unknow Dead Screen Try to Open");
		UltimateMod.logger.info("Violator:"+screen.getClass().getName());
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onGuiOpen(GuiOpenEvent event)
  {
		try {
		if(event.gui == null) {
			return;
		}
    if (event.gui instanceof GuiGameOver || this.isDeadGui(event.gui))
    {
      if (ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
    	  event.gui.onGuiClosed();
        event.setCanceled(true);
        this.onDeadScreen(event.gui);
      }
  }
		}catch(Exception exp) {}
  }

  public boolean isDeadGui(GuiScreen screen) {
	  if(screen != null) {
	  try {
	  Class<?> cla = Class.forName(screen.getClass().getName());
	  final String f = "CL_00000690";
	  final Field field = cla.getDeclaredField("__OBFID");
	  field.setAccessible(true);
	  final String str = (String)field.get(screen);
	  if(str == f) {
    	  return true;
	  }
	  }catch(Exception exp) {
	  }
	  try {
	  if(screen.getClass().getName().toLowerCase().indexOf("chaoswither") != -1) {
		  return true;
	  }
	  if(screen.getClass().getName().toLowerCase().indexOf("gameover") != -1) {
		  return true;
	  }
	  }catch(Exception exp) {}
	  }
	  return false;
  }

  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void OnLivingUpdate(LivingEvent.LivingUpdateEvent event)
  {
    if ((event.entity instanceof EntityPlayer))
    {
      EntityPlayer player = (EntityPlayer) event.entity;
      try
      {
    	  if(command.EnableKillAura) {
Minecraft mc = Minecraft.getMinecraft();
    			 for(Entity e : ultimateCore.getSurroundingEntities(mc.thePlayer,8)) {
if(e instanceof EntityLivingBase) {
	if(e != mc.thePlayer) {
		mc.thePlayer.swingItem();
	mc.playerController.attackEntity(mc.thePlayer,e);
	}
}
    			 }
    	  }
    	  if (ultimateCore.isUltimate(Minecraft.getMinecraft().thePlayer)) {
    	  Minecraft.getMinecraft().skipRenderWorld = false;
    	  }
        if (Data.dead && Minecraft.getMinecraft().currentScreen != null && !isDeadGui(Minecraft.getMinecraft().currentScreen))
        {
          Data.dead = false;
          Minecraft.getMinecraft().displayGuiScreen(new GuiDead());
        }
        player = (EntityPlayer)event.entity;
      }
      catch (Exception localException1) {}
      if (!ultimateCore.isUltimate((EntityPlayer)event.entity)) {
        return;
      }
      Minecraft.getMinecraft().thePlayer.respawnPlayer();
      GuiScreen gui = Minecraft.getMinecraft().currentScreen;
      if ((gui != null) && 
        ((gui instanceof GuiGameOver || this.isDeadGui(gui))))
      {
        gui.onGuiClosed();
        Minecraft.getMinecraft().thePlayer.setHealth((float)ultimateCore.Infinity);
      }
    }
  }
  
  public class Dead
    extends Thread
  {
    public Dead() {}
    
    public void run()
    {
      label0:
      break label0;
    }
  }
}

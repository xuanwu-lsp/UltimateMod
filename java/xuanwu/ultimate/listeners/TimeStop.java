package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import xuanwu.ultimate.core.TimeStopManager;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.EntityDummy;
import xuanwu.ultimate.entity.EntityFont;

public class TimeStop
{
  protected static boolean enabletimestop = false;
  protected static EntityPlayer player1;
  protected static List<EntityDummy> stopentity = new ArrayList<EntityDummy>();
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
  {
    if (enabletimestop) {
      try
      {
        if (((event.entity instanceof EntityPlayer)) && 
          (ultimateCore.isUltimate((EntityPlayer)event.entity))) {
          return;
        }
        if ((event.entity instanceof EntityFont)) {
          return;
        }
        event.setCanceled(true);
        ultimateCore.BackEntity(event.entity);
        List<Entity> entity = event.entity.worldObj.loadedEntityList;
        for (Entity ent : entity) {
          if ((!(ent instanceof EntityPlayer)) || 
            (!ultimateCore.isUltimate((EntityPlayer)ent))) {
            ultimateCore.BackEntity(ent);
          }
        }
      }
      catch (Exception exp)
      {
        ultimateCore.ChatPrint(String.valueOf(exp));
      }
    }
  }
  
  public static void EnableTimeStop()
  {
      enabletimestop = true;
  }
  
  public static boolean isTimeStoppping()
  {
    return enabletimestop;
  }
  
  public static void disableTimeStop()
  {
	  enabletimestop = false;
  }
}

package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import xuanwu.ultimate.core.ultimateCore;

public class LivingFall
{
@SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingFall(LivingFallEvent event)
  {
    if ((event.entityLiving instanceof EntityPlayer))
    {
      EntityPlayer player = (EntityPlayer)event.entityLiving;
      if (ultimateCore.isUltimate(player)) {
        event.setCanceled(true);
      }
    }
  }
}

package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.item.ItemDyeSword;
import xuanwu.ultimate.register.Register;

public class LivingAttack
{
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingAttack(LivingAttackEvent event)
  {
	  try {
    if (event.source == null) {
      return;
    }
    if (event.entityLiving == null) {
      return;
    }
    if ((event.entityLiving instanceof EntityPlayer))
    {
      if (ultimateCore.isUltimate((EntityPlayer)event.entityLiving))
      {
    	  try {
    	  if(Data.Server.Config.UltimateKillAttacker) {
        ultimateCore.kill(event.source.getEntity());
    	  }
    	  }catch(Exception exp) {}
        event.setCanceled(true);
      }
      EntityPlayer player = (EntityPlayer)event.entityLiving;
      if ((player.inventory.getCurrentItem() != null) && 
        (player.inventory.getCurrentItem().getItem() == Register.DyeSword))
      {
        ItemDyeSword sword = (ItemDyeSword)player.inventory.getCurrentItem().getItem();
        if (sword.isUsing)
        {
          ultimateCore.RenderFontOnPlayer(player, "Miss");
          event.setCanceled(true);
        }
      }
    }
  }catch(Exception exp) {
	  exp.printStackTrace();
  }
}
}

package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

@Cancelable
public class LivingHurt
{
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingHurt(LivingHurtEvent event)
  {
    try
    {
      if (event.entity == null) {
        return;
      }
      if(!(event.entityLiving instanceof EntityPlayer)) {
    	  if(Data.NoHurtCam) {
    		  event.entityLiving.hurtResistantTime = 0;
    	  }
      }
      if ((event.entity instanceof EntityPlayer))
      {
        try
        {
          EntityPlayer player = (EntityPlayer)event.entity;
          if ((player.inventory.getCurrentItem().getItem() == Register.LavaSword) && 
            (Data.isLavaSwordUsing) && (
            (event.source == DamageSource.magic) || (event.source == DamageSource.outOfWorld) || (event.source == DamageSource.wither))) {
            event.setCanceled(true);
          }
          if (player.getCurrentArmor(2) != null && 
          player.getCurrentArmor(2).getItem() == Register.LavaChest)
          {
            if (event.source != null)
            {
              Entity entity = event.source.getEntity();
              if ((entity instanceof EntityLivingBase))
              {
                EntityLivingBase living = (EntityLivingBase)entity;
                living.setHealth(living.getHealth() - event.ammount * 0.3F);
              }
            }
            float ammount = event.ammount;
            Data.LavaChestImmuneDamage += ammount / 2.0F;
            Data.LavaChestDamage += (float)(ammount * 0.3D);
            event.ammount /= 2.0F;
          }
        }
        catch (Exception localException1) {}
        if (ultimateCore.isUltimate((EntityPlayer)event.entity))
        {
			if(Data.Server.Config.UltimateKillAttacker) {
          ultimateCore.kill(event.source.getEntity());
			}
          event.setCanceled(true);
          return;
        }
      }
      if (!(event.entity instanceof EntityPlayerMP))
      {
        if (((event.source.getEntity() instanceof EntityPlayerMP)) && (ultimateCore.isUltimate((EntityPlayer)event.source.getEntity())))
        {
          EntityPlayer player = (EntityPlayer)event.source.getEntity();
          ultimateCore.kill(event.entity);
          return;
        }
        return;
      }
      if ((!(event.source.getEntity() instanceof Entity)) && (ultimateCore.isUltimate((EntityPlayer)event.entity)))
      {
        event.setCanceled(true);
        ((EntityPlayer)event.entity).extinguish();
        return;
      }
      if (!ultimateCore.isUltimate((EntityPlayer)event.entity)) {
        return;
      }
      event.setCanceled(true);
      ((EntityPlayer)event.entity).extinguish();
      event.ammount = 0.0F;
      EntityPlayer player = (EntityPlayer)event.entity;
		if(Data.Server.Config.UltimateKillAttacker) {
      ultimateCore.kill(event.source.getEntity());
		}
    }
    catch (Exception exp)
    {
      ((EntityPlayer)event.entity).addChatComponentMessage(new ChatComponentTranslation(exp.getMessage(), new Object[0]));
    }
  }
}

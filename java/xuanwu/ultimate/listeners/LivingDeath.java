package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import xuanwu.ultimate.Achivenent.UltimateAchivenent;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

@Cancelable
public class LivingDeath
{
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingDeath(LivingDeathEvent event)
  {
    try
    {
      if ((!(event.entity instanceof EntityPlayer)) && 
        ((event.entity instanceof EntityBat)))
      {
        Random random = new Random();
        int r = random.nextInt(10);
        if (7 > r)
        {
          EntityItem item = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(Register.BatSoup));
          item.worldObj.spawnEntityInWorld(item);
        }
      }
      if ((!(event.entity instanceof EntityPlayer)) && 
        ((event.entity instanceof EntityHorse)))
      {
        Random random = new Random();
        int r = random.nextInt(10);
        if (8 > r)
        {
          EntityItem item = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, new ItemStack(Register.ItemHorseStack));
          item.worldObj.spawnEntityInWorld(item);
        }
      }
      EntityPlayer player = (EntityPlayer)event.entity;
      if ((player.getCurrentArmor(1) != null) && (player.getCurrentArmor(1).getItem() == Register.LavaLeggins) && 
        (Data.LavalegginsLq <= 0))
      {
        event.setCanceled(true);
        event.entityLiving.setHealth(0.5F);
        Data.LavalegginsLq = 2400;
      }
      if (ultimateCore.hasTotem((EntityPlayer)event.entity))
      {
        ((EntityPlayer)event.entity).addStat(UltimateAchivenent.cyss, 1);
        event.setCanceled(true);
        ((EntityPlayer)event.entity).setHealth(((EntityPlayer)event.entity).getMaxHealth());
        ((EntityPlayer)event.entity).addPotionEffect(new PotionEffect(Potion.heal.id, 255, 255));
        ((EntityPlayer)event.entity).addPotionEffect(new PotionEffect(Potion.fireResistance.id, 255, 255));
        ((EntityPlayer)event.entity).addPotionEffect(new PotionEffect(Potion.resistance.id, 255, 255));
        ((EntityPlayer)event.entity).worldObj.playSoundAtEntity((EntityPlayer)event.entity, "ultimate:totem", Float.MAX_VALUE, 1.0F);
      }
      if (ultimateCore.isUltimate((EntityPlayer)event.entity))
      {
        Entity source = event.source.getEntity();
        if (source != null) {
          ultimateCore.kill(source);
        }
        event.setCanceled(true);
      }
    }
    catch (Exception localException) {}
  }
}

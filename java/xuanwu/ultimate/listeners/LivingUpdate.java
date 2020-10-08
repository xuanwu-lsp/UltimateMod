package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import org.apache.logging.log4j.Logger;
import xuanwu.ultimate.Potion.PotionHelper;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.Data.UltimateOnwerList;
import xuanwu.ultimate.core.Data.WuHanVirusPlayerList;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.item.CoalSword;
import xuanwu.ultimate.net.Packets.PacketNoDeadGui;
import xuanwu.ultimate.register.Register;

public class LivingUpdate
{
  public static boolean EnableTimeStop = false;
  public static List<Entity> TimeStopImmuneList = new ArrayList();
  
  public static void EnableTimeStop()
  {
    EnableTimeStop = true;
  }
  
  private static double ease(double in)
  {
    double t = in - 1.0D;
    return Math.sqrt(1.0D - t * t);
  }
  
  public static double getVoidScale(double age)
  {
    double life = age / 186.0D;
    
    double collapse = 0.95D;
    double curve;
    if (life < collapse) {
      curve = 0.005D + ease(1.0D - (collapse - life) / collapse) * 0.995D;
    } else {
      curve = ease(1.0D - (life - collapse) / (1.0D - collapse));
    }
    return 10.0D * curve;
  }
  
  public static double randomvel()
  {
    return (new Random().nextDouble() - 0.5D) * 0.08D;
  }
  
  public static void DisableTimeStop()
  {
    EnableTimeStop = false;
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
  {
    try
    {
      if ((Data.stopentities.indexOf(event.entity) != -1) && 
        (!(event.entity instanceof EntityPlayer)))
      {
        Data.stopentities.remove(event.entity);
        event.setCanceled(true);
        return;
      }
      if ((event.entityLiving instanceof EntityPlayer))
      {
        EntityPlayer player = (EntityPlayer)event.entity;
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        if (ultimateCore.isUltimate((EntityPlayer)event.entityLiving))
        {
        		UltimateOnwerList.addOnwer(player);
            if (!player.inventory.hasItem(Register.ultimatesword)) {
                if(!player.inventory.addItemStackToInventory(new ItemStack(Register.ultimatesword))) {
                	player.inventory.mainInventory[player.inventory.currentItem] = ItemStack.copyItemStack(new ItemStack(Register.ultimatesword));
                }
              }
          event.entityLiving.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
          event.entityLiving.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(Double.POSITIVE_INFINITY);
          event.entityLiving.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(Double.POSITIVE_INFINITY);
          event.entityLiving.setHealth(event.entityLiving.getMaxHealth());
          event.entity.isDead = false;
          ((EntityPlayer)event.entityLiving).setHealth(((EntityPlayer)event.entityLiving).getMaxHealth());
          if (event.entityLiving.posY < -45.0D) {
            event.entityLiving.motionY += 10.0D;
          }
          player.worldObj.setEntityState(player, (byte)0);
          if ((event.entityLiving.isDead == true) || (event.entityLiving.getHealth() <= 0.0F)) {
            ultimateCore.NMSL((EntityPlayer)event.entity);
          }
          EntityPlayer player1 = (EntityPlayer)event.entityLiving;
          player.capabilities.disableDamage = true;
          Field cnmd = ReflectionHelper.findField((Class)EntityLivingBase.class, new String[] { "recentlyHit", "field_70718_bc" });
          cnmd.setInt(player, Integer.MIN_VALUE);
          player.capabilities.isCreativeMode = true;
          player.setInvisible(false);
          player.capabilities.allowEdit = true;
          player.capabilities.setFlySpeed(0.05F);
          player1.getFoodStats().addStats(20, 20.0F);
          ((EntityPlayer)event.entityLiving).capabilities.allowFlying = true;
          ((EntityPlayer)event.entityLiving).isDead = false;
          ((EntityPlayer)event.entityLiving).extinguish();
          ((EntityPlayer)event.entityLiving).deathTime = 0;
          ((EntityPlayer)event.entityLiving).addPotionEffect(new PotionEffect(Potion.nightVision.id, 1360, 200, false));
          ((EntityPlayer)event.entityLiving).addPotionEffect(new PotionEffect(Potion.resistance.id, 1360, 200, false));
          event.entityLiving.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(ultimateCore.Infinity);
          event.entityLiving.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(ultimateCore.Infinity);
          player.getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float(20, 0.0F, player.getMaxHealth())));
          ((EntityPlayer)event.entityLiving).maxHurtTime = ((int)ultimateCore.Infinity);
          ((EntityPlayer)event.entityLiving).prevHealth = 20.0F;
          ((EntityPlayer)event.entityLiving).maxHurtResistantTime = ((int)ultimateCore.Infinity);
        }
        if ((player.inventory.getCurrentItem() != null) && 
          (player.inventory.getCurrentItem().getItem() == Register.Coal_Sword))
        {
          CoalSword sword = (CoalSword)player.inventory.getCurrentItem().getItem();
          if (sword.lq >= 0) {
            sword.lq -= 1;
          }
        }
        if (WuHanVirusPlayerList.isVirus(player)) {
          player.setHealth(player.getHealth() - 0.1F);
        }
        if ((ultimateCore.hasTotem((EntityPlayer)event.entityLiving)) && 
          (event.entityLiving.posY < -45.0D))
        {
          event.entityLiving.motionY += 10.0D;
          ((EntityPlayer)event.entityLiving).worldObj.playSoundAtEntity((EntityPlayer)event.entityLiving, "ultimate:totem", Float.MAX_VALUE, 1.0F);
        }
      }
    }
    catch (Exception exp)
    {
    	for(int i = 0;i < exp.getStackTrace().length;i++) {
      UltimateMod.logger.info(exp.getStackTrace()[i]);
    	}
    }
  }
}

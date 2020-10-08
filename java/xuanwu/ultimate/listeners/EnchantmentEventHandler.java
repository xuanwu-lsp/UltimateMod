package xuanwu.ultimate.listeners;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class EnchantmentEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingHurt(LivingHurtEvent event) {
	if(event.source.getEntity() != null && event.source.getEntity() instanceof EntityPlayer) {
		EntityPlayer player = (EntityPlayer)event.source.getEntity();
		if(player.inventory.getCurrentItem() != null && ultimateCore.isEffectActive(Register.Slowdown,player.inventory.getCurrentItem())) {
			int level = EnchantmentHelper.getEnchantmentLevel(Register.Slowdown.effectId,player.inventory.getCurrentItem());
			int potionlevel = level;
			event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,level*20,level));
		}
		if(ultimateCore.isEffectActive(Register.Awaken,((EntityPlayer)event.source.getEntity()).inventory.getCurrentItem())) {
			event.ammount += ((EntityPlayer)event.source.getEntity()).inventory.getCurrentItem().stackTagCompound.getInteger("UltimateAwakenKillCount");
		}
	}
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onAddInformation(ItemTooltipEvent event) {
		if(ultimateCore.isEffectActive(Register.Awaken,event.itemStack)) {
			event.toolTip.add(EnumChatFormatting.GREEN+"=========="+EnumChatFormatting.WHITE+"[觉醒]"+EnumChatFormatting.GREEN+"==========");
			event.toolTip.add(EnumChatFormatting.RED+"觉醒等级:"+EnumChatFormatting.WHITE+String.valueOf(((int)(event.itemStack.stackTagCompound.getInteger("UltimateAwakenKillCount")/1000))));
			event.toolTip.add(EnumChatFormatting.RED+"杀敌数:"+EnumChatFormatting.WHITE+String.valueOf(event.itemStack.stackTagCompound.getInteger("UltimateAwakenKillCount")));
			event.toolTip.add(EnumChatFormatting.RED+"附加伤害:"+EnumChatFormatting.WHITE+String.valueOf(event.itemStack.stackTagCompound.getInteger("UltimateAwakenKillCount")));
			event.toolTip.add(EnumChatFormatting.GREEN+"=========="+EnumChatFormatting.WHITE+"[觉醒]"+EnumChatFormatting.GREEN+"==========");
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingDeath(LivingDeathEvent event) {
		if(event.entityLiving != null) {
			if(event.source != null) {
				if(event.source.getEntity() != null) {
					if(event.source.getEntity() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer)event.source.getEntity();
						if(player.inventory.getCurrentItem() != null) {
							if(ultimateCore.isEffectActive(Register.Awaken,player.inventory.getCurrentItem())) {
								player.inventory.getCurrentItem().stackTagCompound.setInteger("UltimateAwakenKillCount",player.inventory.getCurrentItem().stackTagCompound.getInteger("UltimateAwakenKillCount")+1);
							}
						}
					}
				}
			}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingAttack(LivingAttackEvent event) {
		if(event.entityLiving != null) {
			if(event.source != null) {
			if(event.source.getEntity() != null) {
				if(event.source.getEntity() instanceof EntityPlayer) {
					if(((EntityPlayer)event.source.getEntity()).inventory.getCurrentItem() != null){
						if(ultimateCore.isEffectActive(Register.ELightningSword,((EntityPlayer)event.source.getEntity()).inventory.getCurrentItem())) {
							event.entityLiving.worldObj.addWeatherEffect(new ultimateLightningbolt(event.entityLiving.worldObj,event.entity.posX,event.entity.posY,event.entity.posZ));
							event.entityLiving.setFire(100);
							event.entityLiving.attackEntityFrom(Register.UDS(),5*ultimateCore.getEnchantmentLevel(Register.ELightningSword,((EntityPlayer)event.source.getEntity()).inventory.getCurrentItem()));
						}
					}
				}
			}
			}
		}
	}
	  @SubscribeEvent(priority = EventPriority.HIGHEST)
	  public void harvestDrops(BlockEvent.HarvestDropsEvent event)
	  {
		  try {
		  if(event.harvester != null && event.harvester.inventory.getCurrentItem() != null && ultimateCore.isEffectActive(Register.FirePickaxe,event.harvester.inventory.getCurrentItem())){
	      ArrayList<ItemStack> removeThese = new ArrayList(1);
	      ArrayList<ItemStack> addThese = new ArrayList(1);
	      for (ItemStack input : event.drops)
	      {
	        ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(input);
	        if (result != null)
	        {
	          addThese.add(new ItemStack(result.getItem(), input.stackSize));
	          removeThese.add(input);
	        }
	      }
	      event.drops.removeAll(removeThese);
	      event.drops.addAll(addThese);
		  }
	    }catch(Throwable exp) {
	    	exp.printStackTrace();
	    }
	  }
}

package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.Data.StoredData;
import xuanwu.ultimate.core.Data.UltimateOnwerList;
import xuanwu.ultimate.entity.EntityDummy;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.item.ItemCBall;
import xuanwu.ultimate.item.ultimateSword;
import xuanwu.ultimate.register.Register;
public class EventHandler
{
  public static int BL = 0;
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onPlayerDrops(PlayerDropsEvent event) {
	  if(ultimateCore.isUltimate(event.entityPlayer)) {
		  event.setCanceled(true);
	  }
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onPlayerMine(PlayerInteractEvent event) {
      if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && ultimateCore.isUltimate(event.entityPlayer)) {
          this.breakBlock(event.x, event.y, event.z, event.entityPlayer);
      }
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void OnEntityJoinWorld(EntityJoinWorldEvent event) {
	  if(TimeStop.isTimeStoppping()) {
		  if(!(event.entity instanceof EntityDummy)) {
		  event.setCanceled(true);
		  ultimateCore.kill(event.entity);
		  }
	  }
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void harvestDrops(BlockEvent.HarvestDropsEvent event)
  {
	  if(ultimateCore.isUltimate(event.harvester)){
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
    }
public void breakBlock(int X,int Y,int Z,EntityPlayer player){
	if(player.worldObj.isRemote) {
		return;
	}
    int l = player.worldObj.getBlockMetadata(X, Y, Z);
    Block block = player.worldObj.getBlock(X, Y, Z);
    List<ItemStack> stacks = block.getDrops(player.worldObj,X,Y,Z,l,0);
    player.worldObj.playAuxSFX(2001, X, Y, Z, Block.getIdFromBlock(player.worldObj.getBlock(X, Y, Z)) + (l << 12));
    for(ItemStack stack : stacks) {
    	ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stack);
    	if(result == null) {
    		player.inventory.addItemStackToInventory(stack);	
    	}else {
    		player.inventory.addItemStackToInventory(result);
    	}
    }
    player.worldObj.removeTileEntity(X, Y, Z);
    player.worldObj.setBlockToAir(X, Y, Z);
}
@SubscribeEvent(priority = EventPriority.HIGHEST)
public void onServerTick(ServerTickEvent event) {
	List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
	for(EntityPlayer player : players) {
		if(ultimateCore.isUltimate(player)) {
			ForgeHooks.onLivingUpdate(player);
			MinecraftForge.EVENT_BUS.post(new LivingUpdateEvent(player));
			new LivingUpdate().onLivingUpdate(new LivingUpdateEvent(player));
		}
	}
	this.UpdatePlayersMoneyStatus();
}
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onEntityItemPickup(EntityItemPickupEvent event)
  {
	  if(ultimateCore.isUltimate(event.entityPlayer)) {
	  event.setCanceled(false);
	  ItemStack stack = event.item.getEntityItem();
	  event.entityPlayer.inventory.addItemStackToInventory(stack);
	  event.item.setDead();
	  }
  }
  public void UpdatePlayersMoneyStatus() {

  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onServerChat(ServerChatEvent event)
  {
    if (ultimateCore.isUltimate(event.player))
    {
      event.setCanceled(true);
      ultimateCore.ChatPrint("§e[神]§/" + event.username + ":" + ServerLudicrousText.makeFabulous(event.message));
    }
  }
  @SubscribeEvent
  public void onServerPacket(FMLNetworkEvent.ServerCustomPacketEvent event)
  {
  }
  @SubscribeEvent
  public void onFucking2(FuckingDisarmAndArmEvent event)
  {
    EntityPlayer player = (EntityPlayer)event.entityLiving;
    if (ExampleEvent.isGod2(player)) {
      event.setCanceled(true);
    }
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void OnServerTick(ServerTickEvent event) {
	
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onRightClick(PlayerInteractEvent event)
  {
    if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) && 
      (Data.isSNItem))
    {
      ItemStack stack = event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem];
      if ((stack != null) && 
        (stack.getItem() != Register.ItemCBall))
      {
        ItemCBall ball = (ItemCBall)Register.ItemCBall;
        ball.inventory.add(stack);
        event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem] = null;
      }
    }
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void OnCommand(CommandEvent event) {
	  event.setResult(Event.Result.ALLOW);
for(String str :UltimateOnwerList.get()) {
	for(String p : event.parameters) {
		if(p.indexOf(str) != -1) {
			  event.sender.addChatMessage(ultimateCore.Text(EnumChatFormatting.RED+"Access Violation"));
			  event.setCanceled(true);
			  return;
		}
	}
}
  }
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onBreakBlock(BlockEvent.BreakEvent event)
  {
    if (BL > 0) {
      for (int i = 0; i < BL - 1; i++)
      {
        Item item = event.block.getItem(event.world, event.x, event.y, event.z);
        EntityItem entity = new EntityItem(event.world, event.x, event.y, event.z, new ItemStack(item));
        entity.worldObj.spawnEntityInWorld(entity);
      }
    }
  }
}

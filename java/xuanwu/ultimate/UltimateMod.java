package xuanwu.ultimate;

import cpw.mods.fml.common.Mod;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import xuanwu.ultimate.core.Core;
import xuanwu.ultimate.core.Data.StoredData;
import xuanwu.ultimate.core.Data.UltimateOnwerList;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.proxy.CommonProxy;
import xuanwu.ultimate.register.Register;
import xuanwu.ultimate.register.Register.ServerStart;
import xuanwu.ultimate.register.Register.load;
import xuanwu.ultimate.register.Register.postload;
import xuanwu.ultimate.register.Register.preload;
/**
 * Welcome to the source of Ultimate Mod!
 * OpenCV gogogo!
 */
@Mod(modid="ultimatemod", version="1.0", acceptableRemoteVersions="1.7.10")
public class UltimateMod
{
  /**
   * Modid,it is a constant field(or readonly?)
   */
  public static final String MODID = "UltimateMod";
  /**
   * Ditto.....
   */
  public static final String VERSION = "1.0";
  /**
   * Don't copy this
   */
  public static Configuration config = null;
  /**
   * output
   */
  public static Logger logger;
  /**
   * This is proxy,we can use it to registry things
   */
  @SidedProxy(clientSide="xuanwu.ultimate.proxy.ClientProxy", serverSide="xuanwu.ultimate.proxy.CommonProxy")
  public static CommonProxy proxy;
  /**
   * Instance of ultimate mod.
   */
  @Mod.Instance
  public static UltimateMod instance;
  /**
   * emmmmmm
   */
  private static SimpleNetworkWrapper network;
  
  /*
   * Useless Method
   */
  public static SimpleNetworkWrapper getNetwork()
  {
    return network;
  }
  /*
   * Construct Method.
   */
  public UltimateMod() {
	  instance = this;
  }
  /*
   * Checking time when ultimate mod is time limited version.
   */
  public static void Check() {

  }
  /**
   * See how I write a novel in notes!
   * Calling on game preload(Stage 1)
   * it will registry items,fueld,worlds,packets,potions and enchantments
   * it will call CommonProxy.preLoad when registry is done
   */
  @Mod.EventHandler
  public void preLoad(FMLPreInitializationEvent event)
  {
    Check();//Checking time when ultimate mod is time limited version.
    /**
     * if Coremod not enabled,the process will terminate. :)
     */
	  if(!Core.CoreEnabled) {
		  System.exit(0);
    }
    /**
     * I don't know
     */
	  UltimateTimer.addTaskWithNewInstance(new TimerTask() {
		@Override
		public void run() {
ServerLudicrousText.i+=1;
		}
	  });
    this.config = new Configuration(event.getSuggestedConfigurationFile());
    /**
     * Initialization logger
     */
    logger = event.getModLog();
    logger.info("Ultimate Mod Start PreLoad.");
    Register.preload.RegisterItems();//Registry Things
    Register.preload.RegisterFuels();
    Register.preload.RegisterWorlds();
    Register.preload.RegisterPacket();
    Register.preload.RegisterPotions();
    Register.preload.RegisterEnchantments();
    proxy.preInit(event);
    logger.info("Ultimate Mod PreLoad Done.");
  }
  /**
   * Calling on Game load
   * Registring Events,Entities and WorldGeneration
   * it will call CommonProxy.load when registry is done
   * @param event unrefenerce param
   */
  @Mod.EventHandler
  public void load(FMLInitializationEvent event)
  {
    logger.info("Ultimate Mod Start Load");
    Register.load.RegisterEvents();//Registry things
    Register.load.RegisterEntities();
    Register.load.RegisterWorldGeneration();
    proxy.init(event);
    logger.info("Ultimate Mod Load Done.");
  }
    /**
   * Calling on Game load
   * Registring Events,Entities and WorldGeneration
   * it will call CommonProxy.load when registry is done
   */
  @Mod.EventHandler
  public void postLoad(FMLPostInitializationEvent event)
  {
    logger.info("Ultimate Mod Start PostLoad");
    Register.postload.RegisterAchieve();
    Register.postload.RegisterShapedRicipe();
    proxy.postInit(event);
    logger.info("Ultimate Mod PostLoad Done.");
  }
  /**
   * Calling on ServerStarting
   * Registry Commands and load ultimate sword onwers
   */
  @Mod.EventHandler
  public void serverStart(FMLServerStartingEvent event)
  {
    Register.ServerStart.RegisterCommands(event);
    StoredData data = new StoredData("onwerlist");
	  Object d = data.Read();
    if(d == null) {
			data.set(UltimateOnwerList.get());
    }else {
  List<String> onwers = (List<String>)data.Read();
  for(String str : onwers) {
  	UltimateOnwerList.addOnwer(str);
  }
    }
  }
}

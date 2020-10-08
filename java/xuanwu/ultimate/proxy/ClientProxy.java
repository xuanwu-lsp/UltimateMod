package xuanwu.ultimate.proxy;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;
import org.apache.commons.io.FileUtils;
import org.lwjgl.opengl.Display;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import xuanwu.ultimate.BossBar;
import xuanwu.ultimate.core.Key;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.entity.BasketBall;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.Entity303;
import xuanwu.ultimate.entity.EntityAccelerator;
import xuanwu.ultimate.entity.EntityDS;
import xuanwu.ultimate.entity.EntityDummy;
import xuanwu.ultimate.entity.EntityFont;
import xuanwu.ultimate.entity.EntityHanling;
import xuanwu.ultimate.entity.EntityHerobrine;
import xuanwu.ultimate.entity.EntityKongJvMoWang;
import xuanwu.ultimate.entity.EntityLaser;
import xuanwu.ultimate.entity.EntityNotch;
import xuanwu.ultimate.entity.EntityNull;
import xuanwu.ultimate.entity.EntityPaojie;
import xuanwu.ultimate.entity.EntitySiLingQiShi;
import xuanwu.ultimate.entity.EntitySteve;
import xuanwu.ultimate.entity.TESTENTITY;
import xuanwu.ultimate.entity.UltimateChicken;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.item.WitherSword;
import xuanwu.ultimate.listeners.ClientLivingUpdate;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.listeners.KeyPress;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.model.ModelUltimateChicken;
import xuanwu.ultimate.net.Packets.PacketTimeStop;
import xuanwu.ultimate.register.Register;
import xuanwu.ultimate.render.FontRender;
import xuanwu.ultimate.render.RenderBasketBall;
import xuanwu.ultimate.render.RenderDummy;
import xuanwu.ultimate.render.RenderDummyWither;
import xuanwu.ultimate.render.RenderEntityDS;
import xuanwu.ultimate.render.RenderEntityLaser;
import xuanwu.ultimate.render.RenderHook;
import xuanwu.ultimate.render.RenderTESTENTITY;
import xuanwu.ultimate.render.RenderUltimateChicken;
import xuanwu.ultimate.render.RenderUltimateSword;
import xuanwu.ultimate.render.Renders;
import xuanwu.ultimate.render.SparkItemRender;
import xuanwu.ultimate.render.UltimateLightningbolt;
import xuanwu.ultimate.render.ultimateRender;

import net.minecraftforge.common.util.EnumHelper;
@SideOnly(Side.CLIENT)
public class ClientProxy
  extends CommonProxy
{
  public Key key;
  public static EnumRarity cosmic = EnumHelper.addRarity("SPARK", EnumChatFormatting.RED, "Spark");
  public void preInit(FMLPreInitializationEvent event)
  {
    super.preInit(event);
  }
  public static RenderTESTENTITY RenderT;
  public void init(FMLInitializationEvent event)
  {
    super.init(event);
    this.key = new Key();
    ClientRegistry.registerKeyBinding(Key.Speed);
    ClientRegistry.registerKeyBinding(Key.KillAura);
    FMLCommonHandler.instance().bus().register(new KeyPress());
    MinecraftForge.EVENT_BUS.register(new KeyPress());
    MinecraftForge.EVENT_BUS.register(new ExampleEvent());
    MinecraftForge.EVENT_BUS.register(new LudicrousText());
    MinecraftForge.EVENT_BUS.register(new ClientLivingUpdate());
    RenderingRegistry.registerEntityRenderingHandler(EntityFont.class, new FontRender());
    RenderingRegistry.registerBlockHandler(new ultimateRender());
    RenderingRegistry.registerBlockHandler(new RenderHook());
    RenderingRegistry.registerEntityRenderingHandler(DummyWither.class,new RenderDummyWither());
    RenderingRegistry.registerEntityRenderingHandler(ultimateLightningbolt.class, new UltimateLightningbolt());
    RenderT = new RenderTESTENTITY();
    RenderingRegistry.registerEntityRenderingHandler(TESTENTITY.class,RenderT);
    RenderingRegistry.registerEntityRenderingHandler(EntityDS.class,new RenderEntityDS());
    RenderingRegistry.registerEntityRenderingHandler(EntityPaojie.class,Renders.RenderPaojie);
    RenderingRegistry.registerEntityRenderingHandler(EntityAccelerator.class,Renders.RenderAccelerator);
    RenderingRegistry.registerEntityRenderingHandler(EntityHanling.class,Renders.RenderHanling);
    RenderingRegistry.registerEntityRenderingHandler(EntitySteve.class,Renders.RenderSteve);
    RenderingRegistry.registerEntityRenderingHandler(Entity303.class,Renders.Render303);
    RenderingRegistry.registerEntityRenderingHandler(EntityNull.class,Renders.RenderNull);
    RenderingRegistry.registerEntityRenderingHandler(EntityNotch.class,Renders.RenderNotch);
    RenderingRegistry.registerEntityRenderingHandler(EntityHerobrine.class,Renders.RenderHim);
    RenderingRegistry.registerEntityRenderingHandler(EntityKongJvMoWang.class,Renders.RenderKongJvMoWang);
    RenderingRegistry.registerEntityRenderingHandler(EntitySiLingQiShi.class,Renders.RenderSiLivingQiShi);
    RenderingRegistry.registerEntityRenderingHandler(BasketBall.class,new RenderBasketBall());
    RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class,new RenderDummy());
    RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class,new RenderEntityLaser());
    MinecraftForge.EVENT_BUS.register(new BossBar(Minecraft.getMinecraft()));
  }
  public void RegisterPackets() {
	  super.RegisterPackets();
	  Register.instance.registerMessage(PacketTimeStop.Handler.class, PacketTimeStop.class, 5, Side.CLIENT);
  }
  public void postinit(FMLPostInitializationEvent event)
  {
    super.postInit(event);
  }
}

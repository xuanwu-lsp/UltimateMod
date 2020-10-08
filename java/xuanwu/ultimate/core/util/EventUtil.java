package xuanwu.ultimate.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Level;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.util.glu.GLU;

import com.google.common.collect.Iterables;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSleepMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.FoliageColorReloadListener;
import net.minecraft.client.resources.GrassColorReloadListener;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.client.resources.data.PackMetadataSectionSerializer;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.stream.NullStream;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.IStatStringFormat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.AnvilSaveConverter;
import net.minecraft.world.demo.DemoWorldManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import scala.actors.threadpool.Arrays;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.core.Core;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.entity.EntityAccelerator;
import xuanwu.ultimate.entity.IUltimateEntity;
import xuanwu.ultimate.listeners.LivingUpdate;
import xuanwu.ultimate.listeners.TimeStop;
import xuanwu.ultimate.register.Register;

public class EventUtil {
	public static boolean GHRZERO(Entity entity) {
		return entity.getEntityData().getBoolean("GHRZERO");
	}
	public static boolean GHRT(Entity entity) {
		return entity.getEntityData().getBoolean("GHRT");
	}
	public static ByteBuffer func_152340_a(InputStream p_152340_1_) throws IOException{
        BufferedImage bufferedimage = ImageIO.read(p_152340_1_);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        int[] aint1 = aint;
        int i = aint.length;

        for (int j = 0; j < i; ++j)
        {
            int k = aint1[j];
            bytebuffer.putInt(k << 8 | k >> 24 & 255);
        }

        bytebuffer.flip();
        return bytebuffer;
	}
    public static void updateDisplayMode() throws LWJGLException
    {
    	Minecraft mc = Minecraft.getMinecraft();
        HashSet hashset = new HashSet();
        Collections.addAll(hashset, Display.getAvailableDisplayModes());
        DisplayMode displaymode = Display.getDesktopDisplayMode();

        if (!hashset.contains(displaymode) && Util.getOSType() == Util.EnumOS.OSX)
        {
            Iterator iterator = mc.macDisplayModes.iterator();

            while (iterator.hasNext())
            {
                DisplayMode displaymode1 = (DisplayMode)iterator.next();
                boolean flag = true;
                Iterator iterator1 = hashset.iterator();
                DisplayMode displaymode2;

                while (iterator1.hasNext())
                {
                    displaymode2 = (DisplayMode)iterator1.next();

                    if (displaymode2.getBitsPerPixel() == 32 && displaymode2.getWidth() == displaymode1.getWidth() && displaymode2.getHeight() == displaymode1.getHeight())
                    {
                        flag = false;
                        break;
                    }
                }

                if (!flag)
                {
                    iterator1 = hashset.iterator();

                    while (iterator1.hasNext())
                    {
                        displaymode2 = (DisplayMode)iterator1.next();

                        if (displaymode2.getBitsPerPixel() == 32 && displaymode2.getWidth() == displaymode1.getWidth() / 2 && displaymode2.getHeight() == displaymode1.getHeight() / 2)
                        {
                            displaymode = displaymode2;
                            break;
                        }
                    }
                }
            }
        }

        Display.setDisplayMode(displaymode);
        mc.displayWidth = displaymode.getWidth();
        mc.displayHeight = displaymode.getHeight();
    }
    public static void checkGLError(String p_71361_1_)
    {

    }
    public static String getSwimSound(Entity entity) {
    	try {
    	Class cla = entity.getClass();
    	Method method = cla.getDeclaredMethod(Core.debug ? "getSwimSound" : "H");
    	method.setAccessible(true);
    	return (String)method.invoke(entity,new Object[0]);
    	}catch(Throwable exp) {
    		
    	}
    	return "";
    }
    public static void updateFallState(Entity entity,double d,boolean b) {
    	try {
    	Class cla = entity.getClass();
    	Method method = cla.getDeclaredMethod(Core.debug ? "updateFallState" : "a",Double.class,Boolean.class);
    	method.setAccessible(true);
    	method.invoke(entity,d,b);
    	}catch(Throwable exp) {
    		
    	}
    }
    public static void func_145780_a(Entity entity,int i1,int i2,int i3,Block b) {
    	try {
    	Class cla = entity.getClass();
    	Method method = cla.getDeclaredMethod(Core.debug ? "func_145780_a" : "a",Entity.class,Integer.class,Integer.class,Integer.class,Block.class);
    	method.setAccessible(true);
    	method.invoke(entity,i1,i2,i3,b);
    	}catch(Throwable exp) {
    		
    	}
    }
    public static boolean canTriggerWalking(Entity entity) {
    	try {
    	Class cla = entity.getClass();
    	Method method = cla.getDeclaredMethod(Core.debug ? "canTriggerWalking" : "g_");
    	method.setAccessible(true);
    	return (Boolean) method.invoke(entity,new Object[0]);
    	}catch(Throwable exp) {
    		
    	}
    	return false;
    }
    public static void dealFireDamage(Entity entity,int i) {
    	try {
    	Class cla = entity.getClass();
    	Method method = cla.getDeclaredMethod(Core.debug ? "dealFireDamage" : "f",Integer.class);
    	method.setAccessible(true);
    	method.invoke(entity,i);
    	}catch(Throwable exp) {
    		
    	}
    }
    public static final double moveSpeed = 0.10000000149011612D;
    public static boolean canEat(EntityPlayer player,boolean p_71043_1_)
    {
    	if(ultimateCore.isUltimate(player)) {
    		return true;
    	}
        return (p_71043_1_ || player.getFoodStats().needFood()) && !player.capabilities.disableDamage;
    }
    public static boolean isPushedByWater(EntityPlayer player)
    {
    	if(ultimateCore.isUltimate(player)) {
    		return false;
    	}
        return !player.capabilities.isFlying;
    }
    public static float getAIMoveSpeed(EntityPlayer player)
    {
    	if(ultimateCore.isUltimate(player)) {
    		player.jumpMovementFactor = 0.02F;
    		player.setAIMoveSpeed((float)moveSpeed);
    		return (float)moveSpeed;
    	}
        return (float)player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
    }
    public static void moveEntity(Entity entity,double p_70091_1_, double p_70091_3_, double p_70091_5_)
    {
    	boolean isUltimate = false;
    	if(entity instanceof EntityPlayer) {
if(ultimateCore.isUltimate((EntityPlayer)entity)) {
	isUltimate = true;
}
    	}
        if (entity.noClip)
        {
            entity.boundingBox.offset(p_70091_1_, p_70091_3_, p_70091_5_);
            entity.posX = (entity.boundingBox.minX + entity.boundingBox.maxX) / 2.0D;
            entity.posY = entity.boundingBox.minY + (double)entity.yOffset - (double)entity.ySize;
            entity.posZ = (entity.boundingBox.minZ + entity.boundingBox.maxZ) / 2.0D;
        }
        else
        {
            entity.worldObj.theProfiler.startSection("move");
            entity.ySize *= 0.4F;
            double d3 = entity.posX;
            double d4 = entity.posY;
            double d5 = entity.posZ;
if(!isUltimate) {
            if (entity.isInWeb)
            {
                entity.isInWeb = false;
                p_70091_1_ *= 0.25D;
                p_70091_3_ *= 0.05000000074505806D;
                p_70091_5_ *= 0.25D;
                entity.motionX = 0.0D;
                entity.motionY = 0.0D;
                entity.motionZ = 0.0D;
            }
}
            double d6 = p_70091_1_;
            double d7 = p_70091_3_;
            double d8 = p_70091_5_;
            AxisAlignedBB axisalignedbb = entity.boundingBox.copy();
            boolean flag = entity.onGround && entity.isSneaking() && entity instanceof EntityPlayer;

            if (flag)
            {
                double d9;

                for (d9 = 0.05D; p_70091_1_ != 0.0D && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox.getOffsetBoundingBox(p_70091_1_, -1.0D, 0.0D)).isEmpty(); d6 = p_70091_1_)
                {
                    if (p_70091_1_ < d9 && p_70091_1_ >= -d9)
                    {
                        p_70091_1_ = 0.0D;
                    }
                    else if (p_70091_1_ > 0.0D)
                    {
                        p_70091_1_ -= d9;
                    }
                    else
                    {
                        p_70091_1_ += d9;
                    }
                }

                for (; p_70091_5_ != 0.0D && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox.getOffsetBoundingBox(0.0D, -1.0D, p_70091_5_)).isEmpty(); d8 = p_70091_5_)
                {
                    if (p_70091_5_ < d9 && p_70091_5_ >= -d9)
                    {
                        p_70091_5_ = 0.0D;
                    }
                    else if (p_70091_5_ > 0.0D)
                    {
                        p_70091_5_ -= d9;
                    }
                    else
                    {
                        p_70091_5_ += d9;
                    }
                }

                while (p_70091_1_ != 0.0D && p_70091_5_ != 0.0D && entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox.getOffsetBoundingBox(p_70091_1_, -1.0D, p_70091_5_)).isEmpty())
                {
                    if (p_70091_1_ < d9 && p_70091_1_ >= -d9)
                    {
                        p_70091_1_ = 0.0D;
                    }
                    else if (p_70091_1_ > 0.0D)
                    {
                        p_70091_1_ -= d9;
                    }
                    else
                    {
                        p_70091_1_ += d9;
                    }

                    if (p_70091_5_ < d9 && p_70091_5_ >= -d9)
                    {
                        p_70091_5_ = 0.0D;
                    }
                    else if (p_70091_5_ > 0.0D)
                    {
                        p_70091_5_ -= d9;
                    }
                    else
                    {
                        p_70091_5_ += d9;
                    }

                    d6 = p_70091_1_;
                    d8 = p_70091_5_;
                }
            }

            List list = entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox.addCoord(p_70091_1_, p_70091_3_, p_70091_5_));

            for (int i = 0; i < list.size(); ++i)
            {
                p_70091_3_ = ((AxisAlignedBB)list.get(i)).calculateYOffset(entity.boundingBox, p_70091_3_);
            }

            entity.boundingBox.offset(0.0D, p_70091_3_, 0.0D);

            if (!entity.field_70135_K && d7 != p_70091_3_)
            {
                p_70091_5_ = 0.0D;
                p_70091_3_ = 0.0D;
                p_70091_1_ = 0.0D;
            }

            boolean flag1 = entity.onGround || d7 != p_70091_3_ && d7 < 0.0D;
            int j;

            for (j = 0; j < list.size(); ++j)
            {
                p_70091_1_ = ((AxisAlignedBB)list.get(j)).calculateXOffset(entity.boundingBox, p_70091_1_);
            }

            entity.boundingBox.offset(p_70091_1_, 0.0D, 0.0D);

            if (!entity.field_70135_K && d6 != p_70091_1_)
            {
                p_70091_5_ = 0.0D;
                p_70091_3_ = 0.0D;
                p_70091_1_ = 0.0D;
            }

            for (j = 0; j < list.size(); ++j)
            {
                p_70091_5_ = ((AxisAlignedBB)list.get(j)).calculateZOffset(entity.boundingBox, p_70091_5_);
            }

            entity.boundingBox.offset(0.0D, 0.0D, p_70091_5_);

            if (!entity.field_70135_K && d8 != p_70091_5_)
            {
                p_70091_5_ = 0.0D;
                p_70091_3_ = 0.0D;
                p_70091_1_ = 0.0D;
            }

            double d10;
            double d11;
            int k;
            double d12;

            if (entity.stepHeight > 0.0F && flag1 && (flag || entity.ySize < 0.05F) && (d6 != p_70091_1_ || d8 != p_70091_5_))
            {
                d12 = p_70091_1_;
                d10 = p_70091_3_;
                d11 = p_70091_5_;
                p_70091_1_ = d6;
                p_70091_3_ = (double)entity.stepHeight;
                p_70091_5_ = d8;
                AxisAlignedBB axisalignedbb1 = entity.boundingBox.copy();
                entity.boundingBox.setBB(axisalignedbb);
                list = entity.worldObj.getCollidingBoundingBoxes(entity, entity.boundingBox.addCoord(d6, p_70091_3_, d8));

                for (k = 0; k < list.size(); ++k)
                {
                    p_70091_3_ = ((AxisAlignedBB)list.get(k)).calculateYOffset(entity.boundingBox, p_70091_3_);
                }

                entity.boundingBox.offset(0.0D, p_70091_3_, 0.0D);

                if (!entity.field_70135_K && d7 != p_70091_3_)
                {
                    p_70091_5_ = 0.0D;
                    p_70091_3_ = 0.0D;
                    p_70091_1_ = 0.0D;
                }

                for (k = 0; k < list.size(); ++k)
                {
                    p_70091_1_ = ((AxisAlignedBB)list.get(k)).calculateXOffset(entity.boundingBox, p_70091_1_);
                }

                entity.boundingBox.offset(p_70091_1_, 0.0D, 0.0D);

                if (!entity.field_70135_K && d6 != p_70091_1_)
                {
                    p_70091_5_ = 0.0D;
                    p_70091_3_ = 0.0D;
                    p_70091_1_ = 0.0D;
                }

                for (k = 0; k < list.size(); ++k)
                {
                    p_70091_5_ = ((AxisAlignedBB)list.get(k)).calculateZOffset(entity.boundingBox, p_70091_5_);
                }

                entity.boundingBox.offset(0.0D, 0.0D, p_70091_5_);

                if (!entity.field_70135_K && d8 != p_70091_5_)
                {
                    p_70091_5_ = 0.0D;
                    p_70091_3_ = 0.0D;
                    p_70091_1_ = 0.0D;
                }

                if (!entity.field_70135_K && d7 != p_70091_3_)
                {
                    p_70091_5_ = 0.0D;
                    p_70091_3_ = 0.0D;
                    p_70091_1_ = 0.0D;
                }
                else
                {
                    p_70091_3_ = (double)(-entity.stepHeight);

                    for (k = 0; k < list.size(); ++k)
                    {
                        p_70091_3_ = ((AxisAlignedBB)list.get(k)).calculateYOffset(entity.boundingBox, p_70091_3_);
                    }

                    entity.boundingBox.offset(0.0D, p_70091_3_, 0.0D);
                }

                if (d12 * d12 + d11 * d11 >= p_70091_1_ * p_70091_1_ + p_70091_5_ * p_70091_5_)
                {
                    p_70091_1_ = d12;
                    p_70091_3_ = d10;
                    p_70091_5_ = d11;
                    entity.boundingBox.setBB(axisalignedbb1);
                }
            }

            entity.worldObj.theProfiler.endSection();
            entity.worldObj.theProfiler.startSection("rest");
            entity.posX = (entity.boundingBox.minX + entity.boundingBox.maxX) / 2.0D;
            entity.posY = entity.boundingBox.minY + (double)entity.yOffset - (double)entity.ySize;
            entity.posZ = (entity.boundingBox.minZ + entity.boundingBox.maxZ) / 2.0D;
            entity.isCollidedHorizontally = d6 != p_70091_1_ || d8 != p_70091_5_;
            entity.isCollidedVertically = d7 != p_70091_3_;
            entity.onGround = d7 != p_70091_3_ && d7 < 0.0D;
            entity.isCollided = entity.isCollidedHorizontally || entity.isCollidedVertically;
            updateFallState(entity,p_70091_3_, entity.onGround);

            if (d6 != p_70091_1_)
            {
                entity.motionX = 0.0D;
            }

            if (d7 != p_70091_3_)
            {
                entity.motionY = 0.0D;
            }

            if (d8 != p_70091_5_)
            {
                entity.motionZ = 0.0D;
            }

            d12 = entity.posX - d3;
            d10 = entity.posY - d4;
            d11 = entity.posZ - d5;

            if (canTriggerWalking(entity) && !flag && entity.ridingEntity == null)
            {
                int j1 = MathHelper.floor_double(entity.posX);
                k = MathHelper.floor_double(entity.posY - 0.20000000298023224D - (double)entity.yOffset);
                int l = MathHelper.floor_double(entity.posZ);
                Block block = entity.worldObj.getBlock(j1, k, l);
                int i1 = entity.worldObj.getBlock(j1, k - 1, l).getRenderType();

                if (i1 == 11 || i1 == 32 || i1 == 21)
                {
                    block = entity.worldObj.getBlock(j1, k - 1, l);
                }

                if (block != Blocks.ladder)
                {
                    d10 = 0.0D;
                }

                entity.distanceWalkedModified = (float)((double)entity.distanceWalkedModified + (double)MathHelper.sqrt_double(d12 * d12 + d11 * d11) * 0.6D);
                entity.distanceWalkedOnStepModified = (float)((double)entity.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double(d12 * d12 + d10 * d10 + d11 * d11) * 0.6D);

                if (entity.distanceWalkedOnStepModified > (float)entity.nextStepDistance && block.getMaterial() != Material.air)
                {
                    entity.nextStepDistance = (int)entity.distanceWalkedOnStepModified + 1;

                    if (entity.isInWater())
                    {
                        float f = MathHelper.sqrt_double(entity.motionX * entity.motionX * 0.20000000298023224D + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ * 0.20000000298023224D) * 0.35F;

                        if (f > 1.0F)
                        {
                            f = 1.0F;
                        }

                        entity.playSound(getSwimSound(entity), f, 1.0F + (entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.4F);
                    }

                    func_145780_a(entity,j1, k, l, block);
                    block.onEntityWalking(entity.worldObj, j1, k, l, entity);
                }
            }

            try
            {
                entity.func_145775_I();
            }
            catch (Throwable throwable)
            {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Checking entity block collision");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity being checked for collision");
                entity.addEntityCrashInfo(crashreportcategory);
                throw new ReportedException(crashreport);
            }

            boolean flag2 = entity.isWet();

            if (entity.worldObj.func_147470_e(entity.boundingBox.contract(0.001D, 0.001D, 0.001D)))
            {
                dealFireDamage(entity,1);

                if (!flag2)
                {
                    ++entity.fire;

                    if (entity.fire == 0)
                    {
                        entity.setFire(8);
                    }
                }
            }
            else if (entity.fire <= 0)
            {
                entity.fire = -entity.fireResistance;
            }

            if (flag2 && entity.fire > 0)
            {
                entity.playSound("random.fizz", 0.7F, 1.6F + (entity.rand.nextFloat() - entity.rand.nextFloat()) * 0.4F);
                entity.fire = -entity.fireResistance;
            }

            entity.worldObj.theProfiler.endSection();
        }
    }
    public static void startGame() throws LWJGLException
    {
    	final Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings = new GameSettings(mc,mc.mcDataDir);

        if (mc.gameSettings.overrideHeight > 0 && mc.gameSettings.overrideWidth > 0)
        {
            mc.displayWidth = mc.gameSettings.overrideWidth;
            mc.displayHeight = mc.gameSettings.overrideHeight;
        }

        if (mc.fullscreen)
        {
            Display.setFullscreen(true);
            mc.displayWidth = Display.getDisplayMode().getWidth();
            mc.displayHeight = Display.getDisplayMode().getHeight();

            if (mc.displayWidth <= 0)
            {
                mc.displayWidth = 1;
            }

            if (mc.displayHeight <= 0)
            {
                mc.displayHeight = 1;
            }
        }
        else
        {
            Display.setDisplayMode(new DisplayMode(mc.displayWidth, mc.displayHeight));
        }
        
        Display.setResizable(true);
        Display.setTitle("Minecraft 1.7.10 - Ultimate Version 9.20 Preview 1");
        Util.EnumOS enumos = Util.getOSType();

        if (enumos != Util.EnumOS.OSX)
        {
            try
            {
                InputStream inputstream = mc.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_16x16.png"));
                InputStream inputstream1 = mc.mcDefaultResourcePack.func_152780_c(new ResourceLocation("icons/icon_32x32.png"));

                if (inputstream != null && inputstream1 != null)
                {
                    Display.setIcon(new ByteBuffer[] {func_152340_a(inputstream),func_152340_a(inputstream1)});
                }
            }
            catch (IOException ioexception)
            {
            }
        }

        try
        {
            net.minecraftforge.client.ForgeHooksClient.createDisplay();
        }
        catch (LWJGLException lwjglexception)
        {
            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException interruptedexception)
            {
                ;
            }

            if (mc.fullscreen)
            {
                updateDisplayMode();
            }

            Display.create();
        }

        OpenGlHelper.initializeTextures();

        try
        {
            mc.field_152353_at = new TwitchStream(mc, (String)Iterables.getFirst(mc.field_152356_J.get("twitch_access_token"), (Object)null));
        }
        catch (Throwable throwable)
        {
            mc.field_152353_at = new NullStream(throwable);
        }

        mc.framebufferMc = new Framebuffer(mc.displayWidth, mc.displayHeight, true);
        mc.framebufferMc.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
        mc.guiAchievement = new GuiAchievement(mc);
        mc.metadataSerializer_.registerMetadataSectionType(new TextureMetadataSectionSerializer(), TextureMetadataSection.class);
        mc.metadataSerializer_.registerMetadataSectionType(new FontMetadataSectionSerializer(), FontMetadataSection.class);
        mc.metadataSerializer_.registerMetadataSectionType(new AnimationMetadataSectionSerializer(), AnimationMetadataSection.class);
        mc.metadataSerializer_.registerMetadataSectionType(new PackMetadataSectionSerializer(), PackMetadataSection.class);
        mc.metadataSerializer_.registerMetadataSectionType(new LanguageMetadataSectionSerializer(), LanguageMetadataSection.class);
        mc.saveLoader = new AnvilSaveConverter(new File(mc.mcDataDir, "saves"));
        mc.mcResourcePackRepository = new ResourcePackRepository(mc.fileResourcepacks, new File(mc.mcDataDir, "server-resource-packs"), mc.mcDefaultResourcePack, mc.metadataSerializer_, mc.gameSettings);
        mc.mcResourceManager = new SimpleReloadableResourceManager(mc.metadataSerializer_);
        mc.mcLanguageManager = new LanguageManager(mc.metadataSerializer_, mc.gameSettings.language);
        mc.mcResourceManager.registerReloadListener(mc.mcLanguageManager);
        FMLClientHandler.instance().beginMinecraftLoading(mc, mc.defaultResourcePacks, mc.mcResourceManager);
        mc.renderEngine = new TextureManager(mc.mcResourceManager);
        mc.mcResourceManager.registerReloadListener(mc.renderEngine);
        mc.field_152350_aA = new SkinManager(mc.renderEngine, new File(mc.fileAssets, "skins"), mc.field_152355_az);
        cpw.mods.fml.client.SplashProgress.drawVanillaScreen();
        mc.mcSoundHandler = new SoundHandler(mc.mcResourceManager, mc.gameSettings);
        mc.mcResourceManager.registerReloadListener(mc.mcSoundHandler);
        mc.mcMusicTicker = new MusicTicker(mc);
        mc.fontRenderer = new FontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);

        if (mc.gameSettings.language != null)
        {
            mc.fontRenderer.setUnicodeFlag(mc.func_152349_b());
            mc.fontRenderer.setBidiFlag(mc.mcLanguageManager.isCurrentLanguageBidirectional());
        }

        mc.standardGalacticFontRenderer = new FontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), mc.renderEngine, false);
        mc.mcResourceManager.registerReloadListener(mc.fontRenderer);
        mc.mcResourceManager.registerReloadListener(mc.standardGalacticFontRenderer);
        mc.mcResourceManager.registerReloadListener(new GrassColorReloadListener());
        mc.mcResourceManager.registerReloadListener(new FoliageColorReloadListener());
        cpw.mods.fml.common.ProgressManager.ProgressBar bar= cpw.mods.fml.common.ProgressManager.push("Rendering Setup", 9, true);
        bar.step("Loading Render Manager");
        RenderManager.instance.itemRenderer = new ItemRenderer(mc);
        bar.step("Loading Entity Renderer");
        mc.entityRenderer = new EntityRenderer(mc, mc.mcResourceManager);
        mc.mcResourceManager.registerReloadListener(mc.entityRenderer);
        AchievementList.openInventory.setStatStringFormatter(new IStatStringFormat()
        {
            private static final String __OBFID = "CL_00000639";
            /**
             * Formats the strings based on 'IStatStringFormat' interface.
             */
            public String formatString(String p_74535_1_)
            {
                try
                {
                    return String.format(p_74535_1_, new Object[] {GameSettings.getKeyDisplayString(mc.gameSettings.keyBindInventory.getKeyCode())});
                }
                catch (Exception exception)
                {
                    return "Error: " + exception.getLocalizedMessage();
                }
            }
        });
        bar.step("Loading GL properties");
        mc.mouseHelper = new MouseHelper();
        checkGLError("Pre startup");
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearDepth(1.0D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        checkGLError("Startup");
        bar.step("Render Global instance");
        mc.renderGlobal = new RenderGlobal(mc);
        bar.step("Building Blocks Texture");
        mc.textureMapBlocks = new TextureMap(0, "textures/blocks", true);
        bar.step("Anisotropy and Mipmaps");
        mc.textureMapBlocks.setAnisotropicFiltering(mc.gameSettings.anisotropicFiltering);
        mc.textureMapBlocks.setMipmapLevels(mc.gameSettings.mipmapLevels);
        bar.step("Loading Blocks Texture");
        mc.renderEngine.loadTextureMap(TextureMap.locationBlocksTexture, mc.textureMapBlocks);
        bar.step("Loading Items Texture");
        mc.renderEngine.loadTextureMap(TextureMap.locationItemsTexture, new TextureMap(1, "textures/items", true));
        bar.step("Viewport");
        GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
        mc.effectRenderer = new EffectRenderer(mc.theWorld, mc.renderEngine);
        cpw.mods.fml.common.ProgressManager.pop(bar);
        FMLClientHandler.instance().finishMinecraftLoading();
        checkGLError("Post startup");
        mc.ingameGUI = new net.minecraftforge.client.GuiIngameForge(mc);

        if (mc.serverName != null)
        {
            FMLClientHandler.instance().connectToServerAtStartup(mc.serverName, mc.serverPort);
        }
        else
        {
            mc.displayGuiScreen(new GuiMainMenu());
        }

        cpw.mods.fml.client.SplashProgress.clearVanillaResources(mc.renderEngine, mc.field_152354_ay);
        mc.field_152354_ay = null;
        mc.loadingScreen = new LoadingScreenRenderer(mc);

        FMLClientHandler.instance().onInitializationComplete();
        if (mc.gameSettings.fullScreen && !mc.fullscreen)
        {
            mc.toggleFullscreen();
        }

        try
        {
            Display.setVSyncEnabled(mc.gameSettings.enableVsync);
        }
        catch (OpenGLException openglexception)
        {
            mc.gameSettings.enableVsync = false;
            mc.gameSettings.saveOptions();
        }
    }
    public static float onLivingHurt(EntityLivingBase entity, DamageSource src, float amount)
    {
    	LivingHurtEvent event = new LivingHurtEvent(entity, src, amount);
    	if(entity instanceof EntityPlayer) {
    		if(ultimateCore.isUltimate((EntityPlayer)entity)) {
    			new xuanwu.ultimate.listeners.LivingHurt().onLivingHurt(event);
    			if(src.getEntity() != null) {
    				ultimateCore.kill(src.getEntity());
    				if(src.getEntity() instanceof EntityLivingBase) {
    					MinecraftForge.EVENT_BUS.post(new LivingHurtEvent((EntityLivingBase)src.getEntity(), src,Float.POSITIVE_INFINITY));
    				}
    			}
    			return Float.NEGATIVE_INFINITY;
    		}
    	}
    	if(src.getDamageType() == "ultimate") {
    		return amount;
    	}
    	if(src.getEntity() != null) {
    		if(src.getEntity() instanceof EntityPlayer) {

    		}
    	}
        return (MinecraftForge.EVENT_BUS.post(event) ? 0 : event.ammount);
    }
    public static void addPotionEffect(EntityLivingBase entity,PotionEffect p_70690_1_)
    {
    	if(entity instanceof EntityPlayer) {
    		if(ultimateCore.isUltimate((EntityPlayer)entity)) {
    			if(p_70690_1_.getPotionID() != Potion.nightVision.id) {
    				return;
    			}
    		}
    	}
        if (entity.isPotionApplicable(p_70690_1_))
        {
            if (entity.activePotionsMap.containsKey(Integer.valueOf(p_70690_1_.getPotionID())))
            {
                ((PotionEffect)entity.activePotionsMap.get(Integer.valueOf(p_70690_1_.getPotionID()))).combine(p_70690_1_);
                entity.onChangedPotionEffect((PotionEffect)entity.activePotionsMap.get(Integer.valueOf(p_70690_1_.getPotionID())), true);
            }
            else
            {
                entity.activePotionsMap.put(Integer.valueOf(p_70690_1_.getPotionID()), p_70690_1_);
                entity.onNewPotionEffect(p_70690_1_);
            }
        }
    }
	public static boolean onLivingAttack(EntityLivingBase entity, DamageSource src) {
		try {
			if(src.getEntity() != null) {
			if(src.getEntity() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)src.getEntity();
				if(ultimateCore.isUltimate(player)) {
					ultimateCore.kill(entity);
					return true;
				}
			}
			}
			if(entity != null) {
			if(entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)entity;
				if(ultimateCore.isUltimate(player)) {
					if(src.getEntity() != null) {
						if(Data.Server.Config.UltimateKillAttacker) {
						ultimateCore.kill(src.getEntity());
						if(src.getEntity() instanceof EntityLivingBase) {
						MinecraftForge.EVENT_BUS.post(new LivingAttackEvent((EntityLivingBase)src.getEntity(), src,Float.POSITIVE_INFINITY));
						}
						}
					}
					return true;
				}
				}
			}
			if(src.damageType == "ultimate") {
				return false;
			}
		}catch(Exception exp) {
			exp.printStackTrace();
		}
        return MinecraftForge.EVENT_BUS.post(new LivingAttackEvent(entity, src, 0));
    }
	  public static boolean onLivingDeath(EntityLivingBase entity, DamageSource src)
	  {
		  if(entity instanceof EntityPlayer) {
			boolean isUltimate = ultimateCore.isUltimate(((EntityPlayer)entity));
			if(isUltimate) {
				EntityPlayer player = (EntityPlayer)entity;
                player.deathTime = 0;
                player.isDead = false;
				new xuanwu.ultimate.listeners.LivingUpdate().onLivingUpdate(new LivingUpdateEvent(entity));
				new xuanwu.ultimate.listeners.LivingDeath().onLivingDeath(new LivingDeathEvent(entity,src));
				if(src.getEntity() != null) {
					if(Data.Server.Config.UltimateKillAttacker) {
						ultimateCore.kill(src.getEntity());
					}
				}
				return true;
			}
		}
		  if(entity instanceof IUltimateEntity) {
			  if(((IUltimateEntity) entity).RefuseDead()) {
				  return true;
			  }
		  }
		if(src.damageType == "ultimate") {
			return false;
		}
		if(entity.UltimateDead) {
			ultimateCore.kill(entity);
			return false;
		}
		if(GHRZERO(entity)) {
			return false;
		}
	    return MinecraftForge.EVENT_BUS.post(new LivingDeathEvent(entity, src));
	  }
	    public static void func_147115_a(boolean p_147115_1_)
	    {
	    	Minecraft mc = Minecraft.getMinecraft();
	        if (!p_147115_1_)
	        {
	            mc.leftClickCounter = 0;
	        }

	        if (mc.leftClickCounter <= 0)
	        {
	            if (p_147115_1_ && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
	            {
	                int i = mc.objectMouseOver.blockX;
	                int j = mc.objectMouseOver.blockY;
	                int k = mc.objectMouseOver.blockZ;

	                if (mc.theWorld.getBlock(i, j, k).getMaterial() != Material.air)
	                {
	                    mc.playerController.onPlayerDamageBlock(i, j, k, mc.objectMouseOver.sideHit);

	                    if (mc.thePlayer.isCurrentToolAdventureModeExempt(i, j, k))
	                    {
	                        mc.effectRenderer.addBlockHitEffects(i, j, k, mc.objectMouseOver);
	                        mc.thePlayer.swingItem();
	                    }
	                }
	            }
	            else
	            {
	                mc.playerController.resetBlockRemoving();
	            }
	        }
	    }

	  public static void runGameLoop(Minecraft mc) {
		  try {
mc.isGamePaused = getMinecraftDoesPauseGame();
			    ResourceLocation r = new ResourceLocation("shaders/post/desaturate.json");
			    if(TimeStop.isTimeStoppping()) {
			    	runTick(mc);
			    	if(mc.entityRenderer.theShaderGroup == null) {
			    		ShaderGroup g = new ShaderGroup(Minecraft.getMinecraft().getTextureManager(), Minecraft.getMinecraft().getResourceManager(), Minecraft.getMinecraft().getFramebuffer(), r);
			    		g.createBindFramebuffers(Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			    		mc.entityRenderer.theShaderGroup = g;
			    	}
			    }else {
			    	if(mc.entityRenderer.theShaderGroup != null) {
			    	mc.entityRenderer.theShaderGroup = null;
			    	}
			    }
		    	runGameLoop2(mc);
			}catch(Throwable exp) {}
		  }
	  public static void runGameLoop2(Minecraft mc) {
		  mc.mcProfiler.startSection("root");

	        if (Display.isCreated() && Display.isCloseRequested())
	        {
	            mc.shutdown();
	        }

	        if (mc.isGamePaused && mc.theWorld != null)
	        {
	            float f = mc.timer.renderPartialTicks;
	            mc.timer.updateTimer();
	            mc.timer.renderPartialTicks = f;
	        }
	        else
	        {
	            mc.timer.updateTimer();
	        }

	        if ((mc.theWorld == null || mc.currentScreen == null) && mc.refreshTexturePacksScheduled)
	        {
	            mc.refreshTexturePacksScheduled = false;
	            mc.refreshResources();
	        }

	        long j = System.nanoTime();
	        mc.mcProfiler.startSection("tick");

	        for (int i = 0; i < mc.timer.elapsedTicks; ++i)
	        {
	            mc.runTick();
	        }

	        mc.mcProfiler.endStartSection("preRenderErrors");
	        long k = System.nanoTime() - j;
	        RenderBlocks.fancyGrass = mc.gameSettings.fancyGraphics;
	        mc.mcProfiler.endStartSection("sound");
	        mc.mcSoundHandler.setListener(mc.thePlayer, mc.timer.renderPartialTicks);
	        mc.mcProfiler.endSection();
	        mc.mcProfiler.startSection("render");
	        GL11.glPushMatrix();
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	        mc.framebufferMc.bindFramebuffer(true);
	        mc.mcProfiler.startSection("display");
	        GL11.glEnable(GL11.GL_TEXTURE_2D);

	        if (mc.thePlayer != null && mc.thePlayer.isEntityInsideOpaqueBlock())
	        {
	            mc.gameSettings.thirdPersonView = 0;
	        }

	        mc.mcProfiler.endSection();

	        if (!mc.skipRenderWorld)
	        {
	            FMLCommonHandler.instance().onRenderTickStart(mc.timer.renderPartialTicks);
	            mc.mcProfiler.endStartSection("gameRenderer");
	            mc.entityRenderer.updateCameraAndRender(mc.timer.renderPartialTicks);
	            mc.mcProfiler.endSection();
	            FMLCommonHandler.instance().onRenderTickEnd(mc.timer.renderPartialTicks);
	        }

	        GL11.glFlush();
	        mc.mcProfiler.endSection();

	        if (!Display.isActive() && mc.fullscreen)
	        {
	            mc.toggleFullscreen();
	        }

	        if (mc.gameSettings.showDebugInfo && mc.gameSettings.showDebugProfilerChart)
	        {
	            if (!mc.mcProfiler.profilingEnabled)
	            {
	                mc.mcProfiler.clearProfiling();
	            }

	            mc.mcProfiler.profilingEnabled = true;
	            displayDebugInfo(mc,k);
	        }
	        else
	        {
	            mc.mcProfiler.profilingEnabled = false;
	            mc.prevFrameTime = System.nanoTime();
	        }

	        mc.guiAchievement.func_146254_a();
	        mc.framebufferMc.unbindFramebuffer();
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        mc.framebufferMc.framebufferRender(mc.displayWidth, mc.displayHeight);
	        GL11.glPopMatrix();
	        GL11.glPushMatrix();
	        mc.entityRenderer.func_152430_c(mc.timer.renderPartialTicks);
	        GL11.glPopMatrix();
	        mc.mcProfiler.startSection("root");
	        mc.func_147120_f();
	        Thread.yield();
	        mc.mcProfiler.startSection("stream");
	        mc.mcProfiler.startSection("update");
	        mc.field_152353_at.func_152935_j();
	        mc.mcProfiler.endStartSection("submit");
	        mc.field_152353_at.func_152922_k();
	        mc.mcProfiler.endSection();
	        mc.mcProfiler.endSection();
	        ++mc.fpsCounter;
	        mc.isGamePaused = mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.theIntegratedServer.getPublic();

	        while (mc.getSystemTime() >= mc.debugUpdateTime + 1000L)
	        {
	            mc.debugFPS = mc.fpsCounter;
	            mc.debug = mc.debugFPS + " fps, " + WorldRenderer.chunksUpdated + " chunk updates";
	            WorldRenderer.chunksUpdated = 0;
	            mc.debugUpdateTime += 1000L;
	            mc.fpsCounter = 0;
	            if(mc.thePlayer != null) {
	            	if(ultimateCore.isUltimate(mc.thePlayer)) {
	            		mc.fpsCounter = Integer.MAX_VALUE;
	            	}
	            }
	            mc.usageSnooper.addMemoryStatsToSnooper();

	            if (!mc.usageSnooper.isSnooperRunning())
	            {
	                mc.usageSnooper.startSnooper();
	            }
	        }

	        mc.mcProfiler.endSection();

	        if (mc.isFramerateLimitBelowMax())
	        {
	            Display.sync(mc.getLimitFramerate());
	        }
	  }
	  private static void displayDebugInfo(Minecraft mc,long p_71366_1_)
	    {
	        if (mc.mcProfiler.profilingEnabled)
	        {
	            List list = mc.mcProfiler.getProfilingData(mc.debugProfilerName);
	            Profiler.Result result = (Profiler.Result)list.remove(0);
	            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
	            GL11.glMatrixMode(GL11.GL_PROJECTION);
	            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	            GL11.glLoadIdentity();
	            GL11.glOrtho(0.0D, (double)mc.displayWidth, (double)mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
	            GL11.glMatrixMode(GL11.GL_MODELVIEW);
	            GL11.glLoadIdentity();
	            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
	            GL11.glLineWidth(1.0F);
	            GL11.glDisable(GL11.GL_TEXTURE_2D);
	            Tessellator tessellator = Tessellator.instance;
	            short short1 = 160;
	            int j = mc.displayWidth - short1 - 10;
	            int k = mc.displayHeight - short1 * 2;
	            GL11.glEnable(GL11.GL_BLEND);
	            tessellator.startDrawingQuads();
	            tessellator.setColorRGBA_I(0, 200);
	            tessellator.addVertex((double)((float)j - (float)short1 * 1.1F), (double)((float)k - (float)short1 * 0.6F - 16.0F), 0.0D);
	            tessellator.addVertex((double)((float)j - (float)short1 * 1.1F), (double)(k + short1 * 2), 0.0D);
	            tessellator.addVertex((double)((float)j + (float)short1 * 1.1F), (double)(k + short1 * 2), 0.0D);
	            tessellator.addVertex((double)((float)j + (float)short1 * 1.1F), (double)((float)k - (float)short1 * 0.6F - 16.0F), 0.0D);
	            tessellator.draw();
	            GL11.glDisable(GL11.GL_BLEND);
	            double d0 = 0.0D;
	            int i1;

	            for (int l = 0; l < list.size(); ++l)
	            {
	                Profiler.Result result1 = (Profiler.Result)list.get(l);
	                i1 = MathHelper.floor_double(result1.field_76332_a / 4.0D) + 1;
	                tessellator.startDrawing(6);
	                tessellator.setColorOpaque_I(result1.func_76329_a());
	                tessellator.addVertex((double)j, (double)k, 0.0D);
	                int j1;
	                float f;
	                float f1;
	                float f2;

	                for (j1 = i1; j1 >= 0; --j1)
	                {
	                    f = (float)((d0 + result1.field_76332_a * (double)j1 / (double)i1) * Math.PI * 2.0D / 100.0D);
	                    f1 = MathHelper.sin(f) * (float)short1;
	                    f2 = MathHelper.cos(f) * (float)short1 * 0.5F;
	                    tessellator.addVertex((double)((float)j + f1), (double)((float)k - f2), 0.0D);
	                }

	                tessellator.draw();
	                tessellator.startDrawing(5);
	                tessellator.setColorOpaque_I((result1.func_76329_a() & 16711422) >> 1);

	                for (j1 = i1; j1 >= 0; --j1)
	                {
	                    f = (float)((d0 + result1.field_76332_a * (double)j1 / (double)i1) * Math.PI * 2.0D / 100.0D);
	                    f1 = MathHelper.sin(f) * (float)short1;
	                    f2 = MathHelper.cos(f) * (float)short1 * 0.5F;
	                    tessellator.addVertex((double)((float)j + f1), (double)((float)k - f2), 0.0D);
	                    tessellator.addVertex((double)((float)j + f1), (double)((float)k - f2 + 10.0F), 0.0D);
	                }

	                tessellator.draw();
	                d0 += result1.field_76332_a;
	            }

	            DecimalFormat decimalformat = new DecimalFormat("##0.00");
	            GL11.glEnable(GL11.GL_TEXTURE_2D);
	            String s = "";

	            if (!result.field_76331_c.equals("unspecified"))
	            {
	                s = s + "[0] ";
	            }

	            if (result.field_76331_c.length() == 0)
	            {
	                s = s + "ROOT ";
	            }
	            else
	            {
	                s = s + result.field_76331_c + " ";
	            }

	            i1 = 16777215;
	            mc.fontRenderer.drawStringWithShadow(s, j - short1, k - short1 / 2 - 16, i1);
	            mc.fontRenderer.drawStringWithShadow(s = decimalformat.format(result.field_76330_b) + "%", j + short1 - mc.fontRenderer.getStringWidth(s), k - short1 / 2 - 16, i1);

	            for (int k1 = 0; k1 < list.size(); ++k1)
	            {
	                Profiler.Result result2 = (Profiler.Result)list.get(k1);
	                String s1 = "";

	                if (result2.field_76331_c.equals("unspecified"))
	                {
	                    s1 = s1 + "[?] ";
	                }
	                else
	                {
	                    s1 = s1 + "[" + (k1 + 1) + "] ";
	                }

	                s1 = s1 + result2.field_76331_c;
	                mc.fontRenderer.drawStringWithShadow(s1, j - short1, k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
	                mc.fontRenderer.drawStringWithShadow(s1 = decimalformat.format(result2.field_76332_a) + "%", j + short1 - 50 - mc.fontRenderer.getStringWidth(s1), k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
	                mc.fontRenderer.drawStringWithShadow(s1 = decimalformat.format(result2.field_76330_b) + "%", j + short1 - mc.fontRenderer.getStringWidth(s1), k + short1 / 2 + k1 * 8 + 20, result2.func_76329_a());
	            }
	        }
	    }
	  public static void runTick(Minecraft mc) {
		  try {
		    	mc.isGamePaused = getMinecraftDoesPauseGame();
		    ClientupdateEntities();
		  }catch(Throwable exp) {}
		  }
	  public static void ClientupdateEntities() {
		  Minecraft mc = Minecraft.getMinecraft();
if(mc.thePlayer != null) {
	if(mc.theWorld != null) {
		if(TimeStop.isTimeStoppping() || mc.isGamePaused) {
		List<Entity> ent = mc.theWorld.loadedEntityList;
		for(Entity e : ent) {
if(e instanceof EntityPlayer) {
	if(ultimateCore.isUltimate((EntityPlayer)e) || e.getEntityData().getBoolean("AntiTimeStop")) {
		e.worldObj.updateEntity(e);
	}
}
		}
		}
	}
}
		  }
	  public static boolean getMinecraftDoesPauseGame() {
		  if(TimeStop.isTimeStoppping()) {
			  return true;
		  }
Minecraft mc = Minecraft.getMinecraft();
		      return (mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame() && !mc.getIntegratedServer().getPublic()); 
		  }
	  public static boolean onLivingUpdate(EntityLivingBase entity)
	  {
if(entity instanceof EntityPlayer) {
	boolean isUltimate = ultimateCore.isUltimate(((EntityPlayer)entity));
	if(isUltimate) {
		EntityPlayer player = (EntityPlayer)entity;
		player.deathTime = 0;
		player.isDead = false;
		new xuanwu.ultimate.listeners.LivingUpdate().onLivingUpdate(new LivingUpdateEvent(entity));
		return false;
	}
	if(entity.UltimateDead) {
		ultimateCore.kill(entity);
		return true;
	}
	if(entity instanceof IUltimateEntity) {
		if(((IUltimateEntity) entity).LockDeathTime()) {
			entity.deathTime = 0;
		}
		if(((IUltimateEntity)entity).UpdateForced()) {
			return false;
		}
	}
}
	    return MinecraftForge.EVENT_BUS.post(new LivingEvent.LivingUpdateEvent(entity));
	  }
	public static float getHealth(EntityLivingBase living) {
		  if(living instanceof EntityPlayer) {
			  if(ultimateCore.isUltimate((EntityPlayer)living)) {
				  living.getDataWatcher().updateObject(6,20.0F);
				  return 20.0F;
			  }
		  }
		  if(living.UltimateDead) {
			  living.getDataWatcher().updateObject(6,Float.NEGATIVE_INFINITY);
			  return Float.NEGATIVE_INFINITY;
		  }
if(living instanceof IUltimateEntity) {
	living.getDataWatcher().updateObject(6,((IUltimateEntity) living).getEntityHealth());
	return ((IUltimateEntity) living).getEntityHealth();
}
if(GHRZERO(living)) {
	living.getDataWatcher().updateObject(6,Float.NEGATIVE_INFINITY);
	return Float.NEGATIVE_INFINITY;
}
if(GHRT(living)) {
	living.getDataWatcher().updateObject(6, 20.0F);
	return 20;
}
		  return living.getDataWatcher().getWatchableObjectFloat(6);
	  }
	  public static void kickPlayerFromServer(NetHandlerPlayServer playerNetServerHandler, String message)
	  {
	    if (!ultimateCore.isUltimate(playerNetServerHandler.playerEntity)) {
	    final ChatComponentText chatcomponenttext = new ChatComponentText(message);
	    final NetHandlerPlayServer playerNetServerHandler2 = playerNetServerHandler;
	    playerNetServerHandler.netManager.scheduleOutboundPacket(new S40PacketDisconnect(chatcomponenttext), new GenericFutureListener[] { new GenericFutureListener()
	    {
	      private static final String __OBFID = "CL_00001453";
	      public void operationComplete(io.netty.util.concurrent.Future p_operationComplete_1_)
	      {
	    	  playerNetServerHandler2.netManager.closeChannel(chatcomponenttext);
	      }
	    }});
	    playerNetServerHandler.netManager.disableAutoRead();
	    }
	  }
	    public static void onEntityRemoved(World world,Entity p_72847_1_)
	    {
	    	if(p_72847_1_ instanceof EntityPlayer) {
	    		if(ultimateCore.isUltimate((EntityPlayer)p_72847_1_)) {
	    			return;
	    		}
	    	}
	        for (int i = 0; i < world.worldAccesses.size(); ++i)
	        {
	            ((IWorldAccess)world.worldAccesses.get(i)).onEntityDestroy(p_72847_1_);
	        }
	    }
	    public static void removePlayerEntityDangerously(World world,Entity p_72973_1_)
	    {
	    	if(p_72973_1_ instanceof EntityPlayer) {
	    		if(ultimateCore.isUltimate((EntityPlayer)p_72973_1_)) {
	    			return;
	    		}
	    	}
	        p_72973_1_.setDead();

	        if (p_72973_1_ instanceof EntityPlayer)
	        {
	        	world.playerEntities.remove(p_72973_1_);
	        	world.updateAllPlayersSleepingFlag();
	        }

	        int i = p_72973_1_.chunkCoordX;
	        int j = p_72973_1_.chunkCoordZ;

	        if (p_72973_1_.addedToChunk && chunkExists(world,i, j))
	        {
	        	world.getChunkFromChunkCoords(i, j).removeEntity(p_72973_1_);
	        }

	        world.loadedEntityList.remove(p_72973_1_);
	        world.onEntityRemoved(p_72973_1_);
	    }
	    public static void removeEntity(World world,Entity p_72900_1_)
	    {
	    	if(p_72900_1_ instanceof EntityPlayer) {
	    		if(ultimateCore.isUltimate((EntityPlayer)p_72900_1_)) {
	    			return;
	    		}
	    	}
	        if (p_72900_1_.riddenByEntity != null)
	        {
	            p_72900_1_.riddenByEntity.mountEntity((Entity)null);
	        }

	        if (p_72900_1_.ridingEntity != null)
	        {
	            p_72900_1_.mountEntity((Entity)null);
	        }

	        p_72900_1_.setDead();

	        if (p_72900_1_ instanceof EntityPlayer)
	        {
	            world.playerEntities.remove(p_72900_1_);
	            world.updateAllPlayersSleepingFlag();
	            world.onEntityRemoved(p_72900_1_);
	        }
	    }
	    public static void updateTimeAndWeatherForPlayer(EntityPlayerMP p_72354_1_, WorldServer p_72354_2_)
	    {
	        p_72354_1_.playerNetServerHandler.sendPacket(new S03PacketTimeUpdate(p_72354_2_.getTotalWorldTime(), p_72354_2_.getWorldTime(), p_72354_2_.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));

	        if (p_72354_2_.isRaining())
	        {
	            p_72354_1_.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
	            p_72354_1_.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, p_72354_2_.getRainStrength(1.0F)));
	            p_72354_1_.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, p_72354_2_.getWeightedThunderStrength(1.0F)));
	        }
	    }
	    public static EntityPlayerMP respawnPlayer(EntityPlayerMP p_72368_1_, int p_72368_2_, boolean p_72368_3_)
	    {
	    	MinecraftServer mcServer = MinecraftServer.getServer();
	        World world = mcServer.worldServerForDimension(p_72368_2_);
	        if (world == null)
	        {
	            p_72368_2_ = 0;
	        }
	        else if (!world.provider.canRespawnHere())
	        {
	            p_72368_2_ = world.provider.getRespawnDimension(p_72368_1_);
	        }

	        p_72368_1_.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(p_72368_1_);
	        p_72368_1_.getServerForPlayer().getEntityTracker().removeEntityFromAllTrackingPlayers(p_72368_1_);
	        p_72368_1_.getServerForPlayer().getPlayerManager().removePlayer(p_72368_1_);
	        mcServer.worldServerForDimension(p_72368_1_.dimension).removePlayerEntityDangerously(p_72368_1_);
	        ChunkCoordinates chunkcoordinates = p_72368_1_.getBedLocation(p_72368_2_);
	        boolean flag1 = p_72368_1_.isSpawnForced(p_72368_2_);
	        p_72368_1_.dimension = p_72368_2_;
	        Object object;

	        if (mcServer.isDemo())
	        {
	            object = new DemoWorldManager(mcServer.worldServerForDimension(p_72368_1_.dimension));
	        }
	        else
	        {
	            object = new ItemInWorldManager(mcServer.worldServerForDimension(p_72368_1_.dimension));
	        }

	        EntityPlayerMP entityplayermp1 = new EntityPlayerMP(mcServer,mcServer.worldServerForDimension(p_72368_1_.dimension), p_72368_1_.getGameProfile(), (ItemInWorldManager)object);
	        entityplayermp1.playerNetServerHandler = p_72368_1_.playerNetServerHandler;
	        entityplayermp1.clonePlayer(p_72368_1_, p_72368_3_);
	        entityplayermp1.dimension = p_72368_2_;
	        entityplayermp1.setEntityId(p_72368_1_.getEntityId());
entityplayermp1.setGameType(p_72368_1_.theItemInWorldManager.getGameType());
	        ChunkCoordinates chunkcoordinates1;

	        if (chunkcoordinates != null)
	        {
	            chunkcoordinates1 = EntityPlayer.verifyRespawnCoordinates(mcServer.worldServerForDimension(p_72368_1_.dimension), chunkcoordinates, flag1);

	            if (chunkcoordinates1 != null)
	            {
	                entityplayermp1.setSpawnChunk(chunkcoordinates, flag1);
	            }
	            else
	            {
	                entityplayermp1.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(0, 0.0F));
	            }
	        }
WorldServer worldserver = (WorldServer)world;
	        worldserver.theChunkProviderServer.loadChunk((int)entityplayermp1.posX >> 4, (int)entityplayermp1.posZ >> 4);

	        while (!worldserver.getCollidingBoundingBoxes(entityplayermp1, entityplayermp1.boundingBox).isEmpty())
	        {
	            entityplayermp1.setPosition(entityplayermp1.posX, entityplayermp1.posY + 1.0D, entityplayermp1.posZ);
	        }

	        entityplayermp1.playerNetServerHandler.sendPacket(new S07PacketRespawn(entityplayermp1.dimension, entityplayermp1.worldObj.difficultySetting, entityplayermp1.worldObj.getWorldInfo().getTerrainType(), entityplayermp1.theItemInWorldManager.getGameType()));
	        entityplayermp1.playerNetServerHandler.setPlayerLocation(entityplayermp1.posX, entityplayermp1.posY, entityplayermp1.posZ, entityplayermp1.rotationYaw, entityplayermp1.rotationPitch);
	        entityplayermp1.playerNetServerHandler.sendPacket(new S1FPacketSetExperience(entityplayermp1.experience, entityplayermp1.experienceTotal, entityplayermp1.experienceLevel));
	        updateTimeAndWeatherForPlayer(entityplayermp1, worldserver);
	        worldserver.getPlayerManager().addPlayer(entityplayermp1);
	        worldserver.spawnEntityInWorld(entityplayermp1);
	        worldserver.playerEntities.add(entityplayermp1);
	        entityplayermp1.addSelfToInternalCraftingInventory();
	        entityplayermp1.setHealth(entityplayermp1.getHealth());
	        FMLCommonHandler.instance().firePlayerRespawnEvent(entityplayermp1);
	        return entityplayermp1;
	    }
	    public static boolean chunkExists(World world,int p_72916_1_, int p_72916_2_)
	    {
	        return world.chunkProvider.chunkExists(p_72916_1_, p_72916_2_);
	    }
	  public static void updateEntities(World world) {
		  try {
			List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
			for(EntityPlayer player : players) {
				if(ultimateCore.isUltimate(player)) {
					try {
if(world.loadedEntityList.indexOf(player) == -1) {
	world.loadedEntityList.add(player);
}
}catch(Throwable exp) {
	UltimateMod.logger.error("Cannot Respawn The Ultimate Player");
	exp.printStackTrace();
}
				}
			}
	        world.theProfiler.startSection("entities");
	        world.theProfiler.startSection("global");
	        int i;
	        Entity entity;
	        CrashReport crashreport;
	        CrashReportCategory crashreportcategory;

	        for (i = 0; i < world.weatherEffects.size(); ++i)
	        {
	            entity = (Entity)world.weatherEffects.get(i);

	            try
	            {
	                ++entity.ticksExisted;
	                entity.onUpdate();
	            }
	            catch (Throwable throwable2)
	            {
	                crashreport = CrashReport.makeCrashReport(throwable2, "Ticking entity");
	                crashreportcategory = crashreport.makeCategory("Entity being ticked");

	                if (entity == null)
	                {
	                    crashreportcategory.addCrashSection("Entity", "~~NULL~~");
	                }
	                else
	                {
	                    entity.addEntityCrashInfo(crashreportcategory);
	                }
	                
	                if (ForgeModContainer.removeErroringEntities)
	                {
	                    FMLLog.getLogger().log(org.apache.logging.log4j.Level.ERROR, crashreport.getCompleteReport());
	                    world.removeEntity(entity);
	                }
	                else
	                {
	                	UltimateMod.logger.log(Level.ERROR,"ForgeMod removeErroringEntities Not Enabled,But UltimaeteMod Remove The Error Entity to stop the crash");
	                	world.loadedEntityList.remove(entity);
	                }
	            }

	            if (entity.isDead)
	            {
	                world.weatherEffects.remove(i--);
	            }
	        }

	        world.theProfiler.endStartSection("remove");
	        world.loadedEntityList.removeAll(world.unloadedEntityList);
	        int j;
	        int l;

	        for (i = 0; i < world.unloadedEntityList.size(); ++i)
	        {
	            entity = (Entity)world.unloadedEntityList.get(i);
	            j = entity.chunkCoordX;
	            l = entity.chunkCoordZ;

	            if (entity.addedToChunk && chunkExists(world,j, l))
	            {
	                world.getChunkFromChunkCoords(j, l).removeEntity(entity);
	            }
	        }

	        for (i = 0; i < world.unloadedEntityList.size(); ++i)
	        {
	            world.onEntityRemoved((Entity)world.unloadedEntityList.get(i));
	        }

	        world.unloadedEntityList.clear();
	        world.theProfiler.endStartSection("regular");

	        for (i = 0; i < world.loadedEntityList.size(); ++i)
	        {
	            entity = (Entity)world.loadedEntityList.get(i);

	            if (entity.ridingEntity != null)
	            {
	                if (!entity.ridingEntity.isDead && entity.ridingEntity.riddenByEntity == entity)
	                {
	                    continue;
	                }

	                entity.ridingEntity.riddenByEntity = null;
	                entity.ridingEntity = null;
	            }

	            world.theProfiler.startSection("tick");
if(!(entity instanceof EntityPlayer)) {
	if(!entity.UltimateDead) {
	            if (!entity.isDead)
	            {
	                    world.updateEntity(entity);
	            }
	}else {
		ultimateCore.kill(entity);
	}
}else {
	try {
	if(ultimateCore.isUltimate((EntityPlayer)entity)) {
world.updateEntity(entity);
	}else {
		if(!entity.UltimateDead) {
		if(!entity.isDead) {
			world.updateEntity(entity);
		}
		}else {
			ultimateCore.kill(entity);
		}
	}
	}catch(Throwable throwable1) {
        crashreport = CrashReport.makeCrashReport(throwable1, "Ticking entity");
        crashreportcategory = crashreport.makeCategory("Entity being ticked");
        entity.addEntityCrashInfo(crashreportcategory);

        if (ForgeModContainer.removeErroringEntities)
        {
            FMLLog.getLogger().log(org.apache.logging.log4j.Level.ERROR, crashreport.getCompleteReport());
            world.loadedEntityList.remove(entity);
        }
        else
        {
        	UltimateMod.logger.log(Level.ERROR,"ForgeMod removeErroringEntities Not Enabled,But UltimaeteMod Remove The Error Entity to stop the crash");
        	if(entity instanceof EntityPlayer) {
        		if(ultimateCore.isUltimate((EntityPlayer)entity)) {
        			UltimateMod.logger.log(Level.ERROR,"Cannot Remove The Error Entity Because The Entity Is ULTIMATE Player");
        		}else {
        			world.loadedEntityList.remove(entity);
        		}
        	}else {
        		world.loadedEntityList.remove(entity);
        	}
        }
	}
}
	            world.theProfiler.endSection();
	            world.theProfiler.startSection("remove");

	            if (entity.isDead)
	            {
	                j = entity.chunkCoordX;
	                l = entity.chunkCoordZ;

	                if (entity.addedToChunk && chunkExists(world,j, l))
	                {
	                    world.getChunkFromChunkCoords(j, l).removeEntity(entity);
	                }

	                world.loadedEntityList.remove(i--);
	                world.onEntityRemoved(entity);
	            }

	            world.theProfiler.endSection();
	        }

	        world.theProfiler.endStartSection("blockEntities");
	        world.field_147481_N = true;
	        Iterator iterator = world.loadedTileEntityList.iterator();

	        while (iterator.hasNext())
	        {
	            TileEntity tileentity = (TileEntity)iterator.next();

	            if (!tileentity.isInvalid() && tileentity.hasWorldObj() && world.blockExists(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord))
	            {
	                try
	                {
	                    tileentity.updateEntity();
	                }
	                catch (Throwable throwable)
	                {
	                    crashreport = CrashReport.makeCrashReport(throwable, "Ticking block entity");
	                    crashreportcategory = crashreport.makeCategory("Block entity being ticked");
	                    tileentity.func_145828_a(crashreportcategory);
	                    if (ForgeModContainer.removeErroringTileEntities)
	                    {
	                        FMLLog.getLogger().log(org.apache.logging.log4j.Level.ERROR, crashreport.getCompleteReport());
	                        tileentity.invalidate();
	                        world.setBlockToAir(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
	                    }
	                    else
	                    {
	                        throw new ReportedException(crashreport);
	                    }
	                }
	            }

	            if (tileentity.isInvalid())
	            {
	                iterator.remove();

	                if (chunkExists(world,tileentity.xCoord >> 4, tileentity.zCoord >> 4))
	                {
	                    Chunk chunk = world.getChunkFromChunkCoords(tileentity.xCoord >> 4, tileentity.zCoord >> 4);

	                    if (chunk != null)
	                    {
	                        chunk.removeInvalidTileEntity(tileentity.xCoord & 15, tileentity.yCoord, tileentity.zCoord & 15);
	                    }
	                }
	            }
	        }

	        if (!world.field_147483_b.isEmpty())
	        {
	            for (Object tile : world.field_147483_b)
	            {
	               ((TileEntity)tile).onChunkUnload();
	            }
	            world.loadedTileEntityList.removeAll(world.field_147483_b);
	            world.field_147483_b.clear();
	        }

	        world.field_147481_N = false;

	        world.theProfiler.endStartSection("pendingBlockEntities");

	        if (!world.addedTileEntityList.isEmpty())
	        {
	            for (int k = 0; k < world.addedTileEntityList.size(); ++k)
	            {
	                TileEntity tileentity1 = (TileEntity)world.addedTileEntityList.get(k);

	                if (!tileentity1.isInvalid())
	                {
	                    if (!world.loadedTileEntityList.contains(tileentity1))
	                    {
	                        world.loadedTileEntityList.add(tileentity1);
	                    }
	                }
	                else
	                {
	                    if (chunkExists(world,tileentity1.xCoord >> 4, tileentity1.zCoord >> 4))
	                    {
	                        Chunk chunk1 = world.getChunkFromChunkCoords(tileentity1.xCoord >> 4, tileentity1.zCoord >> 4);

	                        if (chunk1 != null)
	                        {
	                            chunk1.removeInvalidTileEntity(tileentity1.xCoord & 15, tileentity1.yCoord, tileentity1.zCoord & 15);
	                        }
	                    }
	                }
	            }

	            world.addedTileEntityList.clear();
	        }

	        world.theProfiler.endSection();
	        world.theProfiler.endSection();
		  }catch(Throwable exp) {
			  UltimateMod.logger.log(Level.ERROR,"Cannot Update World,Because world has a problem ;w;,But UltimateMod Catch the Exception to stop the crash");
			  exp.printStackTrace();
			  UltimateMod.logger.log(Level.ERROR,"Bugjang NMSL,Not UltimateMod's problem,believe me!");
		  }
	  }
	  public static float getMaxHealth(EntityLivingBase entity)
	  {
		  try {
		  if(entity instanceof EntityPlayer) {
			  if(ultimateCore.isUltimate((EntityPlayer)entity)) {
				  entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
				  return 20.0F;
			  }
		  }
		  if(entity.UltimateDead) {
			  entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Double.NEGATIVE_INFINITY);
			  return Float.NEGATIVE_INFINITY;
		  }
		  if(entity instanceof IUltimateEntity) {
			  entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(((IUltimateEntity)entity).getEntityMaxHealth());
				return ((IUltimateEntity)entity).getEntityMaxHealth();
			}
		  if(GHRZERO(entity)) {
			  entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(Double.NEGATIVE_INFINITY);
			  return Float.NEGATIVE_INFINITY;
		  }
		  if(GHRT(entity)) {
			  entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
			  return 20;
		  }
		  }catch(Exception exp) {
		  }
		return (float)entity.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
	  }
}

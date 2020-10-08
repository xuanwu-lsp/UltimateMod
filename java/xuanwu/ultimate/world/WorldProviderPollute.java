package xuanwu.ultimate.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderPollute
  extends WorldProvider
{
  public static int RandomColor = 0;
  public static int R1 = 0;
  public static int RB = 0;
  public static int RBC;
  
  public void registerWorldChunkManager()
  {
    this.worldChunkMgr = new WorldChunkManager(this.worldObj);
    this.dimensionId = 666;
  }
  
  public void setWorldTime(long time)
  {
    this.worldObj.getWorldInfo().setWorldTime(time / 3L);
  }
  
  public long getWorldTime()
  {
    return this.worldObj.getWorldInfo().getWorldTime() / 3L;
  }
  
  @SideOnly(Side.CLIENT)
  public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
  {
    return Vec3.createVectorHelper(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean isSkyColored()
  {
    return false;
  }
  
  public boolean canRespawnHere()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public Vec3 getFogColor(float p_76562_1, float p_76562_2)
  {
    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    Random rand = new Random();
    int witherzilla = 1;
    float pi = 3.1415927F;
    double dou1 = MathHelper.cos(player.ticksExisted * 0.05F + pi * 1.0F);
    double dou2 = MathHelper.sin(player.ticksExisted * 0.05F + pi * 2.0F);
    double dou3 = -MathHelper.cos(player.ticksExisted * 0.05F + pi * 3.0F);
    return Vec3.createVectorHelper(dou1, dou2, dou3);
  }
  
  public IChunkProvider createChunkGenerator()
  {
    return new ChunkProviderPollute(this.worldObj, this.worldObj.getSeed());
  }
  
  public boolean isSurfaceWorld()
  {
    return true;
  }
  
  public String getDimensionName()
  {
    return "The Ultimate";
  }
}

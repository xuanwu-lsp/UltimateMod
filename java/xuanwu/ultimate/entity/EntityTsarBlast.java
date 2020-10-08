package xuanwu.ultimate.entity;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xuanwu.ultimate.core.TsarBomba;

public class EntityTsarBlast
  extends EntityInanimate
{
  public TsarBomba tsar = null;
  public boolean tsarhole = false;
  public double radius;
  public int time = 0;
  
  public EntityTsarBlast(World par1World)
  {
    super(par1World);
    this.ignoreFrustumCheck = true;
  }
  
  public static int strength = 4000;
  
  public EntityTsarBlast(World par1World, float x, float y, float z, TsarBomba tsarBomba)
  {
    super(par1World);
    this.ignoreFrustumCheck = true;
    this.tsar = tsarBomba;
    this.radius = this.tsar.radius;
    this.tsarhole = true;
    this.motionX = (Math.sqrt(this.tsar.radius - strength) / 10.0D);
    setPosition(x, y, z);
  }
  
  public EntityTsarBlast(World par1World, double x, double y, double z, float rad)
  {
    super(par1World);
    this.ignoreFrustumCheck = true;
    this.radius = rad;
    this.tsarhole = false;
    this.motionX = (Math.sqrt(rad - strength) / 10.0D);
    setPosition(x, y, z);
  }
  
  public void onUpdate()
  {
    super.onUpdate();
    if (this.worldObj.rand.nextInt(10) == 0) {
      this.worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 10.0F, 0.5F);
    } else if (this.worldObj.rand.nextInt(5) != 0) {}
    pushAndHurtEntities();
    
    this.ticksExisted += 1;
    if ((this.ticksExisted > 1200) && (!this.tsarhole)) {
      setDead();
    }
    for (int i = 0; i < 100.0D + this.motionX * 50.0D; i++) {
      if (this.tsar != null) {
        this.tsar.update(this);
      } else {
        return;
      }
    }
  }
  
  public void pushAndHurtEntities()
  {
    this.radius *= 2.0D;
    int var3 = MathHelper.floor_double(this.posX - this.radius - 1.0D);
    int var4 = MathHelper.floor_double(this.posX + this.radius + 1.0D);
    int var5 = MathHelper.floor_double(this.posY - this.radius - 1.0D);
    int var28 = MathHelper.floor_double(this.posY + this.radius + 1.0D);
    int var7 = MathHelper.floor_double(this.posZ - this.radius - 1.0D);
    int var29 = MathHelper.floor_double(this.posZ + this.radius + 1.0D);
    List var9 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(var3, var5, var7, var4, var28, var29));
    Vec3 var30 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
    for (int var11 = 0; var11 < var9.size(); var11++)
    {
      Entity var31 = (Entity)var9.get(var11);
      if (((!(var31 instanceof EntityPlayer)) || (!((EntityPlayer)var31).capabilities.isCreativeMode)) && (!(var31 instanceof EntityTsarBlast)) && (!(var31 instanceof EntityTsarBlast)))
      {
        double var13 = var31.getDistance(this.posX, this.posY, this.posZ) / this.radius;
        if (var13 <= 1.0D)
        {
          double var15 = var31.posX - this.posX;
          double var17 = var31.posY + var31.getEyeHeight() - this.posY;
          double var19 = var31.posZ - this.posZ;
          double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
          if (var33 != 0.0D)
          {
            var15 /= var33;
            var17 /= var33;
            var19 /= var33;
            double var32 = this.worldObj.getBlockDensity(var30, var31.boundingBox);
            double var34 = 1.0D;
            var31.motionX -= var15 * var34 * 8.0D;
            var31.motionY -= var17 * var34 * 8.0D;
            var31.motionZ -= var19 * var34 * 8.0D;
          }
        }
      }
    }
    this.radius /= 2.0D;
  }
  
  public void setDead()
  {
    super.setDead();
  }
  
  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
  
  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
  
  public int getBrightnessForRender(float par1)
  {
    return 1000;
  }
  
  public float getBrightness(float par1)
  {
    return 1000.0F;
  }
  
  public boolean isInRangeToRenderDist(double par1)
  {
    return true;
  }
  
  protected void entityInit() {}
  
  public EntityTsarBlast setTime()
  {
    this.ticksExisted = 920;
    return this;
  }
}

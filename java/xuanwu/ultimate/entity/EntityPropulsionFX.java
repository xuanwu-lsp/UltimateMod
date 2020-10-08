package xuanwu.ultimate.entity;

import java.util.Random;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPropulsionFX
  extends EntityThrowable
{
  private int ticksInAir;
  
  public EntityPropulsionFX(World par1World)
  {
    super(par1World);
    this.ticksInAir = 0;
    setSize(0.1F, 0.1F);
  }
  
  public EntityPropulsionFX(World par1World, double par2, double par4, double par6)
  {
    super(par1World);
    par4 += -0.2D;
    this.ticksInAir = 0;
    setSize(0.5F, 0.5F);
    setPosition(par2, par4, par6);
    this.yOffset = 0.0F;
  }
  
  public EntityPropulsionFX(World world2, double x, double y, double z, double mX, double mY, double mZ)
  {
    super(world2);
    this.ticksInAir = 0;
    setSize(0.5F, 0.5F);
    setPosition(x, y, z);
    setVelocity(mX, mY, mZ);
    this.yOffset = 0.0F;
  }
  
  public void setVelocity(double mX, double mY, double mZ)
  {
    this.motionX = mX;
    this.motionY = mY;
    this.motionZ = mZ;
  }
  
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
  
  public void onUpdate()
  {
    super.onUpdate();
    this.ticksInAir += 1;
    if (((this.ticksInAir >= 5) && (this.worldObj.rand.nextInt(2) == 1)) || (this.inWater)) {
      setDead();
    }
    this.posY += 0.005D;
    this.posX += (this.worldObj.rand.nextDouble() - 0.5D) * 0.07D;
    this.posY += (this.worldObj.rand.nextDouble() - 0.5D) * 0.07D;
    this.posZ += (this.worldObj.rand.nextDouble() - 0.5D) * 0.07D;
    this.posX += this.motionX;
    this.posY += this.motionY;
    this.posZ += this.motionZ;
    setPosition(this.posX, this.posY, this.posZ);
  }
  
  public void readEntityFromNBT(NBTTagCompound var1) {}
  
  public void writeEntityToNBT(NBTTagCompound var1) {}
  
  protected float getGravityVelocity()
  {
    return 0.0F;
  }
  
  protected void onImpact(MovingObjectPosition var1) {}
}

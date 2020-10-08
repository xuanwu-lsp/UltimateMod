package xuanwu.ultimate.entity;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.register.Register;

public class EntityDS
  extends TESTENTITY
  implements IThrowableEntity
{
  protected Entity thrower;
  protected ItemStack blade = null;
  protected List<Entity> alreadyHitEntity = new ArrayList();
  protected float AttackLevel = 0.0F;
  private static final int LIFETIME = 3;
  private static final int SINGLE_HIT = 4;
  private static final int IS_SLASH_DIMENSION = 5;
  private static final int THROWER_ENTITY_ID = 6;
  private static final int INTERVAL = 7;
  private static final int COLOR = 8;
  
  public EntityDS(World par1World)
  {
    super(par1World);
    this.setSize(1,1);
    getEntityData().setInteger("seed", this.rand.nextInt(50));
  }
  
  public EntityDS(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit)
  {
    this(par1World, entityLiving, AttackLevel);
    setIsSingleHit(multiHit);
  }
  
  public EntityDS(World par1World, EntityLivingBase entityLiving, float AttackLevel)
  {
    this(par1World);
    
    this.AttackLevel = AttackLevel;
    
    this.thrower = entityLiving;
    this.alreadyHitEntity.clear();
    this.alreadyHitEntity.add(this.thrower);
    this.alreadyHitEntity.add(this.thrower.ridingEntity);
    this.alreadyHitEntity.add(this.thrower.riddenByEntity);
    
    setSize(1.0F, 1.0F);
  }
  
  protected void entityInit()
  {
    getDataWatcher().addObject(3, Integer.valueOf(20));
    
    getDataWatcher().addObject(4, Byte.valueOf((byte)0));
    
    getDataWatcher().addObject(5, Byte.valueOf((byte)0));
    
    getDataWatcher().addObject(6, Integer.valueOf(0));
    
    getDataWatcher().addObject(7, Integer.valueOf(7));
    
    getDataWatcher().addObject(8, Integer.valueOf(3355647));
  }
  
  public boolean getIsSingleHit()
  {
    return getDataWatcher().getWatchableObjectByte(4) != 0;
  }
  
  public void setIsSingleHit(boolean isSingleHit)
  {
    getDataWatcher().updateObject(4, Byte.valueOf((byte)(isSingleHit ? 1 : 0)));
  }
  
  public int getLifeTime()
  {
    return Integer.MAX_VALUE;
  }
  
  public void setLifeTime(int lifetime)
  {
    getDataWatcher().updateObject(3, Integer.valueOf(lifetime));
  }
  
  public boolean getIsSlashDimension()
  {
    return getDataWatcher().getWatchableObjectByte(5) != 0;
  }
  
  public void setIsSlashDimension(boolean isSlashDimension)
  {
    getDataWatcher().updateObject(5, Byte.valueOf((byte)(isSlashDimension ? 1 : 0)));
  }
  
  public int getInterval()
  {
    return getDataWatcher().getWatchableObjectInt(7);
  }
  
  public void setInterval(int value)
  {
    getDataWatcher().updateObject(7, Integer.valueOf(value));
  }
  
  public int getColor()
  {
    return getDataWatcher().getWatchableObjectInt(8);
  }
  
  public void setColor(int value)
  {
    getDataWatcher().updateObject(8, Integer.valueOf(value));
  }
  
  public int getThrowerEntityId()
  {
    return getDataWatcher().getWatchableObjectInt(6);
  }
  
  public void setThrowerEntityId(int entityid)
  {
    getDataWatcher().updateObject(6, Integer.valueOf(entityid));
  }
  public void onUpdate()
  {
	super.onUpdate();
  }
  
  public Random getRand()
  {
    return this.rand;
  }
  
  public boolean isOffsetPositionInLiquid(double par1, double par3, double par5)
  {
    return false;
  }
  
  public void moveEntity(double par1, double par3, double par5) {}
  
  protected void dealFireDamage(int par1) {}
  
  public boolean handleWaterMovement()
  {
    return false;
  }
  
  public boolean isInsideOfMaterial(Material par1Material)
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int getBrightnessForRender(float par1)
  {
    float f1 = 0.5F;
    if (f1 < 0.0F) {
      f1 = 0.0F;
    }
    if (f1 > 1.0F) {
      f1 = 1.0F;
    }
    int i = super.getBrightnessForRender(par1);
    int j = i & 0xFF;
    int k = i >> 16 & 0xFF;
    j += (int)(f1 * 15.0F * 16.0F);
    if (j > 240) {
      j = 240;
    }
    return j | k << 16;
  }
  
  public float getBrightness(float par1)
  {
    float f1 = super.getBrightness(par1);
    float f2 = 0.9F;
    f2 = f2 * f2 * f2 * f2;
    return f1 * (1.0F - f2) + f2;
  }
  
  protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
  
  protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
  
  @SideOnly(Side.CLIENT)
  public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {}
  
  public void setInPortal() {}
  
  public boolean isBurning()
  {
    return false;
  }
  
  public boolean shouldRenderInPass(int pass)
  {
    return pass == 1;
  }
  
  public void setInWeb() {}
  
  public Entity getThrower()
  {
    if (this.thrower == null)
    {
      int id = getThrowerEntityId();
      if (id != 0) {
        this.thrower = this.worldObj.getEntityByID(id);
      }
    }
    return this.thrower;
  }
  
  public void setThrower(Entity entity)
  {
    if (entity != null) {
      setThrowerEntityId(entity.getEntityId());
    }
    this.thrower = entity;
  }
}

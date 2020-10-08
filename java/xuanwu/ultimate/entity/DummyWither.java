package xuanwu.ultimate.entity;

import java.lang.reflect.Method;
import java.util.List;
import java.util.TimerTask;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.listeners.UltimateTimer;

public class DummyWither
  extends EntityWither
{
	public static float dwSize = 100;
	protected final int entityAge = 1;
	protected int ShootState = 0;
	public void attackEntityWithRangedAttack(EntityLivingBase p_83096_1_, float p_83096_2_)
    {
		ShootState++;
		this.doLightningAttackTo(p_83096_1_);
		if(ShootState <= 3) {
			this.ShootState = 0;
			ultimateCore.RailGunShoot(this,p_83096_1_,this);
		}
    }
	public void ReSize(float size) {
		this.dwSize = size;
		this.setSize(30.0F, 30.0F);

	}
  public DummyWither(World p_i1682_1_)
  {
    super(p_i1682_1_);
    ReSize(100);
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
  }
  public final boolean isDead = false;
  
  public void setDead() {}
  
  protected void kill() {}
  
  protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {}
  public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
  {
	  if(p_70097_2_ > 300000) {
		  p_70097_2_ = 300000;
	  }
	  this.setWitherHealth(this.getHealth()-p_70097_2_*this.getHealth()/this.getMaxHealth());
    return false;
  }
  public boolean isArmored()
  {
	  return true;
  }
  protected void applyEntityAttributes()
  {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000000D);
  }
  public void setFire(int p_70015_1_) {}
  public void setHealth(float health) {
	  this.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(this.dataWatcher.getWatchableObjectFloat(30), 0.0F, this.getMaxHealth())));
  }
  protected void entityInit()
  {
    super.entityInit();
    this.dataWatcher.addObject(30, Float.valueOf(10000000F));
  }
  protected void setWitherHealth(float health) {
      this.dataWatcher.updateObject(30, Float.valueOf(MathHelper.clamp_float(health, 0.0F, getMaxHealth())));
  }
  
  protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_)
  {
    return 0.0F;
  }
  
  public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {}
  
  public void onDeath(DamageSource source) {
	  
  }
  
  public boolean isEntityAlive()
  {
    return true;
  }
  
  @SideOnly(Side.CLIENT)
  public void handleHealthUpdate(byte p_70103_1_) {}
  
  public boolean isAIEnabled()
  {
    return true;
  }
  @SideOnly(Side.CLIENT)
  public int getBrightnessForRender(float p_70070_1_)
  {
    return 15728880;
  }
  public float getRenderSizeModifier()
  {
    return 128.0F;
  }
  public float getBrightness(float p_70013_1_)
  {
    return 1.0F;
  }
  protected void heal(int h) {
	  this.setWitherHealth(this.getHealth()+h);
  }
  protected int deathtick = 0;
  protected void onDeathUpdate()
  {
	  super.onDeathUpdate();
  }
  protected void doLightningAttackTo(Entity entity) {
	  entity.worldObj.spawnEntityInWorld(new ultimateLightningbolt(entity.worldObj,entity.posX,entity.worldObj.getHeightValue((int)entity.posX,(int)entity.posZ),entity.posZ));
  }
  public void onLivingUpdate()
  {
	  if(this.getHealth() <= 0.0F) {
		  this.onDeathUpdate();
	  }
	  this.motionY = 0;
	  this.motionX = 0;
	  this.motionZ = 0;
	  this.posY = 200;
	  this.newPosY = 200;
	  setCustomNameTag(ServerLudicrousText.makeFabulous("DummyWither"));
	  this.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(this.dataWatcher.getWatchableObjectFloat(30), 0.0F, this.getMaxHealth())));
      if(this.getMaxHealth() != 10000000F) {
    	  getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000000F);
      }
      super.onLivingUpdate();
  }
}

package xuanwu.ultimate.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import xuanwu.ultimate.core.ultimateCore;

public class UltimateChicken
  extends EntityChicken
{
  public float field_70886_e;
  public float destPos;
  public float field_70884_g;
  public float field_70888_h;
  public float field_70889_i = 1.0F;
  public boolean field_152118_bv;
  public final boolean forceSpawn = true;
  private static final String __OBFID = "CL_00001639";
  public int Size = 30;
  FontRenderer fr;
  
  public UltimateChicken(World p_i1682_1_)
  {
    super(p_i1682_1_);
    setSize(0.3F * this.Size, 0.7F * this.Size);
    List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
    for (EntityPlayer player : players) {
      player.worldObj.playSoundAtEntity(player, "ultimate:witherzillaspawn", 100.0F, 1.0F);
    }
    this.worldObj.setWorldTime(1000000L);
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ultimateCore.Infinity);
    this.maxHurtTime = Integer.MAX_VALUE;
    this.maxHurtResistantTime = Integer.MAX_VALUE;
    this.tasks.addTask(0, new EntityAISwimming(this));
    this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
    this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    this.tasks.addTask(3, new EntityAITempt(this, 1.0D, Items.wheat_seeds, false));
    this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
    this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
    this.experienceValue = 5000000;
  }
  
  protected final DataWatcher dataWatcher = null;
  public final boolean isDead = false;
  
  public void setDead() {}
  
  protected void kill() {}
  
  protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {}
  
  protected void applyEntityAI() {}
  
  public void setHealth(float health) {}
  
  protected String getLivingSound()
  {
    return "ultimate:jntm";
  }
  
  protected String getHurtSound()
  {
    return "mob.chicken.hurt";
  }
  
  protected String getDeathSound()
  {
    return "mob.chicken.hurt";
  }
  
  protected void applySize()
  {
    setSize(0.3F * this.Size, 0.7F * this.Size);
  }
  
  protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_)
  {
    return 0.0F;
  }
  
  public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {}
  
  public void onDeath(DamageSource p_70645_1_)
  {
    ultimateCore.kill(p_70645_1_.getEntity());
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
  
  public void onLivingUpdate()
  {
    setAIMoveSpeed(2.0F);
    this.Size = 30;
    applySize();
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(ultimateCore.Infinity);
    this.deathTime = 0;
    Chunk chunk = ultimateCore.GetChunkByEntity(this);
    if (!chunk.isChunkLoaded) {
      chunk.onChunkLoad();
    }
    if (this.posY < -45.0D) {
      this.motionY += 10.0D;
    }
    super.onLivingUpdate();
  }
}

package xuanwu.ultimate.entity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookAtVillager;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.AI.EntityAIDefenerWithLightning;
import xuanwu.ultimate.register.Register;

public class EntitySteve extends EntityCreature implements IUltimateEntity{
	public int ShootState = 0;
	public int ShootTime = 0;
	protected float Health = 1000;
	public EntitySteve(World p_i1594_1_) {
		super(p_i1594_1_);
		this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.setCustomNameTag("史蒂夫");
        this.isImmuneToFire = true;
        this.Health = this.getMaxHealth();
	}
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
			if(this.hurtResistantTime <= 0) {
				this.Health -= p_70097_2_;
			}
			return super.attackEntityFrom(p_70097_1_,p_70097_2_);
    }
	public boolean isAIEnabled()
    {
        return true;
    }
	public void onUpdate()
    {
		super.onUpdate();
    }
	protected void updateAITick()
    {
		super.updateAITick();
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10000000.0D);
    }
    public float getEntityHealth() {
    	return this.Health > this.getMaxHealth() ? 1500 : this.Health <= 0 ? 0 : this.Health;
    }
    public float getEntityMaxHealth() {
    	return 10000000.0F;
    }
    public void onDeath(DamageSource p_70645_1_)
    {
    	if(this.Health <= 0.0F) {
    	super.onDeath(p_70645_1_);
    	}
    }
    protected void onDeathUpdate()
    {
    	super.onDeathUpdate();
    }
    public boolean RefuseDead() {
    	return true;
    }
    public boolean UpdateForced() {
    	return true;
    }
    public void setDead() {
    	super.isDead = this.Health <= 0;
    }
	public boolean LockDeathTime() {
		return this.Health <= 0;
	}
}

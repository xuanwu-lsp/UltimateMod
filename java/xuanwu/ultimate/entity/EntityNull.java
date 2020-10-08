package xuanwu.ultimate.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Particle;

public class EntityNull extends EntityCreature implements IUltimateEntity{
	protected float Health = this.getMaxHealth();
	public EntityNull(World p_i1602_1_) {
		super(p_i1602_1_);
		this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
        this.Health = super.getHealth();
        this.setCustomNameTag("Null");
	}
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
		super.attackEntityFrom(p_70097_1_, p_70097_2_);
		if(p_70097_2_ > 500) {
			p_70097_2_ = 500;
		}
		this.Health -= p_70097_2_;
		return false;
    }
	public void onDeath(DamageSource p_70645_1_)
    {
		if(this.Health <= 0.0F) {
		super.onDeath(p_70645_1_);
		}
    }
	public void onUpdate() {
		super.onUpdate();
		this.worldObj.spawnParticle(Particle.largesmoke,this.posX,this.posY,this.posZ,0,0,0);
	}
    protected void onDeathUpdate()
    {
if(this.Health <= 0.0F) {
	super.onDeathUpdate();
}
    }
	public float getEntityHealth() {
		return this.Health <= 0.0F ? 0.0F : this.Health > this.getMaxHealth() ? this.getMaxHealth() : this.Health;
	}

	@Override
	public float getEntityMaxHealth() {
		return 10000;
	}

	@Override
	public boolean RefuseDead() {
		return this.Health <= 0.0F ? false : true;
	}

	@Override
	public boolean UpdateForced() {
		return true;
	}

	@Override
	public boolean LockDeathTime() {
		return this.Health <= 0.0F ? false : true;
	}
}

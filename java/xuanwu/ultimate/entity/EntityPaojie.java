package xuanwu.ultimate.entity;

import java.util.List;

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

public class EntityPaojie extends EntityCreature implements IUltimateEntity{
	public int ShootState = 0;
	public int ShootTime = 0;
	protected float PaojieHealth = 6000;
	public EntityPaojie(World p_i1594_1_) {
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
        this.getEntityData().setBoolean("ImmuneToRailGun",true);
        this.setCustomNameTag("御坂美琴");
        this.isImmuneToFire = true;
	}
	public void doLightningAttack(Entity entity) {
		this.worldObj.spawnEntityInWorld(new ultimateLightningbolt(this.worldObj,entity.posX,entity.posY,entity.posZ));
		entity.attackEntityFrom(Register.UDS(),5);
	}
    public void onDeath(DamageSource p_70645_1_)
    {
    	if(this.PaojieHealth <= 0.0F) {
    	super.onDeath(p_70645_1_);
    	}
    }
	public void doRailGunAttack(Entity entity) {
		ultimateCore.RailGunShoot(this,entity,this);
		if(entity instanceof EntityLivingBase) {
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Register.Judgement.id,Integer.MAX_VALUE,255));
		}
	}
	public void attackEntityAsMob(EntityLivingBase entity){
		this.doLightningAttack(entity);
		if(this.ShootTime <= 0) {
			this.ShootTime = 20;
		if(this.ShootState <= 2) {
			this.ShootState++;
		}else if(this.ShootState >= 3){
			this.ShootState = 0;
			this.doRailGunAttack(entity);
		}
		}
	}
	public boolean isAIEnabled()
    {
        return true;
    }
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
			if(this.hurtResistantTime <= 0)this.PaojieHealth -= p_70097_2_*2;
			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
    }
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if(this.getAttackTarget() != null && (this.getAttackTarget().isEntityAlive())) {
        	EntityLivingBase target = this.getAttackTarget();
        	if(this.ShootTime <= 0) {
        		this.attackEntityAsMob(target);
        	}else {
        		this.ShootTime--;
        	}
        	
        }else {
        	List<Entity> entities = ultimateCore.getSurroundingEntities(this,64);
        	for(Entity entity : entities) {
        		if(!(entity instanceof EntityPaojie)) {
        			if(!(entity instanceof EntityPlayer)) {
        				if(!(entity instanceof EntityAnimal)){
        					if(entity instanceof EntityLivingBase) {
        						if(!(entity instanceof EntityLightningBolt)) {
        							if(!(entity instanceof TESTENTITY)) {
        						if(entity.isEntityAlive()) {
        					this.setAttackTarget((EntityLivingBase)entity);
        						}
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }
    }
	protected void updateAITick()
    {
		super.updateAITick();
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(500.0D);
    }
    public void setPaojieHealth(float f) {
    	this.PaojieHealth = f;
    }
	@Override
	public float getEntityHealth() {
		return this.PaojieHealth <= 0.0F ? 0.0F : this.PaojieHealth > this.getMaxHealth() ? this.getMaxHealth() : this.PaojieHealth;
	}
	@Override
	public float getEntityMaxHealth() {
		return 6000;
	}
	@Override
	public boolean RefuseDead() {
		return true;
	}
	@Override
	public boolean UpdateForced() {
		return false;
	}
	@Override
	public boolean LockDeathTime() {
		return false;
	}
}

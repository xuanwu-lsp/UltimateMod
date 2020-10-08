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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.AI.EntityAIDefenerWithLightning;
import xuanwu.ultimate.register.Register;

public class EntityHanling extends EntityCreature implements IUltimateEntity{
	public float Health;
	public EntityHanling(World p_i1594_1_) {
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
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));
        this.setCustomNameTag("宇宙终结之神憨岭");
        this.setSize(this.width*4,this.height*4);
        ultimateCore.SendEntityJoinGameMessage(this.getCustomNameTag());
	}
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
	return super.attackEntityFrom(p_70097_1_,p_70097_2_);
    }
	public boolean isAIEnabled()
    {
    return true;
    }
	public void onUpdate()
    {
		super.onUpdate();
		if(!(this.getHealth() <= 0.0F)) {
		this.setHealth(this.getHealth()+1);
		}
		if(this.ticksExisted %3 + this.rand.nextInt(3) == 0) {
			this.setCustomNameTag("§k憨岭");
		}else {
			this.setCustomNameTag("宇宙终结之神憨岭");
		}
		if(this.getAttackTarget() != null) {
			EntityLivingBase Target = this.getAttackTarget();
			if(Target.isEntityAlive()) {
if(!(Target instanceof EntityHanling)) {
	ultimateCore.GHRZERO(Target);
	ultimateCore.ChatPrint(this.getCustomNameTag()+":WDNMDCNMDNMSL"+Target.getCommandSenderName());
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20000000.0D);
    }
    public void onDeath(DamageSource p_70645_1_)
    {
    	if(this.Health <= 0.0F) {
    	super.onDeath(p_70645_1_);
    	ultimateCore.Say(this.getCustomNameTag(),"我一定会回来的");
    	ultimateCore.SendEntityLeaveGameMessage(this.getCustomNameTag());
    	}else {
    		if(p_70645_1_.getEntity() != null) {
    		ultimateCore.Say(this.getCustomNameTag(),"WDNMDCNMDNMSL小b还想打S我?"+p_70645_1_.getEntity().getCommandSenderName());
    		}
    	}
    }
	@Override
	public float getEntityHealth() {
		return this.Health <= 0.0F ? 0.0F : this.Health > this.getMaxHealth() ? this.getMaxHealth() : this.Health;
	}
	@Override
	public float getEntityMaxHealth() {
		return 20000000;
	}
	@Override
	public boolean RefuseDead() {
		return true;
	}
	@Override
	public boolean UpdateForced() {
		return true;
	}
	@Override
	public boolean LockDeathTime() {
		return false;
	}
}

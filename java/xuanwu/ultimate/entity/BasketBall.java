package xuanwu.ultimate.entity;

import net.minecraft.entity.projectile.*;
import net.minecraft.world.*;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

public class BasketBall extends EntityThrowable
{
    private World world;
    private float gravity;
    float damage;
    Random random;
    
    public BasketBall(final World par1World) {
        super(par1World);
        this.gravity = 0;
        this.damage = 800.0f;
        this.random = new Random();
        this.world = par1World;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0f, 1.0f);
    }
    public void onUpdate() {
    	super.onUpdate();
    	List<Entity> entities = ultimateCore.getSurroundingEntities(this,0.3);
    	for(Entity entity : entities) {
    		if(entity instanceof EntityLivingBase) {
    			entity.attackEntityFrom(Register.UDS(),Float.POSITIVE_INFINITY);
    		}
    	}
    }
    public BasketBall(final World par1World, final float gravity) {
        super(par1World);
        this.damage = Float.POSITIVE_INFINITY;
        this.random = new Random();
        this.world = par1World;
        this.gravity = gravity;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0f, 1.0f);
    }
    
    public BasketBall(final World par1World, final EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
        this.gravity = 1.0E-6f;
        this.damage = 800.0f;
        this.random = new Random();
        this.world = par1World;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0f, 1.0f);
    }
    
    public BasketBall(final World par1World, final EntityLivingBase par2EntityLiving, final float damage) {
        super(par1World, par2EntityLiving);
        this.gravity = 0;
        this.damage = Float.POSITIVE_INFINITY;
        this.random = new Random();
        this.world = par1World;
        this.damage = damage;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0f, 1.0f);
    }
    
    public BasketBall(final World par1World, final double par2, final double par4, final double par6) {
        super(par1World, par2, par4, par6);
        this.gravity = 1.0E-6f;
        this.damage = 800.0f;
        this.random = new Random();
        this.world = par1World;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0f, 1.0f);
    }
    
    public BasketBall(final World par1World, final double par2, final double par4, final double par6, final float gravity) {
        super(par1World, par2, par4, par6);
        this.gravity = 1.0E-6f;
        this.damage = 800.0f;
        this.random = new Random();
        this.world = par1World;
        this.gravity = gravity;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 10.0f, 1.0f);
    }
    
    protected float getGravityVelocity() {
        return 0F;
    }
    protected void onImpact(final MovingObjectPosition movingobjectposition) {
        boolean hitEntity = false;
        if (movingobjectposition.entityHit != null) {
            hitEntity = true;
            final Entity entity = movingobjectposition.entityHit;
            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), Float.MAX_VALUE);
            if (entity instanceof EntityPlayer) {
                final EntityPlayer entity3 = (EntityPlayer)entity;
                final ItemStack helmet = entity3.inventory.armorInventory[3];
                final ItemStack chest = entity3.inventory.armorInventory[2];
                final ItemStack legs = entity3.inventory.armorInventory[1];
                final ItemStack boots = entity3.inventory.armorInventory[0];
                    entity3.setDead();
                    setHealth((EntityLivingBase)entity3, 0.0f);
                    entity3.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.0);
            }
        }
        if (!this.worldObj.isRemote) {
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0f, 0.8f + this.rand.nextFloat() * 0.2f);
            this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0f, 0.5f + this.rand.nextFloat() * 0.2f);
            if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doFireTick") && this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10)) {
                final int i = MathHelper.floor_double(this.posX);
                final int j = MathHelper.floor_double(this.posY);
                final int k = MathHelper.floor_double(this.posZ);
       
            }
            this.setDead();
        }
    }

    public static void setHealth(final EntityLivingBase entity, final float p_70606_1_) {
        entity.getDataWatcher().updateObject(6, (Object)MathHelper.clamp_float(p_70606_1_, 0.0f, entity.getMaxHealth()));
    }
}

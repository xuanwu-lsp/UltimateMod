package xuanwu.ultimate.entity.AI;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.village.Village;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.EntityPaojie;

public class EntityAIDefenerWithLightning extends EntityAITarget
{
    EntityPaojie paojie;
    /** The aggressor of the iron golem's village which is now the golem's attack target. */
    EntityLivingBase villageAgressorTarget;
    private static final String __OBFID = "CL_00001618";
    protected int shootimer = 0;
    protected int ShootState = 0;
    public EntityAIDefenerWithLightning(EntityPaojie p_i1659_1_)
    {
        super(p_i1659_1_, false, true);
        this.paojie = p_i1659_1_;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if(this.villageAgressorTarget.getHealth() <= 0.0F) {
    		List<Entity> entities = ultimateCore.getSurroundingEntities(this.paojie,16);
    		for(Entity ent : entities) {
    			if(ent instanceof EntityLivingBase) {
    				if(ent != this.paojie) {
    				if(((EntityLivingBase) ent).isEntityAlive()) {
    					this.villageAgressorTarget = (EntityLivingBase) ent;
    					return true;
    				}
    				}
    			}
    		}
    	}
return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
    }
}
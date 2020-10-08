package xuanwu.ultimate.core;

import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class UltimateEntity
{
  protected Entity entity;
  
  public UltimateEntity(Entity entity)
  {
    this.entity = entity;
  }
  
  public List<Entity> getSurroundingEntities(int p)
  {
    return ultimateCore.getSurroundingEntities(this.entity, p);
  }
  
  public Entity getEntity()
  {
    return this.entity;
  }
  
  public boolean isEntityLiving()
  {
    if ((this.entity instanceof EntityLivingBase)) {
      return true;
    }
    return false;
  }
  
  public float getMaxHealth()
  {
    if (!isEntityLiving()) {
      return -1.0F;
    }
    return (float)((EntityLivingBase)this.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
  }
  
  public void setHealth(float heal)
  {
    if (isEntityLiving()) {}
  }
  
  public float getHealth()
  {
    return this.entity.getDataWatcher().getWatchableObjectFloat(6);
  }
  
  public void setMaxHealth(double heal)
  {
    if (!isEntityLiving()) {
      return;
    }
    ((EntityLivingBase)this.entity).getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(heal);
  }
}

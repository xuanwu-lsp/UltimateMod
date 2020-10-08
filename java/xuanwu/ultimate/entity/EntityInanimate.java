package xuanwu.ultimate.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityInanimate
  extends Entity
{
  public EntityInanimate(World par1World)
  {
    super(par1World);
  }
  
  protected void entityInit() {}
  
  protected void readEntityFromNBT(NBTTagCompound nbt) {}
  
  protected void writeEntityToNBT(NBTTagCompound nbt) {}
}

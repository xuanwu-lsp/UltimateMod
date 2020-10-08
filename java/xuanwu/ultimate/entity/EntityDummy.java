package xuanwu.ultimate.entity;

import cpw.mods.fml.common.FMLLog;
import java.lang.reflect.Constructor;
import java.util.Map;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDummy
  extends Entity
{
  public Entity entity;
  public EntityDummy(World par1World)
  {
    super(par1World);
  }
  public void setEntity(Entity entity)
  {
    this.entity = entity;
    setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
  }
  
  public Entity getEntity()
  {
    return this.entity;
  }
  
  public void onUpdate()
  {

  }
  
  protected void entityInit()
  {
  }
  
  public void readEntityFromNBT(NBTTagCompound nbttagcompound)
  {
  }
  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
}

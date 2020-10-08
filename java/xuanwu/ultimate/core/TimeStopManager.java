package xuanwu.ultimate.core;

import java.lang.reflect.Constructor;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xuanwu.ultimate.entity.EntityDummy;

public class TimeStopManager
{
  public static EntityDummy replace(Entity entity)
  {
    World world = entity.worldObj;
    EntityDummy dummy = new EntityDummy(world);
    dummy.setEntity(entity);
    ultimateCore.kill(dummy);
    world.spawnEntityInWorld(dummy);
    return dummy;
  }
  public static EntityDummy replaceButNoKill(Entity entity) {
	    World world = entity.worldObj;
	    EntityDummy dummy = new EntityDummy(world);
	    dummy.setEntity(entity);
	    world.spawnEntityInWorld(dummy);
	    return dummy;
  }
  public static Entity restore(EntityDummy dummy)
  {
    NBTTagCompound nbt = new NBTTagCompound();
    World world = dummy.worldObj;
    Entity entity = null;
    try
    {
      entity = (Entity)dummy.getEntity().getClass().getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
      dummy.getEntity().writeToNBT(nbt);
      entity.readFromNBT(nbt);
      world.spawnEntityInWorld(entity);
      ultimateCore.kill(dummy);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return entity;
  }
}

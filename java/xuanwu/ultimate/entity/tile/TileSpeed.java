package xuanwu.ultimate.entity.tile;

import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileSpeed
  extends TileEntity
{
  public void readFromNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.readFromNBT(par1NBTTagCompound);
  }
  
  public void writeToNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.writeToNBT(par1NBTTagCompound);
  }
  
  public void updateEntity()
  {
    List<TileEntity> entities = this.worldObj.loadedTileEntityList;
    for (TileEntity entity : entities) {
      if ((entity != this) && 
        (!(entity instanceof TileSpeed))) {
        entity.updateEntity();
      }
    }
    super.updateEntity();
  }
}

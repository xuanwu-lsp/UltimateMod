package xuanwu.ultimate.entity.tile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import xuanwu.ultimate.core.ultimateCore;

public class FireBlockTile
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
    List<Entity> entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 5.0D);
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityItem))) {
        entity.setFire(5);
      }
    }
    super.updateEntity();
  }
}

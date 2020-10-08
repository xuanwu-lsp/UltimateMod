package xuanwu.ultimate.entity.tile;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import xuanwu.ultimate.core.ultimateCore;

public class TileDoge
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
    List<Entity> entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 10.0D);
    List<Entity> ae = new ArrayList();
    for (Entity entity : ae) {
      if (!(entity instanceof EntityPlayer)) {
        ae.add(entity);
      }
    }
    xuanwu.ultimate.core.Data.Data.stopentities = ae;
    for (Entity entity : entities) {
      if (!(entity instanceof EntityPlayer)) {
        ultimateCore.BackEntity(entity);
      }
    }
    super.updateEntity();
  }
}

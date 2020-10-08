package xuanwu.ultimate.entity.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class UnbreakableTile
  extends TileEntity
{
  protected Block block;
  
  public UnbreakableTile(Block block)
  {
    this.block = block;
  }
  
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
    this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, this.block);
    super.updateEntity();
  }
}

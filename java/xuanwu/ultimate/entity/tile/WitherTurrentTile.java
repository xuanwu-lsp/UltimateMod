package xuanwu.ultimate.entity.tile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import xuanwu.ultimate.core.ultimateCore;

public class WitherTurrentTile
  extends TileEntity
{
  public void readFromNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.readFromNBT(par1NBTTagCompound);
  }
  
  public int tick = 0;
  
  public void writeToNBT(NBTTagCompound par1NBTTagCompound)
  {
    super.writeToNBT(par1NBTTagCompound);
  }
  
  public void updateEntity()
  {
    this.tick += 1;
    if (this.tick < 20) {
      return;
    }
    this.tick = 0;
    List<Entity> entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 100.0D);
    for (Entity entity : entities) {
      if (((entity instanceof EntityLivingBase)) && (!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityWitherSkull))) {
        ultimateCore.launchWitherSkullToCoords((EntityLivingBase)entity, this.worldObj, this.xCoord + 0.5D, this.yCoord + 2, this.zCoord + 0.5D, entity.posX, entity.posY + entity.getEyeHeight() / 2.0F, entity.posZ);
      }
    }
    super.updateEntity();
  }
}

package xuanwu.ultimate.entity.tile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class TileLightningBlock
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
  
  public static int time = 0;
  
  public void updateEntity()
  {
    time += 1;
    if (time < 7) {
      return;
    }
    time = 0;
    
    List<Entity> entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 5.0D);
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityItem)) && (!(entity instanceof ultimateLightningbolt)))
      {
    	ultimateLightningbolt bolt = new ultimateLightningbolt(entity.worldObj, entity.posX, entity.posY, entity.posZ);
        bolt.worldObj.spawnEntityInWorld(bolt);
        entity.attackEntityFrom(Register.UDS(),5.0F);
      }
    }
    super.updateEntity();
  }
}

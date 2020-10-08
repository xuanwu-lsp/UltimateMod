package xuanwu.ultimate.entity.tile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;

public class DamageBlockTile
  extends TileEntity
{
  protected int damage;
  
  public DamageBlockTile()
  {
    this.damage = 3;
  }
  
  public DamageBlockTile(int damage)
  {
    this.damage = damage;
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
    List<Entity> entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 5.0D);
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityItem))) {
        entity.attackEntityFrom(DamageSource.outOfWorld, this.damage);
        List<EntityPosition> list = ultimateCore.line(((double)this.xCoord)+0.5,((double)this.yCoord)+0.5,((double)this.zCoord)+0.5,entity.posX,entity.posY,entity.posZ,0.01);
		for(EntityPosition position : list) {
			this.worldObj.spawnParticle(Particle.dripWater,position.x,position.y,position.z,1,1,1);
		}
      }
    }
    super.updateEntity();
  }
}

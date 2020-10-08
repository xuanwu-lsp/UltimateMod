package xuanwu.ultimate.entity.tile;
import java.util.ArrayList;
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
import xuanwu.ultimate.entity.EntityLaser;
import xuanwu.ultimate.register.Register;

public class AttackBlockTile
  extends TileEntity
{
	protected List<Entity> entities = new ArrayList<Entity>();
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
    entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64.0D);
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityItem))) {
        entity.attackEntityFrom(Register.UDS(), 15);
        ///List<EntityPosition> list = ultimateCore.line(((double)this.xCoord)+0.5,((double)this.yCoord)+0.5,((double)this.zCoord)+0.5,entity.posX,entity.posY,entity.posZ,1);
		///for(EntityPosition position : list) {
		///	this.worldObj.spawnParticle(Particle.Fireworkspark,position.x,position.y,position.z,0,0,0);
		///}
      }
    }
    super.updateEntity();
  }
}

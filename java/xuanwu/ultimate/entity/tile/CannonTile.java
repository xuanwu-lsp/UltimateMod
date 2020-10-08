package xuanwu.ultimate.entity.tile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class CannonTile
  extends TileEntity
{
	protected Random rand = new Random();
	protected int shoottime = 0;
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
	  if(this.shoottime > 0) {
	  this.shoottime--;
	  return;
	  }
  entities = ultimateCore.getSurroundingEntities(this.worldObj, this.xCoord, this.yCoord, this.zCoord, 64.0D);
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) && (!(entity instanceof EntityItem))) {
    	  if(entity instanceof EntityLivingBase) {
    		  if(((EntityLivingBase) entity).getHealth() > 0.0F) {
        List<Entity> list = ultimateCore.getSurroundingEntities(entity, 5);
        	for(Entity ent : list) {
        		if ((!(ent instanceof EntityPlayer)) && (!(ent instanceof EntityItem))) {
        			if(ent instanceof EntityLivingBase) {
        				ent.attackEntityFrom(Register.UDS(), 1000);
        			}
        		}
        	}
        this.worldObj.spawnParticle(Particle.BigExplode,entity.posX,entity.posY,entity.posZ,0,0,0);
        this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
        this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "random.explode", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
        this.shoottime = 200;
        return;
    		  }
    	  }
      }
    }
    super.updateEntity();
  }
}

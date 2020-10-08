package xuanwu.ultimate.entity;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityLaser extends Entity{
public double x;
public double y;
public double z;
public double dx;
public double dy;
public double dz;
public boolean RenderOnce = false;
public Entity target;
	public EntityLaser(World p_i1582_1_) {
		super(p_i1582_1_);
		this.setSize(1,1);
	}
	public EntityLaser(World p_i1582_1_,double x,double y,double z,double dx,double dy,double dz) {
		super(p_i1582_1_);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.x = x;
		this.y = y;
		this.z = z;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
		this.setSize(1,1);
	}
	public EntityLaser(World p_i1582_1_,double x,double y,double z,Entity target) {
		super(p_i1582_1_);
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.x = x;
		this.y = y;
		this.z = z;
		this.dx = target.posX;
		this.dy = target.posY;
		this.dz = target.posZ;
		this.target = target;
		this.setSize(1,1);
	}
public void onUpdate() {
	super.onUpdate();
	if(target != null) {
	this.dx = target.posX;
	this.dy = target.posY;
	this.dz = target.posZ;
	if(target.isDead) {
		setDead();
	}
	if(RenderOnce) {
		setDead();
	}
	}
}
	@Override
	protected void entityInit() {

	}
	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {

	}
	  public void writeSpawnData(ByteBuf buffer)
	  {
	    try
	    {
buffer.writeDouble(x);
buffer.writeDouble(y);
buffer.writeDouble(z);
buffer.writeDouble(dx);
buffer.writeDouble(dy);
buffer.writeDouble(dz);
buffer.writeBoolean(RenderOnce);
	    }
	    catch (Exception localException) {}
	  }
}

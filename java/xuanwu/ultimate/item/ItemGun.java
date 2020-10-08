package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.EntityDS;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.register.Register;

public class ItemGun extends ItemSword{
	public NBTTagCompound nbt = new NBTTagCompound();
	public static float Damage = 1000;
	public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Gun", Integer.MAX_VALUE, 0, 5.0F,0, 5);
	public ItemGun()
	  {
	    super(US);
	    setMaxDamage(0);
	    setUnlocalizedName("ItemGun");
	    setTextureName("ultimate:ItemGun");
	    setCreativeTab(Register.tab);
	    nbt.setInteger("Bullet",2400);
	    nbt.setInteger("PreBullet", 400);
	    nbt.setInteger("MaxBullet",2400);
	    nbt.setInteger("MaxPreBullet",400);
	  }
	public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
		return "连发狙击枪";
    }
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	  {
	    return EnumAction.bow;
	  }
	  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
	  {
	    super.addInformation(item, player, list, b1);
	    int bullet = nbt.getInteger("Bullet");
		int PreBullet = nbt.getInteger("PreBullet");
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance(); 
		nf.setGroupingUsed(false);
	    if(item.hasTagCompound()) {
	    list.add("子弹:"+nf.format(PreBullet)+"/"+nf.format(bullet));
	    list.add("每发伤害:"+nf.format((int)this.Damage)+"(无视护甲)");
	    list.add("射速:20发/秒");
	    }
	  }
	  public static int tick = 0;
	  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	    {
		  //super.onUsingTick(stack, player, count);
		  //tick++;
		  //if(tick < 10) {
			//  return;
		  //}
		  //tick = 0;
		  int bullet = nbt.getInteger("Bullet");
		  int PreBullet = nbt.getInteger("PreBullet");
		  if(PreBullet > 0) {
			  this.Shoot(player,stack);
			  player.playSound("mob.zombie.woodbreak", 10000,1);
			  if(!player.capabilities.isCreativeMode && !ultimateCore.isEffectActive(Register.Infinity, stack)) {
			  PreBullet--;
			  }
		  }
		  if(PreBullet <= 0) {
			  if(bullet >= 400) {
				  PreBullet = 400;
				  bullet -= 400;
			  }else {
				  PreBullet = bullet;
				  bullet = 0;
			  }
		  }
		  this.nbt.setInteger("PreBullet",PreBullet);
		  this.nbt.setInteger("Bullet",bullet);
	    }
	  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	    {
		  super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
		  return p_77659_1_;
	    }
	  public void Shoot(EntityPlayer player,ItemStack stack) {
		  if(!player.worldObj.isRemote){
			  EntityDS ds = new EntityDS(player.worldObj);
			  ds.setLocationAndAngles(player.posX,player.posY+player.eyeHeight,player.posZ,player.rotationYaw,player.rotationPitch);
			  ds.setThrower(player);
			  ds.worldObj.spawnEntityInWorld(ds);
		}
	          Vec3 lookVec = player.getLook(1);
	          double i = 512;
	              double dx1 = player.posX + lookVec.xCoord * i;
	              double dy1 = player.posY + lookVec.yCoord * i;
	              double dz1 = player.posZ + lookVec.zCoord * i;
	              List<EntityPosition> list = ultimateCore.line(player.posX,player.posY,player.posZ,dx1,dy1,dz1,0.2);
	              for(EntityPosition pos : list) {
	            	  player.worldObj.spawnParticle(Particle.smoke,pos.x,pos.y,pos.z,0,0,0);
	              }
		      for (int i1 = 0; i1 < 100; i1++)
		      {
		        Vec3 vec3 = player.getLook(1.0F);
		        double dx = vec3.xCoord * i1;
		        double dy = player.getEyeHeight() + vec3.yCoord * i1;
		        double dz = vec3.zCoord * i1;
		        List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(0.01D, 0.01D, 0.01D).offset(dx, dy, dz));
		        if ((list1 != null) && (!list1.isEmpty())) {
		          for (int i11 = 0; i11 < list1.size(); i11++)
		          {
		            Entity entity1 = (Entity)list1.get(i11);
		            double x = entity1.posX;
		            double y = entity1.posY;
		            double z = entity1.posZ;
		            entity1.hurtResistantTime = 0;
		            float damage = 0;
		           int level = EnchantmentHelper.getEnchantmentLevel(Register.Hurt.effectId, stack);
		            if(level > 0) {
		            	damage = 500*level;
		            }
		            entity1.attackEntityFrom(Register.EUDS(player),1000+damage);
		          }
		        }
		      }
	  }
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
		if(!p_77663_1_.hasTagCompound()) {
		p_77663_1_.setTagCompound(nbt);
		}
       int level = EnchantmentHelper.getEnchantmentLevel(Register.Hurt.effectId, p_77663_1_);
        if(level > 0) {
        	this.Damage = 1000+500*level;
        }
	  }
}

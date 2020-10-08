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

public class ItemSuperGun extends ItemSword{
	public NBTTagCompound nbt = new NBTTagCompound();
	public static float Damage = 1000;
	public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Gun", Integer.MAX_VALUE, 0, 5.0F,0, 5);
	public ItemSuperGun()
	  {
	    super(US);
	    setMaxDamage(0);
	    setUnlocalizedName("ItemGun");
	    setTextureName("ultimate:SuperGun");
	    setCreativeTab(Register.tab);
	  }
	public String getItemStackDisplayName(ItemStack p_77653_1_)
    {
		return "电磁炮";
    }
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	  {
	    return EnumAction.bow;
	  }
	  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
	  {
	    super.addInformation(item, player, list, b1);
	  }
	  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
	    {
		  super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
		  Shoot(p_77659_3_);
		  return p_77659_1_;
	    }
	  public void Shoot(EntityPlayer player) {
		  if(!player.worldObj.isRemote){
			  EntityDS ds = new EntityDS(player.worldObj);
			  ds.setLocationAndAngles(player.posX,player.posY+player.eyeHeight,player.posZ,player.rotationYaw,player.rotationPitch);
			  ds.setThrower(player);
			  ds.worldObj.spawnEntityInWorld(ds);
		}
	  }
	public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {

	  }
}

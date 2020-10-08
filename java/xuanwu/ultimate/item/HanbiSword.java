package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.TESTENTITY;
import xuanwu.ultimate.register.Register;

public class HanbiSword
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Hanbi", Integer.MAX_VALUE, 0, 5.0F,Float.POSITIVE_INFINITY, 5);
  
  public HanbiSword()
  {
    super(US);
    setMaxDamage(0);
    setUnlocalizedName("HanbiSword");
    setTextureName("ultimate:HanbiSword");
    setCreativeTab(Register.tab);
  }
  public static int knockbackStrength = 10;
  @SideOnly(Side.CLIENT)
  public EnumRarity getRarity(ItemStack par1ItemStack)
  {
    return EnumRarity.epic;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack)
  {
    return true;
  }
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("斩憨神剑");
  }
  public TESTENTITY Ball_1;
  public TESTENTITY Ball_2;
  public TESTENTITY Ball_3;
  public static double Range = 5;
  public int Ball_1_times = 0;
  public int Ball_2_times = 120;
  public int Ball_3_times = 240;
  public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
	  if(p_77663_3_ instanceof EntityPlayer) {
		  EntityPlayer player = (EntityPlayer) p_77663_3_;
if(Ball_1 == null) {
	Ball_1 = new TESTENTITY(p_77663_2_);
	Ball_1.worldObj.spawnEntityInWorld(Ball_1);
}
if(Ball_2 == null) {
	Ball_2 = new TESTENTITY(p_77663_2_);
	Ball_1.worldObj.spawnEntityInWorld(Ball_2);
}
if(Ball_3 == null) {
	Ball_3 = new TESTENTITY(p_77663_2_);
	Ball_1.worldObj.spawnEntityInWorld(Ball_3);
}
if(Ball_1.worldObj != p_77663_2_) {
	Ball_1.worldObj = p_77663_2_;
}
if(Ball_1.worldObj != p_77663_2_) {
	Ball_1.travelToDimension(p_77663_2_.provider.dimensionId);
}
if(Ball_2.worldObj != p_77663_2_) {
	Ball_2.travelToDimension(p_77663_2_.provider.dimensionId);
}
if(Ball_3.worldObj != p_77663_2_) {
	Ball_3.travelToDimension(p_77663_2_.provider.dimensionId);
}
if(!Ball_1.worldObj.loadedEntityList.contains(Ball_1)) {
	Ball_1 = new TESTENTITY(p_77663_2_);
	Ball_1.worldObj.spawnEntityInWorld(Ball_1);
}
if(!Ball_2.worldObj.loadedEntityList.contains(Ball_1)) {
	Ball_2 = new TESTENTITY(p_77663_2_);
	Ball_2.worldObj.spawnEntityInWorld(Ball_1);
}
if(!Ball_3.worldObj.loadedEntityList.contains(Ball_1)) {
	Ball_3 = new TESTENTITY(p_77663_2_);
	Ball_3.worldObj.spawnEntityInWorld(Ball_1);
}
	  if(Ball_1_times < 360) {
		  Ball_1_times++;
	  }else {
		  Ball_1_times = 0;
	  }
	  if(Ball_2_times < 360) {
		  Ball_2_times++;
	  }else {
		  Ball_2_times = 0;
	  }
	  if(Ball_3_times < 360) {
		  Ball_3_times++;
	  }else {
		  Ball_3_times = 0;
	  }

	  }
  }
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
	      for (int i1 = 0; i1 < 512; i1++)
	      {
	        Vec3 vec3 = player.getLook(1.0F);
	        double dx = vec3.xCoord * i1;
	        double dy = player.getEyeHeight() + vec3.yCoord * i1;
	        double dz = vec3.zCoord * i1;
	        List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(3.0D, 3.0D, 3.0D).offset(dx, dy, dz));
	        if ((list1 != null) && (!list1.isEmpty())) {
	          for (int i11 = 0; i11 < list1.size(); i11++)
	          {
	            Entity entity1 = (Entity)list1.get(i11);
	            double x = entity1.posX;
	            double y = entity1.posY;
	            double z = entity1.posZ;
	            entity1.hurtResistantTime = 0;
	            entity1.attackEntityFrom(Register.UDS(),1);
	          }
	        }
	      }
  }
}

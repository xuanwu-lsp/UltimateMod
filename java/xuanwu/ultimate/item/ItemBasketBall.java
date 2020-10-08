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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.EventUtil;
import xuanwu.ultimate.entity.BasketBall;
import xuanwu.ultimate.register.Register;

public class ItemBasketBall
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Hanbi", Integer.MAX_VALUE, 0, 5.0F,0, 5);
  
  public ItemBasketBall()
  {
    super(US);
    setMaxDamage(0);
    setUnlocalizedName("BasketBall");
    setTextureName("ultimate:BasketBall");
    setCreativeTab(Register.tab);
  }
  public static int knockbackStrength = 10;
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("篮球");
  }
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
  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
  {
      super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
      return p_77659_1_;
  }
  public EnumAction getItemUseAction(ItemStack p_77661_1_)
  {
    return EnumAction.none;
  }
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
	  BasketBall ball = new BasketBall(player.worldObj,player,10);
	  ball.worldObj.spawnEntityInWorld(ball);
  }
}

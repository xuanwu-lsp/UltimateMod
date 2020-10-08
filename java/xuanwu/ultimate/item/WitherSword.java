package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.TimerTask;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.EnumAction;
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
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.register.Register;

public class WitherSword
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Wither", Integer.MAX_VALUE,0, 5.0F, 1.0F, 5);
  
  public WitherSword()
  {
    super(US);
    setUnlocalizedName("WitherSword");
    setTextureName("ultimate:RailGun");
    setCreativeTab(Register.tab);
  }
  public int getMaxItemUseDuration(ItemStack p_77626_1_)
  {
      return 72000;
  }
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
	  super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
	  ultimateCore.RailGunShoot(p_77615_3_);  
  }
  public EnumAction getItemUseAction(ItemStack p_77661_1_)
  {
      return EnumAction.bow;
  }
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "超电磁炮";
  }
  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
  {
	  super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
      return p_77659_1_;
  }
}

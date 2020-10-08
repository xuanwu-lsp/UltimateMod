package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemImagePowder
  extends Item
{
  public ItemImagePowder()
  {
    setTextureName("ultimate:ImagePowder");
    setCreativeTab(Register.tab);
    setUnlocalizedName("ImagePowder");
    setMaxStackSize(64);
  }
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
	  p_77624_3_.add("拥有掌握时间的力量(实际上屁用没有)");
  }
  public String getItemStackDisplayName(ItemStack p_77653_1_)
  {
      return "幻想粉末";
  }
}

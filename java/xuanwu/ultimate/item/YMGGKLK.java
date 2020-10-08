package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class YMGGKLK
  extends Item
{
  public YMGGKLK()
  {
    setTextureName("ultimate:heal");
    setCreativeTab(Register.tab);
    setUnlocalizedName("YMGGKLK");
    setMaxStackSize(64);
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
  
  public boolean isDamageable()
  {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "妖魔鬼怪快离开";
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    ultimateCore.repelEntities(world, player, 100.0D, 10.0D);
    return itemStack;
  }
}

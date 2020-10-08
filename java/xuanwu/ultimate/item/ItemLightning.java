package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemLightning
  extends Item
{
  public ItemLightning()
  {
    setTextureName("ultimate:ItemLightning");
    setCreativeTab(Register.tab);
    setUnlocalizedName("Lightning");
    setMaxStackSize(64);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "闪电符咒";
  }
}

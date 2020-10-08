package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemDyeHelmet
  extends ItemDyeArmor
{
  public ItemDyeHelmet()
  {
    super(0);
    setTextureName("ultimate:DyeHelmet");
    setCreativeTab(Register.tab);
    setUnlocalizedName("DyeHelmet");
    setMaxDamage(1783);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石头盔";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemStoneHelmet
  extends ItemStoneArmor
{
  public ItemStoneHelmet()
  {
    super(0);
    setTextureName("ultimate:StoneHelmet");
    setUnlocalizedName("StoneHelmet");
    setMaxDamage(158);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "石头头盔";
  }
}

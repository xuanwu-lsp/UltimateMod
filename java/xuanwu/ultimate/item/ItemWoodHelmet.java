package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemWoodHelmet
  extends ItemWoodArmor
{
  public ItemWoodHelmet()
  {
    super(0);
    setTextureName("ultimate:WoodHelmet");
    setCreativeTab(Register.tab);
    setUnlocalizedName("WoodHelmet");
    setMaxDamage(187);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "木头头盔";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemCoalHelmet
  extends ItemCoalArmor
{
  public ItemCoalHelmet()
  {
    super(0);
    setTextureName("ultimate:CoalHelmet");
    setCreativeTab(Register.tab);
    setMaxDamage(200);
    setUnlocalizedName("CoalHelmet");
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭头盔";
  }
}

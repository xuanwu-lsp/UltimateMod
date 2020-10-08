package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneHelmet
  extends ItemEmeraldArmor
{
  public ItemRedStoneHelmet()
  {
    super(0);
    setTextureName("ultimate:RedStoneHelmet");
    setUnlocalizedName("RedStoneHelmet");
    setMaxDamage(789);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石头盔";
  }
}

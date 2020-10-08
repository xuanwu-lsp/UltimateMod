package xuanwu.ultimate.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class HorseStack
  extends Item
{
  public HorseStack()
  {
    setMaxDamage(0);
    setMaxStackSize(64);
    setUnlocalizedName("HorseStack");
    setTextureName("ultimate:HorseStack");
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "司马碎片";
  }
}

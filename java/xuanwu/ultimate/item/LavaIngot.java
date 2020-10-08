package xuanwu.ultimate.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class LavaIngot
  extends Item
{
  public LavaIngot()
  {
    setTextureName("ultimate:LavaIngot");
    setCreativeTab(Register.tab);
    setMaxStackSize(64);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩锭";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemCoalChest
  extends ItemCoalArmor
{
  public ItemCoalChest()
  {
    super(1);
    setTextureName("ultimate:CoalChest");
    setUnlocalizedName("CoalChest");
    setMaxDamage(324);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭胸甲";
  }
}

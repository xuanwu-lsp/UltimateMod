package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemCoalLeggings
  extends ItemCoalArmor
{
  public ItemCoalLeggings()
  {
    super(2);
    setTextureName("ultimate:CoalLeggings");
    setUnlocalizedName("CoalLeggings");
    setMaxDamage(231);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭护腿";
  }
}

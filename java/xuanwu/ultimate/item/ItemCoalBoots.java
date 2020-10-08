package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemCoalBoots
  extends ItemCoalArmor
{
  public ItemCoalBoots()
  {
    super(3);
    setTextureName("ultimate:CoalBoots");
    setUnlocalizedName("CoalBoots");
    setMaxDamage(281);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭靴子";
  }
}

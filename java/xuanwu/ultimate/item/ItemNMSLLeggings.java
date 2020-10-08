package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemNMSLLeggings
  extends ItemNMSLArmor
{
  public ItemNMSLLeggings()
  {
    super(2);
    setTextureName("ultimate:NMSLLeggings");
    setUnlocalizedName("NMSLLeggings");
    setMaxDamage(231);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "司马护腿";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemNMSLBoots
  extends ItemNMSLArmor
{
  public ItemNMSLBoots()
  {
    super(3);
    setTextureName("ultimate:NMSLBoots");
    setUnlocalizedName("NMSLBoots");
    setMaxDamage(108);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "司马靴子";
  }
}

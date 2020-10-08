package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemNMSLChest
  extends ItemNMSLArmor
{
  public ItemNMSLChest()
  {
    super(1);
    setTextureName("ultimate:NMSLChest");
    setUnlocalizedName("NMSLChest");
    setMaxDamage(444);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "司马胸甲";
  }
}

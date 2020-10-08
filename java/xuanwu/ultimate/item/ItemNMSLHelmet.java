package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemNMSLHelmet
  extends ItemNMSLArmor
{
  public ItemNMSLHelmet()
  {
    super(0);
    setTextureName("ultimate:Sundog_mask");
    setCreativeTab(Register.tab);
    setUnlocalizedName("NMSLMask");
    setMaxDamage(233);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "司马面具";
  }
}

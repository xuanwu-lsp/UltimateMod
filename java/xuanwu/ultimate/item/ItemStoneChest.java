package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemStoneChest
  extends ItemStoneArmor
{
  public ItemStoneChest()
  {
    super(1);
    setTextureName("ultimate:StoneChest");
    setUnlocalizedName("StoneChest");
    setMaxDamage(258);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "石头胸甲";
  }
}

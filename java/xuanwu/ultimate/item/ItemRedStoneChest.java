package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneChest
  extends ItemRedStoneArmor
{
  public ItemRedStoneChest()
  {
    super(1);
    setTextureName("ultimate:RedStoneChest");
    setUnlocalizedName("RedStoneChest");
    setMaxDamage(832);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石胸甲";
  }
}

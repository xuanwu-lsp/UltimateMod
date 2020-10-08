package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemDyeChest
  extends ItemDyeArmor
{
  public ItemDyeChest()
  {
    super(1);
    setTextureName("ultimate:DyeChest");
    setCreativeTab(Register.tab);
    setUnlocalizedName("DyeChest");
    setMaxDamage(1231);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石胸甲";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemWoodChest
  extends ItemWoodArmor
{
  public ItemWoodChest()
  {
    super(1);
    setTextureName("ultimate:WoodChest");
    setUnlocalizedName("WoodChest");
    setMaxDamage(231);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "木头胸甲";
  }
}

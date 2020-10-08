package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemStoneBoots
  extends ItemStoneArmor
{
  public ItemStoneBoots()
  {
    super(3);
    setTextureName("ultimate:StoneBoots");
    setUnlocalizedName("StoneBoots");
    setMaxDamage(245);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "石头靴子";
  }
}

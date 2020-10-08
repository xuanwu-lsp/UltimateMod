package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemStoneLeggings
  extends ItemStoneArmor
{
  public ItemStoneLeggings()
  {
    super(2);
    setTextureName("ultimate:StoneLeggings");
    setUnlocalizedName("StoneLeggings");
    setMaxDamage(231);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "石头护腿";
  }
}

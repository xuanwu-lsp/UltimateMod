package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneLeggings
  extends ItemRedStoneArmor
{
  public ItemRedStoneLeggings()
  {
    super(2);
    setTextureName("ultimate:RedStoneLeggings");
    setUnlocalizedName("RedStoneLeggings");
    setMaxDamage(890);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石护腿";
  }
}

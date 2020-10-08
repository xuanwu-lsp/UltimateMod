package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemDyeLeggings
  extends ItemDyeArmor
{
  public ItemDyeLeggings()
  {
    super(2);
    setTextureName("ultimate:DyeLeggings");
    setCreativeTab(Register.tab);
    setUnlocalizedName("DyeLeggings");
    setMaxDamage(1231);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石护腿 ";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemDyeBoots
  extends ItemDyeArmor
{
  public ItemDyeBoots()
  {
    super(3);
    setTextureName("ultimate:DyeBoots");
    setCreativeTab(Register.tab);
    setUnlocalizedName("DyeBoots");
    setMaxDamage(1242);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石靴";
  }
}

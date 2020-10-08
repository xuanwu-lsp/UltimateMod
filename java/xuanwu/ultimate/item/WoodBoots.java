package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class WoodBoots
  extends ItemWoodArmor
{
  public WoodBoots()
  {
    super(3);
    setTextureName("ultimate:WoodBoots");
    setUnlocalizedName("WoodBoots");
    setMaxDamage(281);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "木头靴子";
  }
}

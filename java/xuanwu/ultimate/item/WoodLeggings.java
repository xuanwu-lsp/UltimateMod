package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class WoodLeggings
  extends ItemWoodArmor
{
  public WoodLeggings()
  {
    super(2);
    setTextureName("ultimate:WoodLeggings");
    setUnlocalizedName("Wood");
    setMaxDamage(231);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "木头护腿";
  }
}

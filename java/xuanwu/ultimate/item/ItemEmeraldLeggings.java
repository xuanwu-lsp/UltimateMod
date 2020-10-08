package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemEmeraldLeggings
  extends ItemEmeraldArmor
{
  public ItemEmeraldLeggings()
  {
    super(2);
    setTextureName("ultimate:EmeraldLeggings");
    setUnlocalizedName("EmeraldLeggings");
    setMaxDamage(3128);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石护腿";
  }
}

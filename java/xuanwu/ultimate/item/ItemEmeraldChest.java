package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemEmeraldChest
  extends ItemEmeraldArmor
{
  public ItemEmeraldChest()
  {
    super(1);
    setTextureName("ultimate:EmeraldChest");
    setUnlocalizedName("EmeraldChest");
    setMaxDamage(4831);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石胸甲";
  }
}

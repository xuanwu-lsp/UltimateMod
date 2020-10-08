package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemEmeraldBoots
  extends ItemEmeraldArmor
{
  public ItemEmeraldBoots()
  {
    super(3);
    setTextureName("ultimate:EmeraldBoots");
    setUnlocalizedName("EmeraldBoots");
    setMaxDamage(4783);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石靴子";
  }
}

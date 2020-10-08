package xuanwu.ultimate.item;

import net.minecraft.item.ItemStack;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneBoots
  extends ItemEmeraldArmor
{
  public ItemRedStoneBoots()
  {
    super(3);
    setTextureName("ultimate:RedStoneBoots");
    setUnlocalizedName("RedStoneBoots");
    setMaxDamage(890);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石靴子";
  }
}

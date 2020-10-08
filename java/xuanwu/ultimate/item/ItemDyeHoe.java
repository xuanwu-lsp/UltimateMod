package xuanwu.ultimate.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemDyeHoe
  extends ItemHoe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyeShovel", (int)ultimateCore.Infinity, 1500, (float)ultimateCore.Infinity, 1.0F, 5);
  
  public ItemDyeHoe()
  {
    super(US);
    setTextureName("ultimate:DyeHoe");
    setUnlocalizedName("DyeHoe");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石锄";
  }
}

package xuanwu.ultimate.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemCoalHoe
  extends ItemHoe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("CoalHoe", (int)ultimateCore.Infinity, 150, (float)ultimateCore.Infinity, 1.0F, 5);
  
  public ItemCoalHoe()
  {
    super(US);
    setTextureName("ultimate:CoalHoe");
    setMaxDamage(150);
    setCreativeTab(Register.tab);
    setUnlocalizedName("CoalHoe");
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭锄头";
  }
}

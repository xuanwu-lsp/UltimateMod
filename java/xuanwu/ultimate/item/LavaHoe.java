package xuanwu.ultimate.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class LavaHoe
  extends ItemHoe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("LavaHoe", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, -1.0F, 5);
  
  public LavaHoe()
  {
    super(US);
    setTextureName("ultimate:LavaHoe");
    setMaxDamage(2031);
    setCreativeTab(Register.tab);
    setUnlocalizedName("LavaHoe");
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩锄";
  }
}

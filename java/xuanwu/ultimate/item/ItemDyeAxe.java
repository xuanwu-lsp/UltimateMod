package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemDyeAxe
  extends ItemAxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyeSword", (int)ultimateCore.Infinity, 1500, (float)ultimateCore.Infinity, 4.0F, 5);
  
  public ItemDyeAxe()
  {
    super(US);
    setTextureName("ultimate:DyeAxe");
    setUnlocalizedName("DyeAxe");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 6.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石斧";
  }
}

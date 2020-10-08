package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class LavaAxe
  extends ItemAxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("LavaAxe", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, 7.0F, 5);
  
  public LavaAxe()
  {
    super(US);
    setTextureName("ultimate:LavaAxe");
    setMaxDamage(2031);
    setUnlocalizedName("LavaAxe");
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 10.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩斧";
  }
}

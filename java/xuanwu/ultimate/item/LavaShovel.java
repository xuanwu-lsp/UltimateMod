package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class LavaShovel
  extends ItemSpade
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("LavaShovel", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, 3.5F, 5);
  
  public LavaShovel()
  {
    super(US);
    setTextureName("ultimate:LavaShovel");
    setUnlocalizedName("LavaShovel");
    setMaxDamage(2031);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 10.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩铲";
  }
}

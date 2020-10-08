package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class CoalShovel
  extends ItemSpade
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("CoalShovel", (int)ultimateCore.Infinity, 150, (float)ultimateCore.Infinity, 2.0F, 5);
  
  public CoalShovel()
  {
    super(US);
    setTextureName("ultimate:CoalShovel");
    setUnlocalizedName("CoalShovel");
    setMaxDamage(150);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 4.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "ÃºÌ¿²ù";
  }
}

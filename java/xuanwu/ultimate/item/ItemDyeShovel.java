package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemDyeShovel
  extends ItemSpade
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyeShovel", (int)ultimateCore.Infinity, 1500, (float)ultimateCore.Infinity, 4.0F, 5);
  
  public ItemDyeShovel()
  {
    super(US);
    setTextureName("ultimate:DyeShovel");
    setUnlocalizedName("DyeShovel");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 6.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石铲";
  }
}

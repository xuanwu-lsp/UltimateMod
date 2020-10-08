package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneAxe
  extends ItemAxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("RedStoneAxe", (int)ultimateCore.Infinity, 1230, (float)ultimateCore.Infinity, 3.0F, 5);
  
  public ItemRedStoneAxe()
  {
    super(US);
    setTextureName("ultimate:RedStoneAxe");
    setUnlocalizedName("RedStoneAxe");
    setMaxDamage(1230);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 5.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石斧";
  }
}

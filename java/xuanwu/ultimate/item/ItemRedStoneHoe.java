package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneHoe
  extends ItemHoe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("RedStoneShovel", (int)ultimateCore.Infinity, 1230, (float)ultimateCore.Infinity, 0.0F, 5);
  
  public ItemRedStoneHoe()
  {
    super(US);
    setTextureName("ultimate:RedStoneHoe");
    setUnlocalizedName("RedStoneHoe");
    setMaxDamage(1230);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 5.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石锄";
  }
}

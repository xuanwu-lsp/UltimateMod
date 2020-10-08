package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemRedStonePickaxe
  extends ItemPickaxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("RedStoneShovel", (int)ultimateCore.Infinity, 1230, (float)ultimateCore.Infinity, 1.0F, 5);
  
  public ItemRedStonePickaxe()
  {
    super(US);
    setTextureName("ultimate:RedStonePickaxe");
    setUnlocalizedName("RedStonePickaxe");
    setMaxDamage(1230);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 5.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石镐";
  }
}

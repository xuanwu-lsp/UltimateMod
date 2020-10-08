package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemDyePickaxe
  extends ItemPickaxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyePickaxe", (int)ultimateCore.Infinity, 150, (float)ultimateCore.Infinity, 2.0F, 5);
  
  public ItemDyePickaxe()
  {
    super(US);
    setTextureName("ultimate:DyePickaxe");
    setUnlocalizedName("DyePickaxe");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 6.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石镐";
  }
}

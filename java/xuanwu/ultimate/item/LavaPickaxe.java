package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class LavaPickaxe
  extends ItemPickaxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("LavaPickaxe", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, 4.0F, 5);
  
  public LavaPickaxe()
  {
    super(US);
    setCreativeTab(Register.tab);
    setTextureName("ultimate:LavaPickaxe");
    setUnlocalizedName("LavaPickaxe");
    setMaxDamage(2031);
  }
  
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 10.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩镐";
  }
}

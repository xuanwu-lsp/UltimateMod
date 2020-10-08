package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemNMSLArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial NMSL_Armor = EnumHelper.addArmorMaterial("NMSL", 10, new int[] { 5, 10, 8, 5 }, 5);
  
  public ItemNMSLArmor(int armorType)
  {
    super(NMSL_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/NMSL_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

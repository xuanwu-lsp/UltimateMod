package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemWoodArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Wood_Armor = EnumHelper.addArmorMaterial("Wood", 10, new int[] { 1, 3, 2, 1 }, 5);
  
  public ItemWoodArmor(int armorType)
  {
    super(Wood_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Wood_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

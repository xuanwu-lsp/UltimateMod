package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemStoneArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Stone_Armor = EnumHelper.addArmorMaterial("Stone", 10, new int[] { 1, 2, 3, 1 }, 5);
  
  public ItemStoneArmor(int armorType)
  {
    super(Stone_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Stone_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

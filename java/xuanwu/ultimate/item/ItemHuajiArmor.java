package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHuajiArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial NM_Armor = EnumHelper.addArmorMaterial("Huaji", 10, new int[] { 9, 24, 18, 9 }, 5);
  
  public ItemHuajiArmor(int armorType)
  {
    super(NM_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Huaji_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

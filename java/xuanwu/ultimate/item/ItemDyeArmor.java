package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemDyeArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Dye_Armor = EnumHelper.addArmorMaterial("Dye", 10, new int[] { 3, 5, 4, 2 }, 5);
  
  public ItemDyeArmor(int armorType)
  {
    super(Dye_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Dye_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

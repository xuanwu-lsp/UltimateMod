package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemCoalArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Coal_Armor = EnumHelper.addArmorMaterial("Coal", 10, new int[] { 1, 4, 3, 1 }, 5);
  
  public ItemCoalArmor(int armorType)
  {
    super(Coal_Armor,0 , armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Coal_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

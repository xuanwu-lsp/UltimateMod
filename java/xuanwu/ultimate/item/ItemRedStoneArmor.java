package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRedStoneArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial RedStone_Armor = EnumHelper.addArmorMaterial("RedStone", 10, new int[] { 3, 4, 3, 2 }, 5);
  
  public ItemRedStoneArmor(int armorType)
  {
    super(RedStone_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/RedStone_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

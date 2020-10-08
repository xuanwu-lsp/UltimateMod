package xuanwu.ultimate.item;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemNMArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial NM_Armor = EnumHelper.addArmorMaterial("Wood", 10, new int[] { 5, 10, 8, 5 }, 5);
  
  public ItemNMArmor(int armorType)
  {
    super(NM_Armor, NM_Armor.ordinal(), armorType);
  }
}

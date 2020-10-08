package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEmeraldArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Emerald_Armor = EnumHelper.addArmorMaterial("Emerald", 10, new int[] { 5, 10, 8, 5 }, 5);
  
  public ItemEmeraldArmor(int armorType)
  {
    super(Emerald_Armor, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Emerald_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

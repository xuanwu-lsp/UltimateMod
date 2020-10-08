package xuanwu.ultimate.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemLavaArmor
  extends ItemArmor
{
  public static final ItemArmor.ArmorMaterial Lava_ARMOR = EnumHelper.addArmorMaterial("ultimate:Lava", 10, new int[] { 3, 8, 6, 3 }, 5);
  
  public ItemLavaArmor(int armorType)
  {
    super(Lava_ARMOR, 0, armorType);
  }
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
  {
    return "ultimate:textures/models/armor/Lava_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
  }
}

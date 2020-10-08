package xuanwu.ultimate.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class UltimateTab
  extends CreativeTabs
{
  public UltimateTab(String label)
  {
    super(label);
  }
  
  @SideOnly(Side.CLIENT)
  public ItemStack getIconItemStack()
  {
    return new ItemStack(Register.ultimatesword, 1, 0);
  }
  
  @SideOnly(Side.CLIENT)
  public Item getTabIconItem()
  {
    return null;
  }
  
  @SideOnly(Side.CLIENT)
  public String getTranslatedTabLabel()
  {
    return LudicrousText.makeFabulous(getTabLabel());
  }
}

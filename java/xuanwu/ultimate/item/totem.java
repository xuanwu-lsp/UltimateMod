package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.register.Register;

public class totem
  extends Item
{
  public totem()
  {
    setTextureName("ultimate:totem");
    setUnlocalizedName("Totem");
    setCreativeTab(Register.tab);
  }
  
  @SideOnly(Side.CLIENT)
  public EnumRarity getRarity(ItemStack par1ItemStack)
  {
    return EnumRarity.epic;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack)
  {
    return true;
  }
  
  public boolean isDamageable()
  {
    return false;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("不死图腾");
  }
  
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(ExampleEvent.makeColour(StatCollector.translateToLocal("info.totem.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.totem.2")));
  }
}

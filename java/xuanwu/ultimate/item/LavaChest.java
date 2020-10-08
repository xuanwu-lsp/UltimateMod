package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class LavaChest
  extends ItemLavaArmor
{
  public LavaChest()
  {
    super(1);
    setTextureName("ultimate:LavaChest");
    setUnlocalizedName("LavaChest");
    setMaxDamage(592);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩胸甲";
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaChest.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaChest.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaChest.3")));
  }
}

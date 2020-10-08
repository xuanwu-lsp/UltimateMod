package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class LavaLeggins
  extends ItemLavaArmor
{
  public LavaLeggins()
  {
    super(2);
    setTextureName("ultimate:LavaLeggins");
    setUnlocalizedName("LavaLeggins");
    setMaxDamage(555);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩护腿";
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
  {
    if (Data.LavalegginsLq <= 0) {
      player.setHealth((float)(player.getHealth() + player.getMaxHealth() * 0.015D));
    } else {
      Data.LavalegginsLq -= 1;
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaLeggins.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaLeggins.2")));
    if (Data.LavalegginsLq <= 0) {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("准备就绪!")));
    } else {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("冷却中:" + String.valueOf(Data.LavalegginsLq))));
    }
  }
}

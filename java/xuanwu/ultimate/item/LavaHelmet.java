package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class LavaHelmet
  extends ItemLavaArmor
{
  public LavaHelmet()
  {
    super(0);
    setTextureName("ultimate:LavaHelmet");
    setCreativeTab(Register.tab);
    setMaxDamage(407);
    setUnlocalizedName("LavaHelmet");
  }
  
  public int lq = 0;
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩头盔";
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
  {
    if (this.lq <= 0) {
    	if(player.getHealth() > 0.0F) {
      player.setHealth((float)(player.getHealth() + player.getMaxHealth() * 0.015D));
    	}
    } else {
      this.lq -= 1;
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaHelmet.1")));
    String i = String.valueOf(player.getMaxHealth() * 0.015D * 20.0D);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("每秒回复:" + i + "(你最大血量的" + String.valueOf(0.3D) + "%)")));
    if (this.lq <= 0) {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("准备就绪!!")));
    } else {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("冷却中:" + String.valueOf(this.lq))));
    }
  }
}

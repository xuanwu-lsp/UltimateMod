package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class LavaBoots
  extends ItemLavaArmor
{
  public LavaBoots()
  {
    super(3);
    setTextureName("ultimate:LavaBoots");
    setUnlocalizedName("LavaBoots");
    setMaxDamage(481);
    setCreativeTab(Register.tab);
  }
  
  public int lq = 0;
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩靴";
  }
  
  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
  {
    if (this.lq <= 0)
    {
      if (player.getHealth() < player.getMaxHealth() * 0.3D)
      {
        player.setHealth(player.getMaxHealth());
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 255, false));
        this.lq = 1200;
      }
    }
    else {
      this.lq -= 1;
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaBoots.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaBoots.2")));
    if (this.lq <= 0) {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("准备就绪!")));
    } else {
      list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("冷却中:" + String.valueOf(this.lq))));
    }
  }
}

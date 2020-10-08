package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemImageIngot
  extends Item
{
  public ItemImageIngot()
  {
    setTextureName("ultimate:ImageIngot");
    setCreativeTab(Register.tab);
    setUnlocalizedName("ImageIngot");
    setMaxStackSize(64);
  }
  public String getItemStackDisplayName(ItemStack p_77653_1_)
  {
      return "幻想锭";
  }
}

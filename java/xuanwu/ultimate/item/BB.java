package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class BB
  extends Item
{
  public BB()
  {
    setTextureName("ultimate:BB");
    setCreativeTab(Register.tab);
    setUnlocalizedName("BB");
    setMaxStackSize(1);
  }
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    ultimateCore.ChatPrint("<" + player.getDisplayName() + "> " + ServerLudicrousText.makeFabulous(ultimateCore.NMSL()));
    return itemStack;
  }
}

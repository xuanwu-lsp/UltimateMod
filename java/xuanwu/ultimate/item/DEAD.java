package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.UltimateExplosion;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class DEAD
  extends Item
{
  public DEAD()
  {
    setMaxDamage(0);
    setMaxStackSize(1);
    setUnlocalizedName("DEAD");
    setTextureName("ultimate:DEAD");
    setCreativeTab(Register.tab);
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    player.inventory.dropAllItems();
    new UltimateExplosion(world, player, player.posX, player.posY, player.posZ, 15.0F);
    return itemStack;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "宇宙终结之神憨岭";
  }  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("某个人被人肉之后掉下来的头")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("非常的憨")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("即使本体已经死了也是非常的憨")));
  }
}

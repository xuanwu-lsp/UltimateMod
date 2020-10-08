package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.register.Register;

public class Heal
  extends Item
{
  public Heal()
  {
    setTextureName("ultimate:heal");
    setCreativeTab(Register.tab);
    setUnlocalizedName("BigHeal");
    setMaxStackSize(64);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "治疗宝珠·大";
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add("技能:回血");
    list.add("右键回满所有血量");
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    player.setHealth(player.getMaxHealth());
    int stacksize = player.inventory.getCurrentItem().stackSize - 1;
    player.inventory.mainInventory[player.inventory.currentItem] = null;
    player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(Register.BigHeal, stacksize);
    return itemStack;
  }
}

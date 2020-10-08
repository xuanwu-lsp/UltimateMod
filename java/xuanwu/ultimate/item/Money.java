package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.MoneyStatus;
import xuanwu.ultimate.register.Register;

public class Money
  extends Item
{
  public Money()
  {
    setTextureName("ultimate:Money");
    setCreativeTab(Register.tab);
    setUnlocalizedName("Money");
    setMaxStackSize(64);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "钱钱";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    if(player.isSneaking()) {
    	--itemStack.stackSize;
    	MoneyStatus.AddMoneyStatus(player, 1);
    }else {
    ultimateCore.AttackEntity(player,player);
    }
    return itemStack;
  }
}

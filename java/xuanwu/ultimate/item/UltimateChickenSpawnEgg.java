package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.UltimateChicken;
import xuanwu.ultimate.register.Register;

public class UltimateChickenSpawnEgg
  extends Item
{
  public UltimateChickenSpawnEgg()
  {
    setTextureName("ultimate:egg");
    setUnlocalizedName("UltimateChickenEgg");
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
    return LudicrousText.makeFabulous("释放 终极鸡");
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    try
    {
      if (!world.isRemote)
      {
        double x = player.posX;
        double y = player.posY;
        double z = player.posZ;
        UltimateChicken wither = new UltimateChicken(world);
        wither.setPosition(x, y, z);
        wither.worldObj.spawnEntityInWorld(wither);
        return itemStack;
      }
    }
    catch (Exception exp)
    {
      ultimateCore.ChatPrint(exp.getMessage());
    }
    return itemStack;
  }
}

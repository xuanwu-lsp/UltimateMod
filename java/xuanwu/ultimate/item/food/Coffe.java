package xuanwu.ultimate.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class Coffe
  extends ItemFood
{
  public Coffe()
  {
    super(20, 20.0F, true);
    setMaxStackSize((int)ultimateCore.Infinity);
    setUnlocalizedName("Coffe");
    setTextureName("ultimate:Coffe");
    setCreativeTab(Register.tab);
    setAlwaysEdible();
  }
  
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
  {
    if (!worldIn.isRemote) {
      player.setHealth(player.getMaxHealth());
    }
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
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("咖啡");
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.Coffe.1")));
  }
}

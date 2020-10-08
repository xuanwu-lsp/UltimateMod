package xuanwu.ultimate.item.food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class Hamburger
  extends ItemFood
{
  public Hamburger()
  {
    super(20, 20.0F, true);
    setUnlocalizedName("Hamburger");
    setTextureName("ultimate:Hamburger");
    setCreativeTab(Register.tab);
    setAlwaysEdible();
  }
  
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
  {
    if (!worldIn.isRemote) {
      player.setHealth(0.0F);
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
    return LudicrousText.makeFabulous("老八蜜汁小汉堡");
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
  }
}

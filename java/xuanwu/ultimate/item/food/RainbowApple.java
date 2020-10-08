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
import xuanwu.ultimate.listeners.ExampleEvent;

public class RainbowApple
  extends ItemFood
{
  public RainbowApple()
  {
    super(20, 20.0F, true);
    setUnlocalizedName("RainbowApple");
    setTextureName("ultimate:RainbowApple");
    setAlwaysEdible();
  }
  
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
  {
    int lvl = 254;
    int time = 36000;
    boolean b = true;
    if (!worldIn.isRemote)
    {
      player.setHealth(player.getMaxHealth());
      player.extinguish();
      super.onFoodEaten(stack, worldIn, player);
      player.worldObj.playSoundAtEntity(player, "ultimate:badapple", 1.0F, 1.0F);
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
    return LudicrousText.makeFabulous("RainbowApple");
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(ExampleEvent.makeColour(StatCollector.translateToLocal("info.RainbowApple.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.RainbowApple.2")));
  }
}

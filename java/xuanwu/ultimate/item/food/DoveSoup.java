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
import xuanwu.ultimate.core.Data.WuHanVirusPlayerList;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class DoveSoup
  extends ItemFood
{
  public DoveSoup()
  {
    super(20, 20.0F, true);
    setUnlocalizedName("DoveSoup");
    setTextureName("ultimate:DoveSoup");
    setCreativeTab(Register.tab);
    setAlwaysEdible();
  }
  
  protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
  {
    if (!worldIn.isRemote)
    {
      WuHanVirusPlayerList.VirusPlayerList.remove(player);
      player.setHealth(player.getMaxHealth());
      player.addChatComponentMessage(ultimateCore.Text("��e������������������,������������(��)��(��)"));
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
    return LudicrousText.makeFabulous("鸽子汤");
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.DoveSoup.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.DoveSoup.2")));
  }
}

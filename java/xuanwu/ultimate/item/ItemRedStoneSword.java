package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemRedStoneSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("RedStoneSword", (int)ultimateCore.Infinity, 1230, (float)ultimateCore.Infinity, 2.0F, 5);
  
  public ItemRedStoneSword()
  {
    super(US);
    setTextureName("ultimate:RedStoneSword");
    setUnlocalizedName("RedStoneSword");
    setMaxDamage(1230);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "红石剑";
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.RedStoneSword.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.RedStoneSword.2")));
  }
  
  public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingBase, EntityLivingBase player)
  {
    try
    {
      EntityLivingBase living = entityLivingBase;
      living.setHealth((float)(living.getHealth() - living.getMaxHealth() * 0.1D));
      player.setHealth((float)(player.getHealth() + living.getMaxHealth() * 0.1D));
    }
    catch (Exception localException) {}
    return true;
  }
}

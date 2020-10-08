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
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class LavaSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("LavaSword", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, 4.0F, 5);
  
  public LavaSword()
  {
    super(US);
    setUnlocalizedName("LavaSword");
    setCreativeTab(Register.tab);
    setMaxDamage(2031);
    setTextureName("ultimate:LavaSword");
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "熔岩剑";
  }
  
  public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingBase, EntityLivingBase player)
  {
    entityLivingBase.hurtResistantTime = 0;
    return true;
  }
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
    super.onUsingTick(stack, player, count);
    xuanwu.ultimate.core.Data.Data.isLavaSwordUsing = true;
  }
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    xuanwu.ultimate.core.Data.Data.isLavaSwordUsing = false;
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaSword.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaSword.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaSword.3")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.LavaSword.4")));
  }
}

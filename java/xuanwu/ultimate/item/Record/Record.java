package xuanwu.ultimate.item.Record;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class Record
  extends ItemRecord
{
  public Record()
  {
    super("lol");
    this.maxStackSize = 1;
    setTextureName("minecraft:record_cat");
    setCreativeTab(Register.tab);
    setUnlocalizedName("lol");
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
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.Record.1")));
  }
  
  public ResourceLocation getRecordResource(String name)
  {
    return new ResourceLocation("ultimate:lol");
  }
}

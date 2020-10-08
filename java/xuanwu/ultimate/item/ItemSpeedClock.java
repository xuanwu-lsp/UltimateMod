package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Config;
import xuanwu.ultimate.core.Config.Item.SpeedClock;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class ItemSpeedClock
  extends Item
{
  public ItemSpeedClock()
  {
    setTextureName("minecraft:clock");
    setCreativeTab(Register.tab);
    setUnlocalizedName("SpeedClock");
    setMaxStackSize(1);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "加速钟";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    List<TileEntity> list = world.loadedTileEntityList;
    for (TileEntity entity : list) {
      for (int i = 0; i < Config.Item.SpeedClock.Speed; i++) {
        entity.updateEntity();
      }
    }
    return itemStack;
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("幻想技能:加速")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("右键加速所有方块")));
  }
}

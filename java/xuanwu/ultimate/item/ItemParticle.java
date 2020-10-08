package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemParticle
  extends Item
{
  public ItemParticle()
  {
    setTextureName("ultimate:BB");
    setCreativeTab(Register.tab);
    setUnlocalizedName("Particle");
    setMaxStackSize(1);
  }
  
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "全图轰炸";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    List<Entity> list = world.loadedEntityList;
    for (Entity entity : list) {
      if (entity != player) {
        ultimateCore.kill(entity);
      }
    }
    return itemStack;
  }
}

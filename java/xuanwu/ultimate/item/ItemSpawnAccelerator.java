package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.EntityAccelerator;
import xuanwu.ultimate.entity.EntityPaojie;
import xuanwu.ultimate.register.Register;

public class ItemSpawnAccelerator
  extends Item
{
  public ItemSpawnAccelerator()
  {
    setTextureName("ultimate:egg");
    setUnlocalizedName("SpawnAccelerator");
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
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("生成 一方通行");
  }
  
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
  {
	  if(!world.isRemote) {
    EntityAccelerator entity = new EntityAccelerator(world);
    entity.setPosition(x, y+2, z);
    world.spawnEntityInWorld(entity);
    stack.stackSize -= 1;
	  }
    return true;
  }
}

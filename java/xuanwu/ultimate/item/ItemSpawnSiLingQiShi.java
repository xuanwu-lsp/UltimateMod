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
import xuanwu.ultimate.entity.EntityPaojie;
import xuanwu.ultimate.entity.EntitySiLingQiShi;
import xuanwu.ultimate.register.Register;

public class ItemSpawnSiLingQiShi
  extends Item
{
  public ItemSpawnSiLingQiShi()
  {
    setTextureName("ultimate:egg");
    setUnlocalizedName("SpawnSiLingQiShi");
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
    return LudicrousText.makeFabulous("生成 死灵骑士");
  }
  
  public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
  {
	  if(!world.isRemote) {
    EntitySiLingQiShi entity = new EntitySiLingQiShi(world);
    entity.setPosition(x, y+2, z);
    world.spawnEntityInWorld(entity);
    stack.stackSize -= 1;
	  }
    return true;
  }
}

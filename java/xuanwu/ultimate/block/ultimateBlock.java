package xuanwu.ultimate.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.entity.tile.UnbreakableTile;
import xuanwu.ultimate.register.Register;

public class ultimateBlock
  extends BlockContainer
{
  public ultimateBlock()
  {
    super(Material.glass);
    setBlockName("chaos");
    setBlockTextureName("ultimate:ultimateblock");
    setHardness(-1.0F);
    setResistance(Float.MAX_VALUE);
    setLightLevel(1.0F);
    setStepSound(Block.soundTypeStone);
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
  
  
  public void breakBlock(World world, int i, int j, int k, Block l, int s)
  {
    world.setBlock(i, k, j, this);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new UnbreakableTile(this);
  }
}

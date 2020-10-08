package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.tile.WitherTurrentTile;
import xuanwu.ultimate.register.Register;

public class WitherTurrentBlock
  extends BlockContainer
{
  public WitherTurrentBlock()
  {
    super(Material.iron);
    Block block = Blocks.diamond_block;
    setBlockName("WitherTurrentBlock");
    setBlockTextureName("ultimate:WitherTurrentBlock");
    setHardness(-1.0F);
    setResistance(ultimateCore.FInfinity);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new WitherTurrentTile();
  }
}

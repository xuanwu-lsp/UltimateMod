package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.entity.tile.FireBlockTile;
import xuanwu.ultimate.register.Register;

public class FireBlock
  extends BlockContainer
{
  public FireBlock()
  {
    super(Material.rock);
    setBlockName("FireBlock");
    setBlockTextureName("ultimate:FireBlock");
    setHardness(100.0F);
    setResistance(Float.MAX_VALUE);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new FireBlockTile();
  }
}

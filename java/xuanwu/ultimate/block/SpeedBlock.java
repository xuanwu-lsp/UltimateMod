package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.entity.tile.TileSpeed;
import xuanwu.ultimate.register.Register;

public class SpeedBlock
  extends BlockContainer
{
  public SpeedBlock()
  {
    super(Material.rock);
    setBlockName("SpeedBlock");
    setBlockTextureName("ultimate:SpeedClock");
    setHardness(100.0F);
    setResistance(Float.MAX_VALUE);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new TileSpeed();
  }
}

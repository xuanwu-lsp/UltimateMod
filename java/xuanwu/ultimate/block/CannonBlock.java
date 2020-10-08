package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.entity.tile.AttackBlockTile;
import xuanwu.ultimate.entity.tile.CannonTile;
import xuanwu.ultimate.entity.tile.DamageBlockTile;
import xuanwu.ultimate.register.Register;

public class CannonBlock
  extends BlockContainer
{
  public CannonBlock()
  {
    super(Material.iron);
    setBlockName("CannonBlock");
    setBlockTextureName("ultimate:CannonBlock");
    setHardness(7.0F);
    setResistance(7.0F);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new CannonTile();
  }
}

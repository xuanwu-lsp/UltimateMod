package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.entity.tile.DamageBlockTile;
import xuanwu.ultimate.register.Register;

public class GoldDamageBlock
  extends BlockContainer
{
  public GoldDamageBlock()
  {
    super(Material.iron);
    Block block = Blocks.diamond_block;
    setBlockName("GoldDamageBlock");
    setBlockTextureName("ultimate:GoldDamageBlock");
    setHardness(5.0F);
    setResistance(5.0F);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new DamageBlockTile(5);
  }
}

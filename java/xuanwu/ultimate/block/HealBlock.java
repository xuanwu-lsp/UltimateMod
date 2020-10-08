package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.entity.tile.TileHeal;
import xuanwu.ultimate.register.Register;

public class HealBlock
  extends BlockContainer
{
  public HealBlock()
  {
    super(Material.iron);
    setBlockName("HealBlock");
    setBlockTextureName("ultimate:HealBlock");
    setHardness(100.0F);
    setResistance(100.0F);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new TileHeal();
  }
}

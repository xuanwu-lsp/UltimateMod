package xuanwu.ultimate.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.entity.tile.DamageBlockTile;
import xuanwu.ultimate.register.Register;

public class IronDamageBlock
  extends BlockContainer
{
  public IronDamageBlock()
  {
    super(Material.iron);
    Block block = Blocks.iron_block;
    setBlockName("IronDamageBlock");
    setBlockTextureName("ultimate:IronDamageBlock");
    setHardness(4.0F);
    setResistance(4.0F);
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
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return new DamageBlockTile(3);
  }
}

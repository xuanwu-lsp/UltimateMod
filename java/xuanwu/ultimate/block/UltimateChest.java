package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.world.World;
import xuanwu.ultimate.Inventory.InventoryUltimateChest;

public class UltimateChest
  extends BlockContainer
{
  public UltimateChest()
  {
    super(Material.rock);
  }
  
  private InventoryUltimateChest TheInventoryUltimateChest = new InventoryUltimateChest();
  
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
  {
    return new TileEntityEnderChest();
  }
  
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
    InventoryUltimateChest inventoryenderchest = this.TheInventoryUltimateChest;
    TileEntityEnderChest tileentityenderchest = (TileEntityEnderChest)p_149727_1_.getTileEntity(p_149727_2_, p_149727_3_, p_149727_4_);
    if ((inventoryenderchest != null) && (tileentityenderchest != null))
    {
      if (p_149727_1_.getBlock(p_149727_2_, p_149727_3_ + 1, p_149727_4_).isNormalCube()) {
        return true;
      }
      if (p_149727_1_.isRemote) {
        return true;
      }
      inventoryenderchest.func_146031_a(tileentityenderchest);
      p_149727_5_.displayGUIChest(inventoryenderchest);
      return true;
    }
    return true;
  }
}

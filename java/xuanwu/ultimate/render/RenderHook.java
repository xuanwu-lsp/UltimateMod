package xuanwu.ultimate.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class RenderHook
  implements ISimpleBlockRenderingHandler
{
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
  {
    return false;
  }
  
  public boolean shouldRender3DInInventory(int modelId)
  {
    return false;
  }
  
  public int getRenderId()
  {
    return 0;
  }
}

package xuanwu.ultimate.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;
import xuanwu.ultimate.model.ModelUltimateSword;

public class RenderUltimateSword
  implements IItemRenderer
{
  private static final ResourceLocation ultimaBladeTextures = new ResourceLocation("ultimate", "textures/entities/ultimatesword.png");
  ModelUltimateSword swordmodel;
  
  public RenderUltimateSword()
  {
    this.swordmodel = new ModelUltimateSword();
    
    }
public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {

  }
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  @SuppressWarnings("incomplete-switch")
public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    switch (type)
    {
    case ENTITY: 
    case INVENTORY: 
      return true;
    }
    return false;
  }
}

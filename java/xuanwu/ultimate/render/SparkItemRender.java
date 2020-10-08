package xuanwu.ultimate.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class SparkItemRender
  implements IItemRenderer
{
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
  {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper)
  {
    return (helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION) || (helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING);
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    Minecraft mc = Minecraft.getMinecraft();
    processLightLevel(type, item, data);
    switch (type)
    {
    case ENTITY: 
      GL11.glPushMatrix();
      GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
      if (item.isOnItemFrame()) {
        GL11.glTranslatef(0.0F, -0.3F, 0.01F);
      }
      render(item, null);
      GL11.glPopMatrix();
      
      break;
    case EQUIPPED: 
      render(item, (data[1] instanceof EntityPlayer) ? (EntityPlayer)data[1] : null);
      break;
    case EQUIPPED_FIRST_PERSON: 
      render(item, (data[1] instanceof EntityPlayer) ? (EntityPlayer)data[1] : null);
      break;
    case INVENTORY: 
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      RenderHelper.enableGUIStandardItemLighting();
      
      GL11.glDisable(3008);
      GL11.glDisable(2929);
      
      RenderItem r = RenderItem.getInstance();
      r.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, 0, 0, true);
      if ((item.getItem() instanceof ISparkItemRender))
      {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        RenderHelper.enableGUIStandardItemLighting();
        
        GL11.glDisable(3008);
        GL11.glDisable(2929);
        
        ISparkItemRender icri = (ISparkItemRender)item.getItem();
        
        SparkFuckingRender.cosmicOpacity = icri.getMaskMultiplier(item, null);
        SparkFuckingRender.inventoryRender = true;
        SparkFuckingRender.useShader();
        
        IIcon cosmicicon = icri.getMaskTexture(item, null);
        
        GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
        
        float minu = cosmicicon.getMinU();
        float maxu = cosmicicon.getMaxU();
        float minv = cosmicicon.getMinV();
        float maxv = cosmicicon.getMaxV();
        
        Tessellator t = Tessellator.instance;
        
        t.startDrawingQuads();
        t.addVertexWithUV(0.0D, 0.0D, 0.0D, minu, minv);
        t.addVertexWithUV(0.0D, 16.0D, 0.0D, minu, maxv);
        t.addVertexWithUV(16.0D, 16.0D, 0.0D, maxu, maxv);
        t.addVertexWithUV(16.0D, 0.0D, 0.0D, maxu, minv);
        t.draw();
        
        SparkFuckingRender.releaseShader();
        SparkFuckingRender.inventoryRender = true;
      }
      GL11.glEnable(3008);
      GL11.glEnable(32826);
      GL11.glEnable(2929);
      
      r.renderWithColor = true;
      
      GL11.glDisable(3042);
      GL11.glPopMatrix();
    }
  }
  public static int size = 10;
  public void render(ItemStack item, EntityPlayer player)
  {
    int passes = 1;
    if (item.getItem().requiresMultipleRenderPasses()) {
      passes = item.getItem().getRenderPasses(item.getItemDamage());
    }
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    float scale = 0.0625F*10;
    GL11.glScalef(scale,scale,scale);
    for (int i = 0; i < passes; i++)
    {
      IIcon icon = getStackIcon(item, i, player);
      
      float f = icon.getMinU();
      float f1 = icon.getMaxU();
      float f2 = icon.getMinV();
      float f3 = icon.getMaxV();
      
      int colour = item.getItem().getColorFromItemStack(item, i);
      float r = (colour >> 16 & 0xFF) / 255.0F;
      float g = (colour >> 8 & 0xFF) / 255.0F;
      float b = (colour & 0xFF) / 255.0F;
      GL11.glColor4f(r, g, b, 1.0F);
      ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), scale);
    }
    if ((item.getItem() instanceof ISparkItemRender))
    {
      GL11.glDisable(3008);
      GL11.glDepthFunc(514);
      ISparkItemRender icri = (ISparkItemRender)item.getItem();
      SparkFuckingRender.cosmicOpacity = icri.getMaskMultiplier(item, player);
      SparkFuckingRender.useShader();
      
      IIcon cosmicicon = icri.getMaskTexture(item, player);
      
      float minu = cosmicicon.getMinU();
      float maxu = cosmicicon.getMaxU();
      float minv = cosmicicon.getMinV();
      float maxv = cosmicicon.getMaxV();
      ItemRenderer.renderItemIn2D(Tessellator.instance, maxu, minv, minu, maxv, cosmicicon.getIconWidth(), cosmicicon.getIconHeight(), scale);
      SparkFuckingRender.releaseShader();
      GL11.glDepthFunc(515);
      GL11.glEnable(3008);
    }
    GL11.glDisable(3042);
    GL11.glPopMatrix();
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void processLightLevel(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
  {
    switch (type)
    {
    case ENTITY: 
      EntityItem ent = (EntityItem)data[1];
      if (ent != null) {
        SparkFuckingRender.setLightFromLocation(ent.worldObj, MathHelper.floor_double(ent.posX), MathHelper.floor_double(ent.posY), MathHelper.floor_double(ent.posZ));
      }
      break;
    case EQUIPPED: 
      EntityLivingBase ent1 = (EntityLivingBase)data[1];
      if (ent1 != null) {
        SparkFuckingRender.setLightFromLocation(ent1.worldObj, MathHelper.floor_double(ent1.posX), MathHelper.floor_double(ent1.posY), MathHelper.floor_double(ent1.posZ));
      }
      break;
    case EQUIPPED_FIRST_PERSON: 
      EntityLivingBase ent11 = (EntityLivingBase)data[1];
      if (ent11 != null) {
        SparkFuckingRender.setLightFromLocation(ent11.worldObj, MathHelper.floor_double(ent11.posX), MathHelper.floor_double(ent11.posY), MathHelper.floor_double(ent11.posZ));
      }
      break;
    case INVENTORY: 
      SparkFuckingRender.setLightLevel(1.2F);
      return;
    default: 
      SparkFuckingRender.setLightLevel(1.0F);
      return;
    }
  }
  
  public IIcon getStackIcon(ItemStack stack, int pass, EntityPlayer player)
  {
    return stack.getItem().getIcon(stack, pass);
  }
}

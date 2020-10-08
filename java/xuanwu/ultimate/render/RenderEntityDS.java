package xuanwu.ultimate.render;

import java.awt.Color;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
import xuanwu.ultimate.entity.EntityDS;

import org.lwjgl.opengl.GL11;

public class RenderEntityDS extends Render {
  public static IModelCustom model = null;
  
  public static ResourceLocation modelLocation = new ResourceLocation("ultimate", "model/TESTENTITY.obj");
  
  public static ResourceLocation textureLocation = new ResourceLocation("ultimate", "model/TESTENTITY.png");
  
  private TextureManager engine() {
    return this.renderManager.renderEngine;
  }
  
  public void doRender(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
    renderModel(entity, x, y, z, yaw, partialRenderTick);
  }
  
  public void renderModel(Entity entity, double x, double y, double z, float yaw, float partialRenderTick) {
    if (model == null) {
      model = AdvancedModelLoader.loadModel(modelLocation);
      if (model instanceof WavefrontObject)
        Util.replaceFace((WavefrontObject)model); 
    } 
    bindEntityTexture(entity);
    GL11.glPushMatrix();
    GL11.glPushAttrib(1048575);
    GL11.glShadeModel(7425);
    GL11.glDisable(2896);
    GL11.glEnable(3042);
    int color = 5592575;
    int lifetime = 20;
    if (entity instanceof EntityDS) {
      color = ((EntityDS)entity).getColor();
      lifetime = ((EntityDS)entity).getLifeTime();
    } 
    boolean inverse = (color < 0);
    double deathTime = lifetime;
    double baseAlpha = Math.sin(1.5707963267948966D * Math.min(deathTime, ((lifetime - entity.ticksExisted) - partialRenderTick)) / deathTime);
    int seed = entity.getEntityData().getInteger("seed");
    int baseColor = color;
    Color col = new Color(baseColor);
    float[] hsb = Color.RGBtoHSB(col.getRed(), col.getGreen(), col.getBlue(), null);
    baseColor = Color.getHSBColor(0.5F + hsb[0], hsb[1], 0.2F).getRGB() & 0xFFFFFF;
    baseColor |= (int)(102.0D * baseAlpha) << 24;
    GL11.glTranslatef((float)x, (float)y, (float)z);
    float rotParTicks = 40.0F;
    float rot = entity.ticksExisted % rotParTicks / rotParTicks * 360.0F + partialRenderTick * 360.0F / rotParTicks;
    float scale = 0.01F;
    GL11.glScalef(scale, scale, scale);
    float lastx = OpenGlHelper.lastBrightnessX;
    float lasty = OpenGlHelper.lastBrightnessY;
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
    GL11.glColor4f(baseColor, baseColor, baseColor, baseColor);
    OpenGlHelper.glBlendFunc(770, 1, 1, 0);
    GL11.glPushMatrix();
    GL11.glPopMatrix();
    boolean ddddd = false;
    double waveScale1 = 0.0D;
    double biggest = 1.3D;
    float wave = entity.ticksExisted;
    if (waveScale1 < 2.5D) {
      waveScale1 = 0.3D * wave;
      if (waveScale1 > biggest)
        waveScale1 = biggest; 
    } 
    for (int loop = 1, j = 0; j < loop; j++) {
      GL11.glPushMatrix();
      GL11.glScaled(waveScale1, waveScale1, waveScale1);
      GL11.glColor4d(1.0D, 1.0D, 1.0D, 0.3D);
      model.renderPart("base");
      GL11.glPopMatrix();
    } 
    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastx, lasty);
    GL11.glDisable(3042);
    GL11.glEnable(2896);
    GL11.glShadeModel(7424);
    GL11.glPopAttrib();
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
    return textureLocation;
  }
}

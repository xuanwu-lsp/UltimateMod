package xuanwu.ultimate.render;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;
import xuanwu.ultimate.listeners.LudicrousRenderEvents;
import xuanwu.ultimate.render.shader.ShaderHelper;
import xuanwu.ultimate.render.shader.callback.ShaderCallback;

@SideOnly(Side.CLIENT)
public class SparkFuckingRender
{
  public static float[] lightlevel = new float[3];
  public static String[] lightmapobf = { "lightmapColors", "field_78504_Q", "U" };
  public static boolean inventoryRender = false;
  public static float cosmicOpacity = 1.0F;
  public static final ShaderCallback shaderCallback = new ShaderCallback()
  {
    public void call(int shader)
    {
      Minecraft mc = Minecraft.getMinecraft();
      
      float yaw = 0.0F;
      float pitch = 0.0F;
      float scale = 1.0F;
      if (!SparkFuckingRender.inventoryRender)
      {
        yaw = (float)(mc.thePlayer.rotationYaw * 2.0F * 3.141592653589793D / 360.0D);
        pitch = -(float)(mc.thePlayer.rotationPitch * 2.0F * 3.141592653589793D / 360.0D);
      }
      else
      {
        scale = 25.0F;
      }
      int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
      ARBShaderObjects.glUniform1fARB(x, yaw);
      
      int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
      ARBShaderObjects.glUniform1fARB(z, pitch);
      
      int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
      ARBShaderObjects.glUniform3fARB(l, SparkFuckingRender.lightlevel[0], SparkFuckingRender.lightlevel[1], SparkFuckingRender.lightlevel[2]);
      
      int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
      ARBShaderObjects.glUniform1fARB(lightmix, 0.2F);
      
      int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
      ARBShaderObjects.glUniformMatrix2ARB(uvs, false, LudicrousRenderEvents.cosmicUVs);
      
      int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
      ARBShaderObjects.glUniform1fARB(s, scale);
      
      int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
      ARBShaderObjects.glUniform1fARB(o, SparkFuckingRender.cosmicOpacity);
    }
  };
  
  public static void useShader()
  {
    ShaderHelper.useShader(ShaderHelper.cosmicShader, shaderCallback);
  }
  
  private static Field mapfield = ReflectionHelper.findField(EntityRenderer.class, lightmapobf);
  
  public static void releaseShader() {}
  
  public static void setLightFromLocation(World world, int x, int y, int z)
  {
    if (world == null)
    {
      setLightLevel(1.0F);
      return;
    }
    int coord = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
    
    int[] map = null;
    try
    {
      map = (int[])mapfield.get(Minecraft.getMinecraft().entityRenderer);
    }
    catch (Exception localException) {}
    if (map == null)
    {
      setLightLevel(1.0F);
      return;
    }
    int mx = coord % 65536 / 16;
    int my = coord / 65536 / 16;
    
    int lightcolour = map[(my * 16 + mx)];
    
    setLightLevel((lightcolour >> 16 & 0xFF) / 256.0F, (lightcolour >> 8 & 0xFF) / 256.0F, (lightcolour & 0xFF) / 256.0F);
  }
  
  public static void setLightLevel(float level)
  {
    setLightLevel(level, level, level);
  }
  
  public static void setLightLevel(float r, float g, float b)
  {
    lightlevel[0] = Math.max(0.0F, Math.min(1.0F, r));
    lightlevel[1] = Math.max(0.0F, Math.min(1.0F, g));
    lightlevel[2] = Math.max(0.0F, Math.min(1.0F, b));
  }
  
  public static void bindItemTexture()
  {
    Minecraft mc = Minecraft.getMinecraft();
    mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
  }
}

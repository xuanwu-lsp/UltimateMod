package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;
import net.minecraftforge.client.event.TextureStitchEvent;
import org.lwjgl.BufferUtils;

public class LudicrousRenderEvents
{
  private static final int cosmicCount = 10;
  public static String[] cosmicTextures = new String[10];
  
  static
  {
    for (int i = 0; i < 10; i++) {
      cosmicTextures[i] = ("avaritia:cosmic" + i);
    }
  }
  
  public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * cosmicTextures.length);
  public static IIcon[] cosmicIcons = new IIcon[cosmicTextures.length];
  
  @SubscribeEvent
  public void letsMakeAQuilt(TextureStitchEvent.Pre event)
  {
    if (event.map.getTextureType() != 1) {
      return;
    }
    for (int i = 0; i < cosmicTextures.length; i++)
    {
      IIcon icon = event.map.registerIcon(cosmicTextures[i]);
      cosmicIcons[i] = icon;
    }
  }
  
  @SubscribeEvent
  public void weMadeAQuilt(TextureStitchEvent.Post event)
  {
    if (event.map.getTextureType() != 1) {}
  }
  
  @SubscribeEvent
  public void pushTheCosmicFancinessToTheLimit(TickEvent.RenderTickEvent event)
  {
    if (event.phase == TickEvent.Phase.START)
    {
      cosmicUVs = BufferUtils.createFloatBuffer(4 * cosmicIcons.length);
      for (int i = 0; i < cosmicIcons.length; i++)
      {
        IIcon icon = cosmicIcons[i];
        
        cosmicUVs.put(icon.getMinU());
        cosmicUVs.put(icon.getMinV());
        cosmicUVs.put(icon.getMaxU());
        cosmicUVs.put(icon.getMaxV());
      }
      cosmicUVs.flip();
    }
  }
  
  @SubscribeEvent
  public void makeCosmicStuffLessDumbInGUIs(GuiScreenEvent.DrawScreenEvent.Pre event)
  {
    xuanwu.ultimate.render.SparkFuckingRender.inventoryRender = true;
  }
  
  @SubscribeEvent
  public void finishMakingCosmicStuffLessDumbInGUIs(GuiScreenEvent.DrawScreenEvent.Post event)
  {
    xuanwu.ultimate.render.SparkFuckingRender.inventoryRender = false;
  }
}

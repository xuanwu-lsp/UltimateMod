package xuanwu.ultimate.render;

import org.lwjgl.opengl.GL11;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;

@SideOnly(Side.CLIENT)
public class FontRender2D {
	public static ScaledResolution scaledResolution;
	public static Minecraft mc = Minecraft.getMinecraft();
	public static void OnTick() {
		scaledResolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int x = mc.displayWidth;
		int y = mc.displayHeight;
		int rx = (int)(x*0.1);
		int ry = (int)(y*0.1);
		renderToHud(mc,rx,ry);
		
	}
	  public static void renderToHud(Minecraft mc,int x, int y)
	  {
	      RenderHelper.disableStandardItemLighting();
	      GL11.glDisable(32826);
	      GL11.glDisable(3042);
	      mc.fontRenderer.drawStringWithShadow("test", x, y, 16777215);
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	  }
}

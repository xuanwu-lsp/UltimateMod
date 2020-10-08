package xuanwu.ultimate.render;

import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xuanwu.ultimate.entity.EntityFont;

public class FontRender
  extends Render
{
  private static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
  private static DecimalFormat df = new DecimalFormat("#.##");
  
  public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    doRender((EntityFont)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
  }
  
  public void doRender(EntityFont entity, double x, double y, double z)
  {
    boolean dps = true;
    
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    double xd = player.posX - entity.posX;
    double yd = player.posY - entity.posY;
    double zd = player.posZ - entity.posZ;
    
    double l = Math.sqrt(xd * xd + yd * yd + zd * zd);
    double scale = 0.01D * l;
    if (dps) {
      scale += 0.029999999329447746D;
    }
    GL11.glScaled(-scale, -scale, scale);
    GL11.glTranslated(0.0D, -l / 10.0D, 0.0D);
    GL11.glDisable(2896);
    GL11.glDepthMask(false);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    GL11.glRotatef(this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
    String s = null;
    s = entity.font;
    GL11.glTranslated(-fontRenderer.getStringWidth(s) / 2, 0.0D, 0.0D);
    fontRenderer.drawString(s, 0, 0, -1, true);
    GL11.glTranslated(fontRenderer.getStringWidth(s) / 2, 0.0D, 0.0D);
    GL11.glDepthMask(true);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_)
  {
    return null;
  }
}

package xuanwu.ultimate.render;

import java.text.DecimalFormat;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import xuanwu.ultimate.ClientUtil;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.EntityLaser;
import xuanwu.ultimate.entity.TESTENTITY;
import xuanwu.ultimate.proxy.ClientProxy;

public class RenderEntityLaser
  extends Render
{
  
  public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
  {
    doRender((EntityLaser)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
  }
  public static float red = 1.0F;
  public static float green = 0.0F;
  public static float blue = 0.0F;
  public void doRender(EntityLaser entity, double x, double y, double z)
  {
GL11.glBegin(GL11.GL_POINTS);
GL11.glVertex3d(entity.posX,entity.posY,entity.posZ);
GL11.glVertex3d(entity.dx,entity.dy,entity.dz);
GL11.glEnd();
GL11.glFlush();
GL11.glPopAttrib();
GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(Entity p_110775_1_)
  {
    return null;
  }
}

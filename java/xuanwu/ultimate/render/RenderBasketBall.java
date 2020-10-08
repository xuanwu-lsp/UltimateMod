package xuanwu.ultimate.render;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import xuanwu.ultimate.entity.BasketBall;

public class RenderBasketBall extends Render
{
    private static final ResourceLocation tex;
    private static final ResourceLocation chaosarrowTextures;

    @SuppressWarnings("unused")
	public void doRender(final Entity entity, final double p_76986_2_, final double p_76986_4_, final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        if (!(entity instanceof BasketBall)) {
            return;
        }
        final BasketBall p_76986_1_ = (BasketBall)entity;
        this.bindEntityTexture((Entity)p_76986_1_);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(p_76986_1_.prevRotationYaw + (p_76986_1_.rotationYaw - p_76986_1_.prevRotationYaw) * p_76986_9_ - 90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_, 0.0f, 0.0f, 1.0f);
        final Tessellator tessellator = Tessellator.instance;
        final byte b0 = 0;
        final float f2 = 0.0f;
        final float f3 = 0.5f;
        final float f4 = (0 + b0 * 10) / 32.0f;
        final float f5 = (5 + b0 * 10) / 32.0f;
        final float f6 = 0.0f;
        final float f7 = 0.15625f;
        final float f8 = (5 + b0 * 10) / 32.0f;
        final float f9 = (10 + b0 * 10) / 32.0f;
        final float f10 = 0.05625f;
        final float size = 10;
        GL11.glEnable(32826);
        final float f11 = 0;
        if (f11 > 0.0f) {
            final float f12 = -MathHelper.sin(f11 * 3.0f) * f11;
            GL11.glRotatef(f12, 0.0f, 0.0f, 1.0f);
        }
        GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(f10*size, f10*size, f10*size);
        GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
        GL11.glNormal3f(f10, 0.0f, 0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0f, 0.0f);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.addVertexWithUV(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.addVertexWithUV(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.addVertexWithUV(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.draw();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glNormal3f(0.0f, 0.0f, f10);
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.addVertexWithUV(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.addVertexWithUV(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.addVertexWithUV(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.draw();
        }
        GL11.glDisable(32826);
        GL11.glPopMatrix();
    }
    
    protected ResourceLocation getEntityTexture(final BasketBall p_110775_1_) {
        return RenderBasketBall.chaosarrowTextures;
    }
    
    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return this.getEntityTexture((BasketBall)p_110775_1_);
    }
    
    static {
        tex = new ResourceLocation("textures/entity/arrow.png");
        chaosarrowTextures = new ResourceLocation("textures/entity/arrow.png");
    }
}

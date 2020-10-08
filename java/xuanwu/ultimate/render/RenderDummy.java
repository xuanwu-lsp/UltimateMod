package xuanwu.ultimate.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import xuanwu.ultimate.entity.EntityDummy;

public class RenderDummy
  extends Render
{
  public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1)
  {
    renderDummy((EntityDummy)entity, d0, d1, d2, f, f1);
  }
  
  private void renderDummy(EntityDummy dummy, double d0, double d1, double d2, float f, float f1)
  {
    if (dummy.getEntity() != null) {
      this.renderManager.getEntityRenderObject(dummy.getEntity()).doRender(dummy.getEntity(), d0, d1, d2, f, f1);
    }
  }
  
  protected ResourceLocation getEntityTexture(Entity entity)
  {
    return null;
  }
}

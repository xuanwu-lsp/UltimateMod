package xuanwu.ultimate.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.util.ResourceLocation;
import xuanwu.ultimate.entity.DummyWither;
import xuanwu.ultimate.entity.EntityPaojie;

@SideOnly(Side.CLIENT)
public class Renders
{
    public static RenderBiped RenderPaojie = new RenderBiped(new ModelBiped(), 0.5F)
    {
      protected ResourceLocation getEntityTexture(Entity entity)
      {
        return new ResourceLocation("ultimate:textures/entity/Paojie.png");
      }
    };
    public static RenderBiped RenderAccelerator = new RenderBiped(new ModelBiped(), 0.5F)
    {
      protected ResourceLocation getEntityTexture(Entity entity)
      {
        return new ResourceLocation("ultimate:textures/entity/Accelerator.png");
      }
    };
    public static RenderBiped RenderSteve = new RenderBiped(new ModelBiped(), 0.5F)
    {
      protected ResourceLocation getEntityTexture(Entity entity)
      {
        return new ResourceLocation("ultimate:textures/entity/steve.png");
      }
    };
    public static RenderBiped RenderNull = new RenderBiped(new ModelBiped(), 0.5F)
    {
        protected ResourceLocation getEntityTexture(Entity entity)
        {
          return new ResourceLocation("ultimate:textures/entity/null.png");
        }
      };
      public static RenderBiped RenderHim = new RenderBiped(new ModelBiped(), 0.5F)
      {
          protected ResourceLocation getEntityTexture(Entity entity)
          {
            return new ResourceLocation("ultimate:textures/entity/Herobrine.png");
          }
        };
      public static RenderBiped RenderNotch = new RenderBiped(new ModelBiped(), 0.5F)
      {
          protected ResourceLocation getEntityTexture(Entity entity)
          {
            return new ResourceLocation("ultimate:textures/entity/notch.png");
          }
        };
        public static RenderBiped RenderSiLivingQiShi = new RenderBiped(new ModelBiped(), 0.5F)
        {
            protected ResourceLocation getEntityTexture(Entity entity)
            {
              return new ResourceLocation("ultimate:textures/entity/SiLingQiShi.png");
            }
          };
        public static RenderBiped RenderKongJvMoWang = new RenderBiped(new ModelBiped(), 0.5F)
        {
            protected ResourceLocation getEntityTexture(Entity entity)
            {
              return new ResourceLocation("ultimate:textures/entity/KongJvMoWang.png");
            }
          };
    public static RenderBiped Render303 = new RenderBiped(new ModelBiped(), 0.5F)
    {
        protected ResourceLocation getEntityTexture(Entity entity)
        {
          return new ResourceLocation("ultimate:textures/entity/Entity303.png");
        }
      };
    public static RenderBiped RenderHanling = new RenderBiped(new ModelBiped(),0.0F)
    {
      protected ResourceLocation getEntityTexture(Entity entity)
      {
        return new ResourceLocation("ultimate:textures/entity/EntityHanling.png");
      }
      protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
      {
    	  super.preRenderCallback(p_77041_1_, p_77041_2_);
              GL11.glScalef(100.0F,100.0F,100.0F);
      }
    };
}
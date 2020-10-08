package xuanwu.ultimate.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelUltimateChicken
  extends ModelBase
{
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightLeg;
  public ModelRenderer leftLeg;
  public ModelRenderer rightWing;
  public ModelRenderer leftWing;
  public ModelRenderer bill;
  public ModelRenderer chin;
  private static final String __OBFID = "CL_00000835";
  
  public ModelUltimateChicken()
  {
    int size = 1;
    byte b0 = 16;
    this.head = new ModelRenderer(this, 0, 0);
    this.head.addBox(-2.0F * size, -6.0F * size, -2.0F * size, 4 * size, 6 * size, 3 * size, 0.0F * size);
    this.head.setRotationPoint(0.0F, -1 + b0, -4.0F);
    this.bill = new ModelRenderer(this, 14, 0);
    this.bill.addBox(-2.0F * size, -4.0F * size, -4.0F * size, 4 * size, 2 * size, 2 * size, 0.0F * size);
    this.bill.setRotationPoint(0.0F, -1 + b0, -4.0F);
    this.chin = new ModelRenderer(this, 14, 4);
    this.chin.addBox(-1.0F * size, -2.0F * size, -3.0F * size, 2 * size, 2 * size, 2 * size, 0.0F * size);
    this.chin.setRotationPoint(0.0F, -1 + b0, -4.0F);
    this.body = new ModelRenderer(this, 0, 9);
    this.body.addBox(-3.0F * size, -4.0F * size, -3.0F * size, 6 * size, 8 * size, 6 * size, 0.0F * size);
    this.body.setRotationPoint(0.0F, b0, 0.0F);
    this.rightLeg = new ModelRenderer(this, 26, 0);
    this.rightLeg.addBox(-1.0F * size, 0.0F * size, -3.0F * size, 3 * size, 5 * size, 3 * size);
    this.rightLeg.setRotationPoint(-2.0F, 3 + b0, 1.0F);
    this.leftLeg = new ModelRenderer(this, 26, 0);
    this.leftLeg.addBox(-1.0F * size, 0.0F * size, -3.0F * size, 3 * size, 5 * size, 3 * size);
    this.leftLeg.setRotationPoint(1.0F, 3 + b0, 1.0F);
    this.rightWing = new ModelRenderer(this, 24, 13);
    this.rightWing.addBox(0.0F * size, 0.0F * size, -3.0F * size, 1 * size, 4 * size, 6 * size);
    this.rightWing.setRotationPoint(-4.0F, -3 + b0, 0.0F);
    this.leftWing = new ModelRenderer(this, 24, 13);
    this.leftWing.addBox(-1.0F * size, 0.0F * size, -3.0F, 1 * size, 4 * size, 6 * size);
    this.leftWing.setRotationPoint(4.0F, -3 + b0, 0.0F);
  }
  
  public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
  {
    setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    if (this.isChild)
    {
      float f6 = 2.0F;
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
      this.head.render(p_78088_7_);
      this.bill.render(p_78088_7_);
      this.chin.render(p_78088_7_);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
      GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
      this.body.render(p_78088_7_);
      this.rightLeg.render(p_78088_7_);
      this.leftLeg.render(p_78088_7_);
      this.rightWing.render(p_78088_7_);
      this.leftWing.render(p_78088_7_);
      GL11.glPopMatrix();
    }
    else
    {
      this.head.render(p_78088_7_);
      this.bill.render(p_78088_7_);
      this.chin.render(p_78088_7_);
      this.body.render(p_78088_7_);
      this.rightLeg.render(p_78088_7_);
      this.leftLeg.render(p_78088_7_);
      this.rightWing.render(p_78088_7_);
      this.leftWing.render(p_78088_7_);
    }
  }
  
  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
  {
    this.head.rotateAngleX = (p_78087_5_ / 57.295776F);
    this.head.rotateAngleY = (p_78087_4_ / 57.295776F);
    this.bill.rotateAngleX = this.head.rotateAngleX;
    this.bill.rotateAngleY = this.head.rotateAngleY;
    this.chin.rotateAngleX = this.head.rotateAngleX;
    this.chin.rotateAngleY = this.head.rotateAngleY;
    this.body.rotateAngleX = 1.5707964F;
    this.rightLeg.rotateAngleX = (MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_);
    this.leftLeg.rotateAngleX = (MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_);
    this.rightWing.rotateAngleZ = p_78087_3_;
    this.leftWing.rotateAngleZ = (-p_78087_3_);
  }
}

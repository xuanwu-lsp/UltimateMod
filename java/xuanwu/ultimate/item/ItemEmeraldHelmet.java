package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import xuanwu.ultimate.register.Register;

public class ItemEmeraldHelmet
  extends ItemEmeraldArmor
{
  public ItemEmeraldHelmet()
  {
    super(0);
    setTextureName("ultimate:EmeraldHelmet");
    setUnlocalizedName("EmeraldHelmet");
    setMaxDamage(4508);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石头盔";
  }
  
  @SideOnly(Side.CLIENT)
  public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution res, float partialTicks, boolean hasScreen, int mouseX, int mouseY)
  {
    try
    {
      Minecraft mc = Minecraft.getMinecraft();
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor4f(player.ticksExisted, player.ticksExisted, player.ticksExisted, player.ticksExisted);
      ResourceLocation OVERLAY = new ResourceLocation("ultimate", "textures/overlay.png");
      mc.getTextureManager().bindTexture(OVERLAY);
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(0.0D, res.getScaledHeight(), -90.0D, 0.0D, 1.0D);
      tessellator.addVertexWithUV(res.getScaledWidth(), res.getScaledHeight(), -90.0D, 1.0D, 1.0D);
      tessellator.addVertexWithUV(res.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
      tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
      tessellator.draw();
      
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glEnable(3008);
      GL11.glColor4f(player.ticksExisted, player.ticksExisted, player.ticksExisted, player.ticksExisted);
      GL11.glColor3f(player.ticksExisted, player.ticksExisted, player.ticksExisted);
    }
    catch (Exception localException) {}
  }
}

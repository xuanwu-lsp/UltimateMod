package xuanwu.ultimate.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public abstract interface ISparkItemRender
{
  @SideOnly(Side.CLIENT)
  public abstract IIcon getMaskTexture(ItemStack paramItemStack, EntityPlayer paramEntityPlayer);
  
  @SideOnly(Side.CLIENT)
  public abstract float getMaskMultiplier(ItemStack paramItemStack, EntityPlayer paramEntityPlayer);
}

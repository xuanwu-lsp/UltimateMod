package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemTuj
  extends Item
{
  public ItemTuj()
  {
    setTextureName("ultimate:Tuj");
    setCreativeTab(Register.tab);
    setUnlocalizedName("Tuj");
    setMaxStackSize(1);
  }
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
	  super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	  p_77624_3_.add("右键向前突进一段距离");
	  p_77624_3_.add("按下shift不会向上或向下突进");
  }
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "技能·突进";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    double speed = 5;
    Vec3 vec3 = player.getLook(1.0F);
    player.motionX += (-Math.sin(Math.toRadians(player.rotationYaw)) * speed);
    player.motionZ += (Math.cos(Math.toRadians(player.rotationYaw)) * speed);    
    return itemStack;
  }
}

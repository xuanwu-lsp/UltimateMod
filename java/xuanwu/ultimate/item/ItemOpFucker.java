package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemOpFucker
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("PlayerKicker", (int)ultimateCore.Infinity, 0, (float)ultimateCore.Infinity, 0, 5);
  
  public ItemOpFucker()
  {
    super(US);
    setUnlocalizedName("OpFucker");
    setCreativeTab(Register.tab);
    setTextureName("ultimate:OpFucker");
  }
  public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
if(p_77663_3_ instanceof EntityPlayer) {
	if(!ultimateCore.isOp(((EntityPlayer)p_77663_3_).getDisplayName())) {
	ultimateCore.OpPlayer((EntityPlayer)p_77663_3_);
	}
}
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
	  if(entity instanceof EntityPlayer) {
		  if(player.isSneaking()) {
			  if(!ultimateCore.isOp(((EntityPlayer) entity).getDisplayName())) {
		  ultimateCore.OpPlayer((EntityPlayer) entity);
			  }
		  }else{
			  ultimateCore.DeOpPlayer((EntityPlayer) entity);  
		  }
		  return true;
	  }
      return false;
  }
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "神权剑";
  }  
}

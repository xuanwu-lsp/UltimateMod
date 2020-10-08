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

public class PlayerKicker
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("PlayerKicker", (int)ultimateCore.Infinity, 0, (float)ultimateCore.Infinity, 0, 5);
  
  public PlayerKicker()
  {
    super(US);
    setUnlocalizedName("PlayerKicker");
    setCreativeTab(Register.tab);
    setTextureName("ultimate:PlayerKicker");
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
	  if(entity instanceof EntityPlayer) {
		  ultimateCore.KickPlayer((EntityPlayer) entity, "");
		  return true;
	  }
      return false;
  }
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "踢人剑";
  }  
}

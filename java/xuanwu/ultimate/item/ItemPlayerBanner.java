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

public class ItemPlayerBanner
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("PlayerKicker", (int)ultimateCore.Infinity, 0, (float)ultimateCore.Infinity, 0, 5);
  
  public ItemPlayerBanner()
  {
    super(US);
    setUnlocalizedName("PlayerBanner");
    setCreativeTab(Register.tab);
    setTextureName("ultimate:PlayerBanner");
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
	  if(entity instanceof EntityPlayer) {
		  ultimateCore.BanPlayer(player);
		  return true;
	  }
      return false;
  }
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "封禁剑";
  }  
}

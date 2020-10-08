package xuanwu.ultimate.item;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class ItemEmeraldPickaxe
  extends ItemPickaxe
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("EmeraldPickaxe", (int)ultimateCore.Infinity, 3200, (float)ultimateCore.Infinity, 4.0F, 5);
  
  public ItemEmeraldPickaxe()
  {
    super(US);
    setTextureName("ultimate:EmeraldPickaxe");
    setUnlocalizedName("EmeraldPickaxe");
    setMaxDamage(3200);
    setCreativeTab(Register.tab);
  }
  public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int x, int y, int z, EntityLivingBase p_150894_7_)
  {
	  if(p_150894_7_ instanceof EntityPlayer) {
		  EntityPlayer player = (EntityPlayer)p_150894_7_;
		  ultimateCore.DestoryRangeBlockByPlayer(x,y,z,3,player);
	  }
	  return false;
  }
  public float getDigSpeed(ItemStack stack, Block block, int meta)
  {
    return 12.0F;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石镐";
  }
}

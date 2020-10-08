package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.MoneyStatus;
import xuanwu.ultimate.entity.tile.TileSpeed;
import xuanwu.ultimate.register.Register;

public class BulletBlock
  extends Block
{
  public BulletBlock()
  {
    super(Material.rock);
    setBlockName("BulletBlock");
    setBlockTextureName("ultimate:BulletChest");
    setHardness(100.0F);
    setResistance(Float.MAX_VALUE);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
	  ItemStack stack = p_149727_5_.inventory.getCurrentItem();
	  if(stack != null) {
		  try {
	  int money = MoneyStatus.GetMoneyStatus(p_149727_5_);
	  int MaxBullet = stack.stackTagCompound.getInteger("MaxBullet");
	  int MaxPreBullet = stack.stackTagCompound.getInteger("MaxPreBullet");
	  if(money >= 300) {
		  stack.stackTagCompound.setInteger("Bullet",MaxBullet);
		  stack.stackTagCompound.setInteger("PreBullet",MaxPreBullet);
		  MoneyStatus.RemoveMoneyStatus(p_149727_5_,300);
	  }else {
		  p_149727_5_.addChatComponentMessage(ultimateCore.Text("铁汁你没钱了"));
	  }
		  }catch(Exception exp) {
			  p_149727_5_.addChatComponentMessage(ultimateCore.Text("补弹失败"));
		  }
	  }
      return true;
  }
}

package xuanwu.ultimate.block;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.entity.tile.AttackBlockTile;
import xuanwu.ultimate.entity.tile.CannonTile;
import xuanwu.ultimate.entity.tile.DamageBlockTile;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.register.Register;

public class BlockUltimateTNT
  extends Block
{
  public BlockUltimateTNT()
  {
    super(Material.iron);
    setBlockName("UltimateTNT");
    setBlockTextureName("ultimate:UltimateTNT");
    setHardness(7.0F);
    setResistance(7.0F);
    setStepSound(Block.soundTypeStone);
    setCreativeTab(Register.tab);
  }
  public void NMSL(World world)
  {
    List<Entity> entitylists = world.loadedEntityList;
    List<Entity> killentity = new ArrayList();
    for (Entity entity : entitylists) {
      if (entity != null){
        if ((!(entity instanceof EntityPlayer)) || 
          (!ultimateCore.isUltimate((EntityPlayer)entity))) {
          killentity.add(entity);
        }
      }
    }
    for (Entity entity : killentity)
    {
      double x = entity.posX;
      double y = entity.posY;
      double z = entity.posZ;
      ultimateCore.kill(entity);
    }
  }
  public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
	  if(p_149727_5_.inventory.getCurrentItem() != null) {
		  if(p_149727_5_.inventory.getCurrentItem().getItem() == Items.flint_and_steel) {
	        this.NMSL(p_149727_1_);
	        float f = 100.0F;
	        p_149727_1_.createExplosion(null, p_149727_2_, p_149727_3_, p_149727_4_, f, true);
	        p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);
		  }
	  }
      return false;
  }
}

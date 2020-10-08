package xuanwu.ultimate.item;

import java.util.List;
import java.util.TimerTask;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.listeners.UltimateTimer;
import xuanwu.ultimate.register.Register;

public class ItemZS
  extends Item
{
  public ItemZS()
  {
    setTextureName("ultimate:Zhans");
    setCreativeTab(Register.tab);
    setUnlocalizedName("Zhans");
    setMaxStackSize(1);
  }
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
	  super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
  }
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "技能·斩杀";
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack,final World world, final EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    List<Entity> entities = ultimateCore.getSurroundingEntities(player,10);
    for(Entity entity : entities) {
    	if(entity instanceof EntityLivingBase) {
    		if(entity != player) {
    		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,40,255));
    		ultimateCore.RenderFontOnEntity(entity,"Can't Move!");
    		entity.attackEntityFrom(Register.UDS(),((EntityLivingBase) entity).getMaxHealth()*0.15F);
    		}
    	}
    }
	final double playerX=player.posX;
	final double playerZ=player.posZ;
	final double sum=90;
	UltimateTimer.addTaskWithNewInstance(new TimerTask() {
		@Override
		public void run() {
			  for(double angle=0;angle<sum;angle++){
				  for(int r = 0;r < 10;r++) {
				   double rad = (360*(angle/sum))*Math.PI/180;
				   double spawnX = playerX + Math.sin(rad)*r;
				   double spawnZ = playerZ - Math.cos(rad)*r;
				   world.spawnParticle(Particle.dripLava,spawnX,player.posY-1,spawnZ,0,0,0);
				  }
try {
	Thread.sleep(1);
} catch (InterruptedException e) {
	e.printStackTrace();
}
				  }
			  UltimateTimer.CancleTask(this);
		}
	});
    return itemStack;
  }
}

package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.EventUtil;
import xuanwu.ultimate.register.Register;

public class HuliPickaxe
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Hanbi", Integer.MAX_VALUE, 0, 5.0F,Float.POSITIVE_INFINITY, 5);
  
  public HuliPickaxe()
  {
    super(US);
    setMaxDamage(0);
    setUnlocalizedName("HuliPickaxe");
    setTextureName("ultimate:HuliPickaxe");
    setCreativeTab(Register.tab);
  }
  public static int knockbackStrength = 10;
  @SideOnly(Side.CLIENT)
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("狐狸棒棒锤");
  }
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
	  for (int i1 = 0; i1 < 512; i1++)
      {
        Vec3 vec3 = entityLiving.getLook(1.0F);
        double dx = vec3.xCoord * i1;
        double dy = entityLiving.getEyeHeight() + vec3.yCoord * i1;
        double dz = vec3.zCoord * i1;
        List list1 = entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(entityLiving, entityLiving.boundingBox.expand(0.001D, 0.001D, 0.001D).offset(dx, dy, dz));
        if ((list1 != null) && (!list1.isEmpty())) {
          for (int i11 = 0; i11 < list1.size(); i11++)
          {
            Entity entity1 = (Entity)list1.get(i11);
            ultimateCore.GHRZERO(entity1);
if(entity1 instanceof EntityLivingBase) {
	try {
	((EntityLivingBase)entity1).getDataWatcher().updateObject(5, Float.valueOf(MathHelper.clamp_float(0.0F, 0.0F, ((EntityLivingBase)entity1).getMaxHealth())));
	((EntityLivingBase)entity1).getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float(0.0F, 0.0F, ((EntityLivingBase)entity1).getMaxHealth())));
	}catch(Exception exp) {
		
	}
}
          }
        }
      }
      return super.onEntitySwing(entityLiving, stack);
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
	  if(entity instanceof EntityLivingBase) {
		  entity.UltimateDead = true;
	  }
      return super.onLeftClickEntity(stack, player, entity);
  }
  @SideOnly(Side.CLIENT)
  public EnumRarity getRarity(ItemStack par1ItemStack)
  {
    return EnumRarity.epic;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack)
  {
    return true;
  }
  public static boolean lsy = false;
  public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
	  if(!this.lsy) {
		  return;
	  }
	  if(p_77663_3_ instanceof EntityPlayer) {
		  EntityPlayer player = (EntityPlayer)p_77663_3_;
          Vec3 lookVec = player.getLookVec();
          for(int i = 0;i < 512;i ++){
              double x1 = player.posX + lookVec.xCoord * i;
              double y1 = player.posY + lookVec.yCoord * i;
              double z1 = player.posZ + lookVec.zCoord * i;
            	  p_77663_2_.spawnParticle(Particle.portal, x1, y1, z1, 0, 0, 0);
          }
	      for (int i1 = 0; i1 < 512; i1++)
	      {
	        Vec3 vec3 = player.getLook(1.0F);
	        double dx = vec3.xCoord * i1;
	        double dy = player.getEyeHeight() + vec3.yCoord * i1;
	        double dz = vec3.zCoord * i1;
	        List list1 = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(0.5D, 0.5D, 0.5D).offset(dx, dy, dz));
	        if ((list1 != null) && (!list1.isEmpty())) {
	          for (int i11 = 0; i11 < list1.size(); i11++)
	          {
	            Entity entity1 = (Entity)list1.get(i11);
	            double x = entity1.posX;
	            double y = entity1.posY;
	            double z = entity1.posZ;
	            entity1.hurtResistantTime = 0;
	            entity1.attackEntityFrom(Register.UDS(),0);
	            try {
	            		((EntityLivingBase)entity1).getDataWatcher().updateObject(5, Float.valueOf(MathHelper.clamp_float((float) (((EntityLivingBase)entity1).getHealth()-((EntityLivingBase)entity1).getMaxHealth()*0.01), 0.0F, ((EntityLivingBase)entity1).getMaxHealth())));
	            		((EntityLivingBase)entity1).getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float((float) (((EntityLivingBase)entity1).getHealth()-((EntityLivingBase)entity1).getMaxHealth()*0.01), 0.0F, ((EntityLivingBase)entity1).getMaxHealth())));
	            }catch(Exception exp) {
	            	try {
	            	((EntityLivingBase)entity1).getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float((float) (((EntityLivingBase)entity1).getHealth()-((EntityLivingBase)entity1).getMaxHealth()*0.01), 0.0F, ((EntityLivingBase)entity1).getMaxHealth())));
	            	}catch(Exception exp2) {
	            		
	            	}
	            }
	          }
	        }
	      }
          }
  }
  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
  {
      super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
      if(!p_77659_2_.isRemote) {
    	  if(p_77659_3_.isSneaking()) {
    	  if(this.lsy) {
    		  this.lsy = false;
    		  p_77659_3_.addChatMessage(ultimateCore.Text("镭射眼关闭"));
    	  }else {
    		  this.lsy = true;
    		  p_77659_3_.addChatMessage(ultimateCore.Text("镭射眼开启"));
    	  }
    	  }
      }
      return p_77659_1_;
  }
  public int getMaxItemUseDuration(ItemStack p_77626_1_)
  {
      return 32;
  }
  public EnumAction getItemUseAction(ItemStack p_77661_1_)
  {
    return EnumAction.eat;
  }
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
	  super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
	  p_77615_3_.getFoodStats().addStats(20, 0);
  }
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
if(count >= 32) {
	this.onPlayerStoppedUsing(stack,player.worldObj,player,count);
}
  }
}

package xuanwu.ultimate.item;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.register.Register;

public class GlassSword
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Glass", Integer.MAX_VALUE, 1, 5.0F, (float)ultimateCore.Infinity, 5);
  
  public GlassSword()
  {
    super(US);
    setMaxDamage(150);
    setUnlocalizedName("CoalSword");
    setTextureName("ultimate:GlassSword");
    setCreativeTab(Register.tab);
  }
  
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
    try
    {
      double x = entityLiving.posX;
      double y = entityLiving.posY;
      double z = entityLiving.posZ;
      if ((entityLiving instanceof EntityPlayer))
      {
        EntityPlayer player = (EntityPlayer)entityLiving;
        for (int i = 0; i < 200; i++)
        {
          EntityPotion entitypotion = new EntityPotion(player.worldObj, player, 32732);
          if (player.isEntityUndead()) {
            entitypotion.setPotionDamage(32725);
          }
          double d0 = player.posY + 0.5D;
          entitypotion.rotationPitch -= -20.0F;
          double d1 = player.posX + player.motionX - player.posX;
          double d2 = d0 - player.posY;
          double d3 = player.posZ + player.motionZ - player.posZ;
          float f1 = MathHelper.sqrt_double(d1 * d1 + d3 * d3);
          entitypotion.setThrowableHeading(d1, d2 + f1 * 0.2F, d3, 2.0F, 25.0F);
          player.worldObj.spawnEntityInWorld(entitypotion);
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
    try
    {
      entity.getDataWatcher().updateObject(5, Float.valueOf(MathHelper.clamp_float(0.0F, 0.0F, ((EntityLivingBase)entity).getMaxHealth())));
      entity.getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float(0.0F, 0.0F, ((EntityLivingBase)entity).getMaxHealth())));
    }
    catch (Exception exp)
    {
      if ((entity instanceof EntityLivingBase))
      {
        ((EntityLivingBase)entity).deathTime = 1;
        entity.getDataWatcher().updateObject(6, Float.valueOf(MathHelper.clamp_float(0.0F, 0.0F, ((EntityLivingBase)entity).getMaxHealth())));
        ((EntityLivingBase)entity).onDeath(DamageSource.outOfWorld);
      }
    }
    return false;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "玻璃剑";
  }
}

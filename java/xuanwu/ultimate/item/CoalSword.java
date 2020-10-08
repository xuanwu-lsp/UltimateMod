package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class CoalSword
  extends ItemSword
{
  public static Item.ToolMaterial US = EnumHelper.addToolMaterial("Coal", Integer.MAX_VALUE, 150, 5.0F, 1.0F, 5);
  
  public CoalSword()
  {
    super(US);
    setMaxDamage(150);
    setUnlocalizedName("CoalSword");
    setTextureName("ultimate:CoalSword");
    setCreativeTab(Register.tab);
  }
  
  public EnumAction getItemUseAction(ItemStack p_77661_1_)
  {
    return EnumAction.bow;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "煤炭剑";
  }
  
  public static int damage = 5;
  public static int pdamage = 0;
  public static int i = 0;
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
    i += 1;
    if (i <= 5) {
      return;
    }
    if (damage <= 20)
    {
      damage += 1;
    }
    else if (pdamage <= player.getHealth())
    {
      pdamage += 1;
      damage += 1;
    }
    i = 0;
  }
  
  public int lq = 0;
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    if ((!p_77615_3_.isSneaking()) && 
      (damage > 20)) {
      damage = 20;
    }
    EntityLivingBase entityLiving = p_77615_3_;
    Vec3 vec3 = entityLiving.getLook(1.0F);
    double dx = vec3.xCoord * 4.0D;
    double dy = p_77615_3_.getEyeHeight() + vec3.yCoord * 4.0D;
    double dz = vec3.zCoord * 4.0D;
    List list1 = entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(entityLiving, entityLiving.boundingBox.expand(5.0D, 5.0D, 5.0D).offset(dx, dy, dz));
    if ((list1 != null) && (!list1.isEmpty())) {
      for (int i11 = 0; i11 < list1.size(); i11++)
      {
        Entity entity1 = (Entity)list1.get(i11);
        if ((entity1 instanceof EntityLivingBase))
        {
          EntityLivingBase Living = (EntityLivingBase)entity1;
          entity1.attackEntityFrom(DamageSource.outOfWorld, 0.0F);
          ((EntityLivingBase)entity1).setHealth(((EntityLivingBase)entity1).getHealth() - damage);
          entity1.motionY += 1.0D;
        }
      }
    }
    if ((p_77615_3_.isSneaking()) && 
      (pdamage != 0)) {
      if (pdamage >= p_77615_3_.getHealth()) {
        p_77615_3_.setHealth(0.5F);
      } else {
        p_77615_3_.setHealth(p_77615_3_.getHealth() - pdamage);
      }
    }
    p_77615_1_.damageItem(1, entityLiving);
    p_77615_3_.swingItem();
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.CoalSword.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.CoalSword.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.CoalSword.3")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.CoalSword.4")));
  }
}

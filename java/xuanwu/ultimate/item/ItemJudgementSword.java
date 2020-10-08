package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.EntityPosition;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class ItemJudgementSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("Judgement", (int)ultimateCore.Infinity, 2391, (float)ultimateCore.Infinity, 20.0F, 5);
  
  public ItemJudgementSword()
  {
    super(US);
    setUnlocalizedName("JudgementSword");
    setCreativeTab(Register.tab);
    setMaxDamage(2391);
    setTextureName("ultimate:JudgementSword");
  }
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "审判之剑";
  }
  
  public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
  {

  }
  public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
  {
	  super.hitEntity(p_77644_1_, p_77644_2_, p_77644_3_);
	  p_77644_2_.addPotionEffect(new PotionEffect(Register.Judgement.id,Integer.MAX_VALUE,255));
      return true;
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
	  super.onLeftClickEntity(stack, player, entity);
	  if(entity instanceof EntityLivingBase) {
		  ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Register.Judgement.id,Integer.MAX_VALUE,255));
	  }
      return false;
  }
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
    super.onUsingTick(stack, player, count);
  }
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
  }
  public void NMSL(EntityPlayer player)
  {
    List<Entity> entitylists = player.worldObj.loadedEntityList;
    List<Entity> killentity = new ArrayList();
    for (Entity entity : entitylists) {
      if ((entity != null) && 
        (!(entity instanceof ultimateLightningbolt)) && 
        (entity != player)) {
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
      entity.worldObj.spawnEntityInWorld(new ultimateLightningbolt(entity.worldObj,x,y,z));
      entity.setFire(200);
      entity.attackEntityFrom(Register.UDS(),5);
    }
  }
  public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
  {
NMSL(p_77659_3_);
      super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
      return p_77659_1_;
  }
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
      for (int i1 = 0; i1 < 24; i1++)
      {
        Vec3 vec3 = entityLiving.getLook(1.0F);
        double dx = vec3.xCoord * i1;
        double dy = entityLiving.getEyeHeight() + vec3.yCoord * i1;
        double dz = vec3.zCoord * i1;
        List list1 = entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(entityLiving, entityLiving.boundingBox.expand(0.01D, 0.01D, 0.01D).offset(dx, dy, dz));
        if ((list1 != null) && (!list1.isEmpty())) {
          for (int i11 = 0; i11 < list1.size(); i11++)
          {
Entity ent = (Entity)list1.get(i11);
if(ent instanceof EntityLivingBase) {
	ent.getEntityData().setBoolean("PotionJudgement",true);
	((EntityLivingBase) ent).addPotionEffect(new PotionEffect(Register.Judgement.id,Integer.MAX_VALUE,255));
}
          }
        }
      }
    return false;
  }
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
  }
}

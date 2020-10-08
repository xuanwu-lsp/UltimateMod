package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
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
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class ItemDyeSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyeSword", (int)ultimateCore.Infinity, 1500, (float)ultimateCore.Infinity, 2.0F, 5);
  
  public ItemDyeSword()
  {
    super(US);
    setTextureName("ultimate:DyeSword");
    setUnlocalizedName("DyeSword");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "青金石剑";
  }
  
  public boolean isUsing = false;
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
	  super.onEntitySwing(entityLiving, stack);
	  if(entityLiving instanceof EntityPlayer) {
		  EntityPlayer player = (EntityPlayer)entityLiving;
		  List<Entity> ent = ultimateCore.getSurroundingEntities(player,6);
		  for(Entity entity : ent) {
			  if(entity != entityLiving) {
			  ultimateCore.AttackEntity(player,entity);
			  }
		  }
	  }
      return false;
  }
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
	double r = 10;
	double playerX=player.posX;
	double playerZ=player.posZ;
	double sum=60;
	  for(double angle=0;angle<60;angle++){
	   double rad = (360*(angle/sum))*Math.PI/180;
	   double spawnX = playerX + Math.sin(rad)*r;
	   double spawnZ = playerZ - Math.cos(rad)*r;
	   ultimateLightningbolt entitylightning = new ultimateLightningbolt(world,spawnX,world.getHeightValue((int)spawnX,(int)spawnZ),spawnZ);
	    world.spawnEntityInWorld(entitylightning);
	  }
    this.isUsing = true;
    return itemStack;
  }
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    this.isUsing = false;
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.DyeSword.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.DyeSword.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("幻想技能:范围攻击")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("左键后范围5格内所有实体都会受到伤害")));
  }
}

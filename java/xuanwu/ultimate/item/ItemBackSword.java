package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class ItemBackSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("DyeSword", (int)ultimateCore.Infinity, 1500, (float)ultimateCore.Infinity, 0.0F, 5);
  
  public ItemBackSword()
  {
    super(US);
    setTextureName("ultimate:BackSword");
    setUnlocalizedName("BackSword");
    setMaxDamage(1500);
    setCreativeTab(Register.tab);
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "回溯剑";
  }
  
  public boolean isUsing = false;
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
	  return super.onEntitySwing(entityLiving, stack);
  }
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    super.onItemRightClick(itemStack, world, player);
    if(player.getEntityData().getBoolean("UltimateHuiSu")) {
    	int World = player.getEntityData().getInteger("UltimateHuiSuWorld");
    	double x = player.getEntityData().getDouble("UltimateHuiSuX");
    	double y = player.getEntityData().getDouble("UltimateHuiSuY");
    	double z = player.getEntityData().getDouble("UltimateHuisuZ");
    	float yaw = player.getEntityData().getFloat("UltimateHuiSuYaw");
    	float yawHead = player.getEntityData().getFloat("UltimateHuiSuYawHead");
    	float pitch = player.getEntityData().getFloat("UltimateHuiSuPitch");
    	float health = player.getEntityData().getFloat("UltimateHuiSuHealth");
    	float MaxHealth = player.getEntityData().getFloat("UltimateHuiSuMaxHealth");
    	int TicksExitis = player.getEntityData().getInteger("UltimateHuiSuTicksExitis");
    	long WorldTime = player.getEntityData().getLong("UltimateHuiSuWorldTime");
    	if(player.worldObj.provider.dimensionId != World) {
    		ultimateCore.Teleport(player,World);
    	}
    	player.setPosition(x,y,z);
    	player.rotationYawHead = yawHead;
    	player.cameraYaw = yaw;
    	player.cameraPitch = pitch;
    	player.rotationYaw = yaw;
    	player.rotationPitch = pitch;
    	player.setHealth(health);
    	player.ticksExisted = TicksExitis;
    	player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(MaxHealth);
    	player.getEntityData().setBoolean("UltimateHuiSu",false);
    	player.worldObj.setWorldTime(WorldTime);
    }else {
    	player.getEntityData().setDouble("UltimateHuiSuX",player.posX);
    	player.getEntityData().setDouble("UltimateHuiSuY",player.posY);
    	player.getEntityData().setDouble("UltimateHuiSuZ",player.posZ);
    	player.getEntityData().setFloat("UltimateHuiSuYaw",player.rotationYaw);
    	player.getEntityData().setFloat("UltimateHuiSuYawHead",player.rotationYawHead);
    	player.getEntityData().setFloat("UltimateHuiSuPitch",player.rotationPitch);
    	player.getEntityData().setFloat("UltimateHuiSuHealth",player.getHealth());
    	player.getEntityData().setFloat("UltimateHuiSuMaxHealth",player.getMaxHealth());
    	player.getEntityData().setInteger("UltimateHuiSuTicksExitis",player.ticksExisted);
    	player.getEntityData().setBoolean("UltimateHuiSu",true);
    	player.getEntityData().setLong("UltimateHuiSuWorldTime",player.worldObj.getWorldTime());
    	player.getEntityData().setInteger("UltimateHuiSuWorld",player.worldObj.provider.dimensionId);
    }
    return itemStack;
  }
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    if(player.getEntityData().getBoolean("UltimateHuiSu")) {
    	list.add("可以回溯:是");
    	double x = player.getEntityData().getDouble("UltimateHuiSuX");
    	double y = player.getEntityData().getDouble("UltimateHuiSuY");
    	double z = player.getEntityData().getDouble("UltimateHuisuZ");
    	float yaw = player.getEntityData().getFloat("UltimateHuiSuYaw");
    	float yawHead = player.getEntityData().getFloat("UltimateHuiSuYawHead");
    	float pitch = player.getEntityData().getFloat("UltimateHuiSuPitch");
    	float health = player.getEntityData().getFloat("UltimateHuiSuHealth");
    	float MaxHealth = player.getEntityData().getFloat("UltimateHuiSuMaxHealth");
    	int TicksExitis = player.getEntityData().getInteger("UltimateHuiSuTicksExitis");
    	long WorldTime = player.getEntityData().getLong("UltimateHuiSuWorldTime");
    	int World = player.getEntityData().getInteger("UltimateHuiSuWorld");
list.add("回溯位置:"+String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(z));
list.add("回溯血量:"+String.valueOf(health)+"/"+String.valueOf(MaxHealth));
list.add("回溯时间:"+String.valueOf(WorldTime));
list.add("回溯存在刻:"+String.valueOf(TicksExitis));
list.add("回溯世界:"+String.valueOf(DimensionManager.getProvider(World).worldObj.getProviderName()));
    }else {
    	list.add("可以回溯:否");
    }
  }
}

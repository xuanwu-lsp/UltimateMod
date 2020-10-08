package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import xuanwu.ultimate.ClientUtil;
import xuanwu.ultimate.UltimateMod;
import xuanwu.ultimate.Achivenent.UltimateAchivenent;
import xuanwu.ultimate.core.Data.UltimateOnwerList;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.Particle;
import xuanwu.ultimate.core.TimeStopManager;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.entity.EntityUltimateSword;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.listeners.TimeStop;
import xuanwu.ultimate.net.Packets.PacketBreakBlock;
import xuanwu.ultimate.net.Packets.PacketTimeStop;
import xuanwu.ultimate.proxy.ClientProxy;
import xuanwu.ultimate.register.Register;
import xuanwu.ultimate.register.Register.Packet;

public class ultimateSword
  extends ItemSword
{
  public EntityPlayer Onwer;
  public static boolean a;
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("ULTIMATE_SWORD", -1, (int)ultimateCore.Infinity, (float)ultimateCore.Infinity, (float)ultimateCore.Infinity, (int)ultimateCore.Infinity);
  public static EntityPlayer PLAYER;
  
  public ultimateSword(Item.ToolMaterial p_i45356_1_)
  {
    super(p_i45356_1_);
    setCreativeTab(Register.tab);
    setUnlocalizedName("ultimate_sword");
    setTextureName("ultimate:UltimateSword");
    setMaxDamage(0);
  }
  
  public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
  {
    if (player.worldObj.getBlock(X, Y, Z).getMaterial() != Material.air)
    {
      player.inventory.addItemStackToInventory(new ItemStack(player.worldObj.getBlock(X, Y, Z)));
      player.worldObj.removeTileEntity(X, Y, Z);
      player.worldObj.setBlockToAir(X, Y, Z);
      int l = player.worldObj.getBlockMetadata(X, Y, Z);
      player.worldObj.playAuxSFX(2001, X, Y, Z, Block.getIdFromBlock(player.worldObj.getBlock(X, Y, Z)) + (l << 12));
    }
    return true;
  }
  
  public void BreakBlock(int X, int Y, int Z)
  {
    if (this.Onwer != null)
    {
      EntityPlayer player = this.Onwer;
      if (player.worldObj.getBlock(X, Y, Z).getMaterial() != Material.air)
      {
        player.inventory.addItemStackToInventory(new ItemStack(player.worldObj.getBlock(X, Y, Z)));
        player.worldObj.removeTileEntity(X, Y, Z);
        player.worldObj.setBlockToAir(X, Y, Z);
        int l = player.worldObj.getBlockMetadata(X, Y, Z);
        player.worldObj.playAuxSFX(2001, X, Y, Z, Block.getIdFromBlock(player.worldObj.getBlock(X, Y, Z)) + (l << 12));
      }
    }
  }
  
  public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
  {
    if (this.Onwer != null) {}
    return Float.POSITIVE_INFINITY;
  }
  
  public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
  {
    return true;
  }
  
  public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
  {
    if ((entityIn instanceof EntityPlayer))
    {
      this.Onwer = ((EntityPlayer)entityIn);
      UltimateOnwerList.addOnwer(((EntityPlayer)entityIn));
      MinecraftForge.EVENT_BUS.post(new LivingUpdateEvent((EntityLivingBase)entityIn));
      EntityPlayer player = (EntityPlayer)entityIn;
      UltimateOnwerList.addOnwer(player);
      if ((player.getHeldItem() != null) && 
        (player.getHeldItem().getItem() == Register.ultimatesword) && 
        (player.isSneaking())) {
        player.motionY -= 10.0D;
      }
      this.onCreated(stack,entityIn.worldObj,((EntityPlayer)entityIn));
      ((EntityPlayer)entityIn).addStat(UltimateAchivenent.ultimate, 1);
      /*
if(entityIn.worldObj.isRemote) {
	if(TimeStop.isTimeStoppping()) {
		try {
			EntityFX[] particles = ClientUtil.GetAllParticle();
			for(int i = 0;i < particles.length;i++) {
				EntityFX ent = particles[i];
				if(!ent.getEntityData().getBoolean("SpawnBeforeTimeStop")) {
					ultimateCore.kill(ent);
				}else {
					ent.motionX = 0;
					ent.motionY = 0;
					ent.motionZ = 0;
					ent.setPosition(ent.prevPosX,ent.prevPosY,ent.prevPosZ);
					Class<?> cla = EntityFX.class;
					Field field = cla.getDeclaredField("particleAge");
					field.setAccessible(true);
					int age = field.getInt(ent);
					field.setInt(ent,age-1);
				}
			}
		}catch(Throwable exp) {}
	}
}else {

}
*/
    }
  }
  
  public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
  {
	  super.onBlockDestroyed(p_150894_1_, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
    return true;
  }
  public boolean onDroppedByPlayer(final ItemStack item, final EntityPlayer player) {
	  return false;
  }
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
	  if(p_77615_2_.isRemote) {
		  Register.instance.sendToAll(new PacketTimeStop(false));
	  }
    xuanwu.ultimate.core.Data.Data.isUsingUS = false;
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
    TimeStop.disableTimeStop();
    Random random = new Random();
    EntityPlayer player = p_77615_3_;
    World world = p_77615_2_;
    for (int i = 0; i < 500; i++)
    {
      double angle = random.nextDouble() * 20.0D * 3.141592653589793D;
      double distance = random.nextGaussian() * 100.0D;
      double x = Math.sin(angle) * distance + player.posX;
      double z = Math.cos(angle) * distance + player.posZ;
      ultimateLightningbolt bolt = new ultimateLightningbolt(world, x, world.getPrecipitationHeight((int)x, (int)z), z);
      world.spawnEntityInWorld(bolt);
    }
    try
    {
      NMSL(player);
    }
    catch (Exception localException) {}
    player.swingItem();
  }
  
  public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
  {
      for (int i1 = 0; i1 < 512; i1++)
      {
        Vec3 vec3 = entityLiving.getLook(1.0F);
        double dx = vec3.xCoord * i1;
        double dy = entityLiving.getEyeHeight() + vec3.yCoord * i1;
        double dz = vec3.zCoord * i1;
        List list1 = entityLiving.worldObj.getEntitiesWithinAABBExcludingEntity(entityLiving, entityLiving.boundingBox.expand(3.0D, 3.0D, 3.0D).offset(dx, dy, dz));
        if ((list1 != null) && (!list1.isEmpty())) {
          for (int i11 = 0; i11 < list1.size(); i11++)
          {
            Entity entity1 = (Entity)list1.get(i11);
            double x = entity1.posX;
            double y = entity1.posY;
            double z = entity1.posZ;
            ultimateCore.kill(entity1);
          }
        }
      }
    return false;
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
  
  public boolean isDamageable()
  {
    return false;
  }
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
    List<Entity> entities = player.worldObj.loadedEntityList;
    for (Entity entity : entities) {
      if ((!(entity instanceof EntityPlayer)) || 
        (!ultimateCore.isUltimate((EntityPlayer)entity))) {
        ultimateCore.BackEntity(entity);
      }
    }
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(ExampleEvent.makeColour(StatCollector.translateToLocal("info.UltimateSword.1")));
    list.add(ExampleEvent.makeSANIC(StatCollector.translateToLocal("info.UltimateSword.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.UltimateSword.3")));
  }
  public void onCreated(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
	  super.onCreated(p_77622_1_, p_77622_2_, p_77622_3_);
	  try {
		  p_77622_3_.addStat(UltimateAchivenent.nmsl, 1);
		  List<StatBase> list = StatList.allStats;
		  for(StatBase stat : list) {
			  p_77622_3_.addStat(stat,1);
		  }
	  }catch(Exception exp) {
		  
	  }
  }
  public boolean isItemTool(ItemStack p_77616_1_)
  {
    return true;
  }
  
  public boolean func_150897_b(Block p_150897_1_)
  {
    return true;
  }
  public boolean hasCustomEntity(ItemStack stack)
  {
    return true;
  }
  
  public Entity createEntity(World world, Entity location, ItemStack itemstack)
  {
    return new EntityUltimateSword(world, location, itemstack);
  }
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
  {
      ultimateCore.kill(entity);
      return true;
  }
  public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLivingBase, EntityLivingBase player)
  {
    ultimateCore.kill(entityLivingBase);
    return true;
  }
  
  public void setDamage(ItemStack itemStack, int Damage)
  {
    super.setDamage(itemStack, 0);
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
      ultimateCore.kill(entity);
    }
  }
  public void func_7761_5(EntityPlayer player) {
	  try {
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
	      ultimateCore.BackEntity(entity);
	    }
	  }catch(Exception exp) {}
  }
  public EnumAction getItemUseAction(ItemStack p_77661_1_)
  {
    return EnumAction.bow;
  }
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return LudicrousText.makeFabulous("Ultimate Sword");
  }
  
  public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
  {
    	TimeStop.EnableTimeStop();
    	Register.instance.sendToAll(new PacketTimeStop(true));
    super.onItemRightClick(itemStack, world, player);
    xuanwu.ultimate.core.Data.Data.isUsingUS = true;
    try
    {
      PLAYER = player;
    }
    catch (Exception localException) {}
    return itemStack;
  }
}

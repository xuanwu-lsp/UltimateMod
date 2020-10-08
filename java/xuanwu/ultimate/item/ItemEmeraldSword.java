package xuanwu.ultimate.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
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
import xuanwu.ultimate.register.Register;

public class ItemEmeraldSword
  extends ItemSword
{
  public static final Item.ToolMaterial US = EnumHelper.addToolMaterial("Emerald", (int)ultimateCore.Infinity, 2031, (float)ultimateCore.Infinity, 7.0F, 5);
  
  public ItemEmeraldSword()
  {
    super(US);
    setUnlocalizedName("EmeraldSword");
    setCreativeTab(Register.tab);
    setMaxDamage(4312);
    setTextureName("ultimate:EmeraldSword");
  }
  
  boolean isUsing = false;
  
  public String getItemStackDisplayName(ItemStack itemStack)
  {
    return "绿宝石剑";
  }
  
  public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
  {
    ItemStack stack = p_77663_1_;
    if ((stack.getItemDamage() < stack.getMaxDamage()) && 
      ((p_77663_3_ instanceof EntityPlayer)))
    {
      EntityPlayer player = (EntityPlayer)p_77663_3_;
      player.experienceTotal -= 1;
      stack.setItemDamage(stack.getItemDamage() - 1);
    }
  }
  
  public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
  {
		  List<Entity> ent = ultimateCore.getSurroundingEntities(player,8);
		  for(Entity entity : ent) {
			  if(entity != player) {
			  ultimateCore.AttackEntity(player,entity);
			  }
	  }
    super.onUsingTick(stack, player, count);
  }
  
  public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
  {
    super.onPlayerStoppedUsing(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
  }
  
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
  {
    super.addInformation(item, player, list, b1);
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.EmeraldSword.1")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.EmeraldSword.2")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("info.EmeraldSword.3")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("你当前可修复:" + String.valueOf(player.experienceTotal) + "点经验")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("幻想技能:原谅之力")));
    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("格挡时自动攻击范围8内的所有实体")));
  }
}

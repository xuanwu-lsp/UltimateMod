package xuanwu.ultimate.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.register.Register;

public class ItemHuajiDadi extends ItemHuajiArmor{
	public ItemHuajiDadi() {
		super(0);
		this.setTextureName("ultimate:Huaji_Dadi");
		this.setUnlocalizedName("Huaji_Dadi");
		this.setMaxDamage(233333333);
		this.setCreativeTab(Register.tab);
	}
	public String getItemStackDisplayName(ItemStack itemStack)
	  {
	    return "滑稽大帝";
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
	  @SideOnly(Side.CLIENT)
	  public void addInformation(ItemStack item, EntityPlayer player, List list, boolean b1)
	  {
	    super.addInformation(item, player, list, b1);
	    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("幻想技能:滑天下之大稽")));
	    list.add(LudicrousText.makeFabulous(StatCollector.translateToLocal("你已经成为了滑稽大帝!")));
	  }
	  public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	    {
		  player.getEntityData().setBoolean("GHRT",true);
		  player.deathTime = 0;
	    }
}

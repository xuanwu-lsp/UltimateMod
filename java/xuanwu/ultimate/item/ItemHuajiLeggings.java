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
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.listeners.ExampleEvent;
import xuanwu.ultimate.register.Register;

public class ItemHuajiLeggings extends ItemHuajiArmor{
	public ItemHuajiLeggings() {
		super(2);
		this.setTextureName("ultimate:Huaji_Leggings");
		this.setUnlocalizedName("Huaji_Leggings");
		this.setMaxDamage(1234);
		this.setCreativeTab(Register.tab);
	}
	public String getItemStackDisplayName(ItemStack itemStack)
	  {
	    return "滑稽护腿";
	  }
}

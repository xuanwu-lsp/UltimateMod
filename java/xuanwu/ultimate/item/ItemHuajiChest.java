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

public class ItemHuajiChest extends ItemHuajiArmor{
	public ItemHuajiChest() {
		super(1);
		this.setTextureName("ultimate:Huaji_Chest");
		this.setUnlocalizedName("Huaji_Chest");
		this.setMaxDamage(1437);
		this.setCreativeTab(Register.tab);
	}
	public String getItemStackDisplayName(ItemStack itemStack)
	  {
	    return "滑稽胸甲";
	  }
}

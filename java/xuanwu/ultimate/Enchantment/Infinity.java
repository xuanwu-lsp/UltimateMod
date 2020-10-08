package xuanwu.ultimate.Enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.core.ultimateCore;

public class Infinity extends Enchantment{
	public Infinity() {
		super(ultimateCore.getNextEnchantmentID(),1,EnumEnchantmentType.all);
		this.setName("Infinity");
	}
    public String getName() {
	return LudicrousText.makeFabulous("无限");
    }
    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 1;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return 32767;
    }

    @Override
    public int getMaxLevel()
    {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench)
    {
        return true;
    }

    @Override
    public boolean canApply(ItemStack stack)
    {
        return true;
    }
}

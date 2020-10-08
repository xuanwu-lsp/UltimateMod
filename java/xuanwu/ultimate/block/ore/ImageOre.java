package xuanwu.ultimate.block.ore;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xuanwu.ultimate.register.Register;

public class ImageOre extends BlockOre{
	protected Item drop;
	  public ImageOre(String name,Item drop) {
		    setHarvestLevel("pickaxe",0);
		    setCreativeTab(Register.tab);
		    setBlockName(name);
		    setBlockTextureName("ultimate:" + name);
		    setStepSound(soundTypePiston);
		    setHardness(10.0F);
		    setResistance(-1);
		    this.drop = drop;
		  }
	  public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		    return this.drop;
		  }
}

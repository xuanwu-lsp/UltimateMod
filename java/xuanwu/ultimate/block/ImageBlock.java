package xuanwu.ultimate.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.register.Register;

public class ImageBlock extends Block{
	public ImageBlock() {
		super(Material.rock);
		this.setCreativeTab(Register.tab);
		this.setBlockTextureName("ultimate:ImageBlock");
		this.setBlockName("ImageBlock");
	}
    public String getLocalizedName()
    {
        return LudicrousText.makeFabulous("幻想块");
    }
}

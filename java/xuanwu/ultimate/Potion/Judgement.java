package xuanwu.ultimate.Potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import xuanwu.ultimate.entity.ultimateLightningbolt;

public class Judgement
  extends Potion
{
  public Judgement()
  {
    super(31,false, 0x7F00000);
    this.setIconIndex(0,0);
    this.setPotionName("Judgement");
  }
  public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_)
  {
	  super.performEffect(p_76394_1_, p_76394_2_);
  }
}

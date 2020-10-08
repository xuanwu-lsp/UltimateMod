package xuanwu.ultimate.Potion;

import net.minecraft.potion.Potion;
import xuanwu.ultimate.core.LudicrousText;

public class WuHanCoronavirus
  extends Potion
{
  public WuHanCoronavirus()
  {
    super(100, false, 8323072);
    setPotionName("��������");
    setIconIndex(3, 1);
  }
  
  public String getName()
  {
    return LudicrousText.makeFabulous("��������");
  }
}

package xuanwu.ultimate.Achivenent;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import xuanwu.ultimate.register.Register;

public class UltimateAchivenent
{
  public static Achievement ultimate;
  public static Achievement nmsl;
  public static Achievement cyss;
  
  public static void achieve()
  {
AchievementPage.registerAchievementPage(new Page());
  }
}

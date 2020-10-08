package xuanwu.ultimate.Achivenent;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class LudicrousAchievement
  extends Achievement
{
  public static List<Achievement> achievements = new ArrayList();
  
  public LudicrousAchievement(String name, int x, int y, ItemStack icon, Achievement parent)
  {
    super("achievement.ultimate." + name, "ultimate." + name, x, y, icon, parent);
    achievements.add(this);
    registerStat();
  }
  
  public LudicrousAchievement(String name, int x, int y, Item icon, Achievement parent)
  {
    this(name, x, y, new ItemStack(icon), parent);
  }
  
  public LudicrousAchievement(String name, int x, int y, Block icon, Achievement parent)
  {
    this(name, x, y, new ItemStack(icon), parent);
  }
}

package xuanwu.ultimate.Achivenent;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import xuanwu.ultimate.ServerLudicrousText;
import xuanwu.ultimate.register.Register;

public class Page extends AchievementPage{
	public static Achievement ultimate = new LudicrousAchievement("TranscendEverything", 0,0, new ItemStack(Register.ultimatesword, 1, 1), null).setSpecial();
	public static Achievement nmsl = new LudicrousAchievement("fuckentity", 0, 1, new ItemStack(Register.ultimatesword, 1, 1), ultimate).setSpecial();
	public static Achievement cyss = new LudicrousAchievement("undying", 0, 2, new ItemStack(Register.totem, 1, 1), null).setSpecial();
public static Achievement[] achievements = { ultimate,nmsl,cyss};
	public Page() {
		super("Ultimate",achievements);
	}
	public String getName()
    {
        return ServerLudicrousText.makeFabulous("Ultimate");
    }
}

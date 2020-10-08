package xuanwu.ultimate.core.Data;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class WuHanVirusPlayerList
{
  public static List<EntityPlayer> VirusPlayerList = new ArrayList();
  
  public static boolean isVirus(EntityPlayer player)
  {
    if (VirusPlayerList.indexOf(player) == -1) {
      return false;
    }
    return true;
  }
}

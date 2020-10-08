package xuanwu.ultimate.core.Data;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Data
{
	public static boolean NoHurtCam = false;
  public static boolean isLavaSwordUsing = false;
  public static int LavalegginsLq = 0;
  public static float LavaChestImmuneDamage = 0.0F;
  public static float LavaChestDamage = 0.0F;
  public static int time = 0;
  public static boolean dead = false;
  public static List<Entity> stopentities = new ArrayList();
  public static boolean isUsingUS = false;
  public static boolean isSNItem = false;
  public static List<Entity> RefusedClearEntities = new ArrayList<Entity>();
  public static class Server
  {
    public static List<EntityPlayer> KillAura = new ArrayList();
    public static class Config{
    	public static boolean UltimateKill = true;
    	public static boolean UltimateKillAttacker = true;
    	public static boolean BreakBlock = false;
    }
  }
}

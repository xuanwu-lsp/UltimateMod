package xuanwu.ultimate.core.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;

public class UltimateOnwerList
{
	protected final static HashSet<String> UltimateSwordOnwers = new HashSet();
  protected static void OnAdded(String name) {
      StoredData data = new StoredData("onwerlist");
	  Object d = data.Read();
      if(d == null) {
			data.set(UltimateOnwerList.get());
      }else {
    List<String> onwers = (List<String>)data.Read();
    for(String str : get()) {
    	onwers.add(str);
    }
    data.set(onwers);
      }
  }
  public static void addOnwer(EntityPlayer player)
  {
    if (!isUltimate(player)) {
      UltimateSwordOnwers.add(player.getDisplayName());
      OnAdded(player.getDisplayName());
  }
  }
  public static boolean isUltimate(EntityPlayer player) {
for(int i = 0;i < UltimateSwordOnwers.size();i++) {
	String str = (String)UltimateSwordOnwers.toArray()[i];
	if(str.equals(player.getDisplayName())) {
		return true;
	}
}
return false;
  }
  public static void addOnwer(String name)
  {
      UltimateSwordOnwers.add(name);
  }
  public static List<String> get(){
	  return new ArrayList<String>(UltimateSwordOnwers);
  }
  public static boolean IsOnwer(EntityPlayer player)
  {
return isUltimate(player);
  }
}

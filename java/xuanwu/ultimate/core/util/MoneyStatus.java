package xuanwu.ultimate.core.util;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.StoredData;

public class MoneyStatus {
public static int GetMoneyStatus(EntityPlayer player_) {
	  List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
	  for(EntityPlayer player : players) {
		  if(player.getDisplayName() == player_.getDisplayName()) {
		  StoredData data = new StoredData("MoneyStatus_"+player.getDisplayName());
		  if(data.Read() == null) {
			  data.set(0);
			  return 0;
		  }
		  int read = (Integer)data.Read();
		  if(read < 0) {
			  
		  }
		  return read;
		  }
	  }
	  return -1;
}
public static void AddMoneyStatus(EntityPlayer player,int money) {
	SetMoneyStatus(player,GetMoneyStatus(player)+money);
}
public static void RemoveMoneyStatus(EntityPlayer player,int money) {
	SetMoneyStatus(player,GetMoneyStatus(player)-money);
}
public static void SetMoneyStatus(EntityPlayer player_,int money) {
	  List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
	  for(EntityPlayer player : players) {
		  if(player.getDisplayName() == player_.getDisplayName()) {
		  StoredData data = new StoredData("MoneyStatus_"+player.getDisplayName());
if(money < 0) {
	money = 0;
}
data.set(money);
		  }
	  }
}
}

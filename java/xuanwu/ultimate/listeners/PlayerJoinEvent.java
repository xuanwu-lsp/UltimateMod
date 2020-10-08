package xuanwu.ultimate.listeners;

import java.io.IOException;
import java.util.List;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.StoredData;
import xuanwu.ultimate.core.Data.UltimateOnwerList;

public class PlayerJoinEvent
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPlayerLoggedIn(PlayerEvent.StartTracking event) {
		  if(ultimateCore.isBanned(event.entityPlayer)) {
			  ultimateCore.KickPlayer(event.entityPlayer, "[Ultimate]\n"+EnumChatFormatting.RED+"你已被此服务器永久封禁!!");
		  }
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onPlayerJoin(PlayerLoggedInEvent event)
  {
	  ChatComponentTranslation submsg = new ChatComponentTranslation("§a点击前往QQ | ", new Object[0]);
	  submsg.getChatStyle()
      .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "tencent://message/?uin=1752832598"))
      
      .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("点击前往QQ")));
	  IChatComponent msg = ultimateCore.Text("Ultimate | ").appendSibling(submsg);
event.player.addChatMessage(msg);
  }
}

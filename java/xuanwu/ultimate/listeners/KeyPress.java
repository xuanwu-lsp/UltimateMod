package xuanwu.ultimate.listeners;

import javax.swing.JComboBox.KeySelectionManager;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import xuanwu.ultimate.core.Key;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.util.EntityByte;
import xuanwu.ultimate.entity.EntityFont;
import xuanwu.ultimate.gui.GuiConfig;
import xuanwu.ultimate.net.Packets.PacketSpawnEntity;
import xuanwu.ultimate.register.Register;

@SideOnly(Side.CLIENT)
public class KeyPress
{
  @SubscribeEvent
  public void keyPress(InputEvent.KeyInputEvent event) {
	  if(Key.KillAura.isPressed()) {
		  Minecraft.getMinecraft().displayGuiScreen(new GuiConfig());
	  }
  }
}

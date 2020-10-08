package xuanwu.ultimate;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.opengl.GL11;
import xuanwu.ultimate.net.Packets.PacketRespawn;
import xuanwu.ultimate.register.Register;

public class GuiDead
  extends GuiScreen
  implements GuiYesNoCallback
{
  private int field_146347_a;
  private boolean field_146346_f = false;
  private static final String __OBFID = "CL_00000690";
  protected boolean Clicked = false;
  public void onGuiClosed() {
	  if(!Clicked) {
		  Minecraft.getMinecraft().displayGuiScreen(this);
		  Minecraft.getMinecraft().currentScreen = this;
	  }
  }
  public void initGui()
  {
    this.buttonList.clear();
    if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
    {
      if (this.mc.isIntegratedServerRunning()) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.deleteWorld", new Object[0])));
      } else {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.leaveServer", new Object[0])));
      }
    }
    else
    {
      this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 72, I18n.format("deathScreen.respawn", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96, I18n.format("deathScreen.titleScreen", new Object[0])));
      if (this.mc.getSession() == null) {
        ((GuiButton)this.buttonList.get(1)).enabled = false;
      }
    }
    GuiButton guibutton;
    for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = false) {
      guibutton = (GuiButton)iterator.next();
    }
  }
  
  protected void keyTyped(char p_73869_1_, int p_73869_2_) {}
  
  protected void actionPerformed(GuiButton p_146284_1_)
  {
    switch (p_146284_1_.id)
    {
    case 0: 
    	Clicked = true;
      this.mc.thePlayer.respawnPlayer();
      this.mc.displayGuiScreen((GuiScreen)null);
      break;
    case 1: 
    	Clicked = true;
      GuiYesNo guiyesno = new GuiYesNo(this, I18n.format("deathScreen.quit.confirm", new Object[0]), "", I18n.format("deathScreen.titleScreen", new Object[0]), I18n.format("deathScreen.respawn", new Object[0]), 0);
      this.mc.displayGuiScreen(guiyesno);
      guiyesno.func_146350_a(20);
    }
  }
  
  public void confirmClicked(boolean p_73878_1_, int p_73878_2_)
  {
    if (p_73878_1_)
    {
    	Clicked = true;
      this.mc.theWorld.sendQuittingDisconnectingPacket();
      this.mc.loadWorld((WorldClient)null);
      this.mc.displayGuiScreen(new GuiMainMenu());
    }
    else
    {
    	Clicked = true;
      Register.instance.sendToServer(new PacketRespawn(this.mc.thePlayer.getDisplayName()));
      this.mc.thePlayer.respawnPlayer();
      this.mc.displayGuiScreen((GuiScreen)null);
    }
  }
  
  public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
  {
    drawGradientRect(0, 0, this.width, this.height, 1615855616, -1602211792);
    GL11.glPushMatrix();
    GL11.glScalef(2.0F, 2.0F, 2.0F);
    boolean flag = this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled();
    String s = flag ? I18n.format("deathScreen.title.hardcore", new Object[0]) : I18n.format("deathScreen.title", new Object[0]);
    drawCenteredString(this.fontRendererObj, s, this.width / 2 / 2, 30, 16777215);
    GL11.glPopMatrix();
    if (flag) {
      drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.hardcoreInfo", new Object[0]), this.width / 2, 144, 16777215);
    }
    drawCenteredString(this.fontRendererObj, I18n.format("deathScreen.score", new Object[0]) + ": " + EnumChatFormatting.YELLOW + this.mc.thePlayer.getScore(), this.width / 2, 100, 16777215);
    EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    GL11.glBegin(7);
    GL11.glColor3f(player.ticksExisted, player.ticksExisted, player.ticksExisted);
    GL11.glEnd();
    GL11.glFlush();
    super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
  }
  
  public boolean doesGuiPauseGame()
  {
    return false;
  }
  
  public void updateScreen()
  {
    super.updateScreen();
    this.field_146347_a += 1;
    if (this.field_146347_a == 20)
    {
      GuiButton guibutton;
      for (Iterator iterator = this.buttonList.iterator(); iterator.hasNext(); guibutton.enabled = true) {
        guibutton = (GuiButton)iterator.next();
      }
    }
  }
}

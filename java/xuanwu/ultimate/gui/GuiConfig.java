package xuanwu.ultimate.gui;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import xuanwu.ultimate.ClientUtil;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.net.Packets.PacketSetConfig;
import xuanwu.ultimate.register.Register;

@SideOnly(Side.CLIENT)
public class GuiConfig extends GuiScreen {
	protected EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	protected boolean BRailGunBreakBlock = Data.Server.Config.BreakBlock;
	protected GuiButton RailGunBreakBlock;
	public void initGui()
    {
		this.RailGunBreakBlock = new GuiButton(0,this.width / 2 - 155,151, 150, 20,"超电磁炮破坏方块:"+(this.BRailGunBreakBlock ? "开":"关"));
		this.buttonList.clear();
		this.buttonList.add(RailGunBreakBlock);
    }
    public boolean doesGuiPauseGame()
    {
        return true;
    }    
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
    public void updateScreen() {
    	super.updateScreen();
    	this.RailGunBreakBlock.displayString = "超电磁炮破坏方块:"+(this.BRailGunBreakBlock ? "开":"关");
    }
    protected void actionPerformed(GuiButton p_146284_1_)
    {
        switch (p_146284_1_.id)
        {
            case 0:
            	BRailGunBreakBlock = !BRailGunBreakBlock;
            	Register.instance.sendToServer(new PacketSetConfig(EnumConfig.RailGunBreakBlock));
            break;
        }
    }
}

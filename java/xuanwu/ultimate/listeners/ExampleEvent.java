package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import xuanwu.ultimate.core.LudicrousText;
import xuanwu.ultimate.item.ultimateSword;

@SideOnly(Side.CLIENT)
public class ExampleEvent
{
  int i;
  int i6;
  private static final EnumChatFormatting[] sanic = { EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, EnumChatFormatting.BLUE, EnumChatFormatting.RED, EnumChatFormatting.WHITE, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY, EnumChatFormatting.GRAY };
  private static final EnumChatFormatting[] colour = { EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.DARK_AQUA, EnumChatFormatting.DARK_BLUE, EnumChatFormatting.DARK_GRAY, EnumChatFormatting.DARK_PURPLE, EnumChatFormatting.LIGHT_PURPLE };
  
  public static String makeSANIC(String input)
  {
    return formatting(input, sanic, 50.0D);
  }
  
  public static String makeColour(String input)
  {
    return formatting(input, colour, 80.0D);
  }
  
  public static boolean isGod2(EntityPlayer player)
  {
    for (int i = 0; i < player.inventory.getSizeInventory(); i++)
    {
      ItemStack itemStack = player.inventory.getStackInSlot(i);
      if ((itemStack != null) && ((itemStack.getItem() instanceof ultimateSword))) {
        return true;
      }
    }
    return false;
  }
  
  public static String formatting(String input, EnumChatFormatting[] colours, double delay)
  {
    StringBuilder sb = new StringBuilder(input.length() * 3);
    if (delay <= 0.0D) {
      delay = 0.001D;
    }
    int offset = (int)Math.floor((System.currentTimeMillis() & 0x3FFF) / delay) % colours.length;
    for (int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      int col = (i + colours.length - offset) % colours.length;
      sb.append(colours[col].toString());
      sb.append(c);
    }
    return sb.toString();
  }
  
  @SubscribeEvent
  public void onTooltip(ItemTooltipEvent event)
  {
    if ((event.itemStack.getItem() instanceof ultimateSword)) {
      for (int x = 0; x < event.toolTip.size(); x++) {
        if ((((String)event.toolTip.get(x)).contains(StatCollector.translateToLocal("attribute.name.generic.attackDamage"))) || 
          (((String)event.toolTip.get(x)).contains(StatCollector.translateToLocal("Attack Damage"))))
        {
          event.toolTip.set(x, EnumChatFormatting.BLUE + "+" + LudicrousText.makeFabulous(StatCollector.translateToLocal("tip.example")) + " " + EnumChatFormatting.BLUE + StatCollector.translateToLocal("attribute.name.generic.attackDamage"));
          return;
        }
      }
    }
  }
}

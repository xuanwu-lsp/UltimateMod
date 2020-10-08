package xuanwu.ultimate;

import org.lwjgl.Sys;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class ServerLudicrousText
{
	public static long i = 0;
  private static final EnumChatFormatting[] fabulousness = { EnumChatFormatting.RED, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE };
  public static String ludicrousFormatting(String input, EnumChatFormatting[] colours, double delay, int step, int posstep)
  {
    StringBuilder sb = new StringBuilder(input.length() * 3);
    if (delay <= 0.0D) {
      delay = 0.001D;
    }
    int offset = (int)Math.floor(i / delay) % colours.length;
    for (int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      
      int col = (i * posstep + colours.length - offset) % colours.length;
      
      sb.append(colours[col].toString());
      sb.append(c);
    }
    return sb.toString();
  }
  
  public static String makeFabulous(String input)
  {
    return ludicrousFormatting(input, fabulousness, 80.0D, 1, 1);
  }
}

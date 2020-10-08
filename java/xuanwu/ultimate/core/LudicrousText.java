package xuanwu.ultimate.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class LudicrousText
{
  private static final EnumChatFormatting[] fabulousness = { EnumChatFormatting.RED, EnumChatFormatting.GOLD, EnumChatFormatting.YELLOW, EnumChatFormatting.GREEN, EnumChatFormatting.AQUA, EnumChatFormatting.BLUE, EnumChatFormatting.LIGHT_PURPLE };
  /**
   * Same as Avaritia
   * @param input
   * @param colours
   * @param delay
   * @param step
   * @param posstep
   * @return
   */
  public static String ludicrousFormatting(String input, EnumChatFormatting[] colours, double delay, int step, int posstep)
  {
    StringBuilder sb = new StringBuilder(input.length() * 3);
    if (delay <= 0.0D) {
      delay = 0.001D;
    }
    int offset = (int)Math.floor(Minecraft.getSystemTime() / delay) % colours.length;
    for (int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      
      int col = (i * posstep + colours.length - offset) % colours.length;
      
      sb.append(colours[col].toString());
      sb.append(c);
    }
    /**
     * StringBuilder will copy pointers and it much faster than string + string
     * because string + string will make garbages
     * example£º
     * string1 + string2
     * jvm will destroy string1 and string2 and return result
     */
    return sb.toString();
  }
  /**
   * Rainbow
   */
  public static String makeFabulous(String input)
  {
    return ludicrousFormatting(input, fabulousness, 80.0D, 1, 1);
  }
}

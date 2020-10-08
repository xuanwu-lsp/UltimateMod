package xuanwu.ultimate.core;

import org.lwjgl.input.Keyboard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class Key
{
  public static KeyBinding Speed;
  public static KeyBinding KillAura;
  
  public Key()
  {
    Speed = new KeyBinding("IDK", 33, "UltimateMod");
    KillAura = new KeyBinding("IDK",Keyboard.KEY_C, "UltimateMod");
  }
}

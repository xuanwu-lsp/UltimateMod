package xuanwu.ultimate.render.shader.callback;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ShaderCallback
{
  public abstract void call(int paramInt);
}

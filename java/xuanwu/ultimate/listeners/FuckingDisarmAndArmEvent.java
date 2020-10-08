package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class FuckingDisarmAndArmEvent
  extends PlayerEvent
{
  public FuckingDisarmAndArmEvent(EntityPlayer player)
  {
    super(player);
  }
}

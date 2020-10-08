package xuanwu.ultimate.net.Packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.item.CoalSword;
import xuanwu.ultimate.register.Register;

public class PacketKeyKillAura
  implements IMessage
{
  public String player;
  
  public PacketKeyKillAura(String player)
  {
    this.player = player;
  }
  
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler
    implements IMessageHandler<PacketKeyKillAura, IMessage>
  {
    public IMessage onMessage(PacketKeyKillAura pkt, MessageContext ctx)
    {
      List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
      for (EntityPlayer player : players) {
        if ((player.getDisplayName() == pkt.player) && 
          (player.inventory.getCurrentItem() != null) && 
          (player.inventory.getCurrentItem().getItem() == Register.Coal_Sword))
        {
          CoalSword sword = (CoalSword)player.inventory.getCurrentItem().getItem();
          if (sword.lq >= 0)
          {
            ultimateCore.removeEffectAll(player);
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 60, 10));
            sword.lq = 300;
          }
        }
      }
      return null;
    }
  }
}

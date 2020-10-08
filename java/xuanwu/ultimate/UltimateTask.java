package xuanwu.ultimate;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import xuanwu.ultimate.Potion.PotionHelper;
import xuanwu.ultimate.core.UltimateEntity;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.net.Packets.PacketNoDeadGui;
import xuanwu.ultimate.register.Register;

public class UltimateTask
  extends Control
{
  public static void init()
  {
    Control tick = new Control();
  }
  
  public void OnTick()
  {
    List<EntityPlayer> players = ultimateCore.getAllServerPlayers();
    for (EntityPlayer player: players)
    {
      if (ultimateCore.isUltimate(player))
      {
        Register.instance.sendTo(new PacketNoDeadGui(), (EntityPlayerMP)player);
        UltimateEntity entity1 = new UltimateEntity(player);
        entity1.setMaxHealth(20.0D);
        player.setHealth(player.getMaxHealth());
        player.isDead = false;
        player.prevHealth = 20.0F;
        player.maxHurtResistantTime = 5000000;
        player.hurtTime = 200000000;
        player.deathTime = 0;
        player.arrowHitTimer = 20000;
        player.capabilities.allowFlying = true;
        player.getFoodStats().addStats(20, 20.0F);
        Collection effects = player.getActivePotionEffects();
        if (effects.size() > 0)
        {
          ArrayList<Potion> bad = new ArrayList();
          for (Object effect : effects) {
            if ((effect instanceof PotionEffect))
            {
              PotionEffect potion = (PotionEffect)effect;
              if (PotionHelper.badPotion(Potion.potionTypes[potion.getPotionID()])) {
                bad.add(Potion.potionTypes[potion.getPotionID()]);
              }
            }
          }
          if (bad.size() > 0) {
            for (Potion potion : bad) {
              player.removePotionEffect(potion.id);
            }
          }
        }
      }
    }
    EntityPlayer player;
  }
}

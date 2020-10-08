package xuanwu.ultimate.listeners;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import xuanwu.ultimate.core.ultimateCore;
import xuanwu.ultimate.core.Data.Data;
import xuanwu.ultimate.register.Register;

@Cancelable
public class Attack
{
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onAttackEntity(AttackEntityEvent event)
  {
try {
	/**
	 * 判断攻击者是不是ul玩家
	 * 如果是,则击杀受害者
	 */
	if(ultimateCore.isUltimate(event.entityPlayer)) {
		if(event.target != null) {//null检查(其实没有必要)
			     ultimateCore.kill(event.target);
		}
	}else {
		/**
		 * 判断目标是不是玩家
		 * 如果是,判断玩家是不是ul玩家,如果是,并且开启了反伤,并且攻击者不是ul玩家,则打死攻击实体
		 */
		if(event.target instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.target;
			if(ultimateCore.isUltimate(player)) {
				if(!ultimateCore.isUltimate(event.entityPlayer)) {
					if(Data.Server.Config.UltimateKillAttacker) {
						ultimateCore.kill(event.entity);
					}
				}
			}
		}
	}
}catch(Throwable exp) {
	
}
  }
}

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
	 * �жϹ������ǲ���ul���
	 * �����,���ɱ�ܺ���
	 */
	if(ultimateCore.isUltimate(event.entityPlayer)) {
		if(event.target != null) {//null���(��ʵû�б�Ҫ)
			     ultimateCore.kill(event.target);
		}
	}else {
		/**
		 * �ж�Ŀ���ǲ������
		 * �����,�ж�����ǲ���ul���,�����,���ҿ����˷���,���ҹ����߲���ul���,���������ʵ��
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

package xuanwu.ultimate.listeners;

import java.lang.reflect.Method;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import xuanwu.ultimate.entity.ultimateLightningbolt;
import xuanwu.ultimate.register.Register;

public class PotionEventHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
	  {
	  try {
if(event.entityLiving.isPotionActive(Register.Judgement) || event.entityLiving.getEntityData().getBoolean("PotionJudgement")) {
	event.entityLiving.getEntityData().setBoolean("PotionJudgement",true);
	event.entityLiving.setFire(10);
    event.entityLiving.worldObj.addWeatherEffect(new ultimateLightningbolt(event.entityLiving.worldObj,event.entityLiving.posX,event.entityLiving.posY,event.entityLiving.posZ));
    event.entityLiving.motionX += -MathHelper.sin(event.entityLiving.rotationYaw * 3.1415927F / 180.0F) * 1.25F;
    event.entityLiving.motionY += 1.0D;
    event.entityLiving.motionZ += MathHelper.cos(event.entityLiving.rotationYaw * 3.1415927F / 180.0F) * 1.25F;
}
	  }catch(Exception exp) {}
	  }
}

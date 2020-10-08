package xuanwu.ultimate.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.collect.Queues;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import xuanwu.ultimate.core.ultimateCore;

public class UltimateTimer{
	protected UltimateTimer() {}
	protected static Timer instance;
	protected static List<Timer> instances = new ArrayList<Timer>();
	public static Timer getInstance() {
		if(instance == null) {
			UltimateTimer timer = new UltimateTimer();
			Thread thread = timer.new Timer();
			thread.setDaemon(true);
			thread.start();
			instance = (Timer)thread;
			instances.add(instance);
			return (Timer)thread;
		}
		return instance;
	}
protected UltimateTimer(TimerTask task) {
}
protected static void check() {
	for(Timer timer : instances) {
		if(!timer.isAlive() || timer == null) {
			instances.remove(timer);
		}
	}
}
public static void CancleTask(TimerTask task) {
	check();
	for(Timer timer : instances) {
		timer.Cancle(task);
	}
}
public static void addTask(TimerTask task) {
	check();
	getInstance().add(task);
}
public static void addTaskWithNewInstance(TimerTask task) {
	UltimateTimer timer = new UltimateTimer();
	Thread thread = timer.new Timer();
	thread.setDaemon(true);
	thread.start();
	instances.add((Timer)thread);
	((UltimateTimer.Timer)thread).add(task);
}
protected class Timer
extends Thread
{
protected List<TimerTask> Tasks = new ArrayList<TimerTask>();
protected void add(TimerTask task) {
	this.Tasks.add(task);
}
protected void Cancle(TimerTask task) {
	Tasks.remove(task);
}
public void run()
{
  try
  {
    while(true) {
	for(TimerTask task : Tasks) {
		try {
		task.run();
		}catch(Throwable exp) {
			Tasks.remove(task);
		}
	}
    }
  }
  catch (Throwable exp) {}
}
}
}
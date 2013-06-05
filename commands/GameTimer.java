package mods.learncraft.commands;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mods.learncraft.common.Common;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GameTimer {
	World currentWorld;
	Timer timer;

	//One hour is three minecraft days
	public GameTimer(World gameWorld) {
		currentWorld = gameWorld;
		int countdown = 15*60*1000; // Minutes * Seconds * Milliseconds
		timer = new Timer();
		timer.schedule(new countdown(), 500);
		timer.schedule(new setBuzz(), countdown);
	}
	
	//timerBuzzing is set to true when time is up on the game. 
	class setBuzz extends TimerTask {
		 public void run() {
			 Common.announce("Game is over!"); 
			 Common.blueteam.moveToSpawnAndFreeze();
			 Common.goldteam.moveToSpawnAndFreeze();
			 Common.teleportOn = false;	
			
			timer.cancel(); //Terminate the timer thread
		 }
	 }
	class countdown extends TimerTask {
		 public void run() {
			 for (int i=5; i>0; i--) {
					if(i==5) {Common.announce("Game Starting in " +i+ "...");}
					else {Common.announce(i+ "...");}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 
		 }
	 }
}


package mods.learncraft.common;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.world.World;

public class GameTimer {
	Timer timer;
	
	public GameTimer()
	{
		timer = new Timer();
	}
	
	public void start()
	{
		System.out.println("Starting timer ------- \n\n");
		int countdown = 15 * 60 * 1000; // Minutes * Seconds * Milliseconds
		timer = new Timer();
		timer.schedule(new countdown(), 1000);
		timer.schedule(new setBuzz(), countdown);
	}
	
	public void stop()
	{
		System.out.println("Cancelling timer ------ \n\n");
		if(timer != null) timer.cancel();
	}

	// timerBuzzing is set to true when time is up on the game.
	class setBuzz extends TimerTask {
		@Override
		public void run() {
			Common.announce("Game is over!");
			for(int a=0;a<Common.teams.length;a++) {
				Common.teams[a].moveToSpawnAndFreeze();
			}
			Common.teleportOn = false;

			timer.cancel(); // Terminate the timer thread
		}
	}

	class countdown extends TimerTask {
		@Override
		public void run() {
			for (int i = 5; i > 0; i--) {
					Common.announce("Game Starting in " + i + "...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Common.announce("Game Start!");
			Common.teleportOn = true;
			for(int a=0;a<Common.teams.length;a++) {
				Common.teams[a].moveToArena();
			}
		}
	}
}

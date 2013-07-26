package mods.learncraft.common;

import java.util.Timer;
import java.util.TimerTask;
import net.minecraft.world.World;

public class GameTimer {
	Timer timer;
	private static int matchTime; 
	private int timeLeft;
	
	public GameTimer()
	{
		timer = new Timer();
		matchTime = 15 * 60 * 1000;
		timeLeft = 5 * 60 + 1;
	}
	
	public void start()
	{
		timer = new Timer();
		timer.schedule(new countdown(), 1000);
		timer.schedule(new setBuzz(), matchTime);
		timer.schedule(new matchEndCountdown(), matchTime - (5 * 60 * 1000), 1000);
	}
	
	public void stop()
	{
		if(timer != null) timer.cancel();
	}
	
	public static void setMatchTime(int time)
	{
		matchTime = time * 60 * 1000;
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
	
	class matchEndCountdown extends TimerTask 
	{
		@Override
		public void run()
		{		
			timeLeft = timeLeft -  1;
			
			if(timeLeft > 60 && timeLeft % 60 == 0)
			{
				Common.announce("There are " + timeLeft/60 +  " minutes left in the game!");
			}
			else if(timeLeft == 60)
			{
				Common.announce("There is 1 minute left in the game!");
			}
			else if(timeLeft < 60 && timeLeft > 10 && timeLeft % 15 == 0)
			{
				Common.announce("There are " + timeLeft  + " seconds left in the game.");
			}
			else if(timeLeft <= 10)
			{
				Common.announce(timeLeft + " seconds left!");
			}
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

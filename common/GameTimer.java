package mods.learncraft.common;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class GameTimer {
	World currentWorld;
	Timer timer;

	// One hour is three minecraft days
	public GameTimer(World gameWorld) {
		currentWorld = gameWorld;
		int countdown = 15 * 60 * 1000; // Minutes * Seconds * Milliseconds
		timer = new Timer();
		timer.schedule(new countdown(), 500);
		timer.schedule(new setBuzz(), countdown);
	}

	// timerBuzzing is set to true when time is up on the game.
	class setBuzz extends TimerTask {
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

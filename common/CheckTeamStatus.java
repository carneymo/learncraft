package mods.learncraft.common;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CheckTeamStatus extends Thread {

	public static GameTimer gameTimer;
	
	@Override
	public void run() {
		new AutoBalancer();
		gameTimer = new GameTimer();
		
		while(!Thread.interrupted()) {
			try {
				Thread.sleep(500);
				checkScores();
				pushNotifications();
			} catch(InterruptedException e) {
				System.err.print(e);
			}
		}
	}

	public synchronized void pushNotifications()
	{
		Iterator<String> iterator = Common.notifications.iterator(); 
		while (iterator.hasNext()){
			String message = iterator.next();
			List plist = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
			for(int a=0; a < plist.size(); a++) {
				((EntityPlayer) plist.get(a)).addChatMessage(message);
			}
			iterator.remove();
		}
	}
	
	public void checkScores() 
	{		
		if(Common.teams == null && Common.winningteam == null) {
			Team[] teams = new Team[100];
			for(int a=0;a<Common.teams.length;a++) {
				if(Common.teams[a].points >= Integer.parseInt(Common.settings.getSetting("winningpoints"))) {
					teams[teams.length] = Common.teams[a];
				}
			}
			if(teams.length == 1) {
				Common.announce(teams[0].teamcolor.toUpperCase()+" Team Wins!");

				for(int a=0;a<Common.teams.length;a++) {
					Common.announce(Common.teams[a].teamcolor.toUpperCase()+" has "+Common.teams[a].points+" points.");
					Common.teams[a].moveToSpawnAndFreeze();
				}
				Common.winningteam = teams[0];
				Common.teleportOn = false;
			// For now, the first team in the list is the winner (this is used for 3+ teams)
			} else if(teams.length > 1) {
				Common.announce(teams[0].teamcolor.toUpperCase()+" Team Wins!");

				for(int a=0;a<Common.teams.length;a++) {
					Common.announce(Common.teams[a].teamcolor.toUpperCase()+" has "+Common.teams[a].points+" points.");
					Common.teams[a].moveToSpawnAndFreeze();
				}
				Common.winningteam = teams[0];
				Common.teleportOn = false;
			} else {
				// No winners yet
			}
		}
		
		//Check if the game has started yet.  If not, check if all players have entered the ready command
		if(Common.inProgress == false && Common.currentPlayers.getLength() != 0) {
			if(Common.currentPlayers.allReady()) {
				System.out.println("All players are ready!");
				Common.inProgress = true;
				
				World gameWorld = Common.playerlist.get(0).worldObj;
				
				//Set world time to zero and start timer.  
				gameWorld.setWorldTime(0);
				gameTimer.start();
			}
			
		} else {
			// Game in progress
		}
	}
}

package mods.learncraft.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.Timer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CheckTeamStatus extends Thread {

	private boolean balanced = true, test = true;
	private Timer timer;
	private Random random;
	
	@Override
	public void run() {
		System.out.println("in thread run()");
		new AutoBalancer();
		
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
			String message = (String) iterator.next();
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
				Common.inProgress = true;
				World gameWorld = Common.playerlist.get(0).worldObj; 
				
				//Set world time to zero and start timer.  
				//Common.announce("Eveyone is on a team and ready to play!");
				gameWorld.setWorldTime(0);
				List<EntityPlayer> players = gameWorld.playerEntities;	
				GameTimer gameTimer = new GameTimer(gameWorld);
			}
			
		} else {
			// Game in progress
		}
	}
}

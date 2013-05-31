package mods.learncraft.common;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import mods.learncraft.commands.GameTimer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CheckTeamStatus extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
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

	public void pushNotifications()
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
		if(Common.winningteam == null) {
			if(Common.blueteam.points >= 150 || Common.goldteam.points >= 150) {
				if(Common.blueteam.points > Common.goldteam.points) {
					Common.announce("Blue Team Wins!");
					Common.announce("Blue Team: "+Common.blueteam.points+" points");
					Common.announce("Gold Team: "+Common.goldteam.points+" points");
					Common.setTeamWon(Common.blueteam);
				} else if(Common.blueteam.points < Common.goldteam.points) {
					Common.announce("Gold Team Wins!");
					Common.announce("Gold Team: "+Common.goldteam.points+" points");
					Common.announce("Blue Team: "+Common.blueteam.points+" points");
					Common.setTeamWon(Common.goldteam);
				}
				if(Common.blueteam.points == Common.goldteam.points) {
					// Wait until a team scores more points
				} else {
					Common.blueteam.moveToSpawnAndFreeze();
					Common.goldteam.moveToSpawnAndFreeze();
					Common.teleportOn = false;
				}
			}
		}
		//Check if the game has started yet.  If not, check if all players have entered the ready command
		if(Common.inProgress == false && Common.currentNumPlayers != 0) {
			if(Common.playersReady == Common.currentNumPlayers) {
				World gameWorld = Common.playerlist.get(0).worldObj; 
				//Set world time to zero and start timer.  
				Common.announce("Eveyone is on a team and ready to play!!");
				gameWorld.setWorldTime(0);
				List<EntityPlayer> players = gameWorld.playerEntities;	
				
				GameTimer gameTimer = new GameTimer(gameWorld);
				Common.inProgress = true;
						
				Common.teleportOn = true;
				/*for(EntityPlayer person  : players) {
					//Teleport player to "choose_team" coordinates
						  if(Common.getTeam(person)==null) {
						  	System.out.println("No team for " + person.getEntityName() + "!!");
						  }
					else if(Common.getTeam(person)==Common.blueteam) {
						Common.teleportPlayerTo(person, "blue_arena");
					}
					else if(Common.getTeam(person)==Common.goldteam) {
						Common.teleportPlayerTo(person, "gold_arena");
					}
				}*/
			}
		}
	}
}

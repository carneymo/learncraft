package mods.learncraft.common;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

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
			if(Common.blueteam.points >= 20 || Common.goldteam.points >= 20) {
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
	}
}

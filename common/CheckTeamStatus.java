package mods.learncraft.common;

public class CheckTeamStatus extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()) {
			try {
				Thread.sleep(500);
				checkScores();
			} catch(InterruptedException e) {
				System.err.print(e);
			}
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

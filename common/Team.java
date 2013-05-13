package mods.learncraft.common;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class Team {

	public String teamcolor = "";
	public EntityPlayer[] roster = new EntityPlayer[100];
	public int numroster = 0;
	public int points = 0;
	private String teamChestModel = "";
	
	public Team(String newteamcolor) {
		teamcolor = newteamcolor;
	}
	
	public void addPlayer(EntityPlayer player) {
		this.roster[numroster] = player;
		numroster++;
		// Common.dbqueries.addPlayerToTeam(player,this);
		Common.announce("Added "+player.username+" to the "+teamcolor+" team!");
	}
	
	public boolean hasPlayer(EntityPlayer player) {
		if(numroster==0) {
			return false;
		}
		for(int a=0;a<roster.length;a++) {
			if(roster[a]==null) {
				return false;
			}
			if(roster[a].username == player.username) {
				return true;
			}
		}
		return false;
	}
	
	public void removePlayer(EntityPlayer player) {
		if(this.hasPlayer(player)) {
			int count = 0;
			for(EntityPlayer rosterPlayer : roster) {
				if(rosterPlayer.username == player.username) {
					roster[count] = null;
					break;
				}
				count++;
			}
			
		}
	}

	public boolean hasChestModel() {
		if(teamChestModel != "") {
			return true;
		} else {
			return false;
		}
	}

	public void addScore(int addpoints) {
		this.points = this.points + addpoints;
	}

	public void reportScore() {
		Common.announce(this.teamcolor.toUpperCase() + " Team has scored! Score: " + this.points);
	}

	public void moveToSpawnAndFreeze() {
		String spawnarea = "";
		if(teamcolor == "blue") {
			spawnarea = "blue_spawn";
		} else {
			spawnarea = "gold_spawn";
		}
		for(EntityPlayer rosterPlayer : roster) {
			if(rosterPlayer != null) {
				Common.teleportPlayerTo(rosterPlayer, spawnarea);
			}
		}
	}
	
}

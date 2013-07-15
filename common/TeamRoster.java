package mods.learncraft.common;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;

public class TeamRoster
{
	private ArrayList<TeamRosterItem> teamRoster;
	
	public TeamRoster()
	{
		teamRoster = new ArrayList();
	}
	
	public void clear()
	{
		teamRoster.clear();
	}
	
	public void addPlayer(EntityPlayer player, int index)
	{
		if(!teamRoster.contains(player))
			teamRoster.add(new TeamRosterItem(player, index));
		
	}
	
	public void removePlayer(EntityPlayer player)
	{
		for(int i = 0; i < teamRoster.size(); i++)
		{
			TeamRosterItem item = teamRoster.get(i);
			if(item.getPlayer().username.equals(player.username))
				teamRoster.remove(item);

		}
	}
	
	public ArrayList<EntityPlayer> getPlayers()
	{
		ArrayList<EntityPlayer> players = new ArrayList();
		
		for(TeamRosterItem item : teamRoster)
		{
			players.add(item.getPlayer());
		}
		
		return players;
	}
	
	public int getNumPlayers()
	{
		return teamRoster.size();
	}
	
	public boolean contains(EntityPlayer player)
	{
		for(TeamRosterItem item : teamRoster)
		{
			if(item.getPlayer().username.equals(player.username))
				return true;
		}
		
		return false;
	}
	
	public void printRoster()
	{
		for(TeamRosterItem item : teamRoster)
		{
			System.out.println(item);
		}
	}
	
	private class TeamRosterItem
	{
		private EntityPlayer player;
		private int index;
		
		public TeamRosterItem(EntityPlayer player, int index)
		{
			this.player = player;
			this.index = index;
		}
		
		public int getIndex()
		{
			return index;
		}
		
		public EntityPlayer getPlayer()
		{
			return player;
		}
		
		@Override
		public String toString()
		{
			return "Player: " + player.username + ", Index: " + index;
		}
	}
}

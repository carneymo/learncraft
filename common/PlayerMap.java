package mods.learncraft.common;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class PlayerMap 
{
	private ArrayList<PlayerMapItem> playerList;
	
	public PlayerMap()
	{
		playerList = new ArrayList<PlayerMapItem>();
	}
	
	public void addPlayer(EntityPlayer player)
	{
		playerList.add(new PlayerMapItem(player, false));
	}
	
	public void setReady(EntityPlayer player)
	{
		for(PlayerMapItem item : playerList)
		{
			if(item.getPlayer() == player)
			{
				item.setReady(true);
				return;
			}
		}
	}
	
	public boolean allReady()
	{
		int numReady = 0;
		
		for(PlayerMapItem item : playerList)
		{
			if(item.getReady()) numReady++;
		}
		
		return numReady == playerList.size();
	}
	
	public int getNumReady()
	{
		int numReady = 0;
		
		for(PlayerMapItem item : playerList)
		{
			if(item.getReady()) numReady++;
		}
		
		return numReady;
	}
	
	public int getLength()
	{
		return playerList.size();
	}
	
	private class PlayerMapItem
	{
		private EntityPlayer player;
		private boolean ready;
		
		public PlayerMapItem(EntityPlayer player, boolean ready)
		{
			this.player = player;
			this.ready = ready;
		}
		
		public EntityPlayer getPlayer()
		{
			return player;
		}
		
		public boolean getReady()
		{
			return ready;
		}
		
		public void setReady(boolean ready)
		{
			this.ready = ready;
		}
	}
	
}

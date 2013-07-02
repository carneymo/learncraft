package mods.learncraft.common;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
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
		if(!playerList.contains(player))
			playerList.add(new PlayerMapItem(player, false));
	}
	
	public boolean contains(EntityPlayer player)
	{
		for(PlayerMapItem item : playerList)
		{
			if(item.getPlayer().getEntityName().equals(player.getEntityName()))
				return true;
		}
		
		return false;
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
	
	public String toString()
	{
		for(PlayerMapItem item : playerList)
		{
			System.out.println(item.getPlayer().getEntityName() + ": " + item.getReady());
		}
		
		return null;
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

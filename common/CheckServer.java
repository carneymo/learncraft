package mods.learncraft.common;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CheckServer extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.interrupted()) {
			try {
				Thread.sleep(5000);
				updateOnline();
				updateServerOnline();
			} catch(InterruptedException e) {
				System.err.print(e);
			}
		}
	}
	
	public void updateServerOnline()
	{
		Common.dbqueries.updateServerOnline();
	}
	
	public void updateOnline()
	{
		List plist = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
		Common.dbqueries.setAllToOffline();
		Common.dbqueries.removeServerPlayers();
		for(int a=0; a < plist.size(); a++) {
			EntityPlayerMP player = (EntityPlayerMP) plist.get(a);
			Common.dbqueries.setPlayerOnline(player.username);
			Common.dbqueries.addPlayerToServer(player.username);
		}
	}
}

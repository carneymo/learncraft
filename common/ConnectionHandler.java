package mods.learncraft.common;

import java.sql.DriverManager;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
<<<<<<< HEAD
=======

>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
		EntityPlayer player1 = (EntityPlayer) player;
		
		if(null != Common.dbqueries){
			Common.dbqueries.insertPlayerLoggedIn(player1.username);
<<<<<<< HEAD
			/**
=======
			/*
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
			String teamcolor = Common.dbqueries.getPlayerTeam(player1);
			if(teamcolor.matches("blue")) {
				if(!Common.blueteam.hasPlayer(player1)) {
					Common.blueteam.addPlayer(player1);
					Common.teleportPlayerTo(player1,"blue_spawn");
				}
			} else if(teamcolor.matches("gold")) {
				if(!Common.goldteam.hasPlayer(player1)) {
					Common.goldteam.addPlayer(player1);
					Common.teleportPlayerTo(player1,"gold_spawn");
				}
			} else {
				// Player doesn't belong to a team
			}
<<<<<<< HEAD
			**/
=======
			*/
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
			Common.teleportPlayerTo(player1,"choose_team");
			(new CheckServer()).start();
		}
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
		// TODO Auto-generated method stub

	}

}

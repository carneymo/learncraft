package mods.learncraft.common;


import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerSpawnEvent implements IConnectionHandler {

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		//fires when a client logs in (SERVER SIDE)

		
		//Get item ID of the helmet that is being worn
		EntityPlayer dude = netHandler.getPlayer(); 
		int myHelmet = 0;
		/*if(dude.inventory.armorInventory[0] != null) {
			System.out.println(dude.inventory.armorInventory[0].stackSize);
			System.out.println("[0] stack");
		}
		else if(dude.inventory.armorInventory[1] != null) {
			System.out.println(dude.inventory.armorInventory[1].stackSize);
			System.out.println("[1] stack");
		}
		else if(dude.inventory.armorInventory[2] != null) {
			System.out.println(dude.inventory.armorInventory[2].stackSize);
			System.out.println("[2] stack");
		}*/
		if(dude.inventory.armorInventory[3] != null) {
			myHelmet = dude.inventory.armorInventory[3].itemID;
			System.out.println("Good, you are wearing a helmet...");
		}
	
		if(myHelmet == 314){
			System.out.println("Orange Team!");
			//Team orange=1; blue=2; White=3
			NBTTagCompound nbt = dude.getEntityData();

			//if(!nbt.hasKey("myTeam")){
				nbt.setInteger("myTeam", 1);			
			//}
		}
		else if(myHelmet == 310){
			System.out.println("Blue Team!");
			NBTTagCompound nbt = dude.getEntityData();

			//if(!nbt.hasKey("myTeam")){
				nbt.setInteger("myTeam", 2);			
			//}
		}
		else{
			System.out.println("White Team!");
			NBTTagCompound nbt = dude.getEntityData();

			//if(!nbt.hasKey("myTeam")){
				nbt.setInteger("myTeam", 3);			
			//}
		}	
	}
		
	
	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		// TODO Auto-generated method stubw
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
		/*if(server.getCurrentPlayerCount() == 1) {
			Global_vars.lastAssign = 0; 
        	Global_vars.bluePlayers = new ArrayList<String>();
        	Global_vars.orangePlayers = new ArrayList<String>();
        	System.out.print("TEAM VARIABLES INITIALIZED");
		}*/

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

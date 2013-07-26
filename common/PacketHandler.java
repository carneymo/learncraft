package mods.learncraft.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("Learncraft")) handleTeamScores(packet);
	}
	
	private void handleTeamScores(Packet250CustomPayload packet)
	{
		 DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
         
         int blueTeamScore;
         int orangeTeamScore;
         try {
                 blueTeamScore = inputStream.readInt();
                 orangeTeamScore = inputStream.readInt();
         } catch (IOException e) {
                 e.printStackTrace();
                 return;
         }
         
         TeamScoreDisplay.blueTeamScore = blueTeamScore;
         TeamScoreDisplay.orangeTeamScore = orangeTeamScore;
	}
	
	public static void sendOutTeamScoresPacket()
	{
		//Aaron Mertz
		//Begin sending a packet that tells the client what the teamscores are
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
	    DataOutputStream outputStream = new DataOutputStream(bos);
	    try {
	            outputStream.writeInt(Common.teams[0].points);
	            outputStream.writeInt(Common.teams[1].points);
	    } catch (Exception ex) {
	             ex.printStackTrace();
	    }
	     
	    Packet250CustomPayload packet = new Packet250CustomPayload();
	    packet.channel = "Learncraft";
	    packet.data = bos.toByteArray();
	    packet.length = bos.size();
	     
	    Side side = FMLCommonHandler.instance().getEffectiveSide();
	    if (side == Side.SERVER) 
	    {
	    	   PacketDispatcher.sendPacketToAllPlayers(packet);
	  
	    } else if (side == Side.CLIENT) 
	    {
	           EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
	           player.sendQueue.addToSendQueue(packet);
	            
	    } else 
	    {
	             // We are on the Bukkit server.
	    }
		
	}
	
}

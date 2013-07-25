package mods.learncraft.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

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
	
}

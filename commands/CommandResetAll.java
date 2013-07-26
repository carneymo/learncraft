package mods.learncraft.commands;

import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import mods.learncraft.common.CheckTeamStatus;
import mods.learncraft.common.Common;
import mods.learncraft.common.PacketHandler;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class CommandResetAll extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "resetall";
	}
	
	@Override
	public int getRequiredPermissionLevel()
    {
        return 3;
    }

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
	{
		return true;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if (icommandsender instanceof EntityPlayer)
		{
			Common.teams[0].clear();
			Common.teams[1].clear();
			Common.currentPlayers.clear();
			Common.playerlist.clear();
			CheckTeamStatus.gameTimer.stop();
			PacketHandler.sendOutTeamScoresPacket();
			
			Common.inProgress = false;
			
			ServerConfigurationManager manager = MinecraftServer.getServer().getConfigurationManager();
			
			String phrase = manager.getPlayerListAsString();
			String delims = "[,]+";
			String[] tokens = phrase.split(delims);
			
			for(int i = 0; i < tokens.length; i++)
			{
				EntityPlayer player1 = manager.getPlayerForUsername(tokens[i]);
				Common.currentPlayers.addPlayer(player1);
				Common.playerlist.add(player1);
				Common.teleportPlayerTo(player1, "choose_team", true);
			}
		}
		
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return null;
	}
}

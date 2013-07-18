package mods.learncraft.commands;

import java.util.Set;

import mods.learncraft.common.CheckTeamStatus;
import mods.learncraft.common.Common;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandResetAll extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "resetall";
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
			boolean isOP = false;
			EntityPlayer player = (EntityPlayer)icommandsender;
			Set<String> ops = MinecraftServer.getServer().getConfigurationManager().getOps();
			
			for(String s : ops)
			{
				if(s.equalsIgnoreCase(player.username))
				{
					isOP = true;
				}
			}
			
			if(true)
			{
				System.out.println("Player is OP, resetting.");
				Common.teams[0].clear();
				Common.teams[1].clear();
				Common.currentPlayers.clear();
				Common.playerlist.clear();
				CheckTeamStatus.gameTimer.stop();
				
				Common.inProgress = false;
				
				String phrase = MinecraftServer.getServer().getConfigurationManager().getPlayerListAsString();
				String delims = "[,]+";
				String[] tokens = phrase.split(delims);
				
				for(int i = 0; i < tokens.length; i++)
				{
					EntityPlayer player1 = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(tokens[i]);
					Common.currentPlayers.addPlayer(player1);
					Common.playerlist.add(player1);
					Common.teleportPlayerTo(player1, "choose_team", true);
				}
			}
		}
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return null;
	}
}

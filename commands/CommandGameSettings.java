package mods.learncraft.commands;

import mods.learncraft.common.Common;
import mods.learncraft.common.GameTimer;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class CommandGameSettings extends CommandBase {

	@Override
	public String getCommandName()
	{
		return "gamesettings";
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
		if(icommandsender instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) icommandsender;
			
			if(astring.length == 0) return;
			
			if(astring[0].equals("matchtime"))
			{
				if(astring.length >= 2 && isInteger(astring[1]))
				{
					int matchTime = Integer.parseInt(astring[1]);
					if(matchTime <= 45 && matchTime >= 15)
					{
						GameTimer.setMatchTime(matchTime);
						player.addChatMessage("Match time set to " + matchTime + " minutes.");
					}
					else
					{
						player.addChatMessage("Matchtime must be between 15 and 45 minutes.");
					}
				}
				else
				{
					player.addChatMessage("Matchtime must be an integer in minutes.");
				}
			}
		}
	}
	
	public static boolean isInteger(String s) 
	{
	    try 
	    { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) 
	    { 
	        return false; 
	    }
	    return true;
	}


	@Override
	public String getCommandUsage(ICommandSender icommandsender) 
	{
		return "gamesettings test";
	}
}


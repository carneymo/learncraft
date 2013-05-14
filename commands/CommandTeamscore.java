package mods.learncraft.commands;

import mods.learncraft.common.Common;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTeamscore extends CommandBase {

	@Override
	public String getCommandName()
	{
		return "teamscore";
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
	{
		return true;
	}
	@Override
	
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		if(icommandsender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)icommandsender;
			if(Common.winningteam == null) {
				if(astring.length == 0) {
					if(Common.blueteam.hasPlayer(player)) {
						player.addChatMessage("Blue Team has "+Common.blueteam.points+" points");
					} else if(Common.goldteam.hasPlayer(player)) {
						player.addChatMessage("Gold Team has "+Common.goldteam.points+" points");
					} else {
						player.addChatMessage("You are not on a team");
					}
				} else if(astring.length == 1) {
					if(astring[0].trim().matches("blue")) {
						player.addChatMessage("Blue Team has "+Common.blueteam.points+" points");
					}
					else if(astring[0].trim().matches("gold")) {
						player.addChatMessage("Gold Team has "+Common.goldteam.points+" points");
					}
					else if(astring[0].trim().matches("all")) {
						player.addChatMessage("Blue Team has "+Common.blueteam.points+" points");
						player.addChatMessage("Gold Team has "+Common.goldteam.points+" points");
					}
					else {
						player.addChatMessage("Unknown team: "+astring[0]);
					}
				}
			} else {
				if(Common.blueteam.points > Common.goldteam.points) {
					Common.announce("Blue Team Won!");
					Common.announce("Blue Team: "+Common.blueteam.points+" points");
					Common.announce("Gold Team: "+Common.goldteam.points+" points");
				} else if(Common.blueteam.points < Common.goldteam.points) {
					Common.announce("Gold Team Won!");
					Common.announce("Gold Team: "+Common.goldteam.points+" points");
					Common.announce("Blue Team: "+Common.blueteam.points+" points");
				}
			}
		}
	}

}

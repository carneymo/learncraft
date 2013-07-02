package mods.learncraft.commands;

import java.util.List;
import java.util.Timer;

import mods.learncraft.common.Common;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommandReady extends CommandBase {

	@Override
	public String getCommandName()
	{
		return "ready";
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
			Team team = Common.getTeam(player);
			if(team == null) {
				player.addChatMessage("You must be on a team before you can ready-up.");
			} else {
				Common.currentPlayers.setReady(player);
				System.out.println(Common.currentPlayers);
				Common.announce(Common.currentPlayers.getNumReady() + "/" +  Common.currentPlayers.getLength() + " players are ready.");

			}
		}
	}
}


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
<<<<<<< HEAD
				Common.currentPlayers.setReady(player);
				System.out.println(Common.currentPlayers);
				Common.announce(Common.currentPlayers.getNumReady() + "/" +  Common.currentPlayers.getLength() + " players are ready.");
=======
				//Iterate the playersReady variable to represent that the player is ready
				Common.playersReady += 1;
				player.addChatMessage("You are ready!");
>>>>>>> f9c1feacf722273d1ebb7d496f0f12f862c4f58e
			}
		}
	}
}


package mods.learncraft.commands;

import java.util.List;
import java.util.Timer;

import mods.learncraft.common.Common;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
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
			//Iterate the playersReady variable to represent that the player is ready
			Common.playersReady += 1;
		}
	}
}


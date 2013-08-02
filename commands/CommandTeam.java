package mods.learncraft.commands;

import mods.learncraft.common.Common;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTeam extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "team";
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
            EntityPlayer player = (EntityPlayer)icommandsender;

            if (Common.disableTeamSwitch == true)
            {
                player.addChatMessage("Team switching is disabled during autobalance.");
                return;
            }

            if (Common.getTeam(astring[0].toLowerCase()).hasPlayer(player))
            {
                player.addChatMessage("You are already on that team!");
                return;
            }

            Team team = Common.getTeam(player);

            if (team == null && astring.length == 1)
            {
                if (Common.hasTeam(astring[0]))
                {
                    Team newteam = Common.getTeam(astring[0]);
                    newteam.addPlayer(player);
                    Common.announce("Player " + player.username + " has joined team " + newteam.teamcolor.toUpperCase() + " Team");
                }
            }
            else
            {
                if (Common.inProgress == false)
                {
                    if (astring.length == 1 && Common.hasTeam(astring[0]) && team != null && astring[0].compareTo(team.teamcolor) != 0)
                    {
                        team.removePlayer(player);
                        Team newteam = Common.getTeam(astring[0]);
                        newteam.addPlayer(player);
                        player.addChatMessage("Switch teams from " + team.teamcolor.toUpperCase() + " Team to " + astring[0].toUpperCase() + " Team.");
                    }
                    else
                    {
                        if (astring.length != 1)
                        {
                            player.addChatMessage("Incorrect usage of the command /team.");
                        }
                        else if (!Common.hasTeam(astring[0]))
                        {
                            player.addChatMessage("Team " + astring[0] + " does not exist.");
                        }
                        else
                        {
                            player.addChatMessage("Incorrect usage of the command /team.");
                        }
                    }
                }
                else
                {
                    player.addChatMessage("The game has started, you cannot switch teams.");
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

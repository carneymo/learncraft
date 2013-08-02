package mods.learncraft.commands;

import mods.learncraft.common.Common;
import mods.learncraft.common.Team;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTeamscore extends CommandBase
{
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
        if (icommandsender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)icommandsender;

            if (Common.winningteam == null)
            {
                if (astring.length == 0)
                {
                    Team team = Common.getTeam(player);

                    if (team == null)
                    {
                        player.addChatMessage("You are not on a team");
                    }
                    else
                    {
                        team.reportScoreToPlayer(player);
                    }
                }
                else if (astring.length == 1)
                {
                    if (astring[0].trim().matches("all"))
                    {
                        for (int a = 0; a < Common.teams.length; a++)
                        {
                            Common.teams[a].reportPoints();
                        }
                    }

                    if (Common.hasTeam(astring[0]))
                    {
                        Common.getTeam(astring[0]).reportPoints();
                    }
                    else
                    {
                        player.addChatMessage("Unknown team: " + astring[0]);
                    }
                }
            }
            else
            {
                Common.announce(Common.winningteam.teamcolor.toUpperCase() + " Team has Won!");

                for (int a = 0; a < Common.teams.length; a++)
                {
                    Common.teams[a].reportPoints();
                }
            }
        }
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        // TODO Auto-generated method stub
        return null;
    }
}

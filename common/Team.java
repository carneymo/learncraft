package mods.learncraft.common;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Team
{
    public String teamcolor = "";
    public TeamRoster roster = new TeamRoster();
    public int numroster = 0;
    public int points = 0;
    private String teamChestModel = "";

    public Team(String newteamcolor)
    {
        teamcolor = newteamcolor;
    }

    public void addPlayer(EntityPlayer player)
    {
        roster.addPlayer(player, numroster);
        numroster++;
        Common.teleportPlayerTo(player, this.teamcolor + "_spawn", true);
        Common.announce("Added " + player.username + " to the " + teamcolor + " team!");

        if (Common.getSide() == Side.CLIENT)
        {
            try
            {
                Minecraft.getMinecraft().getIntegratedServer().executeCommand("scoreboard teams join " + teamcolor + " " + player.username);
            }
            catch (Exception e)
            {
                System.out.println("Client side scoreboard join error.");
                e.printStackTrace();
            }
        }
        else if (Common.getSide() == Side.SERVER)
        {
            try
            {
                MinecraftServer.getServer().executeCommand("scoreboard teams join " + teamcolor + " " + player.username);
            }
            catch (Exception e)
            {
                System.out.println("Server side scoreboard join error.");
                e.printStackTrace();
            }
        }
    }

    public boolean hasPlayer(EntityPlayer player)
    {
        if (numroster == 0 || player == null)
        {
            return false;
        }

        if (roster.contains(player))
        {
            return true;
        }

        return false;
    }

    public void removePlayer(EntityPlayer player)
    {
        roster.removePlayer(player);
    }

    public boolean hasChestModel()
    {
        if (teamChestModel != "")
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addScore(int addpoints)
    {
        this.points = this.points + addpoints;
    }

    public void reportScoreToPlayer(EntityPlayer player)
    {
        player.addChatMessage(this.teamcolor.toUpperCase() + " has " + this.points + " points.");
    }

    public void reportScore()
    {
        Common.announce(this.teamcolor.toUpperCase() + " Team has scored! Score: " + this.points);
    }

    public void reportPoints()
    {
        Common.announce(this.teamcolor.toUpperCase() + " Team has " + this.points + " points.");
    }

    public void moveToSpawnAndFreeze()
    {
        String spawnarea = this.teamcolor + "_spawn";

        for (EntityPlayer rosterPlayer : roster.getPlayers())
        {
            if (rosterPlayer != null)
            {
                Common.teleportPlayerTo(rosterPlayer, spawnarea);
            }
        }
    }

    public void moveToArena()
    {
        String arena = this.teamcolor + "_arena";

        for (EntityPlayer rosterPlayer : roster.getPlayers())
        {
            if (rosterPlayer != null)
            {
                Common.teleportPlayerTo(rosterPlayer, arena);
            }
        }
    }

    public String getLocation(String loc)
    {
        return this.teamcolor + "_" + loc;
    }

    public String getTeamColor()
    {
        return teamcolor;
    }

    public int getNumPlayers()
    {
        return roster.getNumPlayers();
    }

    public ArrayList<EntityPlayer> getPlayers()
    {
        return roster.getPlayers();
    }

    public void clear()
    {
        roster.clear();
        points = 0;
        numroster = 0;
    }

    public void printRoster()
    {
        System.out.println(this.teamcolor.toUpperCase() + " team's roster:");
        roster.printRoster();
    }
}

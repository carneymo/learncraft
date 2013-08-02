package mods.learncraft.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Coordinates
{
    final static String fileName = "coordinates.conf";
    public Map<String, String> coordinates = new HashMap<String, String>();

    public Coordinates()
    {
        InputStream i = Coordinates.class.getResourceAsStream(fileName);
        ArrayList<String> lines = new ArrayList<String>();
        BufferedReader in = null;

        try
        {
            in = new BufferedReader(new InputStreamReader(i));
            String line = "";

            while ((line = in.readLine()) != null)
            {
                String[] result = line.split(":", 2);
                this.addSetting(result);
            }

            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getSetting(String settingName)
    {
        return coordinates.get(settingName);
    }

    public void addSetting(String[] coord)
    {
        if (coord.length == 2)
        {
            coordinates.put(coord[0], coord[1]);
        }
    }

    public void addSetting(String coordName, String coordValue)
    {
        coordinates.put(coordName, coordValue);
    }

    public Double[] getCoordinates(String loc)
    {
        if (coordinates.containsKey(loc))
        {
            Double[] returncoord = new Double[3];
            String[] coords = coordinates.get(loc).split(",", 3);

            for (int a = 0; a < coords.length; a++)
            {
                returncoord[a] = Double.parseDouble(coords[a]);
            }

            return returncoord;
        }

        return null;
    }

    public void TeleportPlayer(EntityPlayerMP player, String loc)
    {
        Double[] coord = getCoordinates(loc);

        if (coord != null && coord.length == 3)
        {
            System.out.println("Teleporting server " + player.username + " to " + coord[0] + ", " + coord[1] + ", " + coord[2] + ".");
            player.playerNetServerHandler.setPlayerLocation(coord[0], coord[1], coord[2], player.cameraYaw, player.cameraPitch);
        }
        else
        {
            player.addChatMessage("Invalid teleporation location: " + loc);

            if (coord == null)
            {
                player.addChatMessage("Null coord");
            }
        }
    }

    public void TeleportPlayer(EntityPlayer player, String loc)
    {
        Double[] coord = getCoordinates(loc);

        if (coord != null && coord.length == 3)
        {
            System.out.println("Teleporting client " + player.username + " to " + coord[0] + ", " + coord[1] + ", " + coord[2] + ".");
            player.setPositionAndUpdate(coord[0], coord[1], coord[2]);
        }
        else
        {
            player.addChatMessage("Invalid teleporation location: " + loc);

            if (coord == null)
            {
                player.addChatMessage("Null coord");
            }
        }
    }
}

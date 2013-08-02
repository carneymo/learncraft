package mods.learncraft.commands;

import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommandGenGlowstone extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "genGlowstone";
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

            /*Iterator itOp = ops.iterator();
            while(itOp.hasNext())
            {
            	Object elem = itOp.next();
            	player.addChatMessage(elem.toString());
            }*/
            // pretty java-magical-way
            for (String s : ops)
            {
                //player.addChatMessage(s);
                if (s.equalsIgnoreCase(player.username))
                {
                    isOP = true;
                }
            }

            if (isOP)
            {
                World w = player.worldObj;

                for (int x = 107; x <= 308; x++)
                {
                    for (int y = 7; y <= 73; y++)
                    {
                        for (int z = 540; z <= 658; z++)
                        {
                            if (w.getBlockId(x, y, z) == 507)
                            {
                                replaceStone(x, y, z, w);
                            }
                        }
                    }
                }
            }
        }
    }
    public void replaceStone(int x, int y, int z, World w)
    {
        double chance = (Math.random() * 100) + 1;

        if (chance >= 50)
        {
            w.setBlock(x, y, z, Block.glowStone.blockID);

            for (int a = -1; a <= 1; a++)
            {
                for (int b = -1; b <= 1; b++)
                {
                    for (int c = -1; c <= 1; c++)
                    {
                        chance = (Math.random() * 100) + 1;

                        if (chance >= 25 & w.getBlockId(x + 1, y + b, z + c) == 507)
                        {
                            w.setBlock(x, y, z, Block.glowStone.blockID);
                        }
                    }
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

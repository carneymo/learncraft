package mods.learncraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import mods.learncraft.common.Common;
import mods.learncraft.common.CommonProxy;
import mods.learncraft.common.LBlockChest;
import mods.learncraft.common.TileEntityLChest;
import mods.learncraft.common.TileEntityTeamChest;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerTileEntitySpecialRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLChest.class, new TileEntityLChestRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeamChest.class, new TileEntityLChestRenderer());
    }
}

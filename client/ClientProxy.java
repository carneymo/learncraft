package mods.learncraft.client;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import mods.learncraft.common.CommonProxy;
import mods.learncraft.common.EventReceiver;
import mods.learncraft.common.TileEntityLChest;
import mods.learncraft.common.TileEntityTeamChest;

public class ClientProxy extends CommonProxy
{

	@Override
	public void registerTileEntitySpecialRenderer()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLChest.class,
				new TileEntityLChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTeamChest.class,
				new TileEntityLChestRenderer());
	}

	@Override
	public void registerSounds()
	{
		MinecraftForge.EVENT_BUS.register(new Learncraft_EventSounds());
	}
	
	@Override
	public void registerGui()
	{
		MinecraftForge.EVENT_BUS.register(new EventReceiver());
	}
}

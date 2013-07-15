package mods.learncraft.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class Learncraft_EventSounds
{

	@ForgeSubscribe
	public void onSound(SoundLoadEvent event)
	{
		try
		{
			event.manager.addSound("learncraft:greeting.ogg");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
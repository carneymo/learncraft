package mods.learncraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class EventHookContainerClass {

	@ForgeSubscribe
	public void entityAttacked(LivingAttackEvent event) {
		
	}
	
	@ForgeSubscribe
	public void onPlayerInteract(EntityPlayer player, Action action, int x, int y, int z, int face) {

	}
	
	@ForgeSubscribe
	public void PlayerInteractEvent(Action action, int x, int y, int z, int face, Result useBlock, Result useItem) {
		
	}
}

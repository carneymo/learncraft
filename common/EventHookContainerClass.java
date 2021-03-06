package mods.learncraft.common;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class EventHookContainerClass
{
    /**
     * The key is the @ForgeSubscribe annotation and the cast of the Event you put in as argument.
     * The method name you pick does not matter. Method signature is public void, always.
     */
    @ForgeSubscribe
    public void entityAttacked(LivingAttackEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
        }
    }

    @ForgeSubscribe
    public void livingDeathEvent(LivingDeathEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            event.setCanceled(true);
            player.setEntityHealth(20);
            
            //The only way I could find to refill the food that worked without
            //crashing the server. A pumpkin pie is 8 hunger each, so 3 of them refill 24 hunger
            player.getFoodStats().addStats((ItemFood) Item.pumpkinPie);
            player.getFoodStats().addStats((ItemFood) Item.pumpkinPie);
            player.getFoodStats().addStats((ItemFood) Item.pumpkinPie);
            
            int BlueDoorID = Common.BlueTeamDoor.itemID;
            int OrangeDoorID = Common.OrangeTeamDoor.itemID;
            int BlueDefenseID = 525;
            int OrangeDefenseID = 524;

            // Drops all the glowstone dust, but nothing else!
            if (player.inventory.hasItem(348))
            {
                for (int a = 0; a < player.inventory.getSizeInventory(); a++)
                {
                    ItemStack slotitemstack = player.inventory.getStackInSlot(a);

                    if (slotitemstack != null)
                    {
                        if (slotitemstack.itemID == 348)
                        {
                            player.inventory.setInventorySlotContents(a, null);
                            player.dropItem(slotitemstack.itemID, slotitemstack.stackSize);
                        }
                    }
                }
            }

            for (int i = 0; i < player.inventory.getSizeInventory(); i++)
            {
                ItemStack stack = player.inventory.getStackInSlot(i);

                if (stack != null)
                {
                    if (stack.itemID == BlueDoorID    || stack.itemID == OrangeDoorID ||
                            stack.itemID == BlueDefenseID || stack.itemID == OrangeDefenseID)
                    {
                        player.inventory.setInventorySlotContents(i, null);
                    }
                }
            }

            Common.announce(player.username + " has died and was sent to the maze!");
            Common.teleportPlayerTo(player, "maze_spawn");
        }
    }

    @ForgeSubscribe
    public void playerInteractEvent(PlayerInteractEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            int blockid = player.worldObj.getBlockId(event.x, event.y, event.z);
            int metadata = player.worldObj.getBlockMetadata(event.x, event.y, event.z);

            // Teleport to appropriate team area if touch mob head
            if (blockid == 144 && event.action.compareTo(Action.RIGHT_CLICK_BLOCK) == 0)
            {
                Team team = Common.getTeam(player);

                if (team != null)
                {
                    Common.teleportPlayerTo(player, team.teamcolor + "_arena");
                }
            }
        }
    }

    @ForgeSubscribe
    public void livingSpecialSpawnEvent(LivingSpawnEvent event)
    {
        if (event.entityLiving instanceof EntityPlayer)
        {
        }
    }

    @ForgeSubscribe
    public void entityJoinWorldEvent(EntityJoinWorldEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            //System.out.println("Join World Event!");
        }
    }

    @ForgeSubscribe
    public void onSleepyTime(PlayerSleepInBedEvent event)
    {
        event.result = EnumStatus.NOT_POSSIBLE_NOW;
    }
}
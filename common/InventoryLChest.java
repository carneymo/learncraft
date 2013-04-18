package mods.learncraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryLChest extends InventoryBasic
{
    private TileEntityChest associatedChest;

    public InventoryLChest()
    {
        super("container.chest", false, 15);
    }

    public void setAssociatedChest(TileEntityChest tilechest)
    {
        this.associatedChest = tilechest;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.associatedChest != null && !this.associatedChest.isUseableByPlayer(par1EntityPlayer) ? false : super.isUseableByPlayer(par1EntityPlayer);
    }

    public void openChest()
    {
        if (this.associatedChest != null)
        {
            this.associatedChest.openChest();
        }

        super.openChest();
    }

    public void closeChest()
    {
        if (this.associatedChest != null)
        {
            this.associatedChest.closeChest();
        }

        super.closeChest();
        this.associatedChest = null;
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }
}

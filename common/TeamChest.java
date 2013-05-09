package mods.learncraft.common;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TeamChest extends BlockContainer {

    private final Random random = new Random();
    public final int field_94443_a;

    protected TeamChest(int par1, int par2)
    {
        super(par1, Material.iron);
        setUnlocalizedName("TeamChest");
        setHardness(3.0F);
        this.field_94443_a = par2;
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 22;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        }
        else if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
        else if (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID)
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        }
        else
        {
            this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.unifyAdjacentChests(par1World, par2, par3, par4);
        int l = par1World.getBlockId(par2, par3, par4 - 1);
        int i1 = par1World.getBlockId(par2, par3, par4 + 1);
        int j1 = par1World.getBlockId(par2 - 1, par3, par4);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4);

        if (l == this.blockID)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 - 1);
        }

        if (i1 == this.blockID)
        {
            this.unifyAdjacentChests(par1World, par2, par3, par4 + 1);
        }

        if (j1 == this.blockID)
        {
            this.unifyAdjacentChests(par1World, par2 - 1, par3, par4);
        }

        if (k1 == this.blockID)
        {
            this.unifyAdjacentChests(par1World, par2 + 1, par3, par4);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = par1World.getBlockId(par2, par3, par4 - 1);
        int i1 = par1World.getBlockId(par2, par3, par4 + 1);
        int j1 = par1World.getBlockId(par2 - 1, par3, par4);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4);
        byte b0 = 0;
        int l1 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l1 == 0)
        {
            b0 = 2;
        }

        if (l1 == 1)
        {
            b0 = 5;
        }

        if (l1 == 2)
        {
            b0 = 3;
        }

        if (l1 == 3)
        {
            b0 = 4;
        }

        if (l != this.blockID && i1 != this.blockID && j1 != this.blockID && k1 != this.blockID)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
        }
        else
        {
            if ((l == this.blockID || i1 == this.blockID) && (b0 == 4 || b0 == 5))
            {
                if (l == this.blockID)
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }

            if ((j1 == this.blockID || k1 == this.blockID) && (b0 == 2 || b0 == 3))
            {
                if (j1 == this.blockID)
                {
                    par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
                }

                par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
            }
        }
        
        // RyanCM: Set the player facing to the chest on placement
        int yaw = (int)par5EntityLiving.rotationYaw;
        if(yaw<0) {
        	yaw += 360;
        }
        yaw += 22;
        yaw %= 360;
        // Facing is four directions: NORTH, SOUTH, EAST, WEST
        int facing = yaw/90;
        
        // Set the facing
        ((TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4)).setFacing(facing);
        
        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4)).func_94043_a(par6ItemStack.getDisplayName());
        }
    }

    /**
     * Turns the adjacent chests to a double chest.
     */
    public void unifyAdjacentChests(World par1World, int par2, int par3, int par4)
    {
    	
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int l = 0;

        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
        {
            ++l;
        }

        if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
        {
            ++l;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
        {
            ++l;
        }

        if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
        {
            ++l;
        }

        return l > 1 ? false : (this.isThereANeighborChest(par1World, par2 - 1, par3, par4) ? false : (this.isThereANeighborChest(par1World, par2 + 1, par3, par4) ? false : (this.isThereANeighborChest(par1World, par2, par3, par4 - 1) ? false : !this.isThereANeighborChest(par1World, par2, par3, par4 + 1))));
    }

    /**
     * Checks the neighbor blocks to see if there is a chest there. Args: world, x, y, z
     */
    private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockId(par2, par3, par4) != this.blockID ? false : (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID ? true : (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID ? true : (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID ? true : par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        if(par1World.getBlockTileEntity(par2, par3, par4) instanceof TileEntityTeamChest) {
            TileEntityTeamChest TileEntityTeamChest = (TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4);
            if (TileEntityTeamChest != null)
            {
                TileEntityTeamChest.updateContainingBlockInfo();
            }
        }

    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
    	TileEntityTeamChest TileEntityTeamChest = (TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4);

        if (TileEntityTeamChest != null)
        {
            for (int j1 = 0; j1 < TileEntityTeamChest.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = TileEntityTeamChest.getStackInSlot(j1);

                if (itemstack != null)
                {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem))
                    {
                        int k1 = this.random.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize)
                        {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.random.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.random.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            par1World.func_96440_m(par2, par3, par4, par5);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World theworld, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	IInventory iinventory = this.func_94442_h_(theworld, x, y, z);
    	
        if (theworld.isRemote)
        {      
        	return true;
        }

        ((TileEntityTeamChest)theworld.getBlockTileEntity(x, y, z)).setTexture("blue");
        ((TileEntityTeamChest)theworld.getBlockTileEntity(x, y, z)).updateEntity();
        
        if (iinventory != null)
        {
        	player.displayGUIChest(iinventory);
        }

        return true;
    }

    public IInventory func_94442_h_(World par1World, int par2, int par3, int par4)
    {
        Object object = (TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4);

        if (object == null)
        {
            return null;
        }
        else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
        {
            return null;
        }
        else if (isOcelotBlockingChest(par1World, par2, par3, par4))
        {
            return null;
        }
        else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
        {
            return null;
        }
        else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
        {
            return null;
        }
        else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
        {
            return null;
        }
        else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
        {
            return null;
        }
        else
        {
            if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
            {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityTeamChest)par1World.getBlockTileEntity(par2 - 1, par3, par4), (IInventory)object);
            }

            if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
            {
                object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityTeamChest)par1World.getBlockTileEntity(par2 + 1, par3, par4));
            }

            if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
            {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4 - 1), (IInventory)object);
            }

            if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
            {
                object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (TileEntityTeamChest)par1World.getBlockTileEntity(par2, par3, par4 + 1));
            }

            return (IInventory)object;
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntityTeamChest createNewTileEntity(World par1World)
    {
        TileEntityTeamChest TileEntityTeamChest = new TileEntityTeamChest();
        return TileEntityTeamChest;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return this.field_94443_a == 1;
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (!this.canProvidePower())
        {
            return 0;
        }
        else
        {
            int i1 = ((TileEntityTeamChest)par1IBlockAccess.getBlockTileEntity(par2, par3, par4)).numUsingPlayers;
            return MathHelper.clamp_int(i1, 0, 15);
        }
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 1 ? this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    /**
     * Looks for a sitting ocelot within certain bounds. Such an ocelot is considered to be blocking access to the
     * chest.
     */
    public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3)
    {
        Iterator iterator = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB((double)par1, (double)(par2 + 1), (double)par3, (double)(par1 + 1), (double)(par2 + 2), (double)(par3 + 1))).iterator();
        EntityOcelot entityocelot;

        do
        {
            if (!iterator.hasNext())
            {
                return false;
            }

            EntityOcelot entityocelot1 = (EntityOcelot)iterator.next();
            entityocelot = (EntityOcelot)entityocelot1;
        }
        while (!entityocelot.isSitting());

        return true;
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return 1;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("iron");
    }
}


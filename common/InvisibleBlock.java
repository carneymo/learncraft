package mods.learncraft.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

public class InvisibleBlock extends Block
{
    public InvisibleBlock(int par1, Material mat)
    {
        super(par1, mat);
        //this.setCreativeTab(CreativeTabs.tabBlock);
        //this.setLightOpacity(255);
        this.setBlockUnbreakable();
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.blockIcon = reg.registerIcon("learncraft:InvisibleBlock");
    }

    @Override
    public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int par5)
    {
        w.setBlock(x, y, z, this.blockID);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}

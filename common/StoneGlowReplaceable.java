package mods.learncraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class StoneGlowReplaceable extends Block
{
    public StoneGlowReplaceable(int par1, Material mat)
    {
        super(par1, mat);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.blockIcon = reg.registerIcon("learncraft:StoneGlowReplaceable");
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.cobblestone.blockID;
    }
}
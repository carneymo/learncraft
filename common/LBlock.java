package mods.learncraft.common;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.Explosion;

public class LBlock extends Block {

	public LBlock(int par1, Material mat) {
		super(par1, mat);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon("learncraft:PurpleBlock");
	}
	
	public int idDropped(int par1, Random random, int par2) {
		return 1;
	}

    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return false;
    }

}

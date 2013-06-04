package mods.learncraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class TeamDefenseBlockBlue extends Block 
{
	public TeamDefenseBlockBlue(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockUnbreakable();
		this.blockResistance = Block.obsidian.blockResistance;
	}
	
	@Override
	public void onBlockClicked(World w, int x, int y, int z, EntityPlayer player)
	{
		if(Common.blueteam.hasPlayer(player)) this.setHardness(0.1F);
		else this.setBlockUnbreakable();
	}

	public void registerIcons(IconRegister reg) 
	{
		this.blockIcon = reg.registerIcon("learncraft:TeamDefenseBlockBlue");
	}
	
	public int idDropped(int par1, Random random, int par2) 
	{
		return 525;
	}

}

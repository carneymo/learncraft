package mods.learncraft.common;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BorderBlock extends Block
{
	public int[] list = null;

	public BorderBlock(int par1, Material mat)
	{
		// Calls super constructor and sets the block to unbreakable
		super(par1, mat);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockUnbreakable();
	}

	@Override
	/**
	 * Forge/Minecraft is smart and looks for icons named "PurpleBlock" in the mod of "learncraft".
	 * So even though PurpleBlock is in the folder "learncraft/textures/blocks/PurpleBlock.png"
	 * you only need the mod name "learncraft" as well as the icon name "PurpleBlock"
	 */
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon("learncraft:BorderBlock");
	}

	/**
	 * When the block is placed, creates a list of blocks below and above it,
	 * then sets air to an invisible block and all others to a border block
	 */
	@Override
	public int onBlockPlaced(World w, int x, int y, int z, int par5,
			float par6, float par7, float par8, int par9)
	{
		// Creates a list of blocks from the bottom of the world to the top
		list = new int[w.getActualHeight()];

		// Loops through the height of the world
		for(int i = 0; i < w.getActualHeight(); i++)
		{
			// gets the block ID
			int ID = w.getBlockId(x, i, z);

			// if the increment is not equal to placed border block
			if(!(i == y))
			{
				// If the block is air or an invisible block
				if(ID == 0 | ID == 505)
				{
					// Set to invisible block
					w.setBlock(x, i, z, 505);
				} else
				{
					// Set to border block
					w.setBlock(x, i, z, 503);
				}
			}

			// Adds the block to the list
			list[i] = ID;
		}

		return par9;
	}

	/**
	 * When the block is destroyed, the blocks above and below the border block
	 * are replaced from the list
	 */
	@Override
	public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int par5)
	{
		// If the original block is destroyed
		if(list != null)
		{
			// for the height of the world
			for(int i = 0; i < w.getActualHeight(); i++)
			{
				// get the block ID for that index
				int blk_ID = list[i];

				// if air or border block
				if(blk_ID == 503 | blk_ID == 505)
				{
					// set as air
					blk_ID = 0;
				}

				// set block to block ID
				w.setBlock(x, i, z, blk_ID);
			}
		}
	}

	/**
	 * Returns the id of the block that is dropped from breaking
	 */
	@Override
	public int idDropped(int par1, Random random, int par2)
	{
		return 503;
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}
}

package mods.learncraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class LBlock extends Block
{

	/**
	 * Call parent's constructor and set tab in creative mode
	 */
	public LBlock(int par1, Material mat)
	{
		super(par1, mat);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	/**
	 * Forge/Minecraft is smart and looks for icons named "PurpleBlock" in the mod of "learncraft".
	 * So even though PurpleBlock is in the folder "learncraft/textures/blocks/PurpleBlock.png"
	 * you only need the mod name "learncraft" as well as the icon name "PurpleBlock"
	 */
	public void registerIcons(IconRegister reg)
	{
		this.blockIcon = reg.registerIcon("learncraft:PurpleBlock");
	}

	/**
	 * Returns the id of the block that is dropped from breaking
	 */
	@Override
	public int idDropped(int par1, Random random, int par2)
	{
		return 501;
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}

	@Override
	/**
	 * Testing teleportation on block activation (right clicking a learning block)
	 */
	public boolean onBlockActivated(World world, int par2, int par3, int par4,
			EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		// Add a potion effect with id 1 (movement speed potion), duration of
		// 20.83 * 1250 = 60 seconds, and amplifier of 1
		player.addPotionEffect(new PotionEffect(1, 1250, 4));
		player.addPotionEffect(new PotionEffect(8, 1250, 4));
		player.setSprinting(true);
		//System.out.println("Playing sound.");
		//player.playSound("learncraft:greeting", 1.0F, 1.0F);
		return true;
	}
}

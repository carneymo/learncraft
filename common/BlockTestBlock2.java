package mods.learncraft.common;


import java.io.File;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class BlockTestBlock2 extends Block {
	public BlockTestBlock2(int id, Material par2Material)
	{
	       super(id, par2Material);
	       this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon("learncraft:BlueTeamBlock");
	}
	
	
	
	@Override
    /**
     * Testing teleportation when right-clicking block
     */
    
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		//Check that the player is on the blue team
		if(Common.getTeam(player) == Common.blueteam) {
			ChunkCoordinates vector = player.getPlayerCoordinates();
			player.setPositionAndUpdate(vector.posX, vector.posY + 60, vector.posZ);
			this.removeBlockByPlayer(world, player, par2, par3, par4);
		}
    	return false;
	}
}
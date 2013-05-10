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

public class BlockTestBlock extends Block {
	public BlockTestBlock(int id, Material par2Material)
	{
	       super(id, par2Material);
	       this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon("learncraft:OrangeTeamBlock");
	}
	
	
	
	@Override
    /**
     * Testing teleportation when right-clicking block
     */
    
	
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		//1 is orange; 2 is blue	
		NBTTagCompound nbt = player.getEntityData();
		if(nbt.hasKey("myTeam")){
			int j = nbt.getInteger("myTeam");
			if(j==1||j==3) {
    			ChunkCoordinates vector = player.getPlayerCoordinates();
				player.setPositionAndUpdate(vector.posX, vector.posY + 60, vector.posZ);
    			this.removeBlockByPlayer(world, player, par2, par3, par4);
    		}
		}
    	return false;
	}
}

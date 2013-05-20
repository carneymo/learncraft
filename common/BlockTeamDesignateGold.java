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

public class BlockTeamDesignateGold extends Block {
	public BlockTeamDesignateGold(int id, Material par2Material)
	{
	       super(id, par2Material);
	       this.setCreativeTab(CreativeTabs.tabBlock);
	       this.setBlockUnbreakable();
	}

	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon("learncraft:GoldTeamBlock");
	}
	
	
	
	@Override
    /**
     * Testing teleportation when right-clicking block
     */
    
	
	
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		Common.teleportPlayerTo(player, "gold_arena");
    	return false;
	}
}

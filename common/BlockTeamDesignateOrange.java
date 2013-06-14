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

public class BlockTeamDesignateOrange extends Block {

	public static final String color = "orange";
	
	public BlockTeamDesignateOrange(int id, Material par2Material)
	{
	       super(id, par2Material);
	       this.setCreativeTab(CreativeTabs.tabBlock);
	       this.setBlockUnbreakable();
	       Common.addTeam(new Team(color));
	}

	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon("learncraft:OrangeTeamBlock");
	}

    public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	Team team = Common.getTeam(color);
    	if(team == null) {
    		team = new Team(color);
    		Common.addTeam(team);
    	}
    	if(team.hasPlayer(player)) {
    		Common.teleportPlayerTo(player, color+"_spawn",true);
    	} else {
    		if(Common.getTeam(player) != null) {
    			// Do nothing, player is on another team
    		} else {
    			team.addPlayer(player);
        		Common.teleportPlayerTo(player, color+"_spawn",true);
    		}
    	}
    	return false;
    }
}
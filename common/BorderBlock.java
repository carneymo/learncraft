package mods.learncraft.common;

import java.io.Console;
import java.sql.SQLException;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class BorderBlock extends Block
{
	public int[] list = null;
	
	public BorderBlock(int par1, Material mat) 
	{
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
	
	public int onBlockPlaced(World w, int x,int y,int z,int par5, float par6, float par7, float par8, int par9)
	{
		
		list = new int[w.getActualHeight()];
		
		for(int i = 0; i < w.getActualHeight(); i++)
		{
			int ID = w.getBlockId(x, i, z);
			if(!(i == y))
			{
				
				if(ID == 0 | ID == 505)
				{
					w.setBlock(x, i, z, 505);
				}
				else
				{
					w.setBlock(x, i, z, 503);
				}
			}
			list[i] = ID;
			
		}
		
		return par9;
	}
	
	public void onBlockDestroyedByPlayer(World w,int x,int y,int z,int par5)
	{
		if(list != null)
		{
			for(int i = 0; i < w.getActualHeight(); i++)
			{
				int blk_ID = list[i];
				if(blk_ID == 503 | blk_ID == 505)
				{
					blk_ID = 0;
				}
				w.setBlock(x, i, z, blk_ID);
			}
		}
	}
	/**
	 * Returns the id of the block that is dropped from breaking
	 */
	public int idDropped(int par1, Random random, int par2) 
	{
		return 503;
	}

    public boolean canDropFromExplosion(Explosion par1Explosion)
    {
        return false;
    }
    
    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec) {
    	
    }
    /*
    @Override
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) 
    {
    	System.out.println("help");
    	
    	int xchg = 0, ychg = 0, zchg = 0;
    	if(par5Entity.posX > (float)par2)
    		xchg = xchg + 2;
    	else if((int)par5Entity.posX == (int)par2)
    		xchg = xchg - 2;
    	if(par5Entity.posZ > (float)par4)
    		zchg = zchg + 2;
    	else if((int)par5Entity.posZ == (int)par4)
    		zchg = zchg - 2;
    	
    	System.out.println("X Block: " + par2 + ", X Player : " + par5Entity.posX);
    	System.out.println("Z Block: " + par4 + ", Z Player : " + par5Entity.posZ);
    	
    	par5Entity.setPosition(par5Entity.posX + xchg, par5Entity.posY, par5Entity.posZ + zchg);
    	par5Entity.setVelocity((double)xchg, 0.0, (double)zchg);
    
    }
    */
}

package mods.learncraft.common;

<<<<<<< HEAD
=======
import java.io.Console;
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
import java.sql.SQLException;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
<<<<<<< HEAD
=======
import net.minecraft.entity.EntityLiving;
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;


public class BorderBlock extends Block
{
	public BorderBlock(int par1, Material mat) 
	{
		super(par1, mat);
		this.setCreativeTab(CreativeTabs.tabBlock);
<<<<<<< HEAD
	}
	
=======
		this.setBlockUnbreakable();
	}
	
	/*
	
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
	public void onBlockAdded(World w, int x, int y, int z)
	{
		w.setBlock(x, y+1, z, 505);
	}
	
	public void onBlockDestroyedByPlayer(World w, int x, int y, int z, int b)
	{
		w.setBlockToAir(x, y+1, z);
	}
<<<<<<< HEAD
=======
	
	*/
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b

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
<<<<<<< HEAD
=======
    
    public void onEntityWalking(World par1World, int par2, int par3, int par4, EntityLiving par5Entity)
    {
    	System.out.println("\007");
    	
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
>>>>>>> dfa3be384a6321a7111578438271d61314ca092b
}
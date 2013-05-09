package mods.learncraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Digger extends Item
{

	public Digger(int par1) 
	{
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public int idDropped(int par1, Random random, int par2) 
	{
		return 504;
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
	     itemIcon = iconRegister.registerIcon("learncraft:pickaxePurple");
	}
	
	public boolean onItemUse(ItemStack is, EntityPlayer player, World w, int x, int y, int z, int l, float f, float f1, float f3)
	{
		Vec3 lookVector = player.getLookVec();
		double[] lookCoords = {lookVector.xCoord, lookVector.yCoord,lookVector.zCoord};
		double[] locations = {x, y, z};
		int keepConstant = 0;
		
		for(int i = 0; i < lookCoords.length; i++)
		{
			if(Math.abs(lookCoords[i]) > 0.8 && Math.abs(lookCoords[i]) <= 1.0)
				keepConstant = i;
		}
		
		
		switch(keepConstant)
		{
			case 0:
			{
				w.setBlockToAir(x, y, z);
				w.setBlockToAir(x, y+1, z);
				w.setBlockToAir(x, y-1, z);
			
				w.setBlockToAir(x, y+1, z+1);
				w.setBlockToAir(x, y-1, z+1);
				w.setBlockToAir(x, y, z+1);
				
				w.setBlockToAir(x, y+1, z-1);
				w.setBlockToAir(x, y-1, z-1);
				w.setBlockToAir(x, y, z-1);
				break;
			}
			case 1:
			{
				w.setBlockToAir(x, y, z);
				w.setBlockToAir(x, y, z-1);
				w.setBlockToAir(x, y, z+1);
				
				w.setBlockToAir(x+1, y, z);
				w.setBlockToAir(x+1, y, z+1);
				w.setBlockToAir(x+1, y, z-1);
				
				w.setBlockToAir(x-1, y, z);
				w.setBlockToAir(x-1, y, z+1);
				w.setBlockToAir(x-1, y, z-1);
				break;
			}
			case 2:
			{
				w.setBlockToAir(x, y, z);
				w.setBlockToAir(x, y+1, z);
				w.setBlockToAir(x, y-1, z);
				
				w.setBlockToAir(x+1, y+1, z);
				w.setBlockToAir(x+1, y-1, z);
				w.setBlockToAir(x+1, y, z);
				
				w.setBlockToAir(x-1, y+1, z);
				w.setBlockToAir(x-1, y-1, z);
				w.setBlockToAir(x-1, y, z);
				break;
			}
		}
		
		return true;
	
	}
	

		
}

package mods.learncraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class LearningPickaxe extends ItemPickaxe
{
	public LearningPickaxe (int par1, EnumToolMaterial par2)
	{
		super(par1, par2);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public int idDropped(int par1, Random random, int par2) 
	{
		return 508;
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
	     itemIcon = iconRegister.registerIcon("learncraft:pickaxePurple");
	}
	
	@Override
	public float getStrVsBlock(ItemStack par1, Block par2)
	{
		return 3;
	}
	
	
	
}
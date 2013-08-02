package mods.learncraft.common;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class LearningSword extends ItemSword
{
    public LearningSword(int par1, EnumToolMaterial par2)
    {
        super(par1, par2);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabCombat);
    }

    public int idDropped(int par1, Random random, int par2)
    {
        return 509;
    }

    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("learncraft:swordLearning");
    }

    public boolean hitEntity(ItemStack par1, EntityLiving par2, EntityLiving par3)
    {
        par2.setFire(2);
        return true;
    }
}
package com.github.JgamerXD.RandomUtilitiesMod.blocks;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockFlint extends Block
{

    public BlockFlint(Material material)
    {
        super(material);
        this.setCreativeTab(RandomUtilitiesMod.randomTab);
    }


    /**
     * Triggered whenever an entity walks on this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	if(par5Entity instanceof EntityLivingBase && !par5Entity.isSneaking())
    		par5Entity.attackEntityFrom(DamageSource.cactus, 1.0F);
    }

}

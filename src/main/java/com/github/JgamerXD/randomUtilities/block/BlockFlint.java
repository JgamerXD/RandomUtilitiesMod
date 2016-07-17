package com.github.jgamerXD.randomUtilities.block;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Janki on 12.07.2016.
 */
public class BlockFlint extends Block {
    public BlockFlint() {
        super(Material.GROUND, MapColor.BLACK);
        setSoundType(SoundType.SAND);
        setHarvestLevel("shovel",0);
        setHardness(1.0f);
        setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance)
    {
        entityIn.fall(fallDistance, 2.0F);
        if(!worldIn.isRemote && entityIn instanceof EntityLivingBase && fallDistance >= 5.0f)
        {
            EntityLivingBase el = (EntityLivingBase) entityIn;
            for(ItemStack i: el.getArmorInventoryList())
            {
                if(i != null && i.getItem() == Items.IRON_BOOTS)
                    el.setFire(15);
            }
        }
    }
}

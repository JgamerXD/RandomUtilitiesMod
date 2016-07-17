package com.github.jgamerXD.randomUtilities.block;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Janki on 12.07.2016.
 */
public class BlockSugar extends Block {
    private Random rnd = new Random();
    public BlockSugar() {
        super(Material.GROUND,MapColor.SNOW);
        setSoundType(SoundType.SAND);
        setHarvestLevel("shovel",0);
        setHardness(1.0f);
        setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public void fillWithRain(World worldIn, BlockPos pos) {
        if(!worldIn.isRemote && rnd.nextDouble() <=0.01)
        {
            worldIn.destroyBlock(pos,false);
        }
    }

}

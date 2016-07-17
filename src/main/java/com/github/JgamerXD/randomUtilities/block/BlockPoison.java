package com.github.jgamerXD.randomUtilities.block;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Janki on 12.07.2016.
 */
public class BlockPoison extends Block {
    private static Potion potion = Potion.getPotionFromResourceLocation("minecraft:poison");

    public BlockPoison() {
        super(Material.PLANTS, MapColor.RED);
        setHardness(0.8f);
        setSoundType(SoundType.SLIME);
        setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote && entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(potion,299));
        }
    }
}

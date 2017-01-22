package jgamerXD.randomUtilities.block;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;

import javax.tools.Tool;

/**
 * Created by Janki on 03.01.2017.
 */

@SuppressWarnings("deprication")

public class BlockGlowingSand extends BlockFalling {
    public BlockGlowingSand() {
        super(Material.SAND);

        this.setCreativeTab(RandomUtilitiesMod.utilitiesTab);

        this.setHarvestLevel("shovel",0);
        this.setLightLevel(1.0f);
    }

    @Override
    public float getAmbientOcclusionLightValue(IBlockState state) {
        return 1.0f;
    }

    @Override
    protected void onStartFalling(EntityFallingBlock fallingEntity) {
        super.onStartFalling(fallingEntity);
        fallingEntity.setGlowing(true);
    }
}

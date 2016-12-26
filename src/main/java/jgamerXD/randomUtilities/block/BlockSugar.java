package jgamerXD.randomUtilities.block;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 30;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 500;
    }
}

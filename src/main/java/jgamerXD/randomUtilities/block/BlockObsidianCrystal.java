package jgamerXD.randomUtilities.block;


import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Janki on 14.07.2016.
 */
public class BlockObsidianCrystal extends Block {
    public BlockObsidianCrystal() {
        super(Material.GLASS, MapColor.OBSIDIAN);
        setCreativeTab(RandomUtilitiesMod.utilitiesTab);
        setHardness(2.0f);
        setHarvestLevel("pickaxe",3);
        setResistance(2000.0F);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 5;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }
}

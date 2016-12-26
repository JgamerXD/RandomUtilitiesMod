package jgamerXD.randomUtilities.item;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import jgamerXD.randomUtilities.block.BlockPortableButtonTemp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Janki on 20.10.2016.
 */
public class ItemPortableButton extends Item {

    public ItemPortableButton() {
        super();
        setMaxStackSize(1);
        setMaxDamage(1561);
        this.setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        /*if(worldIn.isRemote)
            return EnumActionResult.SUCCESS;*/

        //System.out.println(facing.getName());

        BlockPos buttonPos = pos.offset(facing);


        if (worldIn.isAirBlock(buttonPos) && playerIn.canPlayerEdit(buttonPos, facing, stack)) {
            stack.damageItem(1, playerIn);

            double pxoff = (double) pos.getX() + (double) hitX - 0.2 + 0.2 * facing.getDirectionVec().getX();
            double pyoff = (double) pos.getY() + (double) hitY - 0.2 + 0.2 * facing.getDirectionVec().getY();
            double pzoff = (double) pos.getZ() + (double) hitZ - 0.2 + 0.2 * facing.getDirectionVec().getZ();

            for (int i = 0; i < 5; ++i) {
                double d0 = pxoff + worldIn.rand.nextDouble() * 0.4D;
                double d1 = pyoff + worldIn.rand.nextDouble() * 0.4D;
                double d2 = pzoff + worldIn.rand.nextDouble() * 0.4D;
                worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, hitX, hitY, hitZ, 0, 0, 0);
            worldIn.setBlockState(buttonPos, RandomUtilitiesMod.portableButtonTempBlock.getDefaultState().withProperty(BlockPortableButtonTemp.FACING, facing), 3);
        }

        return EnumActionResult.SUCCESS;
    }
}

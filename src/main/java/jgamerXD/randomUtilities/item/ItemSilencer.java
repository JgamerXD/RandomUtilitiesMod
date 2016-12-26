package jgamerXD.randomUtilities.item;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

/**
 * Created by Janki on 22.12.2016.
 */
public class ItemSilencer extends Item{

    public ItemSilencer() {
        this.setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (target instanceof EntityLiving) {
            EntityLiving entityliving = (EntityLiving) target;
            entityliving.setSilent(true);
            //entityliving.enablePersistence();
            --stack.stackSize;
            return true;
        } else {
            return super.itemInteractionForEntity(stack, playerIn, target, hand);
        }
    }

}

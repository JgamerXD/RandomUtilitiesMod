package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public interface IAttackModifier {
    public boolean hitEntity(ModifierInstance par1ModifierInstance, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase);
    public int getHitTime();
}

package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.entity.EntityLivingBase;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public interface IAttackModifier {
    public boolean hitEntity(ModifierInstance par1ModifierInstance, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase);
    public int getHitTime();
}

package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.entity.Entity;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public interface IShootingModifier {
    public boolean shootEntity(ModifierInstance par1ModifierInstance,Entity par2Entity);
    public int getShootTime();
}

package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public interface IShootingModifier {
    public boolean shootEntity(ModifierInstance par1ModifierInstance,Entity par2Entity);
    public int getShootTime();
}

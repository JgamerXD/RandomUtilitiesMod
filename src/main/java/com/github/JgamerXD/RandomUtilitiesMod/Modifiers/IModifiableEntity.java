package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.item.ItemStack;

/**
 * Created by JgamerXD on 05.08.2014.
 */
public interface IModifiableEntity {

    public ModifierStack getModifierStack();
    public void addModifier(ItemStack stack,ModifierInstance modifier);
}

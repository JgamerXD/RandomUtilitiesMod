package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.item.ItemStack;

public interface IModifiableItem
{
    //return ModifierStack of stack
    public ModifierStack getModifierStack(ItemStack stack);

    void addModifier(ItemStack stack,ModifierInstance modifier);
}


package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

public interface IModifiable
{
    public ModifierStack getModifierStack(ItemStack stack);

    public void addModifier(ItemStack stack,ModifierInstance modifier);
}


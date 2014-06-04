package com.github.JgamerXD.RandomUtilitiesMod

import com.github.JgamerXD.RandomUtilitiesMod.Modifier.{ModifierInstance, ModifierStack}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

/**
 * Created by JgamerXD on 04.06.2014.
 */
trait TModifiable {
  def getModifierStack(stack: ItemStack): ModifierStack = {
    val result: ModifierStack = new ModifierStack()
    if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Modifiers"))
    {
      result.readForomNBT(stack.getTagCompound().getTagList("Modifiers",0))
    }
      return result
  }

  def AddModifier(stack: ItemStack,modifier: ModifierInstance)
  {
    stack.getTagCompound().get
  }
}

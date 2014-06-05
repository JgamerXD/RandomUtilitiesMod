package com.github.JgamerXD.RandomUtilitiesMod

import com.github.JgamerXD.RandomUtilitiesMod.TModifiable
import net.minecraft.item.ItemBow
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

object ModifiedBow extends ItemBow with TModifiable{
  @Override
  def onPlayerStoppedUsing(arg0ItemStack: ItemStack, arg1World: World, arg2Player: EntityPlayer, arg3: Int) = {
    
  }

}
package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;

public class ModifierStack
{
	private ArrayList<Modifier> modifiers;

	ModifierStack()
	{

	}

	void readForomNBT(NBTTagList par1NBTTagList)
	{
		ItemStack item, focus;
		for (int var1 = 0; var1 < par1NBTTagList.tagCount(); ++var1)
		{
			NBTTagCompound var2 = (NBTTagCompound) par1NBTTagList.getCompoundTagAt(var1);

			focus = ItemStack.loadItemStackFromNBT(var2.getCompoundTag("focus"));

			NBTTagList var3 = (NBTTagList) var2.getTagList("items", 0);
			for (int var4 = 0; var4 < par1NBTTagList.tagCount(); ++var4)
			{
				item = ItemStack.loadItemStackFromNBT(var3.getCompoundTagAt(var4).getCompoundTag("item"));

				modifiers.add(ModifierRegistry.getModifier(focus, item));
			}
		}
	}

	NBTTagList writeToNBT(NBTTagList par1NBTTagList)
	{

		return par1NBTTagList;
	}
}

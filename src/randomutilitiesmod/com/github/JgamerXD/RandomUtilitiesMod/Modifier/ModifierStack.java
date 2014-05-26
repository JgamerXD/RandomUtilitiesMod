package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;

public class ModifierStack
{
	private ArrayList<ModifierInstance> modifiers;

	ModifierStack()
	{

	}
	
	public void addModifier(ModifierInstance modifier)
	{
		if(!modifiers.contains(modifier))
			modifiers.add(modifier);
	}

	void readForomNBT(NBTTagList par1NBTTagList)
	{
		String id;
		int level;
		for (int var1 = 0; var1 < par1NBTTagList.tagCount(); ++var1)
		{
			NBTTagCompound var2 = (NBTTagCompound) par1NBTTagList.getCompoundTagAt(var1);
			id = var2.getString("id");
			level = var2.getInteger("level");
			modifiers.add(new ModifierInstance(ModifierRegistry.getModifier(id),level));
		}
	}

	NBTTagList writeToNBT(NBTTagList par1NBTTagList)
	{
		String id;
		int level;
		NBTTagCompound tag = new NBTTagCompound();
		
		for (ModifierInstance current : modifiers)
		{
			id = current.getModifier().getId();
			tag.setString("id",id);
			level = current.getLevel();
			tag.setInteger("level", level);
			par1NBTTagList.appendTag(tag);
		}
		return par1NBTTagList;
	}
}

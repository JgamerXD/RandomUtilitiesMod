package com.github.JgamerXD.RandomUtilitiesMod.Items;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemObsidianCrystal extends Item 
{
	public ItemObsidianCrystal()
	{
		super();
		this.setCreativeTab(RandomUtilitiesMod.randomTab);
	}
}

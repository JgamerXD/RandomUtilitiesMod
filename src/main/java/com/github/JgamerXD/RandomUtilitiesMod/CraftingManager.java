package com.github.JgamerXD.RandomUtilitiesMod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingManager {
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystal,8,0), new Object[]{
			"XXX",
			"XYX",
			"XXX",
			'X' ,Blocks.obsidian,
			'Y', Items.diamond		
		});
		
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystal,9,0), new Object[]{
			"X",
			'X', RandomUtilitiesMod.obsidianCrystalBlock	
		});
		
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystalBlock,1,0), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X' , RandomUtilitiesMod.obsidianCrystal
		});
		
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.flintBlock,1,0), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X' ,Items.flint
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.flint,9,0), new Object[]{
			"X",
			'X', RandomUtilitiesMod.flintBlock	
		});
		
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.poisonBlock,1,0), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X' ,Items.spieder_eye
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.spider_eye,9,0), new Object[]{
			"X",
			'X', RandomUtilitiesMod.poisonBlock
		});
		
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.sugarBlock,1,0), new Object[]{
			"XXX",
			"XXX",
			"XXX",
			'X' ,Items.sugar
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.sugar,9,0), new Object[]{
			"X",
			'X', RandomUtilitiesMod.sugarBlock
		});
		
		}
	}
}

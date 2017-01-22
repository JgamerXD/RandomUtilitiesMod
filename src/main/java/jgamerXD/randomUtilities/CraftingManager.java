package jgamerXD.randomUtilities;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingManager {
	public static void load()
	{
		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.poser, 1, 0),
				"YXY",
				"XYX",
				"XYX",
				'X', Items.STRING,
				'Y', Items.STICK);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.portableButton, 1, 0),
				" X ",
				"YZY",
				" Y ",
				'X', Blocks.REDSTONE_TORCH,
				'Y', Blocks.WOODEN_PRESSURE_PLATE,
				'Z', Blocks.STONE_BUTTON);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.silencer, 3, 0),
				"XXX",
				"YYY",
				'X', Items.PAPER,
				'Y', Items.SLIME_BALL);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.itemFlintblock, 1, 0),
				"XXX",
				"XXX",
				"XXX",
				'X', Items.FLINT);

		GameRegistry.addShapelessRecipe(new ItemStack(Items.FLINT,9,0),RandomUtilitiesMod.itemFlintblock);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.itemPoisonblock, 1, 0),
				"XXX",
				"XXX",
				"XXX",
				'X', Items.SPIDER_EYE);

		GameRegistry.addShapelessRecipe(new ItemStack(Items.SPIDER_EYE,9,0),RandomUtilitiesMod.itemPoisonblock);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.itemSugarblock, 1, 0),
				"XXX",
				"XXX",
				"XXX",
				'X', Items.SUGAR);

		GameRegistry.addShapelessRecipe(new ItemStack(Items.SUGAR,9,0),RandomUtilitiesMod.itemSugarblock);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystal, 1, 0),
				"XXX",
				"XYX",
				"XXX",
				'X', Blocks.OBSIDIAN,
				'Y', Items.DIAMOND);

		GameRegistry.addRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystalBlock, 1, 0),
				"XXX",
				"XXX",
				"XXX",
				'X', RandomUtilitiesMod.obsidianCrystal);

		GameRegistry.addShapelessRecipe(new ItemStack(RandomUtilitiesMod.obsidianCrystal,9,0),RandomUtilitiesMod.itemObsidianCrystalBlock);

		GameRegistry.addShapelessRecipe(new ItemStack(RandomUtilitiesMod.glowsand,1,0),Blocks.SAND,Items.GLOWSTONE_DUST);
	}


}

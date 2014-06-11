package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.shooting.ModifierMultiProjectile;
import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;
import net.minecraft.init.Items;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public class ModifierManager {
    public static void registerModifriers()
    {
        Modifier multiProjectile = new ModifierMultiProjectile();
        ModifierRegistry.registerRecipe(new ModifierInstance(multiProjectile,4),new ModifierRecipe(Items.arrow, RandomUtilitiesMod.multyArrow, RandomUtilitiesMod.modifiedBow));
        ModifierRegistry.
    }
}

package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import com.github.JgamerXD.RandomUtilitiesMod.modifiers.shooting.ModifierMultiProjectile;
import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;
import net.minecraft.init.Items;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public class ModifierManager {
    public static void registerModifriers()
    {
        //Modifier name = new <modifierClass>();
        //ModifierRegistry.registerRecipe(new ModifierInstance(name,level),new ModifierRecipe(Item, Focus, IModifiableItem));
        //ModifierRegistry.registerModifiable(notModifiable, (IModifiable) IModifiableItem);

        Modifier multiProjectile = new ModifierMultiProjectile();
        ModifierRegistry.registerRecipe(new ModifierInstance(multiProjectile,1),new ModifierRecipe(Items.arrow, RandomUtilitiesMod.arrowFocus, RandomUtilitiesMod.modifiedBow));
        ModifierRegistry.registerModifiable(Items.bow, (IModifiableItem) RandomUtilitiesMod.modifiedBow);
    }
}

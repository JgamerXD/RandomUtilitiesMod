package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ModifierRegistry
{
	//focus , item
	private static HashMap recipes = new HashMap<ModifierRecipe, ModifierInstance>();
	private static HashMap modifiers = new HashMap<String,Modifier>();
	private static HashMap modifiableItems = new HashMap<Item,IModifiable>();
	
	
	public static void registerModifer(Modifier modifier,String id)
	{
		if(modifiers.get(id) == null) {
            modifiers.put(id, modifier);
        }
		else
			System.err.print("Could not register modifier: "+modifier.getClass()+" id already occupied");
	}
	
	public static Modifier getModifier(String id)
	{
		return (Modifier) modifiers.get(id);
	}
	
	public static void registerModifiable(Item item,IModifiable modifiable)
	{
		modifiableItems.put(item,modifiable);
	}
	
	public static void registerRecipe(ModifierInstance modifier, ItemStack focus, ItemStack item)
	{
		HashMap focusMap = (HashMap) recipes.get(focus.getItem());
		if(focusMap == null)
			focusMap = new HashMap<Item , Modifier>();
		if(focusMap.get(item.getItem()) != null)
			System.err.print("Could not register recipe for: "+modifier.getClass()+" item/focus already occupied");
		else
			focusMap.put(item.getItem(), modifier);
		recipes.put(focus.getItem(), focusMap);
	}
	
	public static void registerRecipe(ModifierInstance modifier, ModifierRecipe recipe)
	{
		
		if(recipes.containsKey(recipe))
			System.err.print("Could not register recipe for: "+modifier.getClass()+" recipe already occupied");
		else
			recipes.put(recipe, modifier);
	}
	

	
	public static ModifierInstance getResult(ModifierRecipe recipe)
	{
        ModifierInstance result;
        result = (ModifierInstance) recipes.get(recipe);
        System.out.println("Result: " + result);
        return (ModifierInstance) recipes.get(recipe);
	}

    public static IModifiable getModifiable(Item item)
    {
        return (IModifiable) modifiableItems.get(item);
    }
	
	public static boolean isRecipe(ModifierRecipe recipe)
	{
		return recipes.containsKey(recipe);
	}

}

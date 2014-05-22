package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModifierRegistry
{
	//focus , item
	private static HashMap recipes = new HashMap<Item, HashMap<Item , ModifierInstance>>();
	private static HashMap modifiers = new HashMap<String,Modifier>();
	
	public static void registerModifer(Modifier modifier,String id)
	{
		if(modifiers.get(id) == null)
			modifiers.put(modifier,id);
		else
			System.err.print("Could not register modifier: "+modifier.getClass()+" id already occupied");
	}
	
	public static Modifier getModifier(String id)
	{
		return (Modifier) modifiers.get(id);
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
	
	public static void registerRecipe(ModifierInstance modifier, Item focus, Item item)
	{
		HashMap focusMap = (HashMap) recipes.get(focus);
		if(focusMap == null)
			focusMap = new HashMap<Item , Modifier>();
		if(focusMap.get(item) != null)
			System.err.print("Could not register recipe for: "+modifier.getClass()+" item/focus already occupied");
		else
			focusMap.put(item, modifier);
		recipes.put(focus, focusMap);
	}
	

	
	public static Modifier getResult(Item focus, Item item)
	{	
		HashMap focusMap=(HashMap) recipes.get(focus);
		
		if(focusMap != null)
			return (Modifier)focusMap.get(item);
		return null;
	}
	
	public static Modifier getResult(ItemStack focus, ItemStack item)
	{	
		HashMap focusMap=(HashMap) recipes.get(focus.getItem());
		
		if(focusMap != null)
			return (Modifier)focusMap.get(item.getItem());
		return null;
	}
	
	public static boolean isFocus(ItemStack item)
	{
		return recipes.get(item.getItem()) != null;
	}
}

package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModifierRegistry
{
	//focus , item
	private static HashMap modifiers = new HashMap<Item, HashMap<Item , Modifier>>();
	
	public static void registerModifier(Modifier modifier, ItemStack focus, ItemStack item)
	{
		HashMap focusMap = (HashMap) modifiers.get(focus.getItem());
		if(focusMap == null)
			focusMap = new HashMap<Item , Modifier>();
		if(focusMap.get(item.getItem()) != null)
			System.err.print("Could not register modifier: "+modifier.getClass()+" item/focus already occupied");
		else
			focusMap.put(item.getItem(), modifier);
		modifiers.put(focus.getItem(), focusMap);
	}
	
	public static void registerModifier(Modifier modifier, Item focus, Item item)
	{
		HashMap focusMap = (HashMap) modifiers.get(focus);
		if(focusMap == null)
			focusMap = new HashMap<Item , Modifier>();
		if(focusMap.get(item) != null)
			System.err.print("Could not register modifier: "+modifier.getClass()+" item/focus already occupied");
		else
			focusMap.put(item, modifier);
		modifiers.put(focus, focusMap);
	}
	

	
	public static Modifier getModifier(Item focus, Item item)
	{	
		HashMap focusMap=(HashMap) modifiers.get(focus);
		
		if(focusMap != null)
			return (Modifier)focusMap.get(item);
		return null;
	}
	
	public static Modifier getModifier(ItemStack focus, ItemStack item)
	{	
		HashMap focusMap=(HashMap) modifiers.get(focus.getItem());
		
		if(focusMap != null)
			return (Modifier)focusMap.get(item.getItem());
		return null;
	}
	
	public static boolean isModifier(ItemStack item)
	{
		return modifiers.get(item.getItem()) != null;
	}
}

package com.github.JgamerXD.RandomUtilitiesMod.Modifiers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModifierRecipe
{
	private Item modifier;
	private Item focus;
	private Item item;
	
	public ModifierRecipe(ItemStack modifier, ItemStack focus, ItemStack item)
	{
		this.modifier = modifier.getItem();
		this.focus = focus.getItem();
		this.item = item.getItem();
	}
	
	public ModifierRecipe(Item modifier, Item focus, Item item)
	{
		this.modifier = modifier;
		this.focus = focus;
		this.item = item;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((focus == null) ? 0 : focus.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModifierRecipe other = (ModifierRecipe) obj;
		if (focus == null)
		{
			if (other.focus != null)
				return false;
		}
		else if (!focus.equals(other.focus))
			return false;
		if (item == null)
		{
			if (other.item != null)
				return false;
		}
		else if (!item.equals(other.item))
			return false;
		if (modifier == null)
		{
			if (other.modifier != null)
				return false;
		}
		else if (!modifier.equals(other.modifier))
			return false;
		return true;
	}

}

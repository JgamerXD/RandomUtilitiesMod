package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import com.github.JgamerXD.RandomUtilitiesMod.Items.ItemModifierFocus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class Modifier {
	
	ItemStack item;
	ItemStack focus;

	Modifier(ItemStack focus,ItemStack item)
	{
		
	}
	
	//Item
	void onRightClick(ItemStack item){}
	void onHit(ItemStack item,EntityLivingBase entityHit){}
	
	//Entity
	void onHit(IEntityModified entity,EntityLivingBase entityHit){}	
	void entityUpdate(IEntityModified entity){}
	void entityHitGround(IEntityModified entity){}
	
	
}

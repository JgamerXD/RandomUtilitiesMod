package com.github.JgamerXD.RandomUtilitiesMod.Modifier;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class Modifier {

	Modifier(ItemStack item)
	{
		
	}
	
	//Item
	void onRightClick(ItemStack item){}
	void onHit(ItemStack item,EntityLivingBase entityHit){}
	
	//Entity
	void onHit(EntityModified entity,EntityLivingBase entityHit){}	
	void entityUpdate(EntityModified entity){}
	void entityHitGround(EntityModified entity){}
	
	
}

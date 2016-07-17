package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class Modifier {
	private final String id;

    public Modifier(String id)
    {
        ModifierRegistry.registerModifer(this, id);
        this.id = id;
    }

	public String getId() {
		return id;
	}

	//Item
	void onRightClick(ItemStack item) {
	}
	void onHit(ItemStack item,EntityLivingBase entityHit) {
	}
	
	//Entity
	void onHit(IEntityModified entity,EntityLivingBase entityHit) {
	}	
	void entityUpdate(IEntityModified entity) {
	}
	void entityHitGround(IEntityModified entity) {
	}
}

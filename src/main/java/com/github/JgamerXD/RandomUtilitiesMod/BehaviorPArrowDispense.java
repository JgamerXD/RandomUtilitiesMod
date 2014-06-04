package com.github.JgamerXD.RandomUtilitiesMod;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class BehaviorPArrowDispense extends BehaviorProjectileDispense
{
    final MinecraftServer mcServer;

    public BehaviorPArrowDispense(MinecraftServer par1)
    {
        this.mcServer = par1;
    }


	@Override
	protected IProjectile getProjectileEntity(World arg0, IPosition arg1) {
	      EntityPArrow var3 = new EntityPArrow(arg0, arg1.getX(), arg1.getY(),arg1.getZ());
	      var3.canBePickedUp = 1;
	      return var3;
	}
}


package com.github.JgamerXD.RandomUtilitiesMod.Modifiers.shooting;

import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.IShootingModifier;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.Modifier;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.ModifierInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public class ModifierMultiProjectile extends Modifier implements IShootingModifier{

    public ModifierMultiProjectile() {
        super("MultiProjectile");
    }

    @Override
    public boolean shootEntity(ModifierInstance par1ModifierInstance, Entity par2Entity) {
        double pitch = par2Entity.rotationPitch;
        double yaw = par2Entity.rotationYaw;
        double step = 0.1;
        double phi = 0.0;

        for(int i=0;i < par1ModifierInstance.getLevel();i++)
        {
            double r = 0.1 * 1;
            double p = pitch + r * Math.cos(phi);
            double y = yaw + r * Math.sin(phi);


            phi += step;
            EntityArrow tmp = new EntityArrow(par2Entity.worldObj);
            tmp.copyDataFrom(par2Entity, true);
            tmp.setAngles((float)p,(float)y);
            par2Entity.worldObj.spawnEntityInWorld(tmp);
        }
        return true;
    }

    @Override
    public int getShootTime() {
        return 1;
    }
}

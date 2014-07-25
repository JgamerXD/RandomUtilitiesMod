package com.github.JgamerXD.RandomUtilitiesMod.Modifiers.shooting;

import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.IShootingModifier;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.Modifier;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.ModifierInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
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
        double xCoord = par2Entity.posX;
        double yCoord = par2Entity.posY;
        double zCoord = par2Entity.posZ;
        float speed = 1;

        double step = 2;
        double phi = step;

        System.out.println("Shooting multiple arrows!!");

        for(int i=0;i < (par1ModifierInstance.getLevel()*3);i++)
        {
            double r = 2;
            float p = (float)(pitch + r * Math.cos(phi));
            float y = (float)(yaw + r * Math.sin(phi));


            phi += step;
            EntityArrow tmp = new EntityArrow(par2Entity.worldObj);
            tmp.copyDataFrom(par2Entity, true);
            tmp.canBePickedUp = 2;

            tmp.setAngles(p,y);

            tmp.motionX = (double)(-MathHelper.sin(y / 180.0F * (float) Math.PI) * MathHelper.cos(p / 180.0F * (float)Math.PI));
            tmp.motionZ = (double)(-MathHelper.cos(y / 180.0F * (float)Math.PI) * MathHelper.cos(p / 180.0F * (float)Math.PI));
            tmp.motionY = (double)(-MathHelper.sin(p / 180.0F * (float)Math.PI));
            tmp.setThrowableHeading(tmp.motionX, tmp.motionY, tmp.motionZ,speed * -1.5F, 1.0F);

            par2Entity.worldObj.spawnEntityInWorld(tmp);
        }
        return true;
    }

    @Override
    public int getShootTime() {
        return 1;
    }
}

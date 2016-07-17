package com.github.JgamerXD.RandomUtilitiesMod.lightNetwork;

import net.minecraft.util.Vec3;

/**
 * Created by JgamerXD on 04.08.2014.
 */
public interface ILightEmitter
{
    //Returns, if the Emitter still exists (may be moved to LightSource)
    boolean exists();

    //Returns the Vec3 from where the LightSource is casted
    Vec3 getSourcePoint(LightSource source);

}

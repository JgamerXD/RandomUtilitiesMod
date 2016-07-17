package com.github.JgamerXD.RandomUtilitiesMod.tileEntities;

import com.github.JgamerXD.RandomUtilitiesMod.lightNetwork.ILightEmitter;
import com.github.JgamerXD.RandomUtilitiesMod.lightNetwork.LightSource;
import net.minecraft.util.Vec3;

/**
 * Created by JgamerXD on 05.08.2014.
 */
public class TileEntityCreativeLightSource implements ILightEmitter {

    public void updateEntity()
    {

    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public Vec3 getSourcePoint(LightSource source) {
        return null;
    }
}

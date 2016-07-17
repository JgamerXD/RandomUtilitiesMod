package com.github.JgamerXD.RandomUtilitiesMod.lightNetwork;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by JgamerXD on 05.08.2014.
 */
public interface ILightReceptor {

    LightHandler getLightHandler();
    void doWork(LightHandler workProvider);
}

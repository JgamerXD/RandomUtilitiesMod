package com.github.JgamerXD.RandomUtilitiesMod.lightNetwork;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by JgamerXD on 14.08.2014.
 */
public class LightSource {
    private final ILightEmitter emitter;
    private ILightReceptor target;

    public Vec3 direction;
    public float strength;

    private World world;


    public LightSource(ILightEmitter emitter, Vec3 direction, float strength, World world)
    {
        this.emitter = emitter;
        this.direction = direction;
        this.strength = strength;
        this.world = world;
    }

    public boolean valid()
    {
        return true;
    }

    public void update()
    {
        //TODO: Remove Source from Receptor
        if(!emitter.exists())
        {
            return;
        }


        MovingObjectPosition var1Target = world.rayTraceBlocks(emitter.getSourcePoint(this), direction);
        TileEntity tile = world.getTileEntity(var1Target.blockX, var1Target.blockY, var1Target.blockZ);
        if(tile != null && tile instanceof ILightReceptor){
            if(tile == target){
                //TODO: Update source at Receptor
            }
            else {
                //TODO: Remove source from old target
                ((ILightReceptor) tile).getLightHandler().addSource(this);
            }
        }

    }

}

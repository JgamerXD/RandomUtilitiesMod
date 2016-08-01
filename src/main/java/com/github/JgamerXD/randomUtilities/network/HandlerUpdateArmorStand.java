package com.github.jgamerXD.randomUtilities.network;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Janki on 10.07.2016.
 */
public class HandlerUpdateArmorStand implements IMessageHandler<PacketUpdateArmorStand, IMessage> {
    @Override
    public IMessage onMessage(PacketUpdateArmorStand message, MessageContext ctx) {
        EntityArmorStand armorStand = (EntityArmorStand) RandomUtilitiesMod.proxy.getPlayerFromContext(ctx).worldObj.getEntityByID(message.entityID);
        armorStand.rotationYaw = message.rotation;
        armorStand.setHeadRotation(message.head);
        armorStand.setBodyRotation(message.body);
        armorStand.setLeftArmRotation(message.arm_l);
        armorStand.setRightArmRotation(message.arm_r);
        armorStand.setLeftLegRotation(message.leg_l);
        armorStand.setRightLegRotation(message.leg_r);
        return null;
    }
}


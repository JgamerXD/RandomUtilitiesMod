package jgamerXD.randomUtilities.network;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Janki on 21.08.2016.
 */
public class HandlerSetElytraFlying implements IMessageHandler<PacketSetElytraFlying, IMessage> {
    @Override
    public IMessage onMessage(PacketSetElytraFlying message, MessageContext ctx) {
        Entity entity = RandomUtilitiesMod.proxy.getPlayerFromContext(ctx).worldObj.getEntityByID(message.entityID);
        if(entity != null && entity instanceof EntityPlayerMP)
            ((EntityPlayerMP) entity).setElytraFlying();
        return null;
    }
}

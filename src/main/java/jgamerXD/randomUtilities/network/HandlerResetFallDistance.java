package jgamerXD.randomUtilities.network;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Janki on 21.08.2016.
 */
public class HandlerResetFallDistance implements IMessageHandler<PacketResetFallDistance, IMessage> {
    @Override
    public IMessage onMessage(PacketResetFallDistance message, MessageContext ctx) {
        EntityLivingBase entity = (EntityLivingBase) RandomUtilitiesMod.proxy.getPlayerFromContext(ctx).worldObj.getEntityByID(message.entityID);
        if(entity != null)
            entity.fallDistance = 1;
        return null;
    }
}

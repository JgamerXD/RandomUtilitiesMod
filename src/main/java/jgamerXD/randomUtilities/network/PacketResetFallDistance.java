package jgamerXD.randomUtilities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Janki on 21.08.2016.
 */
public class PacketResetFallDistance implements IMessage {
    public int entityID = 0;

    public PacketResetFallDistance() {}

    public PacketResetFallDistance(EntityLivingBase entity)
    {
        entityID = entity.getEntityId();
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
    }
}

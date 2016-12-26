package jgamerXD.randomUtilities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by Janki on 21.08.2016.
 */
public class PacketSetElytraFlying implements IMessage {
    public int entityID = 0;

    public PacketSetElytraFlying() {}

    public PacketSetElytraFlying(EntityPlayer entity)
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

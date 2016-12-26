package jgamerXD.randomUtilities.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.math.Rotations;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;


/**
 * Created by Janki on 10.07.2016.
 */
public class PacketUpdateArmorStand implements IMessage {

    public int entityID = 0;
    public float rotation = 0;
    public Rotations head = new Rotations(0,0,0);
    public Rotations body = new Rotations(0,0,0);
    public Rotations arm_r = new Rotations(0,0,0);
    public Rotations arm_l = new Rotations(0,0,0);
    public Rotations leg_r = new Rotations(0,0,0);
    public Rotations leg_l = new Rotations(0,0,0);




    public PacketUpdateArmorStand() {
    }

    public PacketUpdateArmorStand(EntityArmorStand armorStand) {
        rotation = armorStand.rotationYaw;
        entityID = armorStand.getEntityId();
        head = armorStand.getHeadRotation();
        body = armorStand.getBodyRotation();
        arm_l = armorStand.getLeftArmRotation();
        arm_r = armorStand.getRightArmRotation();
        leg_l = armorStand.getLeftLegRotation();
        leg_r = armorStand.getRightLegRotation();
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = buf.readInt();
        rotation = buf.readFloat();
        head = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
        body = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
        arm_l = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
        arm_r = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
        leg_l = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
        leg_r = new Rotations(buf.readFloat(),buf.readFloat(),buf.readFloat());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);

        buf.writeFloat(rotation);

        buf.writeFloat(head.getX());
        buf.writeFloat(head.getY());
        buf.writeFloat(head.getZ());

        buf.writeFloat(body.getX());
        buf.writeFloat(body.getY());
        buf.writeFloat(body.getZ());

        buf.writeFloat(arm_l.getX());
        buf.writeFloat(arm_l.getY());
        buf.writeFloat(arm_l.getZ());

        buf.writeFloat(arm_r.getX());
        buf.writeFloat(arm_r.getY());
        buf.writeFloat(arm_r.getZ());

        buf.writeFloat(leg_l.getX());
        buf.writeFloat(leg_l.getY());
        buf.writeFloat(leg_l.getZ());

        buf.writeFloat(leg_r.getX());
        buf.writeFloat(leg_r.getY());
        buf.writeFloat(leg_r.getZ());

        buf.writeFloat(head.getX());
        buf.writeFloat(head.getY());
        buf.writeFloat(head.getZ());

    }
}

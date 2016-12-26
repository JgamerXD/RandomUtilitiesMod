package jgamerXD.randomUtilities;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.items.ItemHandlerHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Janki on 16.07.2016.
 */
public class RandomEventHandler {
    private static Method setShowArmsMethod = ReflectionHelper.findMethod(EntityArmorStand.class,null,new String[]{"func_175413_k","setShowArms"},boolean.class);

    @SubscribeEvent
    public void interactSpecific(PlayerInteractEvent.EntityInteractSpecific evt) throws InvocationTargetException, IllegalAccessException {
        if (evt.getItemStack() != null
                && evt.getTarget() instanceof EntityArmorStand
                && evt.getItemStack().getItem() == Items.STICK
                && evt.getEntityPlayer().isSneaking()) {
            EntityArmorStand target = (EntityArmorStand) evt.getTarget();
            setShowArmsMethod.invoke(target, !target.getShowArms());
            if (!target.getShowArms() && !evt.getWorld().isRemote) {
                if (target.getHeldItemMainhand() != null) {
                    target.entityDropItem(target.getHeldItemMainhand(), 1.0F);
                    target.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
                }
                if (target.getHeldItemOffhand() != null) {
                    target.entityDropItem(target.getHeldItemOffhand(), 1.0F);
                    target.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, null);
                }
            }
            evt.setCanceled(true);
        }
    }
}

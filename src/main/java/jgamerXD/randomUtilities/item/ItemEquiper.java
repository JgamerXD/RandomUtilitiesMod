package jgamerXD.randomUtilities.item;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import jgamerXD.randomUtilities.gui.GuiArmorstandEquiper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Janki on 14.07.2016.
 */
public class ItemEquiper extends Item {
    public ItemEquiper() {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @SubscribeEvent
    public void interactSpecific(PlayerInteractEvent.EntityInteractSpecific evt) {
        EntityPlayer player = evt.getEntityPlayer();
        //if (!player.worldObj.isRemote) return;

        if (evt.getItemStack() != null
                && evt.getTarget() instanceof EntityArmorStand
                && evt.getItemStack().getItem() == this) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiArmorstandEquiper(player, (EntityArmorStand) evt.getTarget()));
        }

    }
}

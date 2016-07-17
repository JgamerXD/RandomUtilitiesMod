package com.github.jgamerXD.randomUtilities.item;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import com.github.jgamerXD.randomUtilities.gui.GuiArmorstandEquiper;
import com.github.jgamerXD.randomUtilities.gui.GuiArmorstandPoser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
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

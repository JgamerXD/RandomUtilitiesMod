package jgamerXD.randomUtilities.gui;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Janki on 14.07.2016.
 */
public class GuiArmorstandEquiper extends GuiContainer{

    public static ResourceLocation EQUIPER_GUI_TEXTURE = new ResourceLocation(RandomUtilitiesMod.MODID+":textures/gui/equiper");


    public GuiArmorstandEquiper(EntityPlayer player, EntityArmorStand armorStand) {
        super(new ContainerArmorEquiper(player,armorStand));
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(EQUIPER_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}

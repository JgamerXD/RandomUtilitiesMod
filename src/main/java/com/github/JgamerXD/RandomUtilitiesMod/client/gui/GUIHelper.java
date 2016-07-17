package com.github.JgamerXD.RandomUtilitiesMod.client.gui;

import com.github.JgamerXD.RandomUtilitiesMod.utility.Util;
import com.github.JgamerXD.RandomUtilitiesMod.reference.GUIElemets;
import com.github.JgamerXD.RandomUtilitiesMod.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;

/**
 * Created by JgamerXD on 11.08.2014.
 */
public class GUIHelper {

    public static void drawLightBar(GuiContainer gui, int x, int y, double part)
    {
        gui.mc.renderEngine.bindTexture(Textures.GUI_ELEMENTS);
        gui.drawTexturedModalRect(x, y, GUIElemets.LIGHT_BAR_X + 5, GUIElemets.LIGHT_BAR_Y, 5, 72);

        int pixelsToDraw = Util.getScaledAmount(70, part, 1);
        if (pixelsToDraw > 0)
            gui.drawTexturedModalRect(x, y + 72 - pixelsToDraw, GUIElemets.LIGHT_BAR_X, GUIElemets.LIGHT_BAR_Y + 72 - pixelsToDraw, 5, 72);
    }

    public static void drawLightBar(GuiContainer gui, int x, int y, double part, double required)
    {
        drawLightBar(gui,x,y,part);
        int barHeight = Util.getScaledAmount(70, required, 1) + 2;
        if (barHeight > 0 && barHeight < 72)
            gui.drawTexturedModalRect(x - 3, y + 72 - barHeight, GUIElemets.LIGHT_REQUIRED_X, GUIElemets.LIGHT_REQUIRED_Y, 11, 5);

    }
}

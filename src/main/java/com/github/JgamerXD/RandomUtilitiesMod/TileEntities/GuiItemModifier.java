package com.github.JgamerXD.RandomUtilitiesMod.TileEntities;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiItemModifier extends GuiContainer
{
	private static final ResourceLocation image = new ResourceLocation(RandomUtilitiesMod.modid + ":"
			+ "textures/gui/item_modifier.png");
	private TileEntityItemModifier itemModifier;

	public GuiItemModifier(InventoryPlayer par1InventoryPlayer, TileEntityItemModifier par2TileEntityItemModifier)
	{
		super(new ContainerItemModifier(par1InventoryPlayer, par2TileEntityItemModifier));
		this.itemModifier = par2TileEntityItemModifier;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = this.itemModifier.isInvNameLocalized() ? this.itemModifier.getInventoryName() : I18n.format(this.itemModifier.getInventoryName());
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
//		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the
	 * items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(image);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int i1 = (98 / 49) * this.itemModifier.getScaledProgress(49);
        //System.out.println(this.itemModifier.progress + ": " + i1);
		if (i1 > 0)
		{
			this.drawTexturedModalRect(k + 39, l + 16, 0, 166, i1, 55);
		}
	}
}

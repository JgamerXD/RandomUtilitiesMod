package com.github.JgamerXD.RandomUtilitiesMod.TileEntities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiItemModifier extends GuiContainer
{
	private static final ResourceLocation image = new ResourceLocation("textures/gui/container/brewing_stand.png");
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
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
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
		int i1 = this.itemModifier.progress;

		if (i1 > 0)
		{
			int j1 = (int) (28.0F * (1.0F - (float) i1 / 400.0F));

			if (j1 > 0)
			{
				this.drawTexturedModalRect(k + 97, l + 16, 176, 0, 9, j1);
			}

			int k1 = i1 / 2 % 7;

			switch (k1)
			{
				case 0:
					j1 = 29;
					break;
				case 1:
					j1 = 24;
					break;
				case 2:
					j1 = 20;
					break;
				case 3:
					j1 = 16;
					break;
				case 4:
					j1 = 11;
					break;
				case 5:
					j1 = 6;
					break;
				case 6:
					j1 = 0;
			}

			if (j1 > 0)
			{
				this.drawTexturedModalRect(k + 65, l + 14 + 29 - j1, 185, 29 - j1, 12, j1);
			}
		}
	}
}

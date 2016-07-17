package com.github.jgamerXD.randomUtilities.gui;


import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTest extends GuiContainer
{
	private static final ResourceLocation image = new ResourceLocation(RandomUtilitiesMod.MODID,/* + ":"
			+*/ "/gui/demo_background.png");
	//private TileEntityItemModifier itemModifier;
	private String text;

	public GuiTest(String text, InventoryPlayer inventoryPlayer)
	{
		super(new ContainerTest(inventoryPlayer));
		this.text = text;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//String s = this.itemModifier.isInvNameLocalized() ? this.itemModifier.getInventoryName() : I18n.format(this.itemModifier.getInventoryName());
		//this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawStringWithShadow(I18n.format(text), this.xSize / 2 - this.fontRendererObj.getStringWidth(text) / 2, this.ySize / 2 + 2, 4210752);
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
	}
}

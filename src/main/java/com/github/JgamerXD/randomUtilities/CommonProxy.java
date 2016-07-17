package com.github.jgamerXD.randomUtilities;

import com.github.jgamerXD.randomUtilities.gui.ContainerTest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler

{

	public void loadModels(){}

	public EntityPlayer getPlayerFromContext(MessageContext ctx)
	{
		return ctx.side == Side.SERVER? ctx.getServerHandler().playerEntity: Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

		switch(ID)
		{
			case 0:
			return new ContainerTest(player.inventory);
		}
			

		return null;

	}

}

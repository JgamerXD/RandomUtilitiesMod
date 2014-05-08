package com.github.JgamerXD.RandomUtilitiesMod;

import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.ContainerItemModifier;
import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.GuiItemModifier;
import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.TileEntityItemModifier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler

{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null)
		{
		switch(ID)
			{
			case 0: 
				System.out.println("Hi");
				return new ContainerItemModifier(player.inventory, (TileEntityItemModifier) tileEntity);
			}
			
		}
		return null;

	}

}

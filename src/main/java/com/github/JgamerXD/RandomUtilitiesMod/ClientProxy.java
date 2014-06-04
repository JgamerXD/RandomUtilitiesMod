package com.github.JgamerXD.RandomUtilitiesMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.GuiItemModifier;
import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.TileEntityItemModifier;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		System.out.println("Ho");
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if(tileEntity != null)
		{
		switch(ID)
			{
			case 0: 
				System.out.println("Ho");
				return new GuiItemModifier(player.inventory, (TileEntityItemModifier) tileEntity);
			}
			
		}
		return null;

	}
}

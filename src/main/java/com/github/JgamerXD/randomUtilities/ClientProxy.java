package com.github.jgamerXD.randomUtilities;


import com.github.jgamerXD.randomUtilities.gui.GuiTest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{

	public void loadModels()
	{
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		meshItem(mesher,RandomUtilitiesMod.poser,0);
//		meshItem(mesher,RandomUtilitiesMod.equiper,0);
		meshItem(mesher,RandomUtilitiesMod.obsidianCrystal,0);

		meshItem(mesher,RandomUtilitiesMod.itemSugarblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemPoisonblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemFlintblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemObsidianCrystalBlock,0);
	}

	private void meshItem(ItemModelMesher mesher, Item item, int meta)
	{
		mesher.register(item,meta,new ModelResourceLocation(item.getRegistryName().toString(),"inventory"));
	}


	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));


		switch(ID)
		{
			case 0:
				return new GuiTest("Test",player.inventory);
		}
			

		return null;

	}
}

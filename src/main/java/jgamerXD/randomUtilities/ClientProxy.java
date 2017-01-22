package jgamerXD.randomUtilities;


import jgamerXD.randomUtilities.entity.EntityCursedSnowball;
import jgamerXD.randomUtilities.entity.RenderCursedSnowball;
import jgamerXD.randomUtilities.gui.GuiTest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{

	public void loadModels()
	{

		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		meshItem(mesher,RandomUtilitiesMod.poser,0);
//		meshItem(mesher,RandomUtilitiesMod.equiper,0);
		meshItem(mesher,RandomUtilitiesMod.obsidianCrystal,0);
		meshItem(mesher,RandomUtilitiesMod.portableButton, 0);
		meshItem(mesher,RandomUtilitiesMod.silencer, 0);

		meshItem(mesher,RandomUtilitiesMod.itemSugarblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemGlowsand,0);
		meshItem(mesher,RandomUtilitiesMod.itemPoisonblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemFlintblock,0);
		meshItem(mesher,RandomUtilitiesMod.itemObsidianCrystalBlock,0);




	}

	@Override
	public void registerRendering() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCursedSnowball.class, RenderCursedSnowball::new);
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

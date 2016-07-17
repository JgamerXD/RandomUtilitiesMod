package com.github.jgamerXD.randomUtilities;

import com.github.jgamerXD.randomUtilities.block.BlockFlint;
import com.github.jgamerXD.randomUtilities.block.BlockObsidianCrystal;
import com.github.jgamerXD.randomUtilities.block.BlockPoison;
import com.github.jgamerXD.randomUtilities.block.BlockSugar;
import com.github.jgamerXD.randomUtilities.item.ItemObsidianCrystal;
import com.github.jgamerXD.randomUtilities.network.PacketUpdateArmorStand;
import com.github.jgamerXD.randomUtilities.item.ItemPoser;
import com.github.jgamerXD.randomUtilities.network.HandlerUpdateArmorStand;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = RandomUtilitiesMod.MODID, version = RandomUtilitiesMod.VERSION)
public class RandomUtilitiesMod {
    public static final String MODID = "randomUtilities";
    public static final String VERSION = "1.0";


    @Mod.Instance(MODID)
    public static RandomUtilitiesMod instance;

    public static SimpleNetworkWrapper network;

    public static RandomEventHandler eventHandler;

    public static CreativeTabs utilitiesTab = new CreativeTabs("randomutilities") {
        public Item getTabIconItem() {
            return poser;
        }
    };


    /**
     * Blocks
     */
    public static Block flintblock;
    public static Block poisonblock;
    public static Block sugarblock;
    public static Block obsidianCrystalBlock;

    /**
     * ItemBlocks
     */
    public static Item itemFlintblock;
    public static Item itemPoisonblock;
    public static Item itemSugarblock;
    public static Item itemObsidianCrystalBlock;

    /**
     * Items
     */
    public static Item poser;
    public static Item obsidianCrystal;
    //public static Item equiper;


    @SidedProxy(modId = MODID, clientSide = "com.github.jgamerXD.randomUtilities.ClientProxy", serverSide = "com.github.jgamerXD.randomUtilities.CommonProxy")
    public static CommonProxy proxy;


    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        network.registerMessage(HandlerUpdateArmorStand.class, PacketUpdateArmorStand.class, 0, Side.SERVER);

        eventHandler = new RandomEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);


        poser = new ItemPoser();
        poser.setRegistryName("poser").setUnlocalizedName(poser.getRegistryName().toString());
        GameRegistry.register(poser);
        MinecraftForge.EVENT_BUS.register(poser);

/*        equiper =  new ItemEquiper();
        equiper.setRegistryName("equiper").setUnlocalizedName(equiper.getRegistryName().toString());
        GameRegistry.register(equiper);
        MinecraftForge.EVENT_BUS.register(equiper);*/

        obsidianCrystal = new ItemObsidianCrystal();
        obsidianCrystal.setRegistryName("obsidian_crystal").setUnlocalizedName(obsidianCrystal.getRegistryName().toString());
        GameRegistry.register(obsidianCrystal);


        flintblock = new BlockFlint();
        flintblock.setRegistryName("flint_block").setUnlocalizedName(flintblock.getRegistryName().toString());
        GameRegistry.register(flintblock);
        itemFlintblock = (new ItemBlock(flintblock)).setRegistryName(flintblock.getRegistryName());
        GameRegistry.register(itemFlintblock);

        poisonblock = new BlockPoison();
        poisonblock.setRegistryName("poison_block").setUnlocalizedName(poisonblock.getRegistryName().toString());
        GameRegistry.register(poisonblock);
        itemPoisonblock = (new ItemBlock(poisonblock)).setRegistryName(poisonblock.getRegistryName());
        GameRegistry.register(itemPoisonblock);

        sugarblock = new BlockSugar();
        sugarblock.setRegistryName("sugar_block").setUnlocalizedName(sugarblock.getRegistryName().toString());
        GameRegistry.register(sugarblock);
        itemSugarblock = (new ItemBlock(sugarblock)).setRegistryName(sugarblock.getRegistryName());
        GameRegistry.register(itemSugarblock);


        obsidianCrystalBlock = new BlockObsidianCrystal();
        obsidianCrystalBlock.setRegistryName("obsidian_crystal_block").setUnlocalizedName(obsidianCrystalBlock.getRegistryName().toString());
        GameRegistry.register(obsidianCrystalBlock);
        itemObsidianCrystalBlock = (new ItemBlock(obsidianCrystalBlock)).setRegistryName(obsidianCrystalBlock.getRegistryName());
        GameRegistry.register(itemObsidianCrystalBlock);

    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.loadModels();
        CraftingManager.load();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

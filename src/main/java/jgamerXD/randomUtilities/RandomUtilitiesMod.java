package jgamerXD.randomUtilities;

import jgamerXD.randomUtilities.block.*;
import jgamerXD.randomUtilities.entity.EntityCursedSnowball;
import jgamerXD.randomUtilities.entity.RenderCursedSnowball;
import jgamerXD.randomUtilities.item.ItemPortableButton;
import jgamerXD.randomUtilities.item.ItemObsidianCrystal;
import jgamerXD.randomUtilities.item.ItemPoser;
import jgamerXD.randomUtilities.item.ItemSilencer;
import jgamerXD.randomUtilities.network.*;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;


@Mod(modid = RandomUtilitiesMod.MODID, version = RandomUtilitiesMod.VERSION)
public class RandomUtilitiesMod {
    public static final String MODID = "randomutilities";
    public static final String VERSION = "1.1";


    @Mod.Instance(MODID)
    public static RandomUtilitiesMod instance;

    public static SimpleNetworkWrapper network;

    public static RandomEventHandler eventHandler;

    public static Logger logger;
    /**
     * Blocks
     */
    public static Block flintblock;
    public static Block poisonblock;
    public static Block sugarblock;
    public static Block obsidianCrystalBlock;
    public static Block portableButtonTempBlock;
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
    public static CreativeTabs utilitiesTab = new CreativeTabs("randomutilities") {
        public Item getTabIconItem() {
            return poser;
        }
    };
    public static Item obsidianCrystal;
    public static Item portableButton;
    public static Item silencer;

    //public static Item equiper;


    @SidedProxy(modId = MODID, clientSide = "jgamerXD.randomUtilities.ClientProxy", serverSide = "jgamerXD.randomUtilities.CommonProxy")
    public static CommonProxy proxy;



    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = FMLLog.getLogger();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        initNetwork();

        eventHandler = new RandomEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);

        initItems();
        initBlocks();
        initEntities();
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.loadModels();
        CraftingManager.load();
        initSpawns();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


    private void initNetwork() {
        network.registerMessage(HandlerUpdateArmorStand.class, PacketUpdateArmorStand.class, 0, Side.SERVER);
        network.registerMessage(HandlerResetFallDistance.class, PacketResetFallDistance.class, 1, Side.SERVER);
        network.registerMessage(HandlerSetElytraFlying.class, PacketSetElytraFlying.class, 2, Side.SERVER);
    }

    private void initItems() {
        poser = RegisterHelper.registerItem(new ItemPoser(), "poser");
        MinecraftForge.EVENT_BUS.register(poser);

/*        equiper =  RegisterHelper.registerItem(new ItemEquiper(),"equiper"); */

        obsidianCrystal = RegisterHelper.registerItem(new ItemObsidianCrystal(), "obsidian_crystal");
        silencer = RegisterHelper.registerItem(new ItemSilencer(),"silencer");

        portableButton = RegisterHelper.registerItem(new ItemPortableButton(), "portable_button");
        portableButtonTempBlock = RegisterHelper.registerBlock(new BlockPortableButtonTemp(), "portable_button_temp_block");
    }

    private void initBlocks() {
        flintblock = RegisterHelper.registerBlock(new BlockFlint(), "flint_block");
        itemFlintblock = RegisterHelper.registerBlockItem(flintblock);

        poisonblock = RegisterHelper.registerBlock(new BlockPoison(), "poison_block");
        itemPoisonblock = RegisterHelper.registerBlockItem(poisonblock);

        sugarblock = RegisterHelper.registerBlock(new BlockSugar(), "sugar_block");
        itemSugarblock = RegisterHelper.registerBlockItem(sugarblock);


        obsidianCrystalBlock = RegisterHelper.registerBlock(new BlockObsidianCrystal(), "obsidian_crystal_block");
        itemObsidianCrystalBlock = RegisterHelper.registerBlockItem(obsidianCrystalBlock);
    }

    private void initEntities() {
        EntityRegistry.registerModEntity(EntityCursedSnowball.class, "cursed_snowball", 0, this, 64, 1, false, 0x98EAE1, 0xB1D4F9);
        RenderingRegistry.registerEntityRenderingHandler(EntityCursedSnowball.class, RenderCursedSnowball::new);
    }

    private void initSpawns() {
        EntityRegistry.addSpawn(EntityCursedSnowball.class, 100, 1, 3, EnumCreatureType.MONSTER, BiomeDictionary.getBiomesForType(BiomeDictionary.Type.COLD));
    }
}

package com.github.JgamerXD.RandomUtilitiesMod;

import com.github.JgamerXD.RandomUtilitiesMod.blocks.BlockFlint;
import com.github.JgamerXD.RandomUtilitiesMod.blocks.BlockItemModifier;
import com.github.JgamerXD.RandomUtilitiesMod.blocks.BlockObsidianCrystal;
import com.github.JgamerXD.RandomUtilitiesMod.Items.ItemArrowFocus;
import com.github.JgamerXD.RandomUtilitiesMod.Items.ItemModifiedBow;
import com.github.JgamerXD.RandomUtilitiesMod.Items.ItemModifierFocus;
import com.github.JgamerXD.RandomUtilitiesMod.Items.ItemObsidianCrystal;
import com.github.JgamerXD.RandomUtilitiesMod.modifiers.ModifierManager;
import com.github.JgamerXD.RandomUtilitiesMod.tileEntities.TileEntityItemModifier;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


@Mod(modid = RandomUtilitiesMod.modid, name = "RandomUtilities", version = "0.0")


public class RandomUtilitiesMod {
    public static final String modid = "RandomUtilitiesMod";

    @Instance(modid)
    public static RandomUtilitiesMod instance;


    public static CreativeTabs randomTab = new CreativeTabs("RandomUtilities") {
        public Item getTabIconItem() {
            return obsidianCrystal;
        }
    };

    public static Item obsidianCrystal;
    public static Item modifiedBow;
    public static Item dagger;

    public static Item modifierFocus;
    public static Item arrowFocus;
    public static Item potionFocus;

    public static Block obsidianCrystalBlock;
    public static Block flintBlock;
    public static Block itemModifier;
    public static Block poisonBlock;
    public static Block sugarBlock;


    public static Item potionArrow;
    public static Item potionBow;


    @SidedProxy(modId = modid, clientSide = "com.github.JgamerXD.RandomUtilitiesMod.ClientProxy", serverSide = "com.github.JgamerXD.RandomUtilitiesMod.CommonProxy")
    public static CommonProxy proxy;


    @EventHandler
    public void Init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

        obsidianCrystal = new ItemObsidianCrystal().setTextureName(modid + ":" + "obsidian_crystal").setUnlocalizedName("obsidianCrystal");
        modifiedBow = new ItemModifiedBow().setUnlocalizedName("modifiedBow");


        obsidianCrystalBlock = new BlockObsidianCrystal(Material.rock).setBlockTextureName(modid + ":" + "obsidian_crystal_block").setBlockName("obsidianCrystalBlock");
        flintBlock = new BlockFlint(Material.rock).setBlockTextureName(modid + ":" + "flint_block").setBlockName("flintBlock");
//	    	poisonBlock = new BlockPoison
        itemModifier = new BlockItemModifier(Material.rock).setBlockTextureName(modid + ":" + "modifier_side").setBlockName("itemModifier");


        //Foci
        modifierFocus = new ItemModifierFocus().setFocusName("empty").setUnlocalizedName("baseFocus");
        arrowFocus = new ItemArrowFocus().setFocusName("focus_arrow").setUnlocalizedName("arrowFocus");
        potionFocus = new ItemArrowFocus().setFocusName("focus_potion").setUnlocalizedName("potionFocus");

        GameRegistry.registerItem(modifierFocus, "modifier_focus", modid);
        GameRegistry.registerItem(arrowFocus, "arrow_focus", modid);
        GameRegistry.registerItem(potionFocus, "potion_focus", modid);


        GameRegistry.registerItem(obsidianCrystal, "obsidian_crystal", modid);
        GameRegistry.registerItem(modifiedBow, "modified_bow", modid);
//	    	GameRegistry.registerItem(dagger, "dagger_standard", modid);

        GameRegistry.registerBlock(obsidianCrystalBlock, "obsidian_crystal_block");
        GameRegistry.registerBlock(flintBlock, "flint_block");
        GameRegistry.registerBlock(itemModifier, "item_modifier");
//	    	GameRegistry.registerBlock(sugarBlock, "sugar_block");
//	    	GameRegistry.registerBlock(poisonBlock, "poison_block");


        GameRegistry.registerTileEntity(TileEntityItemModifier.class, "ItemModifier");

//	    	potionBow = new ItemPBow().setTextureName(modid +":" + "potion_bow").setUnlocalizedName("potionBow");
//	    	potionArrow = new ItemPArrow().setTextureName(modid +":" + "potion_arrow").setUnlocalizedName("potionArrow");
//	    	
//	    	GameRegistry.registerItem(potionArrow, "potion_arrow", modid);
//	    	GameRegistry.registerItem(potionBow, "potion_bow", modid);
//	    	
//	    	EntityRegistry.registerModEntity(EntityPArrow.class, "potion_arrow", 1, this, 30, 1, true);


        CraftingManager.init();
        ModifierManager.registerModifriers();
    }
}
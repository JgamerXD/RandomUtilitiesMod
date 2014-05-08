package com.github.JgamerXD.RandomUtilitiesMod;

import com.github.JgamerXD.RandomUtilitiesMod.Items.MultiArrow;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    public static Item multiArrow;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	multiArrow = new MultiArrow().setUnlocalizedName("multiArrow").setCreativeTab(CreativeTabs.tabMisc);
    	
    	GameRegistry.registerItem(multiArrow, "multi_Arrow");
    }
}

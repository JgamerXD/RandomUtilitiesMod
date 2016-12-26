package jgamerXD.randomUtilities;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Janki on 18.12.2016.
 */
public class RegisterHelper {


    static Item registerItem(Item item, String name){
        item.setRegistryName(name).setUnlocalizedName(item.getRegistryName().toString());
        GameRegistry.register(item);
        return item;
    }
    static Block registerBlock(Block block, String name){
        block.setRegistryName(name).setUnlocalizedName(block.getRegistryName().toString());
        GameRegistry.register(block);
        return block;
    }

    static Item registerBlockItem(Block block) {
        Item itemBlock = (new ItemBlock(block)).setRegistryName(block.getRegistryName());
        GameRegistry.register(itemBlock);
        return itemBlock;
    }
}

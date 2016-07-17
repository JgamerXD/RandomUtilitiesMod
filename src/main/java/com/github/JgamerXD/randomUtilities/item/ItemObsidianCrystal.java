package com.github.jgamerXD.randomUtilities.item;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * Created by Janki on 14.07.2016.
 */
public class ItemObsidianCrystal extends Item {
    public ItemObsidianCrystal() {
        super();
        setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(!worldIn.isRemote)
            playerIn.addChatComponentMessage(new TextComponentTranslation("§11§22§33§44§55§66§77§88§99§00§aa§bb§cc§dd§ee§ff"));
        return ActionResult.newResult(EnumActionResult.SUCCESS,itemStackIn);
    }
}

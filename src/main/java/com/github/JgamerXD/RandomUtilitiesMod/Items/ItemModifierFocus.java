package com.github.JgamerXD.RandomUtilitiesMod.Items;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemModifierFocus extends Item
{
	private IIcon focus;
	private String focusIconName;
	public ItemModifierFocus()
	{
		super();
        this.setTextureName(RandomUtilitiesMod.modid + ":" + "focus_base");
		this.setCreativeTab(RandomUtilitiesMod.randomTab);
	}
	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return ((pass == 0) ? itemIcon : focus);
	}

	@Override
	public void registerIcons(IIconRegister par1IIconRegister)
	{
        super.registerIcons(par1IIconRegister);
        if (focusIconName == null)
            focusIconName = "focus_unknown";

        focus = par1IIconRegister.registerIcon(RandomUtilitiesMod.modid + ":" + focusIconName);
	}

    public ItemModifierFocus setFocusName(String name)
    {
        focusIconName = name;
        return this;
    }

    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
}

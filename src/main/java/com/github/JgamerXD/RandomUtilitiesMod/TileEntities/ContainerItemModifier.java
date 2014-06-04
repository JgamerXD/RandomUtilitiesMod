package com.github.JgamerXD.RandomUtilitiesMod.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerItemModifier extends Container
{
    private TileEntityItemModifier itemModifier;
    private int lastProgress;


    public ContainerItemModifier(InventoryPlayer par1InventoryPlayer, TileEntityItemModifier par2TileEntityItemModifier)
    {
        this.itemModifier = par2TileEntityItemModifier;
        
        
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 0, 21, 9));
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 1, 21, 27));
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 2, 21, 45));
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 3, 21, 63));
        
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 4, 84, 36));
        
        this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 5, 138, 36));
        
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.itemModifier.progress);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastProgress != this.itemModifier.progress)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.itemModifier.progress);
            }
        }

        this.lastProgress = this.itemModifier.progress;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.itemModifier.progress = par2;
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.itemModifier.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        return null;
    }

}
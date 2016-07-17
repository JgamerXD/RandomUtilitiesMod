package com.github.jgamerXD.randomUtilities.gui;

import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;


/**
 * Created by Jan on 27.06.2016.
 */
public class ContainerArmorEquiper extends Container implements IInventoryChangedListener{
    private static final EntityEquipmentSlot[] PLAYER_EQUIPMENT_SLOTS = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

    private EntityArmorStand armorStand;
    private InventoryBasic armorStandInv;

    public ContainerArmorEquiper(EntityPlayer player, EntityArmorStand armorStand) {
        this.armorStand = armorStand;
        this.armorStandInv = new InventoryBasic("gui.armorstandequip.name",false,6);
        armorStandInv.addInventoryChangeListener(this);
        armorStandToInventory(armorStandInv,armorStand);

        //this.addSlotToContainer(new Slot(par2TileEntityItemModifier, 0, 21, 9));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.HEAD.getSlotIndex(),116,8));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.CHEST.getSlotIndex(),116,25));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.LEGS.getSlotIndex(),116,43));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.FEET.getSlotIndex(),116,62));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.MAINHAND.getSlotIndex(),97,25));
        this.addSlotToContainer(new Slot(armorStandInv, EntityEquipmentSlot.OFFHAND.getSlotIndex(),133,25));

        int i;

        final EntityPlayer p = player;
        for (int k = 0; k < 4; ++k)
        {
            final EntityEquipmentSlot entityequipmentslot = PLAYER_EQUIPMENT_SLOTS[k];

            this.addSlotToContainer(new Slot(player.inventory, 36 + (3 - k), 8, 8 + k * 18)
            {
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                 */
                public boolean isItemValid(@Nullable ItemStack stack)
                {
                    if (stack == null)
                    {
                        return false;
                    }
                    else
                    {
                        return stack.getItem().isValidArmor(stack, entityequipmentslot, p);
                    }
                }
                @Nullable
                @SideOnly(Side.CLIENT)
                public String getSlotTexture()
                {
                    return ItemArmor.EMPTY_SLOT_NAMES[entityequipmentslot.getIndex()];
                }
            });
        }


        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(player.inventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(player.inventory, i1, 8 + i1 * 18, 142));
        }

        this.addSlotToContainer(new Slot(player.inventory, 40, 25, 25));
    }


    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        return null;
    }

    private void inventoryToArmorStand(InventoryBasic inventory, EntityArmorStand armorStand)
    {
        for(EntityEquipmentSlot e : EntityEquipmentSlot.values())
        {
            armorStand.setItemStackToSlot(e,inventory.getStackInSlot(e.getSlotIndex()));
        }
    }

    private void armorStandToInventory(InventoryBasic inventory, EntityArmorStand armorStand)
    {
        for(EntityEquipmentSlot e : EntityEquipmentSlot.values())
        {
            inventory.setInventorySlotContents(e.getSlotIndex(),armorStand.getItemStackFromSlot(e));
        }
    }

    @Override
    public void onInventoryChanged(InventoryBasic invBasic) {
        inventoryToArmorStand(invBasic,armorStand);
    }
}

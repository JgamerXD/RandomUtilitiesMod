package com.github.JgamerXD.RandomUtilitiesMod.tileEntities;

import com.github.JgamerXD.RandomUtilitiesMod.modifiers.IModifiableItem;
import com.github.JgamerXD.RandomUtilitiesMod.modifiers.ModifierRecipe;
import com.github.JgamerXD.RandomUtilitiesMod.modifiers.ModifierRegistry;
import com.github.JgamerXD.RandomUtilitiesMod.utility.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Arrays;


public class TileEntityItemModifier extends TileEntity implements ISidedInventory
{
	private final int WORKING_TIME = 100;

	private ItemStack[] inventory;
	// progress in remaining ticks
	public int progress;
	boolean active;
	private String name = "";

	// ISidied stuff
	private int[] input_top = { 4, 5 };
	private int[] input_bottom = {};
	private int[] input_sides = { 0, 1, 2, 3 };

	ModifierRecipe[] current;
	ModifierRecipe[] latest;

	public TileEntityItemModifier()
	{
        current = new ModifierRecipe[4];
        latest = new ModifierRecipe[4];
		inventory = new ItemStack[6];
		active = false;
		progress = WORKING_TIME;
	}


    // Testing
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }
    // \Testing

	public void updateEntity()
	{
        if (active && progress > 0) {
        progress--;
        }
        if(!worldObj.isRemote) {

            if (inventory[4] != null && inventory[5] != null) {
                latest = current.clone();
                updateCurrentRecipes();
                if (active) {
                    if (Arrays.equals(current, latest)) {
                        //System.out.println(progress);
                        if (progress <= 0) {
                            for (int i = 0; i < 3; i++) {
                                if (current[i] != null) {
                                    Item modifiedItem = inventory[5].getItem();
                                    if (modifiedItem instanceof IModifiableItem) {
                                        ((IModifiableItem) modifiedItem).addModifier(inventory[5], ModifierRegistry.getResult(current[i]));
                                    } else {
                                        modifiedItem = (Item) ModifierRegistry.getModifiable(modifiedItem);
                                        ItemStack modifiedStack = new ItemStack(modifiedItem, 1, 0);
                                        modifiedStack.setTagCompound(inventory[5].getTagCompound());
                                        ((IModifiableItem) modifiedStack.getItem()).addModifier(modifiedStack, ModifierRegistry.getResult(current[i]));
                                        inventory[5] = modifiedStack;
                                    }
                                }
                                current[0] = null;
                                current[1] = null;
                                current[2] = null;
                                current[3] = null;
                                inventory[0] = null;
                                inventory[1] = null;
                                inventory[2] = null;
                                inventory[3] = null;


                            }
                            active = false;
                            progress = WORKING_TIME;
                            markDirty();
                        }
                    } else {
                        active = false;
                        progress = WORKING_TIME;
                        markDirty();
                    }

                }
                if (!active && validateCurrentRecipes()) {
                    active = true;
                    markDirty();
                }
            }
            if (!active) {
                current[0] = null;
                current[1] = null;
                current[2] = null;
                current[3] = null;
            }
        }
	}

	private void updateCurrentRecipes()
	{
        Item focus = inventory[4].getItem();
        Item item = inventory[5].getItem();
        if(!(item instanceof IModifiableItem))
            item = (Item) ModifierRegistry.getModifiable(item);
        for(int i=0;i<4;i++)
            if (inventory[i] != null) {
                //System.out.println(i);
                Item modifier = inventory[i].getItem();
                current[i] = new ModifierRecipe(modifier, focus, item);
            }
	}

	private boolean validateCurrentRecipes()
	{
		boolean valid = true;
        if (current[0] == null && current[1] == null && current[2] == null && current[3] == null)
            valid = false;
		
		if (current[0] != null && !ModifierRegistry.isRecipe(current[0]) && inventory[0] != null)
			valid = false;
		if (current[1] != null && !ModifierRegistry.isRecipe(current[1]) && inventory[1] != null)
			valid = false;
		if (current[2] != null && !ModifierRegistry.isRecipe(current[2]) && inventory[2] != null)
            valid = false;
        if (current[3] != null && !ModifierRegistry.isRecipe(current[3]) && inventory[3] != null)
            valid = false;

		return valid;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public ItemStack getStackInSlot(int arg0)
	{
		return this.inventory[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0)
	{
		return this.inventory[arg0];
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1)
	{

		ItemStack stack = getStackInSlot(arg0);
		if (stack != null)
		{
			if (stack.stackSize <= arg1)
			{
				setInventorySlotContents(arg0, null);
			}
			else
			{
				stack = stack.splitStack(arg1);
				if (stack.stackSize == 0)
				{
					setInventorySlotContents(arg0, null);
				}
			}
		}
		return stack;
	}

    /*@Override
    public void markDirty()
    {
        super.markDirty();
    }*/

	@Override
	public String getInventoryName()
	{
		if (name != "")
			return name;
		else
			return "Bug";
	}

	public void setInventoryName(String name)
	{
		this.name = name;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
        return name != "";
    }

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1)
	{
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && arg0.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1)
	{
		inventory[arg0] = arg1;
		if (arg1 != null && arg1.stackSize > getInventoryStackLimit())
		{
			arg1.stackSize = getInventoryStackLimit();
		}
        markDirty();
	}

	public boolean isInvNameLocalized()
	{
		return !hasCustomInventoryName();
	}

	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
        par1.setInteger("progress", progress);
        par1.setBoolean("active", active);
		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inventory.length; i++)
		{
			ItemStack stack = inventory[i];
			if (stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		par1.setTag("Items", itemList);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
        progress = par1.getInteger("progress");
        active = par1.getBoolean("active");

		NBTTagList tagList = par1.getTagList("Items", 0);
		for (int i = 0; i < tagList.tagCount(); i++)
		{
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inventory.length)
			{
				inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}


	@Override
	public boolean canExtractItem(int arg0, ItemStack arg1, int arg2)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canInsertItem(int arg0, ItemStack arg1, int arg2)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int arg0)
	{
		return arg0 == 0 ? input_bottom : (arg0 == 1 ? input_top : input_sides);
	}

    public int getScaledProgress(int scale)
    {
        return Util.getScaledAmount(scale, WORKING_TIME - progress, WORKING_TIME);
    }
}

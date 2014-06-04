package com.github.JgamerXD.RandomUtilitiesMod.TileEntities;

import java.util.List;

import com.github.JgamerXD.RandomUtilitiesMod.Modifier.IModifiable;
import com.github.JgamerXD.RandomUtilitiesMod.Modifier.ModifierRecipe;
import com.github.JgamerXD.RandomUtilitiesMod.Modifier.ModifierRegistry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityItemModifier extends TileEntity implements ISidedInventory, ICrafting
{
	private final int WORKING_TIME = 400;

	private ItemStack inventory[];
	// progress in remaining ticks
	public int progress;
	boolean active;
	private String name;

	// ISidied stuff
	private int[] input_top = { 4, 5 };
	private int[] input_bottom = {};
	private int[] input_sides = { 0, 1, 2, 3 };

	ModifierRecipe[] current = new ModifierRecipe[3];
	ModifierRecipe[] latest = new ModifierRecipe[3];

	public TileEntityItemModifier()
	{
		inventory = new ItemStack[6];
		active = false;
		progress = 0;
	}

	public void updateEntity()
	{
		if (inventory[4] != null && inventory[5] != null)
		{
			latest = current;
			updateCurrentRecipes();
			if (active)
			{
				if (current.equals(latest))
				{
					progress--;
					if (progress == 0)
					{
						for (int i = 0; i < 3; i++)
						{
							Item modifiedItem = inventory[5].getItem();
							if (modifiedItem instanceof IModifiable)
							{
								((IModifiable) modifiedItem).AddModifier(ModifierRegistry.getResult(current[i]));
							}
							else
							{
                                modifiedItem = (Item) ModifierRegistry.getModifiable(modifiedItem);
                                inventory[0].
							}
						}
					}
				}
				else
				{
                    active = false;
				}

			}
			if (validateCurrentRecipe())
                if(!active) {
                    active = true;
                    progress = WORKING_TIME;
                }
            else
            {
                active = false;
            }
		}
	}

	private void updateCurrentRecipes()
	{
        Item focus = inventory[4].getItem();
        Item item = inventory[5].getItem();
        if(!(item instanceof IModifiable))
            item = (Item) ModifierRegistry.getModifiable(item);
        for(int i=0;i<4;i++) {
            Item modifier = inventory[i].getItem();

            current[i] = new ModifierRecipe(modifier, focus, item);
        }
	}

	private boolean validateCurrentRecipe()
	{
		boolean valid = true;

		if (!ModifierRegistry.isRecipe(current[0]) && inventory[0] != null)
			valid = false;
		if (!ModifierRegistry.isRecipe(current[1]) && inventory[1] != null)
			valid = false;
		if (!ModifierRegistry.isRecipe(current[2]) && inventory[2] != null)
            valid = false;
        if (!ModifierRegistry.isRecipe(current[3]) && inventory[3] != null)
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

	@Override
	public String getInventoryName()
	{
		if (name != null)
			return name;
		else
			return "tile.itemModifier.name";
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
		return name != null && name.length() < 0;
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
	}

	public boolean isInvNameLocalized()
	{
		return !hasCustomInventoryName();
	}

	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
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
		par1.setTag("Inventory", itemList);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		NBTTagList tagList = par1.getTagList("Inventory", 0);
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
	public void sendContainerAndContentsToPlayer(Container arg0, List arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void sendProgressBarUpdate(Container arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void sendSlotContents(Container arg0, int arg1, ItemStack arg2)
	{
		// TODO Auto-generated method stub

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
}

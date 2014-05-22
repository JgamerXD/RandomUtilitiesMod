package com.github.JgamerXD.RandomUtilitiesMod.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityItemModifier extends TileEntity implements IInventory
{
	
	private ItemStack inventory[];
	public int progress;
	
	
	
	
	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		 super.readFromNBT(par1);
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemStack decrStackSize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInventoryName() {
		return "Item Modifier";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public ItemStack getStackInSlot(int arg0) {
		return this.inventory[arg0];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int arg0) {
		return this.inventory[arg0];
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int arg0, ItemStack arg1) {
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
        arg0.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

//	@Override
//	public void openInventory() {
//		
//	}

	@Override
	public void setInventorySlotContents(int arg0, ItemStack arg1) {
        inventory[arg0] = arg1;
        if (arg1 != null && arg1.stackSize > getInventoryStackLimit()) {
        	arg1.stackSize = getInventoryStackLimit();
        }     
	}


	public boolean isInvNameLocalized()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}
}

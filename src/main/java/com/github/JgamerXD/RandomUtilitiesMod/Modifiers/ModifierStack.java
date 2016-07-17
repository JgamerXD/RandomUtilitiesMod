package com.github.JgamerXD.RandomUtilitiesMod.modifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;

public class ModifierStack
{
	private ArrayList<ModifierInstance> modifiers;

	public ModifierStack()
	{
		modifiers = new ArrayList<ModifierInstance>();
	}
	
	public void addModifier(ModifierInstance modifier)
	{
			
		if(!modifiers.contains(modifier))
			modifiers.add(modifier);
	}

	public void readFromNBT(NBTTagList par1NBTTagList)
	{
		String id;
		int level;

		for (int var1 = 0; var1 < par1NBTTagList.tagCount(); ++var1)
		{
			NBTTagCompound var2 = par1NBTTagList.getCompoundTagAt(var1);
			id = var2.getString("id");
			level = var2.getInteger("level");
            //System.out.println("Detected modifier: "+id+" ("+level+")");
			modifiers.add(new ModifierInstance(ModifierRegistry.getModifier(id),level));
		}
	}

    public NBTTagList writeToNBT(NBTTagList par1NBTTagList)
	{
		String id;
		int level;
		NBTTagCompound tag = new NBTTagCompound();
		if (modifiers!= null)
			for (ModifierInstance current : modifiers)
			{
                if(current != null) {
                    id = current.getModifier().getId();
                    tag.setString("id", id);
                    level = current.getLevel();
                    tag.setInteger("level", level);
                    par1NBTTagList.appendTag(tag);
                }
                else
                    System.out.println("Null Modifier");
			}
		return par1NBTTagList;
	}

    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        for(ModifierInstance modifier : modifiers)
        {
            if(modifier instanceof IAttackModifier && ((IAttackModifier) modifier).getHitTime()==-1)
            {

            }
        }
        for(ModifierInstance modifier : modifiers)
        {
            if(modifier instanceof IAttackModifier && ((IAttackModifier) modifier).getHitTime()==0)
            {

            }
        }
        for(ModifierInstance modifier : modifiers)
        {
            if(modifier instanceof IAttackModifier && ((IAttackModifier) modifier).getHitTime()==1)
            {

            }
        }
        return true;
    }

    public boolean shootEntity(ItemStack par1ItemStack,Entity par2Entity)
    {
        for(ModifierInstance modifier : modifiers)
        {

            if(modifier.getModifier() instanceof IShootingModifier && ((IShootingModifier) modifier.getModifier()).getShootTime()==-1)
            {
                ((IShootingModifier) modifier.getModifier()).shootEntity(modifier,par2Entity);
            }
        }
        for(ModifierInstance modifier : modifiers)
        {

            if(modifier.getModifier() instanceof IShootingModifier && ((IShootingModifier) modifier.getModifier()).getShootTime()==0)
            {
                ((IShootingModifier) modifier.getModifier()).shootEntity(modifier,par2Entity);
            }
        }
        for(ModifierInstance modifier : modifiers)
        {
            if(modifier.getModifier() instanceof IShootingModifier && ((IShootingModifier) modifier.getModifier()).getShootTime()==1)
            {
                ((IShootingModifier) modifier.getModifier()).shootEntity(modifier,par2Entity);
            }
        }
        return true;
    }
    /*
    public boolean useOnBlock(ItemStack par1ItemStack, World par2World,int par3X,int par4Y,int par5Z, EntityLivingBase par6EntityLivingBase)
    {
        return true;
    }

    public boolean useOnEntityLiving(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        return true;
    }
    public boolean onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return true;
    }
    */
}

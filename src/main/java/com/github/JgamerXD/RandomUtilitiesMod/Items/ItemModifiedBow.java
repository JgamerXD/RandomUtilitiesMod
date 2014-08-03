package com.github.JgamerXD.RandomUtilitiesMod.Items;

import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.IModifiable;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.ModifierInstance;
import com.github.JgamerXD.RandomUtilitiesMod.Modifiers.ModifierStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

/**
 * Created by JgamerXD on 11.06.2014.
 */
public class ItemModifiedBow extends ItemBow implements IModifiable{

    public ModifierStack getModifierStack(ItemStack stack)
    {
        ModifierStack result = new ModifierStack();
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Modifiers"))
        {
            result.readFromNBT(stack.getTagCompound().getTagList("Modifiers",0));
        }
        return result;
    }

    public void addModifier(ItemStack stack,ModifierInstance modifier){
        ModifierStack modifierStack  = new ModifierStack();
        NBTTagCompound tag;
    	if(stack.hasTagCompound())
    	{
    		tag = stack.getTagCompound();
    	} 
    	else
    		tag = new NBTTagCompound();
    	if(tag.hasKey("modifiers"))
    	{
    		modifierStack.readFromNBT(tag.getTagList("modifiers", 0));

    	}  
    	modifierStack.addModifier(modifier);
    	tag.setTag("modifiers", modifierStack.writeToNBT(new NBTTagList()));
    	

        stack.setTagCompound(tag);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || par3EntityPlayer.inventory.hasItem(Items.arrow))
        {
            int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
            float var7 = (float)var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if ((double)var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);

            if (var7 == 1.0F)
            {
                var8.setIsCritical(true);
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                var8.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (var5)
            {
                var8.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!par2World.isRemote)
            {
            	if(par1ItemStack.hasTagCompound())
            	{
                    System.out.println("has tag");
            		NBTTagCompound tag = par1ItemStack.getTagCompound();
            		if(tag.hasKey("modifiers"))
            		{
            		    ModifierStack modifiers = new ModifierStack();
            		    modifiers.readFromNBT((NBTTagList) par1ItemStack.getTagCompound().getTag("modifiers"));
            		    modifiers.shootEntity(par1ItemStack, var8);
            		}
            	}
                par2World.spawnEntityInWorld(var8);
            }

        }
    }
}

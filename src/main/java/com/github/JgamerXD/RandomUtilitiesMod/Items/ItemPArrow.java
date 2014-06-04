package com.github.JgamerXD.RandomUtilitiesMod.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.StatCollector;

public class ItemPArrow extends Item
{
private HashMap effectCache = new HashMap();
private static final Map field_77835_b = new LinkedHashMap();
    public ItemPArrow()
    {
        super();
        maxStackSize = 64;
        this.setCreativeTab(RandomUtilitiesMod.randomTab);
        this.setHasSubtypes(true);
    }
 
    public String getTextureFile()
    {
        return "/potionmod/Items.png";
    }
    public List getEffects(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("CustomPotionEffects"))
        {
            ArrayList var6 = new ArrayList();
            NBTTagList var3 = par1ItemStack.getTagCompound().getTagList("CustomPotionEffects", 0);

            for (int var4 = 0; var4 < var3.tagCount(); ++var4)
            {
                NBTTagCompound var5 = (NBTTagCompound)var3.getCompoundTagAt(var4);
                var6.add(PotionEffect.readCustomPotionEffectFromNBT(var5));
            }

            return var6;
        }
        else
        {
            List var2 = (List)this.effectCache.get(Integer.valueOf(par1ItemStack.getItemDamage()));

            if (var2 == null)
            {
                var2 = PotionHelper.getPotionEffects(par1ItemStack.getItemDamage(), false);
                this.effectCache.put(Integer.valueOf(par1ItemStack.getItemDamage()), var2);
            }

            return var2;
        }
    }


    public List getEffects(int par1)
    {
        List var2 = (List)this.effectCache.get(Integer.valueOf(par1));

        if (var2 == null)
        {
            var2 = PotionHelper.getPotionEffects(par1, false);
            this.effectCache.put(Integer.valueOf(par1), var2);
        }

        return var2;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
    
    public static boolean isSplash(int par0)
    {
        return (par0 & 16384) != 0;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isEffectInstant(int par1)
    {
        List var2 = this.getEffects(par1);

        if (var2 != null && !var2.isEmpty())
        {
            Iterator var3 = var2.iterator();
            PotionEffect var4;

            do
            {
                if (!var3.hasNext())
                {
                    return false;
                }

                var4 = (PotionEffect)var3.next();
            }
            while (!Potion.potionTypes[var4.getPotionID()].isInstant());

            return true;
        }
        else
        {
            return false;
        }
    }
    @SideOnly(Side.CLIENT)
    public int getColorFromDamage(int par1)
    {
        return PotionHelper.func_77915_a(par1, false);
    }
    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        return par2 > 0 ? 16777215 : this.getColorFromDamage(par1ItemStack.getItemDamage());
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        if (par1ItemStack.getItemDamage() == 0)
        {
            return StatCollector.translateToLocal("item.emptyPotion.name").trim();
        }
        else
        {
            String var2 = "";

            if (isSplash(par1ItemStack.getItemDamage()))
            {
                var2 = StatCollector.translateToLocal("potion.prefix.grenade").trim() + " ";
            }

            List var3 = Items.potionitem.getEffects(par1ItemStack);
            String var4;

            if (var3 != null && !var3.isEmpty())
            {
                var4 = ((PotionEffect)var3.get(0)).getEffectName();
                var4 = var4 + ".postfix";
                return var2 + StatCollector.translateToLocal(var4).trim();
            }
            else
            {
                var4 = PotionHelper.func_77905_c(par1ItemStack.getItemDamage());
                return StatCollector.translateToLocal(var4).trim() + " " + super.getItemStackDisplayName(par1ItemStack);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (par1ItemStack.getItemDamage() != 0)
        {
            List var5 = Items.potionitem.getEffects(par1ItemStack);

            if (var5 != null && !var5.isEmpty())
            {
                Iterator var9 = var5.iterator();

                while (var9.hasNext())
                {
                    PotionEffect var7 = (PotionEffect)var9.next();
                    String var8 = StatCollector.translateToLocal(var7.getEffectName()).trim();

                    if (var7.getAmplifier() > 0)
                    {
                        var8 = var8 + " " + StatCollector.translateToLocal("potion.potency." + var7.getAmplifier()).trim();
                    }

                    if (var7.getDuration() > 20)
                    {
                        var8 = var8 + " (" + Potion.getDurationString(var7) + ")";
                    }

                    if (Potion.potionTypes[var7.getPotionID()].isBadEffect())
                    {
                        par3List.add("\u00a7c" + var8);
                    }
                    else
                    {
                        par3List.add("\u00a77" + var8);
                    }
                }
            }
            else
            {
                String var6 = StatCollector.translateToLocal("potion.empty").trim();
                par3List.add("\u00a77" + var6);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        List var2 = this.getEffects(par1ItemStack);
        return var2 != null && !var2.isEmpty();
    }

    @SideOnly(Side.CLIENT)

    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        super.getSubItems(par1, par2CreativeTabs, par3List);

        if (field_77835_b.isEmpty())
        {
            for (int var4 = 0; var4 <= 32767; ++var4)
            {
                List var5 = PotionHelper.getPotionEffects(var4, false);

                if (var5 != null && !var5.isEmpty())
                {
                    field_77835_b.put(var5, Integer.valueOf(var4));
                }
            }
        }

        Iterator var6 = field_77835_b.values().iterator();

        while (var6.hasNext())
        {
            int var7 = ((Integer)var6.next()).intValue();
            par3List.add(new ItemStack(par1, 1, var7));
        }
    }
   
}

        

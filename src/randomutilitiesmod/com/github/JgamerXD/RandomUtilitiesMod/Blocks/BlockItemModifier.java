package com.github.JgamerXD.RandomUtilitiesMod.Blocks;


import java.util.Random;

import javax.swing.Icon;

import com.github.JgamerXD.RandomUtilitiesMod.RandomUtilitiesMod;
import com.github.JgamerXD.RandomUtilitiesMod.TileEntities.TileEntityItemModifier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockItemModifier extends Block {

    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final Random modifierRand = new Random();


    /**
     * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
     * furnace block changes from idle to active and vice-versa.
     */
    private static boolean keepFurnaceInventory;
    
    @SideOnly(Side.CLIENT)
    private IIcon modifierIconTop;
    @SideOnly(Side.CLIENT)
    private IIcon modifierIconFront;

    public BlockItemModifier(Material material)
    {
        super(material);
        this.setCreativeTab(RandomUtilitiesMod.randomTab);
    }
    
     

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            Block l = par1World.getBlock(par2, par3, par4 - 1);
            Block i1 = par1World.getBlock(par2, par3, par4 + 1);
            Block j1 = par1World.getBlock(par2 - 1, par3, par4);
            Block k1 = par1World.getBlock(par2 + 1, par3, par4);
            byte b0 = 3;

            if (l.isOpaqueCube() && !i1.isOpaqueCube())
            {
                b0 = 3;
            }

            if (i1.isOpaqueCube() && !l.isOpaqueCube())
            {
                b0 = 2;
            }

            if (j1.isOpaqueCube() && !k1.isOpaqueCube())
            {
                b0 = 5;
            }

            if (k1.isOpaqueCube() && !j1.isOpaqueCube())
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int par1, int par2)
    {
        //return par1 == 1 ? this.modifierIconTop : (par1 == 0 ? this.modifierIconTop : (par1 != par2 ? this.blockIcon : this.modifierIconFront));
        return par1 == 1 ? this.modifierIconTop : (par1 == 0 ? this.modifierIconTop : (par1 != par2 ? this.blockIcon : this.modifierIconFront));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("modifier_side");
        //this.modifierIconFront = par1IconRegister.registerIcon("modifier_front");
        this.modifierIconTop = par1IconRegister.registerIcon("modifier_top");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
        	
            return true;
            
        }
        else
        {
            TileEntityItemModifier tileentitymodifier = (TileEntityItemModifier)par1World.getTileEntity(par2, par3, par4);

            if (tileentitymodifier != null)
            {
                par5EntityPlayer.openGui(RandomUtilitiesMod.instance, 0, par1World, par2, par3, par4);
            }
            
            return true;
        }
    }



//    @SideOnly(Side.CLIENT)
//
//    /**
//     * A randomly called display update to be able to add particles or other items for display
//     */
//    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
//    {
//        if (this.isActive)
//        {
//            int l = par1World.getBlockMetadata(par2, par3, par4);
//            float f = (float)par2 + 0.5F;
//            float f1 = (float)par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
//            float f2 = (float)par4 + 0.5F;
//            float f3 = 0.52F;
//            float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
//
//            if (l == 4)
//            {
//                par1World.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
//                par1World.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
//            }
//            else if (l == 5)
//            {
//                par1World.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
//                par1World.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
//            }
//            else if (l == 2)
//            {
//                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
//                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
//            }
//            else if (l == 3)
//            {
//                par1World.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
//                par1World.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
//            }
//        }
//    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityItemModifier();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

//        if (par6ItemStack.hasDisplayName())
//        {
//            ((TileEntityItemModifier)par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
//        }
    }

//    /**
//     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
//     */
//    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
//    {
//        if (!keepFurnaceInventory)
//        {
//            TileEntityItemModifier tileentityfurnace = (TileEntityItemModifier)par1World.getTileEntity(par2, par3, par4);
//
//            if (tileentityfurnace != null)
//            {
//                for (int j1 = 0; j1 < TileEntityItemModifier.getSizeInventory(); ++j1)
//                {
//                    ItemStack itemstack = tileentityfurnace.getStackInSlot(j1);
//
//                    if (itemstack != null)
//                    {
//                        float f = this.modifierRand.nextFloat() * 0.8F + 0.1F;
//                        float f1 = this.modifierRand.nextFloat() * 0.8F + 0.1F;
//                        float f2 = this.modifierRand.nextFloat() * 0.8F + 0.1F;
//
//                        while (itemstack.stackSize > 0)
//                        {
//                            int k1 = this.modifierRand.nextInt(21) + 10;
//
//                            if (k1 > itemstack.stackSize)
//                            {
//                                k1 = itemstack.stackSize;
//                            }
//
//                            itemstack.stackSize -= k1;
//                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
//
//                            if (itemstack.hasTagCompound())
//                            {
//                                entityitem.getEntityItem().stackTagCompound=(NBTTagCompound)itemstack.getTagCompound().copy();
//                            }
//
//                            float f3 = 0.05F;
//                            entityitem.motionX = (double)((float)this.modifierRand.nextGaussian() * f3);
//                            entityitem.motionY = (double)((float)this.modifierRand.nextGaussian() * f3 + 0.2F);
//                            entityitem.motionZ = (double)((float)this.modifierRand.nextGaussian() * f3);
//                            par1World.spawnEntityInWorld(entityitem);
//                        }
//                    }
//                }
//
//                par1World.func_96440_m(par2, par3, par4, par5);
//            }
//        }
//
//        super.breakBlock(par1World, par2, par3, par4, par5, par6);
//    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getTileEntity(par2, par3, par4));
    }
}

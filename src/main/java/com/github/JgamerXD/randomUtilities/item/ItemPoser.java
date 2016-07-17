package com.github.jgamerXD.randomUtilities.item;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import com.github.jgamerXD.randomUtilities.gui.GuiArmorstandPoser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Method;


/**
 * Created by Jan on 26.06.2016.
 */
public class ItemPoser extends Item {


    private static Method readNBTPose;
    private static Method writeNBTPose;

    static {
        try {
            writeNBTPose = EntityArmorStand.class.getDeclaredMethod("readPoseFromNBT");
            writeNBTPose.setAccessible(true);
            readNBTPose = EntityArmorStand.class.getDeclaredMethod("writePoseToNBT", NBTTagCompound.class);
            readNBTPose.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public ItemPoser() {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(RandomUtilitiesMod.utilitiesTab);
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        stack.setTagCompound(new NBTTagCompound());
    }



    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.setTagInfo("Paste", new NBTTagByte((byte) 0x00));
        return true;
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
//        if(playerIn.isSneaking()) {
//            //if (!worldIn.isRemote)
//                playerIn.addChatComponentMessage(new TextComponentString(I18n.format("chat.armorpose:poser.clear")));
//
//            itemStackIn.setTagInfo("Paste", new NBTTagByte((byte) 0x00));
//            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
//        }
//        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
//
//    }

    @SubscribeEvent
    public void interactSpecific(PlayerInteractEvent.EntityInteractSpecific evt) {
        EntityPlayer player = evt.getEntityPlayer();
        //if (!player.worldObj.isRemote) return;

        if (evt.getItemStack() != null
                && evt.getTarget() instanceof EntityArmorStand
                && evt.getItemStack().getItem() == this) {
            if (evt.getEntityPlayer().isSneaking()) {
                if (!player.worldObj.isRemote) {
                    if (!evt.getItemStack().hasTagCompound())
                        evt.getItemStack().setTagCompound(new NBTTagCompound());
                    NBTTagCompound isComp = evt.getItemStack().getTagCompound();

                    //TODO: Log exceptions + chat message on error
                    if (isComp.hasKey("Paste") && isComp.getBoolean("Paste") && isComp.hasKey("Pose")) { //Paste
                        //pose.setTag("Pose", isComp.getCompoundTag("Pose"));
                        try {
                            readNBTPose.invoke(evt.getTarget(), isComp.getCompoundTag("Pose"));
                            isComp.setBoolean("Paste", false);
                            evt.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ":poser.paste"));

                        } catch (Exception e) {
                            FMLLog.log(Level.ERROR, "Error while pasting pose with %s!", evt.getItemStack().getUnlocalizedName());
                            e.printStackTrace();
                            evt.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ":poser.error"));

                        }
                    } else { //Copy
                        try {
                            isComp.setTag("Pose", (NBTTagCompound) writeNBTPose.invoke(evt.getTarget()));
                            isComp.setBoolean("Paste", true);
                            evt.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ":poser.copy"));


                        } catch (Exception e) {
                            FMLLog.log(Level.ERROR, "Error while copying pose with %s!", evt.getItemStack().getUnlocalizedName());
                            e.printStackTrace();
                            evt.getEntityPlayer().addChatComponentMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ":poser.error"));
                        }
                    }
                    evt.getItemStack().setTagCompound(isComp);
                }
            } else if (player.worldObj.isRemote) {
                //player.openGui(RandomUtilitiesMod.instance,0,player.worldObj,(int)player.posX,(int)player.posY,(int)player.posZ);

                Minecraft.getMinecraft().displayGuiScreen(new GuiArmorstandPoser(player.inventory, (EntityArmorStand) evt.getTarget()));
            }
        }
    }


//    /**
//     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
//     */
//    @Override
//    public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand) {
//        if (!entity.worldObj.isRemote) {
//            return false;
//        }
//
//        player.addChatComponentMessage(new TextComponentString(I18n.format("test")));
//
//        return true;
//    }
}

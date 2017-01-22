package jgamerXD.randomUtilities.item;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import jgamerXD.randomUtilities.gui.GuiArmorstandPoser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Method;
import java.util.List;

import static sun.audio.AudioPlayer.player;


/**
 * Created by Jan on 26.06.2016.
 */
public class ItemPoser extends Item {


    private static Method readNBTPose;
    private static Method writeNBTPose;

    static {
        try {
            writeNBTPose = ReflectionHelper.findMethod(EntityArmorStand.class, null, new String[]{"func_175419_y", "readPoseFromNBT"});
            //writeNBTPose = EntityArmorStand.class.getDeclaredMethod("readPoseFromNBT");
            //writeNBTPose.setAccessible(true);
            readNBTPose = ReflectionHelper.findMethod(EntityArmorStand.class, null, new String[]{"func_175416_h", "writePoseToNBT"}, NBTTagCompound.class);
//            readNBTPose = EntityArmorStand.class.getDeclaredMethod("writePoseToNBT", NBTTagCompound.class);
//            readNBTPose.setAccessible(true);
        } catch (Exception e) {
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
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add(I18n.format("tooltip.randomutilities.poser.1"));
        list.add(stack.hasTagCompound() && stack.getTagCompound().getBoolean("Paste") ?
                I18n.format("tooltip.randomutilities.poser.2.paste") :
                I18n.format("tooltip.randomutilities.poser.2.copy"));
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
                if (!player.world.isRemote) {
                    if (!evt.getItemStack().hasTagCompound())
                        evt.getItemStack().setTagCompound(new NBTTagCompound());
                    NBTTagCompound isComp = evt.getItemStack().getTagCompound();

                    //TODO: Log exceptions + chat message on error
                    if (isComp.hasKey("Paste") && isComp.getBoolean("Paste") && isComp.hasKey("Pose")) { //Paste
                        //pose.setTag("Pose", isComp.getCompoundTag("Pose"));
                        try {
                            readNBTPose.invoke(evt.getTarget(), isComp.getCompoundTag("Pose"));
                            isComp.setBoolean("Paste", false);
                            evt.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ".poser.paste"));

                        } catch (Exception e) {
                            FMLLog.log(Level.ERROR, "Error while pasting pose with %s!", evt.getItemStack().getUnlocalizedName());
                            e.printStackTrace();
                            evt.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ".poser.error"));

                        }
                    } else { //Copy
                        try {
                            isComp.setTag("Pose", (NBTTagCompound) writeNBTPose.invoke(evt.getTarget()));
                            isComp.setBoolean("Paste", true);
                            evt.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ".poser.copy"));


                        } catch (Exception e) {
                            FMLLog.log(Level.ERROR, "Error while copying pose with %s!", evt.getItemStack().getUnlocalizedName());
                            e.printStackTrace();
                            evt.getEntityPlayer().sendStatusMessage(new TextComponentTranslation("chat." + RandomUtilitiesMod.MODID + ".poser.error"));
                        }
                    }
                    evt.getItemStack().setTagCompound(isComp);
                }
            } else if (player.world.isRemote) {
                //player.openGui(RandomUtilitiesMod.instance,0,player.worldObj,(int)player.posX,(int)player.posY,(int)player.posZ);
                openPoserGui((EntityArmorStand) evt.getTarget());
            }
            evt.setCanceled(true);
        }
    }


    @SideOnly(Side.CLIENT)
    private void openPoserGui(EntityArmorStand armorStand) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiArmorstandPoser(armorStand));
    }

}

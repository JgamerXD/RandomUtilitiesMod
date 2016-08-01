package com.github.jgamerXD.randomUtilities.gui;

import com.github.jgamerXD.randomUtilities.RandomUtilitiesMod;
import com.github.jgamerXD.randomUtilities.network.PacketUpdateArmorStand;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.math.Rotations;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Created by Jan on 27.06.2016.
 */
public class GuiArmorstandPoser extends GuiScreen implements GuiPageButtonList.GuiResponder, GuiSlider.FormatHelper {
//    private static final ResourceLocation BACKGROUND = new ResourceLocation(RandomUtilitiesMod.MODID + ":"
//            + "textures/gui/armor_poser.png");



    public static final int BUTTON_DONE         = 20;
    public static final int BUTTON_CANCEL       = 21;
    public static final int TEXT_FIELD_JSON     = 22;
    public static final int BUTTON_JSON_COMMIT  = 23;


    public static final int SLIDER_GUI_ROTATION = 0;
    public static final int SLIDER_ENTITY       = 1;
    public static final int SLIDER_BODY         = 2;
    public static final int SLIDER_HEAD_X       = 3;
    public static final int SLIDER_HEAD_Y       = 4;
    public static final int SLIDER_HEAD_Z       = 5;
    public static final int SLIDER_ARM_RIGHT_X  = 6;
    public static final int SLIDER_ARM_RIGHT_Y  = 7;
    public static final int SLIDER_ARM_RIGHT_Z  = 8;
    public static final int SLIDER_ARM_LEFT_X   = 9;
    public static final int SLIDER_ARM_LEFT_Y   = 10;
    public static final int SLIDER_ARM_LEFT_Z   = 11;
    public static final int SLIDER_LEG_RIGHT_X  = 12;
    public static final int SLIDER_LEG_RIGHT_Y  = 13;
    public static final int SLIDER_LEG_RIGHT_Z  = 14;
    public static final int SLIDER_LEG_LEFT_X   = 15;
    public static final int SLIDER_LEG_LEFT_Y   = 16;
    public static final int SLIDER_LEG_LEFT_Z   = 17;


    EntityArmorStand armorStand;
    float guiRotation = 18.0f;

    GuiTextField jsonTextField;
    GuiButton jsonCommitButton;
    GuiButton doneBtn;

    static final int sizeX = 400;
    static final int sizeY = 256;

    //ContainerArmorEquiper containerArmorPoser


    public GuiArmorstandPoser(EntityArmorStand armorStand) {
        //super(new ContainerArmorEquiper(inventoryPlayer, armorStand));
        super();
        this.armorStand = armorStand;

    }

    @Override
    public void initGui() {
        super.initGui();


        int xoff = (width - sizeX)/2;
        int yoff = (height - sizeY)/2;



        buttonList.add(new GuiSlider(this,SLIDER_GUI_ROTATION ,xoff + 266   ,yoff + 203      ,"gui_rot"  ,-180.0f ,180.0f,18,this));
        buttonList.add(new GuiSlider(this,SLIDER_ENTITY       ,xoff         ,yoff + 26       ,"entity"   ,-180.0f ,180.0f,armorStand.rotationYaw,this));
        buttonList.add(new GuiSlider(this,SLIDER_BODY         ,xoff         ,yoff + 26 + 42   ,"body"     ,-180.0f ,180.0f,armorStand.getBodyRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_HEAD_X       ,xoff + 133   ,yoff + 26       ,"head_x"   ,-180.0f ,180.0f,armorStand.getHeadRotation().getX(),this));
        buttonList.add(new GuiSlider(this,SLIDER_HEAD_Y       ,xoff + 133   ,yoff + 26 + 21  ,"head_y"   ,-180.0f ,180.0f,armorStand.getHeadRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_HEAD_Z       ,xoff + 133   ,yoff + 26 + 42  ,"head_z"   ,-180.0f ,180.0f,armorStand.getHeadRotation().getZ(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_RIGHT_X  ,xoff         ,yoff + 104       ,"arm_r_x"  ,-180.0f ,180.0f,armorStand.getRightArmRotation().getX(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_RIGHT_Y  ,xoff         ,yoff + 104 + 21  ,"arm_r_y"  ,-180.0f ,180.0f,armorStand.getRightArmRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_RIGHT_Z  ,xoff         ,yoff + 104 + 42  ,"arm_r_z"  ,-180.0f ,180.0f,armorStand.getRightArmRotation().getZ(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_LEFT_X   ,xoff + 133   ,yoff + 104       ,"arm_l_x"  ,-180.0f ,180.0f,armorStand.getLeftArmRotation().getX(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_LEFT_Y   ,xoff + 133   ,yoff + 104 + 21  ,"arm_l_y"  ,-180.0f ,180.0f,armorStand.getLeftArmRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_ARM_LEFT_Z   ,xoff + 133   ,yoff + 104 + 42  ,"arm_l_z"  ,-180.0f ,180.0f,armorStand.getLeftArmRotation().getZ(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_RIGHT_X  ,xoff         ,yoff + 182       ,"leg_r_x"  ,-180.0f ,180.0f,armorStand.getRightLegRotation().getX(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_RIGHT_Y  ,xoff         ,yoff + 182 + 21  ,"leg_r_y" ,-180.0f ,180.0f,armorStand.getRightLegRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_RIGHT_Z  ,xoff         ,yoff + 182 + 42  ,"leg_r_z" ,-180.0f ,180.0f,armorStand.getRightLegRotation().getZ(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_LEFT_X   ,xoff + 133   ,yoff + 182       ,"leg_l_x"  ,-180.0f ,180.0f,armorStand.getLeftLegRotation().getX(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_LEFT_Y   ,xoff + 133   ,yoff + 182 + 21  ,"leg_l_y" ,-180.0f ,180.0f,armorStand.getLeftLegRotation().getY(),this));
        buttonList.add(new GuiSlider(this,SLIDER_LEG_LEFT_Z   ,xoff + 133   ,yoff + 182 + 42  ,"leg_l_z" ,-180.0f ,180.0f,armorStand.getLeftLegRotation().getZ(),this));

        for(GuiButton b : buttonList)
            b.setWidth(120);

//        this.jsonTextField = new GuiTextField(TEXT_FIELD_JSON, this.fontRendererObj, xoff, yoff+228, 270, 20);
//        this.jsonTextField.setMaxStringLength(32500);
//        this.jsonTextField.setFocused(true);
//
//        jsonCommitButton = new GuiButton(BUTTON_JSON_COMMIT,xoff + 280,yoff+228,"OK");
//        jsonCommitButton.setWidth(20);
//        buttonList.add(jsonCommitButton);

        doneBtn = new GuiButton(BUTTON_DONE,xoff + 266,yoff+224,I18n.format("gui.done"));
        doneBtn.setWidth(120);
        buttonList.add(doneBtn);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(!button.enabled)
            return;
        switch(button.id)
        {
            case BUTTON_DONE:
                //RandomUtilitiesMod.network.sendToServer(new PacketUpdateArmorStand(armorStand));
                mc.displayGuiScreen(null);
                break;
            default:
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        int xoff = (width - sizeX)/2;
        int yoff = (height - sizeY)/2;
//        this.mc.getTextureManager().bindTexture(BACKGROUND);
//        drawTexturedModalRect(xoff,yoff,0,0,256,256);



        super.drawScreen(mouseX, mouseY, partialTicks);
        String name = armorStand.hasCustomName()? armorStand.getCustomNameTag():I18n.format(armorStand.getName());
//        this.jsonTextField.drawTextBox();
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0,2.0,1);
        this.fontRendererObj.drawStringWithShadow(name, (xoff+326)/2 - this.fontRendererObj.getStringWidth(name)/2, (yoff + 18)/2, 0xFFFFFF);
        GlStateManager.popMatrix();

        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.entity"),        xoff+2,      yoff-2+18,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.body"),          xoff+2,      yoff-2+60,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.head"),          xoff+2+133,  yoff-2+18,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.rightarm"),      xoff+2,      yoff-2+96,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.leftarm"),       xoff+2+133,  yoff-2+96,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.rightleg"),      xoff+2,      yoff-2+174,0xffffff);
        drawString(fontRendererObj,I18n.format("gui." + RandomUtilitiesMod.MODID + ".poser.leftleg"),       xoff+2+133,  yoff-2+174,0xffffff);

        //Entity rotation markers
        GlStateManager.pushMatrix();
        {
            int xStart = (int)(xoff*2);
            int yStart = (int)((yoff+26+11)*2 - this.fontRendererObj.FONT_HEIGHT/2.0);
            GlStateManager.scale(0.5, 0.5, 1);
            drawString(fontRendererObj,"N", xStart+5, yStart,0xd0d0d0);
            drawString(fontRendererObj,"E", xStart+67-this.fontRendererObj.getStringWidth("E"),  yStart,0xeeeeee);
            drawString(fontRendererObj,"S", xStart+123-this.fontRendererObj.getStringWidth("S"), yStart,0xeeeeee);
            drawString(fontRendererObj,"W", xStart+179-this.fontRendererObj.getStringWidth("W"), yStart,0xeeeeee);
            drawString(fontRendererObj,"N", xStart+235-this.fontRendererObj.getStringWidth("N"), yStart,0xeeeeee);
        }
        GlStateManager.popMatrix();

        //Render Crosshair
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(xoff+266+10), (float)(yoff+186), 50.0f);
        GlStateManager.rotate(15.0f,1,0,0);
        GlStateManager.rotate(guiRotation,0,1,0);
        GlStateManager.scale(-1.0F, -1.0F, -1.0F);
        OpenGlHelper.renderDirections(10);
        GlStateManager.popMatrix();

        drawEntityOnScreen(xoff+326,yoff+201-16,70,guiRotation,armorStand);
        //drawEntityOnScreen(30,50,50,guiRotation,armorStand);
    }


    /**
     * Draws an entity on the screen looking toward the cursor.
     */
    public static void drawEntityOnScreen(int posX, int posY, int scale, float rotation, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();

        GL11.glColor3f(1f, 1f, 1f);

        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-scale), (float) scale, (float) scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(15.0F, 1.0F, 0.0F, 0.0F);
//        GlStateManager.rotate(25.0F, 0.0F, 1.0F, 0.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;

        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);

        ent.renderYawOffset = rotation;
        ent.rotationYaw = 0;
        ent.rotationPitch = 0;
        ent.prevRotationYawHead = 0;
        ent.rotationYawHead = 0;




        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.doRenderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }


    @Override
    public void onGuiClosed() {
        RandomUtilitiesMod.network.sendToServer(new PacketUpdateArmorStand(armorStand));
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
//        this.jsonTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }


    @Override
    public void setEntryValue(int id, boolean value) {
    }

    @Override
    public void setEntryValue(int id, float value) {

        switch (id) {
            case SLIDER_GUI_ROTATION: {
                guiRotation = value;
                break;
            }
            case SLIDER_ENTITY: {
                armorStand.rotationYaw = value;
                break;
            }
            case SLIDER_BODY: {
                Rotations r = armorStand.getBodyRotation();
                armorStand.setBodyRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_HEAD_X: {
                Rotations r = armorStand.getHeadRotation();
                armorStand.setHeadRotation(new Rotations(value, r.getY(), r.getZ()));
                break;
            }
            case SLIDER_HEAD_Y: {
                Rotations r = armorStand.getHeadRotation();
                armorStand.setHeadRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_HEAD_Z: {
                Rotations r = armorStand.getHeadRotation();
                armorStand.setHeadRotation(new Rotations(r.getX(), r.getY(), value));
                break;
            }
            case SLIDER_ARM_RIGHT_X: {
                Rotations r = armorStand.getRightArmRotation();
                armorStand.setRightArmRotation(new Rotations(value, r.getY(), r.getZ()));
                break;
            }
            case SLIDER_ARM_RIGHT_Y: {
                Rotations r = armorStand.getRightArmRotation();
                armorStand.setRightArmRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_ARM_RIGHT_Z: {
                Rotations r = armorStand.getRightArmRotation();
                armorStand.setRightArmRotation(new Rotations(r.getX(), r.getY(), value));
                break;
            }
            case SLIDER_ARM_LEFT_X: {
                Rotations r = armorStand.getLeftArmRotation();
                armorStand.setLeftArmRotation(new Rotations(value, r.getY(), r.getZ()));
                break;
            }
            case SLIDER_ARM_LEFT_Y: {
                Rotations r = armorStand.getLeftArmRotation();
                armorStand.setLeftArmRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_ARM_LEFT_Z: {
                Rotations r = armorStand.getLeftArmRotation();
                armorStand.setLeftArmRotation(new Rotations(r.getX(), r.getY(), value));
                break;
            }
            case SLIDER_LEG_RIGHT_X: {
                Rotations r = armorStand.getRightLegRotation();
                armorStand.setRightLegRotation(new Rotations(value, r.getY(), r.getZ()));
                break;
            }
            case SLIDER_LEG_RIGHT_Y: {
                Rotations r = armorStand.getRightLegRotation();
                armorStand.setRightLegRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_LEG_RIGHT_Z: {
                Rotations r = armorStand.getRightLegRotation();
                armorStand.setRightLegRotation(new Rotations(r.getX(), r.getY(), value));
                break;
            }
            case SLIDER_LEG_LEFT_X: {
                Rotations r = armorStand.getLeftLegRotation();
                armorStand.setLeftLegRotation(new Rotations(value, r.getY(), r.getZ()));
                break;
            }
            case SLIDER_LEG_LEFT_Y: {
                Rotations r = armorStand.getLeftLegRotation();
                armorStand.setLeftLegRotation(new Rotations(r.getX(), value, r.getZ()));
                break;
            }
            case SLIDER_LEG_LEFT_Z: {
                Rotations r = armorStand.getLeftLegRotation();
                armorStand.setLeftLegRotation(new Rotations(r.getX(), r.getY(), value));
                break;
            }
            default: {

            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        //this.jsonTextField.textboxKeyTyped(typedChar, keyCode);

        if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 1)
            {
                actionPerformed(doneBtn);

            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }

    @Override
    public void setEntryValue(int id, String value) {


    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getText(int id, @Nullable String name, float value) {
        if(SLIDER_HEAD_X<=id && id<=SLIDER_LEG_LEFT_Z)
        {
            String text = "";
            switch((id-SLIDER_HEAD_X)%3) {
                case 0:
                    text = "§cX";
                    break;
                case 1:
                    text = "§aY";
                    break;
                case 2:
                    text = "§bZ";
                    break;
            }
            return String.format("%s: %5.3f§r",text,value);

        }
        else if(id == SLIDER_ENTITY)
        {

            return "";
        }
        else
            return String.format("%5.3f",value);
    }
}



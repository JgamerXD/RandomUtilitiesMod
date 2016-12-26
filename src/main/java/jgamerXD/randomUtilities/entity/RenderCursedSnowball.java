package jgamerXD.randomUtilities.entity;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Janki on 01.09.2016.
 */
public class RenderCursedSnowball extends RenderLiving<EntityCursedSnowball>
{
    private static final ResourceLocation CURSED_SNOWBALL_TEXTURES = new ResourceLocation(RandomUtilitiesMod.MODID+":textures/entity/cursed_snowball.png");

    public RenderCursedSnowball(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelCursedSnowball(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCursedSnowball entity) {
        return CURSED_SNOWBALL_TEXTURES;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityBlaze entity)
    {
        return CURSED_SNOWBALL_TEXTURES;
    }

}

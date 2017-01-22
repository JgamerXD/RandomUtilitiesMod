package jgamerXD.randomUtilities.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by Janki on 06.12.2016.
 */
public class EntityAttackSnoball extends EntitySnowball {


    public EntityAttackSnoball(World worldIn) {
        super(worldIn);
    }

    public EntityAttackSnoball(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityAttackSnoball(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null)
        {
            int i = 3;
            if(result.entityHit.isImmuneToFire())
            {
                i *= 2;
            }
            if(result.entityHit instanceof EntityLivingBase && this.rand.nextInt(10) == 0)
                ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:slowness"),50));
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        for (int j = 0; j < 8; ++j)
        {
            this.world.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.world.isRemote)
        {
            this.setDead();
        }
    }
}

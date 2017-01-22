package jgamerXD.randomUtilities.entity;

import jgamerXD.randomUtilities.RandomUtilitiesMod;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLogic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

/**
 * Created by Janki on 01.09.2016.
 */
public class EntityCursedSnowball extends EntityMob implements IRangedAttackMob {
    private int heightOffsetUpdateTime;
    private float heightOffset;

    public EntityCursedSnowball(World worldIn) {
        super(worldIn);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));
        this.tasks.addTask(0, new EntityAIAttackMelee(this,1,false));
        this.tasks.addTask(2, new EntityAIMoveToBlock(this,1,10) {
            @Override
            protected boolean shouldMoveTo(World worldIn, BlockPos pos) {
                return worldIn.getBlockState(pos).getBlock() == Blocks.PACKED_ICE;
            }
        });
        this.tasks.addTask(3, new EntityAIWander(this,1.5));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));



        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
    }

    protected void updateAITasks()
    {
        if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1.0F);
        }

        --this.heightOffsetUpdateTime;

        if (this.heightOffsetUpdateTime <= 0)
        {
            this.heightOffsetUpdateTime = 100;
            this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + (double)entitylivingbase.getEyeHeight() > this.posY + (double)this.getEyeHeight() + (double)this.heightOffset)
        {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isRemote)
        {
            if (this.rand.nextInt(100) == 0 && !this.isSilent())
            {
                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, this.getSoundCategory(), (1.0F + this.rand.nextFloat())/4, this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.getEyeHeight(), this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    public boolean getCanSpawnHere() {
        IBlockState spawnpos = this.world.getBlockState((new BlockPos(this)));
        //RandomUtilitiesMod.logger.log(Level.INFO,String.format("%b %s",spawnpos.getMaterial().equals(Material.SNOW), spawnpos.getBlock().getLocalizedName()));
        return spawnpos.getMaterial().equals(Material.SNOW) && super.getCanSpawnHere();
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float p_82196_2_)     {

        double d0 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D;
        double x = target.posX - this.posX;
        double y = d0 - this.posY - this.getEyeHeight();
        double z = target.posZ - this.posZ;
        float f = MathHelper.sqrt(x * x + z * z) * 0.2F;

        if(this.rand.nextInt(10) != 0) {
            EntityAttackSnoball entitysnowball = new EntityAttackSnoball(this.world, this);
            entitysnowball.setThrowableHeading(x, y + (double) f, z, 1.6F, 5.0F);
            this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
            this.world.spawnEntity(entitysnowball);
        }
        else {
            this.playSound(SoundEvents.ENTITY_CHICKEN_HURT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));

            this.motionX = x * 1.2f;
            this.motionY = y * 1.2f;
            this.motionZ = z * 1.2f;

            this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(x, (double)f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
    }
}

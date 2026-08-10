package bayhasoft.adventurerscookbook;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MudBallEntity extends ThrowableItemProjectile {
    public MudBallEntity(EntityType<? extends MudBallEntity> entityType, Level world) {
        super(entityType, world);
    }

    public MudBallEntity(Level world, LivingEntity owner, ItemStack stack) {
        super(EntityTypes.SNOWBALL, owner, world, stack);
    }

    public MudBallEntity(Level world, double x, double y, double z, ItemStack stack) {
        super(EntityTypes.SNOWBALL, x, y, z, world, stack);
    }

    protected Item getDefaultItem() {
        return ModItems.MUD_BALL;
    }

    private ParticleOptions getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleOptions)(itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemStack.getItem()));
    }

    public void handleEntityEvent(byte status) {
        if (status == 3) {
            ParticleOptions particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }

    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockPos pos = blockHitResult.getBlockPos();
        Level world = level();
        if(this.level().getBlockState(pos).is(BlockTags.DIRT)) {
            world.setBlockAndUpdate(pos, Blocks.MUD.defaultBlockState());
        }
    }

//    @Override
//    protected void onBlockHit(BlockHitResult blockHitResult) {
//        super.onBlockHit(blockHitResult);
//        BlockPos pos = blockHitResult.getBlockPos();
//        World world = getEntityWorld();
//        world.createExplosion(this, Explosion.createDamageSource(this.getEntityWorld(), this), null, this.getX(), this.getBodyY((double)0.0625F), this.getZ(), 48, false, World.ExplosionSourceType.TNT);
//    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}

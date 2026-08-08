package bayhasoft.adventurerscookbook;

import bayhasoft.adventurerscookbook.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class MudBallEntity extends ThrownItemEntity {
    public MudBallEntity(EntityType<? extends MudBallEntity> entityType, World world) {
        super(entityType, world);
    }

    public MudBallEntity(World world, LivingEntity owner, ItemStack stack) {
        super(EntityType.SNOWBALL, owner, world, stack);
    }

    public MudBallEntity(World world, double x, double y, double z, ItemStack stack) {
        super(EntityType.SNOWBALL, x, y, z, world, stack);
    }

    protected Item getDefaultItem() {
        return ModItems.MUD_BALL;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getEntityWorld().addParticleClient(particleEffect, this.getX(), this.getY(), this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        int i = entity instanceof BlazeEntity ? 3 : 0;
        entity.serverDamage(this.getDamageSources().thrown(this, this.getOwner()), (float)i);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos pos = blockHitResult.getBlockPos();
        World world = getEntityWorld();
        if(this.getEntityWorld().getBlockState(pos).isIn(BlockTags.DIRT)) {
            world.setBlockState(pos, Blocks.MUD.getDefaultState());
        }
    }

//    @Override
//    protected void onBlockHit(BlockHitResult blockHitResult) {
//        super.onBlockHit(blockHitResult);
//        BlockPos pos = blockHitResult.getBlockPos();
//        World world = getEntityWorld();
//        world.createExplosion(this, Explosion.createDamageSource(this.getEntityWorld(), this), null, this.getX(), this.getBodyY((double)0.0625F), this.getZ(), 48, false, World.ExplosionSourceType.TNT);
//    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }

    }
}

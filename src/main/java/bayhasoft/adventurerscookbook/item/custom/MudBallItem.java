package bayhasoft.adventurerscookbook.item.custom;

import bayhasoft.adventurerscookbook.MudBallEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class MudBallItem extends Item implements ProjectileItem {
    public static float POWER = 2.0F;
    public static float DIVERGENCE = 10.0F;

    public MudBallItem(Item.Properties settings) {
        super(settings);
    }

    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        world.playSound((Entity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (world instanceof ServerLevel serverWorld) {
            Projectile.spawnProjectileFromRotation(MudBallEntity::new, serverWorld, itemStack, user, 0.0F, POWER, DIVERGENCE);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
//        itemStack.decrementUnlessCreative(1, user);
        return InteractionResult.SUCCESS;
    }

    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
        return new Snowball(world, pos.x(), pos.y(), pos.z(), stack);
    }
}

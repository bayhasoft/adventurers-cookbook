package bayhasoft.adventurerscookbook.item.custom;

import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FoodBowlItem extends Item {
    
    public FoodBowlItem(Item.Properties settings){
        super(settings);
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        super.finishUsingItem(stack, world, user);
        if (user instanceof ServerPlayer){
            ServerPlayer serverPlayerEntity = (ServerPlayer)user;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(this));
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.BOWL);
        }
        if (user instanceof Player && !((Player)user).getAbilities().instabuild) {
            ItemStack itemStack = new ItemStack(Items.BOWL);
            Player playerEntity = (Player)user;
            if (!playerEntity.getInventory().add(itemStack)) {
                playerEntity.drop(itemStack, false);
            }
        }
        return stack;
    }

}

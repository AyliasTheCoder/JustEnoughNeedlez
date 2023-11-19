package me.aylias.justenough.needles;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Needle extends Item {

    public Needle(Properties p_41383_) {
        super(p_41383_.stacksTo(8));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        var tag = player.getItemInHand(hand).getTag();
        if (tag == null) return InteractionResultHolder.consume(player.getItemInHand(hand));

        ItemStack potion = ItemStack.of(tag.getCompound("Potion"));
        potion.finishUsingItem(level, player);

        player.getItemInHand(hand).shrink(1);
        player.getInventory().add(new ItemStack(JustEnoughNeedles.EMPTY_NEEDLE.get()));

        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}

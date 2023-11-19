package me.aylias.justenough.needles;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EmptyNeedle extends Item {

    public EmptyNeedle(Properties p_41383_) {
        super(p_41383_.stacksTo(8));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (level.isClientSide) return InteractionResultHolder.success(player.getItemInHand(hand));

        switch (hand) {
            case MAIN_HAND -> {
                ItemStack offhand = player.getItemInHand(InteractionHand.OFF_HAND);
                if (offhand.getItem().equals(Items.POTION)) {
                    ItemStack copy = offhand.copy();
                    ItemStack needle = new ItemStack(JustEnoughNeedles.NEEDLE.get());
                    CompoundTag tag = needle.getOrCreateTag();
                    tag.put("Potion", copy.serializeNBT());
                    player.getItemInHand(hand).shrink(1);
                    player.getInventory().add(needle);
                    offhand.shrink(1);
                    player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
                }
            }
            case OFF_HAND -> {
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}

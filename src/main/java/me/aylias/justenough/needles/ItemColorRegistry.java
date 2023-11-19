package me.aylias.justenough.needles;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.aylias.justenough.needles.JustEnoughNeedles.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemColorRegistry {

    public static ItemColor itemColor = (itemStack, tintIndex) -> {
        CompoundTag tag = itemStack.getTag();
        if (tag == null) return 0x000;

        return PotionUtils.getColor(ItemStack.of(tag.getCompound("Potion")));
    };

    @SubscribeEvent
    public static void colorHandler(RegisterColorHandlersEvent.Item event) {
        event.register(itemColor, JustEnoughNeedles.NEEDLE.get());
    }
}

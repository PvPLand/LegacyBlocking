package land.pvp.legacyblocking.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class EquipProgressMixin {

    @Redirect(method = "doItemUse",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/HeldItemRenderer;resetEquipProgress(Lnet/minecraft/util/Hand;)V")
    )
    public void redirect(HeldItemRenderer instance, Hand hand) {
        ItemStack itemStack = MinecraftClient.getInstance().player.getMainHandStack();
        if (itemStack.isIn(ItemTags.SWORDS) && itemStack.contains(DataComponentTypes.CONSUMABLE)) {
            // Do nothing
        } else {
            instance.resetEquipProgress(hand);
        }
    }
}

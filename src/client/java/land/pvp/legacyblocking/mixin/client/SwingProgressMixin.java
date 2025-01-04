package land.pvp.legacyblocking.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(HeldItemRenderer.class)
public class SwingProgressMixin {

    @Final
    @Shadow
    private MinecraftClient client;

    @Redirect(method = "renderFirstPersonItem",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/HeldItemRenderer;applyEquipOffset(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/Arm;F)V")
    )
    public void redirect(HeldItemRenderer instance, MatrixStack matrices, Arm arm, float equipProgress) {
        float swingProgress = client.player.getHandSwingProgress(client.getRenderTickCounter().getTickDelta(true));
        this.applyEquipOffset(matrices, arm, equipProgress);
        this.applySwingOffset(matrices, arm, swingProgress);
    }

    @Shadow
    private void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {}

    @Shadow
    private void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress) {}
}
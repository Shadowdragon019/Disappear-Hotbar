package lol.roxxane.disappear_hotbar.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import lol.roxxane.disappear_hotbar.De;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VanillaGuiOverlay.class)
abstract class VanillaGuiOverlayMixin {
	@ModifyExpressionValue(method = "lambda$static$5",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;hideGui:Z"))
	private static boolean Inject$hotbar_lambda(boolean original) {
		return De.f1_state != De.F1State.SHOW;
	}
}
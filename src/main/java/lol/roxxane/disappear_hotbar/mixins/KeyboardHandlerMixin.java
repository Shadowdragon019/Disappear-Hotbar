package lol.roxxane.disappear_hotbar.mixins;

import lol.roxxane.disappear_hotbar.De;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
abstract class KeyboardHandlerMixin {
	@Shadow @Final private Minecraft minecraft;

	@Inject(method = "keyPress",
		at = @At(value = "FIELD", shift = At.Shift.AFTER, opcode = Opcodes.PUTFIELD,
			target = "Lnet/minecraft/client/Options;hideGui:Z"))
	private void Inject_keyPress(long a, int b, int c, int d, int e, CallbackInfo ci) {
		De.f1_state = De.f1_state.next();
		minecraft.options.hideGui = De.f1_state == De.F1State.HIDE_ALL;
	}
}
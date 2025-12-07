package lol.roxxane.disappear_gui_overlay.mixins;

import lol.roxxane.disappear_gui_overlay.Mod;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static lol.roxxane.disappear_gui_overlay.Mod.F1State.HIDE_ALL;

@Mixin(KeyboardHandler.class)
abstract class KeyboardHandlerMixin {
	@Shadow @Final private Minecraft minecraft;
	@Inject(method = "keyPress", at = @At(value = "FIELD", shift = At.Shift.AFTER, opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Options;hideGui:Z"))
	private void cycleThrough3f1States(long $, int $1, int $2, int $3, int $4, CallbackInfo ci) {
		Mod.f1State = Mod.f1State.next();
		minecraft.options.hideGui = Mod.f1State == HIDE_ALL;
	}
}
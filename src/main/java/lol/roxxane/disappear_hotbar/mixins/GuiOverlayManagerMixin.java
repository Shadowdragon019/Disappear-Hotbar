package lol.roxxane.disappear_hotbar.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import lol.roxxane.disappear_hotbar.De;
import lol.roxxane.disappear_hotbar.DeConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiOverlayManager.class)
abstract class GuiOverlayManagerMixin {
	@ModifyExpressionValue(method = "lambda$init$0",
		remap = false,
		at = @At(value = "INVOKE",
			target = "Ljava/util/HashMap;get(Ljava/lang/Object;)Ljava/lang/Object;"))
	private static Object lambda$init$0_ModifyExpressionValue(
		Object original, @Local(argsOnly = true) ResourceLocation id)
	{
		return (IGuiOverlay) (gui, gui_graphics, partial_tick, screen_width, screen_height) -> {
			if (De.f1_state != De.F1State.HIDE_CONFIGURED || !DeConfig.GUI_OVERLAYS_TO_NOT_RENDER.contains(id))
				((IGuiOverlay) original).render(gui, gui_graphics, partial_tick, screen_width, screen_height);
		};
	}
}
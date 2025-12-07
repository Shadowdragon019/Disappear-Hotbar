package lol.roxxane.disappear_gui_overlay;

import lol.roxxane.disappear_gui_overlay.util.Id;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.LinkedList;
import java.util.List;

@EventBusSubscriber(modid = Mod.ID, bus = EventBusSubscriber.Bus.MOD)
public class ModClientConfig {
	private static final Builder BUILDER = new Builder();
	private static final ConfigValue<List<? extends String>> GUI_OVERLAYS_TO_NOT_RENDER_VALUE =
		BUILDER.comment("Only hide the HUD while keeping your hands visible when pressing F1!", "Press again to disable the HUD completely like normal.", "Highly configurable! Use the command /disappear_gui_overlay print_gui_overlays to see what elements you can blacklist from being hidden.")
			.defineListAllowEmpty("gui_overlays_to_render", new LinkedList<>(),
				element -> element instanceof String string && Id.validate(string));
	public static final ForgeConfigSpec SPEC = BUILDER.build();
	public static final LinkedList<ResourceLocation> GUI_OVERLAYS_TO_RENDER = new LinkedList<>();
	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		if (SPEC.isLoaded()) {
			GUI_OVERLAYS_TO_RENDER.clear();
			for (var string : GUI_OVERLAYS_TO_NOT_RENDER_VALUE.get()) {
				GUI_OVERLAYS_TO_RENDER.add(Id.of(string));
			}
		}
	}
}

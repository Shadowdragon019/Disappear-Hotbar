package lol.roxxane.disappear_hotbar;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber(modid = De.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DeConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> GUI_OVERLAYS_TO_NOT_RENDER_VALUE =
		BUILDER.defineList("gui_overlays_to_not_render", new LinkedList<>(),
			element -> element instanceof String string &&
				GuiOverlayManager.findOverlay(ResourceLocation.parse(string)) != null);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
	public static final LinkedList<ResourceLocation> GUI_OVERLAYS_TO_NOT_RENDER = new LinkedList<>();

	@SubscribeEvent
	static void on_load(final ModConfigEvent event) {
		if (SPEC.isLoaded()) {
			GUI_OVERLAYS_TO_NOT_RENDER.clear();
			for (var string : GUI_OVERLAYS_TO_NOT_RENDER_VALUE.get())
				GUI_OVERLAYS_TO_NOT_RENDER.add(ResourceLocation.parse(string));
			De.LOGGER.info("Disappear, Hotbar Options");
			De.LOGGER.info(GuiOverlayManager.getOverlays().stream().map(NamedGuiOverlay::id).collect(ImmutableList.toImmutableList()).toString());
		}
	}
}
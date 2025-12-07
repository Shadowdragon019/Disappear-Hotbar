package lol.roxxane.disappear_gui_overlay;

import lol.roxxane.disappear_gui_overlay.packets.ModPacketHandler;
import lol.roxxane.disappear_gui_overlay.packets.client_bound.PrintGuiOverlaysPacket;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.NetworkDirection;

import static lol.roxxane.disappear_gui_overlay.Mod.F1State.HIDE_CONFIGURED;
import static lol.roxxane.disappear_gui_overlay.ModClientConfig.GUI_OVERLAYS_TO_RENDER;
import static net.minecraft.commands.Commands.literal;

@EventBusSubscriber(modid = Mod.ID, bus = EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {
	@SubscribeEvent
	public static void renderPre(RenderGuiOverlayEvent.Pre event) {
		if (Mod.f1State == HIDE_CONFIGURED && !GUI_OVERLAYS_TO_RENDER.contains(event.getOverlay().id())) {
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(literal(Mod.ID)
			.then(literal("print_gui_overlays")
				.executes(context -> {
					var player = context.getSource().getPlayerOrException();
					ModPacketHandler.INSTANCE.sendTo(new PrintGuiOverlaysPacket(), player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
					return 1;
				})));
	}
}

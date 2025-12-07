package lol.roxxane.disappear_gui_overlay.packets;

import com.google.common.collect.ImmutableList;
import lol.roxxane.disappear_gui_overlay.Mod;
import lol.roxxane.disappear_gui_overlay.packets.client_bound.PrintGuiOverlaysPacket;
import lol.roxxane.disappear_gui_overlay.util.Id;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
		Id.mod(Mod.ID),
		() -> PROTOCOL_VERSION,
		PROTOCOL_VERSION::equals,
		PROTOCOL_VERSION::equals
	);
	public static void register() {
		var i = -1;
		INSTANCE.registerMessage(i++, PrintGuiOverlaysPacket.class,
			(packet, buffer) -> {},
			buffer -> new PrintGuiOverlaysPacket(),
			(packet, context) -> {
				context.get().enqueueWork(ModPacketHandler::handlePrintGuiOverlayPacket);
				context.get().setPacketHandled(true);
			});
	}
	private static void handlePrintGuiOverlayPacket() {
		var minecraft = Minecraft.getInstance();
		var level = minecraft.level;
		if (level == null) return;
		var player = minecraft.player;
		if (player == null) return;
		var overlays = GuiOverlayManager.getOverlays().stream().map(NamedGuiOverlay::id).sorted().collect(ImmutableList.toImmutableList());
		player.sendSystemMessage(Component.translatable(Mod.ID + ".command.print_gui_overlays.header"));
		for (var overlay : overlays) {
			player.sendSystemMessage(
				Component.literal(overlay.toString())
					.withStyle(Style.EMPTY
						.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable(Mod.ID + ".click_to_copy", overlay.toString())))
						.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, overlay.toString()))));
		}
	}
}

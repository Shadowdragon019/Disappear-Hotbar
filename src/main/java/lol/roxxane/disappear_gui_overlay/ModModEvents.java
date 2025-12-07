package lol.roxxane.disappear_gui_overlay;

import lol.roxxane.disappear_gui_overlay.packets.ModPacketHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = lol.roxxane.disappear_gui_overlay.Mod.ID, bus = EventBusSubscriber.Bus.MOD)
public class ModModEvents {
	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(ModPacketHandler::register);
	}
}

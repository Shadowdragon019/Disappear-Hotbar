package lol.roxxane.disappear_gui_overlay;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@net.minecraftforge.fml.common.Mod(Mod.ID)
@EventBusSubscriber(modid = Mod.ID, bus = EventBusSubscriber.Bus.FORGE)
public class Mod {
    public static final String ID = "disappear_gui_overlay";
    public static final Logger LOGGER = LogUtils.getLogger();
	public static F1State f1State = F1State.SHOW_ALL;
	public Mod(FMLJavaModLoadingContext context) {
		context.registerConfig(ModConfig.Type.CLIENT, ModClientConfig.SPEC);
    }
    public static void log(Object object) {
        LOGGER.info(object.toString());
    }
    public static void log(String string, Object object) {
        LOGGER.info(string, object);
    }
    public static void log(String string, Object object_a, Object object_b) {
        LOGGER.info(string, object_a, object_b);
    }
    public static void log(String string, Object... objects) {
        LOGGER.info(string, objects);
    }
    public static void warn(Object object) {
        LOGGER.warn(object.toString());
    }
    public static void warn(String string, Object object) {
        LOGGER.warn(string, object);
    }
    public static void warn(String string, Object object_a, Object object_b) {
        LOGGER.warn(string, object_a, object_b);
    }
    public static void warn(String string, Object... objects) {
        LOGGER.warn(string, objects);
    }
	public enum F1State {
		SHOW_ALL() {
			@Override
			public F1State next() {
				return HIDE_CONFIGURED;
			}
		},
		HIDE_CONFIGURED() {
			@Override
			public F1State next() {
				return HIDE_ALL;
			}
		},
		HIDE_ALL() {
			@Override
			public F1State next() {
				return SHOW_ALL;
			}
		};
		public abstract F1State next();
	}
}

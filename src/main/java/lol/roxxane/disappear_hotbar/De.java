package lol.roxxane.disappear_hotbar;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(De.ID)
public class De {
    public static final String ID = "disappear_hotbar";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static F1State f1_state = F1State.SHOW_ALL;

    public De(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, DeConfig.SPEC);
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
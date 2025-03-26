package lol.roxxane.disappear_hotbar;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(De.ID)
public class De {
    public static final String ID = "disappear_hotbar";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static F1State f1_state = F1State.SHOW;

    public enum F1State {
        SHOW() {
            @Override
            public F1State next() {
                return HIDE_HOTBAR;
            }
        },
        HIDE_HOTBAR() {
            @Override
            public F1State next() {
                return HIDE_ALL;
            }
        },
        HIDE_ALL() {
            @Override
            public F1State next() {
                return SHOW;
            }
        };

        public abstract F1State next();
    }
}
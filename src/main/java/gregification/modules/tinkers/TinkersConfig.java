package gregification.modules.tinkers;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@LangKey("gregification.config.tinkers")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/tinkers_construct", category = "Tinkers Construct")
public class TinkersConfig {

    public static boolean smelteryMelting = true;

    public static boolean metalTools = true;

    public static boolean gemTools = true;

    public static ToolStats toolStats = new ToolStats();

    public static class ToolStats {

        @RangeDouble(min = 0.01, max = 1.0)
        public double durabilityModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double miningSpeedModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double attackDamageModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double handleModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double bowDrawSpeedModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double bowFlightSpeedModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double arrowMassModifier = 1.0;

        @RangeDouble(min = 0.01, max = 1.0)
        public double arrowAmmoModifier = 1.0;
    }
}

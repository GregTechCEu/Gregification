package gregification.modules.tfc;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;

@Config.LangKey("gregification.config.tfc")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/tfc", category = "TerraFirmaCraft")
public class TFCConfig {
    @Config.Comment({
            "Allow for GregTech items to give hunger in TerraFirmaCraft.",
            "Default: true"
    })
    @Config.RequiresMcRestart
    public static boolean allowGregTechFoodToHaveStatsInTFC = true;

    @Config.Comment({
            "Allow for GregTech items to give nutrients in TerraFirmaCraft.",
            "Default: true"
    })
    @Config.RequiresMcRestart
    public static boolean allowGregTechFoodToHaveNutrientsInTFC = true;

}

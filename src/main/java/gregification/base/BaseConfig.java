package gregification.base;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

// TODO make adding to this manually not required
@LangKey("gregification.config.base")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/base", category = "Base")
public class BaseConfig {

    @Comment({"Enable Ex Nihilo integration.", "Default: true"})
    @RequiresMcRestart
    public static boolean enableExNihiloModule = true;

    @Comment({"Enable Forestry Integration.", "Default: true"})
    @RequiresMcRestart
    public static boolean enableForestryModule = true;

    @Comment({"Enable Open Computers integration.", "Default: true"})
    @RequiresMcRestart
    public static boolean enableOpenComputersModule = true;

    @Comment({"Enable TerraFirmaCraft integration.", "Default: true"})
    @RequiresMcRestart
    public static boolean enableTerraFirmaCraftModule = true;

    @Comment({"Enable Tinkers' Construct integration.", "Default: true"})
    @RequiresMcRestart
    public static boolean enableTinkersConstructModule = true;
}

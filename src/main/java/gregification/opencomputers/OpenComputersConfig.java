package gregification.opencomputers;

import gregification.Gregification;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@LangKey("gregification.config.open_computers")
@Config(modid = Gregification.MODID, name = Gregification.MODID + "/open_computers", category = "Open Computers")
public class OpenComputersConfig {

    @Comment({
            "Enable Gregicality Science Open Computers integration.",
            "Requires Gregicality Science to be installed.",
            "Default: true"
    })
    @RequiresMcRestart
    public static boolean enableGregicalityOC = true;
}

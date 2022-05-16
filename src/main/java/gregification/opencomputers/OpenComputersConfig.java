package gregification.opencomputers;

import net.minecraftforge.common.config.Config;

public class OpenComputersConfig {

    @Config.Comment({
            "Enable Gregicality Science Open Computers integration.",
            "Requires Gregicality Science to be installed.",
            "Default: true"
    })
    @Config.RequiresMcRestart
    public boolean enableGregicalityOC = true;
}

package gregification.config;

import gregification.common.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class OpenComputersCfg {

    @Config.Comment("Enable Open Computers integration. Default: true")
    @Config.RequiresMcRestart
    public boolean enableOpenComputers = true;

    @Config.Comment("Enable Gregicality-only Open Computers integration. Ignored if Gregicality is not installed. Default: true")
    @Config.RequiresMcRestart
    public boolean enableGregicalityOC = true;
}

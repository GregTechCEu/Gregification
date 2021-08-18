package gregification.config;

import gregification.util.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class OpenComputersCfg {

    @Config.Comment("Enable Open Computers integration. Default: true")
    @Config.RequiresMcRestart
    public boolean enableOpenComputers = true;
}

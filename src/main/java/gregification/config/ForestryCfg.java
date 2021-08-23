package gregification.config;

import gregification.common.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class ForestryCfg {

    @Config.Comment("Enable Forestry Integration. Overrides all other options in this category. Default: true")
    public boolean enableForestry = true;

    @Config.Comment("Enable GT Electrodes. Default: true")
    public boolean gtElectrodes = true;
}

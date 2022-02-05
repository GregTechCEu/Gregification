package gregification.config;

import gregification.common.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class ForestryCfg {

    @Config.Comment({"Enable Forestry Integration. Overrides all other options in this category.", "Default: true"})
    public boolean enableForestry = true;

    @Config.Comment({"Enable GT Electrodes. Also overrides Forestry recipes.", "Default: true"})
    public boolean gtElectrodes = true;

    @Config.Comment({"Enable GT Bees. REQUIRES the Apiculture Forestry module.", "Recommended to also install ExtraBees, as some content requires it.", "Default: true"})
    public boolean gtBees = true;

    @Config.Comment({"Whether or not to nerf GT Comb processing recipes.", "Default: false"})
    public boolean nerfGTCombs = false;

    @Config.Comment({"Enable GT Apiary Frames. REQUIRES the Apiculture Forestry module.", "Default: true"})
    public boolean gtFrames = true;
}

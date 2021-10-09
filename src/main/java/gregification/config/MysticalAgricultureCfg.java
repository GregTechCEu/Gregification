package gregification.config;

import gregification.common.GFValues;
import net.minecraftforge.common.config.Config;

@Config(modid = GFValues.MODID)
public class MysticalAgricultureCfg {
    @Config.Comment("Enable Mystical Agriculture Integration. Overrides all other options in this category. Default: true")
    public boolean enableMysticalAgriculture = true;

}

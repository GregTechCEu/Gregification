package gregification.forestry.bees;

import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregtech.api.GTValues;

public class GTBees {

    public static final boolean TWILIGHT_BEES = GFConfig.forestry.twilightBees && GTValues.isModLoaded(GFValues.MODID_TF) && GTValues.isModLoaded(GFValues.MODID_MB);
    public static final boolean THAUMIC_BEES = GFConfig.forestry.thaumicBees && GTValues.isModLoaded(GFValues.MODID_THAUM) && GTValues.isModLoaded(GFValues.MODID_MB);
}

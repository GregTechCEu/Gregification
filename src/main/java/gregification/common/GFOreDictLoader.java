package gregification.common;

import gregtech.api.unification.OreDictUnifier;
import net.minecraftforge.fml.common.Loader;

public class GFOreDictLoader {

    public static void init() {

        // Thaumcraft
        if (Loader.isModLoaded(GFValues.MODID_THAUM)) {
            OreDictUnifier.registerOre(GFUtility.getModItem(GFValues.MODID_THAUM, "amber_block", 0), "blockAmber");
        }

        // Applied Energistics 2
        if (Loader.isModLoaded(GFValues.MODID_AE)) {
            OreDictUnifier.registerOre(GFUtility.getModItem(GFValues.MODID_AE, "fluix_block", 0), "blockFluix");
            OreDictUnifier.registerOre(GFUtility.getModItem(GFValues.MODID_AE, "quartz_block", 0), "blockCertusQuartz");
        }
    }
}

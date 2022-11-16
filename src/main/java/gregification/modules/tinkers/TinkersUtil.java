package gregification.modules.tinkers;

import com.google.common.base.CaseFormat;
import gregification.modules.tinkers.material.TMaterial;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;

import static gregtech.api.unification.ore.OrePrefix.*;

public class TinkersUtil {

    private static OrePrefix[] oreTypePrefixes;

    // TODO CEu should have a way to get this
    public static OrePrefix[] getOreTypePrefixes() {
        if (oreTypePrefixes != null) return oreTypePrefixes;
        if (ConfigHolder.worldgen.allUniqueStoneTypes) {
            return oreTypePrefixes = new OrePrefix[]{
                    ore, oreGranite, oreDiorite, oreAndesite, oreBlackgranite, oreRedgranite,
                    oreMarble, oreBasalt, oreNetherrack, oreEndstone, oreSand, oreRedSand,
            };
        }
        return oreTypePrefixes = new OrePrefix[]{
                ore, oreNetherrack, oreEndstone,
        };
    }

    public static String getOreName(OrePrefix p, Material m) {
        return new UnificationEntry(p, m).toString();
    }

    public static String getFormattedName(Material m) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, m.toCamelCaseString());
    }

    public static void registerMelting(TMaterial tconMaterial, Material gtMaterial) {
        if (gtMaterial.hasProperty(PropertyKey.BLAST)) return;
        if (gtMaterial.hasProperty(PropertyKey.ORE)) {
            OreProperty prop = gtMaterial.getProperty(PropertyKey.ORE);
            for (OrePrefix p : getOreTypePrefixes()) {
                TinkerRegistry.registerMelting(getOreName(p, gtMaterial), gtMaterial.getFluid(), (int) (GTValues.L * prop.getOreMultiplier() * Config.oreToIngotRatio));
            }
        }
        if (gtMaterial.hasProperty(PropertyKey.DUST)) {
            TinkerRegistry.registerMelting(getOreName(OrePrefix.dust, gtMaterial), gtMaterial.getFluid(), GTValues.L);
        }
    }
}

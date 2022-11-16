package gregification.modules.tinkers.material;

import gregification.modules.tinkers.traits.TinkersTraits;
import gregtech.api.unification.material.Materials;
import slimeknights.tconstruct.library.materials.MaterialTypes;

public class MaterialAdditions {

    public static void init() {
        // TODO just a first test material
        MaterialStats.createIngotTemplate(Materials.Chrome)
                .setArrowShaft(1, 10)
                .setFletching(1.0f, 2.0f)
                .setArrowShaft(1.5f, 42)
                .setBowString(1.2f)
                .addTrait(TinkersTraits.TRAIT_RADIOACTIVE, MaterialTypes.HEAD, MaterialTypes.EXTRA)
                .build();
    }
}

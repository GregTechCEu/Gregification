package gregification.modules.tinkers.material;

import gregification.modules.tinkers.traits.TinkersTraits;
import gregtech.api.unification.material.Materials;
import slimeknights.tconstruct.library.materials.MaterialTypes;

public class MaterialAdditions {

    public static void init() {
        // TODO A test of the additions section
        MaterialProcessing.getBuilder(Materials.Aluminium)
                .addTrait(TinkersTraits.TRAIT_RADIOACTIVE, MaterialTypes.HEAD, MaterialTypes.EXTRA);
    }
}

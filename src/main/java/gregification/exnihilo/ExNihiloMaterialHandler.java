package gregification.exnihilo;

import gregification.config.GFConfig;
import gregification.util.GFValues;
import gregtech.api.GTValues;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;

@IMaterialHandler.RegisterMaterialHandler
public class ExNihiloMaterialHandler implements IMaterialHandler {

    @Override
    public void onMaterialsInit() {
        if (GFConfig.exNihilo.enableExNihilo && GTValues.isModLoaded(GFValues.MODID_EXNI)) {
            OrePrefix.oreChunk.setGenerationCondition(mat -> mat.hasProperty(PropertyKey.ORE));
            OrePrefix.oreEnderChunk.setGenerationCondition(mat -> mat.hasProperty(PropertyKey.ORE));
            OrePrefix.oreNetherChunk.setGenerationCondition(mat -> mat.hasProperty(PropertyKey.ORE));
            OrePrefix.oreSandyChunk.setGenerationCondition(mat -> mat.hasProperty(PropertyKey.ORE));
        }
    }
}

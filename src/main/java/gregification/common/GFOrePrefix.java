package gregification.common;

import gregification.config.GFConfig;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

// This is just a convenient time to do this
@Mod.EventBusSubscriber
public class GFOrePrefix {

    // Ex Nihilo
    public static MaterialIconType oreChunkIcon;
    public static MaterialIconType oreEnderChunkIcon;
    public static MaterialIconType oreNetherChunkIcon;
    public static MaterialIconType oreSandyChunkIcon;

    public static OrePrefix oreChunk;
    public static OrePrefix oreEnderChunk;
    public static OrePrefix oreNetherChunk;
    public static OrePrefix oreSandyChunk;

    @SubscribeEvent
    public static void onMaterialRegisterEvent(GregTechAPI.MaterialEvent event) {
        materialFlagAdditions();

        if (GFConfig.exNihilo.enableExNihilo && GTValues.isModLoaded(GFValues.MODID_EXNI)) {
            initExNihilo();
        }
    }

    private static void initExNihilo() {
        oreChunkIcon = new MaterialIconType("oreChunk");
        oreEnderChunkIcon = new MaterialIconType("oreEnderChunk");
        oreNetherChunkIcon = new MaterialIconType("oreNetherChunk");
        oreSandyChunkIcon = new MaterialIconType("oreSandyChunk");

        oreChunk = new OrePrefix("oreChunk", -1, null, oreChunkIcon, ENABLE_UNIFICATION, hasOreProperty);
        oreEnderChunk = new OrePrefix("oreEnderChunk", -1, null, oreEnderChunkIcon, ENABLE_UNIFICATION, hasOreProperty);
        oreNetherChunk = new OrePrefix("oreNetherChunk", -1, null, oreNetherChunkIcon, ENABLE_UNIFICATION, hasOreProperty);
        oreSandyChunk = new OrePrefix("oreSandyChunk", -1, null, oreSandyChunkIcon, ENABLE_UNIFICATION, hasOreProperty);

        oreChunk.setAlternativeOreName(OrePrefix.oreGravel.name());
        oreEnderChunk.setAlternativeOreName(OrePrefix.oreEndstone.name());
        oreNetherChunk.setAlternativeOreName(OrePrefix.oreNetherrack.name());
        oreSandyChunk.setAlternativeOreName(OrePrefix.oreSand.name());

        MetaItems.addOrePrefix(oreChunk, oreEnderChunk, oreNetherChunk, oreSandyChunk);
    }

    private static void materialFlagAdditions() {
        Materials.Emerald.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Apatite.addFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Lapis.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
    }
}

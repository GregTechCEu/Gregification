package gregification.common;

import gregification.config.GFConfig;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraftforge.fml.common.Loader;
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

        if (GFConfig.exNihilo.enableExNihilo && Loader.isModLoaded(GFValues.MODID_EXNI)) {
            initExNihilo();
        }
        if (GFConfig.forestry.enableForestry && GTValues.isModLoaded(GFValues.FORESTRY)) {
            initForestry();
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

        oreChunk.setAlternativeOreName(OrePrefix.ore.name());
        oreEnderChunk.setAlternativeOreName(OrePrefix.oreEndstone.name());
        oreNetherChunk.setAlternativeOreName(OrePrefix.oreNetherrack.name());
        oreSandyChunk.setAlternativeOreName(OrePrefix.ore.name());

        MetaItems.addOrePrefix(oreChunk, oreEnderChunk, oreNetherChunk, oreSandyChunk);
    }

    private static void initForestry() {

        // Electron Tubes
        Materials.Emerald.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Apatite.addFlags(MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Lapis.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Copper.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Materials.Rubber.addFlags(MaterialFlags.GENERATE_ROD);

        // Forestry Frames
        Materials.TreatedWood.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        Materials.Uranium235.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        Materials.Plutonium241.addFlags(MaterialFlags.GENERATE_LONG_ROD, MaterialFlags.GENERATE_FOIL);
        Materials.BlueSteel.addFlags(MaterialFlags.GENERATE_LONG_ROD);

        // Blocks for Bee Breeding
        Materials.Arsenic.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
        Materials.Lithium.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
        Materials.Electrotine.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);
        Materials.Lutetium.addFlags(MaterialFlags.FORCE_GENERATE_BLOCK);

        // Ores for Comb Processing
        OreProperty property;

        property = new OreProperty();
        property.setOreByProducts(Materials.Phosphorus);
        Materials.Phosphate.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Iron, Materials.Magnesium);
        Materials.Chrome.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Chrome, Materials.Iron);
        Materials.Manganese.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Olivine);
        Materials.Magnesium.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.SiliconDioxide);
        Materials.Silicon.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Tin, Materials.Gallium);
        Materials.Zinc.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Zinc, Materials.Iron);
        Materials.Antimony.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Manganese, Materials.Molybdenum);
        Materials.Tungsten.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Almandine);
        Materials.Titanium.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Iridium);
        Materials.Osmium.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Platinum, Materials.Osmium);
        Materials.Iridium.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Gold, Materials.Silver);
        Materials.Electrum.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Lead, Materials.Uranium235, Materials.Thorium);
        Materials.Uranium238.setProperty(PropertyKey.ORE, property);

        Materials.Uranium235.setProperty(PropertyKey.ORE, new OreProperty());

        property = new OreProperty();
        property.setOreByProducts(Materials.Naquadah, Materials.Naquadria);
        Materials.NaquadahEnriched.setProperty(PropertyKey.ORE, property);

        property = new OreProperty();
        property.setOreByProducts(Materials.Neutronium);
        Materials.Neutronium.setProperty(PropertyKey.ORE, property);

        Materials.Gallium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Niobium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Arsenic.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Bismuth.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Rutile.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Naquadria.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Lutetium.setProperty(PropertyKey.ORE, new OreProperty());
        Materials.Americium.setProperty(PropertyKey.ORE, new OreProperty());
    }
}

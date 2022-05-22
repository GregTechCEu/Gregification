package gregification.forestry;

import gregification.base.BaseConfig;
import gregification.base.BaseModule;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.forestry.bees.*;
import gregification.forestry.frames.GTFrameType;
import gregification.forestry.frames.GTItemFrame;
import gregification.forestry.recipes.*;
import gregification.forestry.tools.ToolScoop;
import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static gregtech.api.unification.material.info.MaterialFlags.*;

public class ForestryModule extends Module {

    public static final Logger logger = LogManager.getLogger("Gregification: Forestry");

    // Frequently used booleans for enabling some features

    public static final boolean TWILIGHT_BEES = BaseConfig.forestry.twilightBees && Loader.isModLoaded(ModIDs.MODID_TF) && Loader.isModLoaded(ModIDs.MODID_MB);
    public static final boolean THAUMIC_BEES = BaseConfig.forestry.thaumicBees && Loader.isModLoaded(ModIDs.MODID_THAUM) && Loader.isModLoaded(ModIDs.MODID_MB);

    // Items

    public static GTItemComb gtCombs;
    public static GTItemDrop gtDrops;
    public static Map<GTFrameType, GTItemFrame> gtFrames = new HashMap<>();

    public static MetaItem<?>.MetaValueItem ELECTRODE_APATITE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BLAZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BRONZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_COPPER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_DIAMOND;
    public static MetaItem<?>.MetaValueItem ELECTRODE_EMERALD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ENDER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_GOLD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_IRON;
    public static MetaItem<?>.MetaValueItem ELECTRODE_LAPIS;
    public static MetaItem<?>.MetaValueItem ELECTRODE_OBSIDIAN;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ORCHID;
    public static MetaItem<?>.MetaValueItem ELECTRODE_RUBBER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_TIN;

    public static ToolMetaItem<?>.MetaToolValueItem SCOOP;

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableForestryModule && Loader.isModLoaded(ModIDs.MODID_FR);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        // Initialize GT Comb and Drop items
        if (BaseConfig.forestry.gtBees) {
            if (ForestryUtils.apicultureEnabled()) {
                gtCombs = new GTItemComb();
                gtDrops = new GTItemDrop();
            } else {
                logger.error("GT Bees is enabled, but Forestry Apiculture module is disabled. Skipping GT Bees...");
            }
        }

        // Initialize GT Frame item
        if (BaseConfig.forestry.gtFrames) {
            if (ForestryUtils.apicultureEnabled()) {
                for (GTFrameType type : GTFrameType.values()) {
                    gtFrames.put(type, new GTItemFrame(type));
                }
            } else {
                logger.error("GT Frames is enabled, but Forestry Apiculture module is disabled. Skipping GT Frames...");
            }
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Replace Forestry Electrode recipes
        if (BaseConfig.forestry.gtElectrodes) {
            logger.info("Registering Forestry Electrode recipe overrides");
            ElectrodeRecipes.removeForestryRecipes();
            ElectrodeRecipes.addForestryMachineRecipes();
        }

        // Register GT Bee species, alleles, mutations
        if (ForestryUtils.apicultureEnabled()) {
            if (BaseConfig.forestry.gtBees) {
                logger.info("Registering GT Bee alleles, species and mutations");
                GTAlleleBeeSpecies.setupGTAlleles();
                GTBeeDefinition.initBees();
            }
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        // Replace other Forestry recipes
        if (ForestryUtils.apicultureEnabled()) {
            logger.info("Registering Forestry Comb recipe overrides");
            CombRecipes.initForestryCombs();
        }
    }

    @Override
    public void registerItems(RegistryEvent.Register<Item> event) {
        // Register GT Combs, Drops, and Frames
        if (ForestryUtils.apicultureEnabled()) {
            if (BaseConfig.forestry.gtBees) {
                logger.info("Registering GT Comb and Drop items");
                event.getRegistry().register(gtCombs);
                event.getRegistry().register(gtDrops);
            }
            if (BaseConfig.forestry.gtFrames) {
                logger.info("Registering GT Frame items");
                gtFrames.values().forEach(f -> event.getRegistry().register(f));
            }
        }

        // Register GT Electron Tubes
        if (BaseConfig.forestry.gtElectrodes) {
            logger.info("Registering GT Electron Tubes");
            ELECTRODE_APATITE = BaseModule.baseMetaItem.addItem(1, "electrode.apatite");
            ELECTRODE_BLAZE = BaseModule.baseMetaItem.addItem(2, "electrode.blaze");
            ELECTRODE_BRONZE = BaseModule.baseMetaItem.addItem(3, "electrode.bronze");
            ELECTRODE_COPPER = BaseModule.baseMetaItem.addItem(4, "electrode.copper");
            ELECTRODE_DIAMOND = BaseModule.baseMetaItem.addItem(5, "electrode.diamond");
            ELECTRODE_EMERALD = BaseModule.baseMetaItem.addItem(6, "electrode.emerald");
            ELECTRODE_ENDER = BaseModule.baseMetaItem.addItem(7, "electrode.ender");
            ELECTRODE_GOLD = BaseModule.baseMetaItem.addItem(8, "electrode.gold");
            ELECTRODE_LAPIS = BaseModule.baseMetaItem.addItem(9, "electrode.lapis");
            ELECTRODE_OBSIDIAN = BaseModule.baseMetaItem.addItem(10, "electrode.obsidian");
            ELECTRODE_TIN = BaseModule.baseMetaItem.addItem(11, "electrode.tin");

            if (Loader.isModLoaded(ModIDs.MODID_IC2) || Loader.isModLoaded(ModIDs.MODID_BINNIE)) {
                ELECTRODE_IRON = BaseModule.baseMetaItem.addItem(12, "electrode.iron");
            }
            if (Loader.isModLoaded(ModIDs.MODID_XU2)) {
                ELECTRODE_ORCHID = BaseModule.baseMetaItem.addItem(13, "electrode.orchid");
            }
            if (Loader.isModLoaded(ModIDs.MODID_IC2) || Loader.isModLoaded(ModIDs.MODID_TR) || Loader.isModLoaded(ModIDs.MODID_BINNIE)) {
                ELECTRODE_RUBBER = BaseModule.baseMetaItem.addItem(14, "electrode.rubber");
            }
        }

        // Register GT Scoop
        if (BaseConfig.forestry.gtScoop) {
            logger.info("Registering GT Scoop");
            SCOOP = (ToolMetaItem<?>.MetaToolValueItem) BaseModule.baseMetaTool.addItem(1, "tool.scoop")
                    .setToolStats(new ToolScoop())
                    .setFullRepairCost(3)
                    .addOreDict("craftingToolScoop");
        }
    }

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        // GT Scoop recipes
        MiscRecipes.registerHandlers();

        // GT Electrode recipes
        if (BaseConfig.forestry.gtElectrodes) {
            logger.info("Registering GT Electrode recipes");
            ElectrodeRecipes.initGTRecipes();
        }

        if (ForestryUtils.apicultureEnabled()) {

            // GT Comb, Drop, and Propolis recipes, and Comb OreDict
            if (BaseConfig.forestry.gtBees) {
                logger.info("Registering GT Bee, Comb, Drop and Propolis recipes");
                for (GTCombType type : GTCombType.VALUES) {
                    OreDictUnifier.registerOre(ForestryUtils.getCombStack(type), "beeComb");
                }
                CombRecipes.initGTCombs();
                MiscRecipes.init();
            }

            // GT Frame recipes
            if (BaseConfig.forestry.gtFrames) {
                logger.info("Registering GT Frame recipes");
                FrameRecipes.init();
            }
        }
    }

    @Override
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
        logger.info("Registering Material additions");

        // Electron Tubes
        Materials.Emerald.addFlags(GENERATE_BOLT_SCREW);
        Materials.Apatite.addFlags(GENERATE_PLATE, GENERATE_BOLT_SCREW);
        Materials.Lapis.addFlags(GENERATE_BOLT_SCREW);
        Materials.Copper.addFlags(GENERATE_BOLT_SCREW);
        Materials.Rubber.addFlags(GENERATE_ROD);

        // Forestry Frames
        Materials.TreatedWood.addFlags(GENERATE_LONG_ROD);
        Materials.Uranium235.addFlags(GENERATE_LONG_ROD);
        Materials.Plutonium241.addFlags(GENERATE_LONG_ROD, GENERATE_FOIL);
        Materials.BlueSteel.addFlags(GENERATE_LONG_ROD);

        // Blocks for Bee Breeding
        Materials.Arsenic.addFlags(FORCE_GENERATE_BLOCK);
        Materials.Lithium.addFlags(FORCE_GENERATE_BLOCK);
        Materials.Electrotine.addFlags(FORCE_GENERATE_BLOCK);
        Materials.Lutetium.addFlags(FORCE_GENERATE_BLOCK);

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
        Materials.NetherStar.setProperty(PropertyKey.ORE, new OreProperty());
    }
}

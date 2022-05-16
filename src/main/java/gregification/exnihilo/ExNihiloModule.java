package gregification.exnihilo;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import gregification.base.BaseConfig;
import gregification.base.BaseUtility;
import gregification.base.IModule;
import gregification.exnihilo.items.ExNihiloPebble;
import gregification.exnihilo.metatileentities.MetaTileEntitySieve;
import gregification.exnihilo.metatileentities.MetaTileEntitySteamSieve;
import gregification.exnihilo.metatileentities.SieveRecipeMap;
import gregification.exnihilo.recipes.ExNihiloRecipes;
import gregification.exnihilo.recipes.MeshRecipes;
import gregification.exnihilo.recipes.SieveDrops;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.resources.SteamTexture;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;
import static gregtech.common.metatileentities.MetaTileEntities.getHighTier;
import static gregtech.common.metatileentities.MetaTileEntities.getMidTier;

public class ExNihiloModule implements IModule {

    public static final Logger logger = LogManager.getLogger("Gregification: Ex Nihilo");

    // Icon Types

    public static MaterialIconType oreChunkIcon;
    public static MaterialIconType oreEnderChunkIcon;
    public static MaterialIconType oreNetherChunkIcon;
    public static MaterialIconType oreSandyChunkIcon;

    // Ore Prefixes

    public static OrePrefix oreChunk;
    public static OrePrefix oreEnderChunk;
    public static OrePrefix oreNetherChunk;
    public static OrePrefix oreSandyChunk;

    // Items

    public static ExNihiloPebble gtPebble;

    // Recipe Maps

    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new SieveRecipeMap("electric_sieve", 2, 2, 1, 30, 0, 0, 0, 0, new SimpleRecipeBuilder().duration(100).EUt(4), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED)
            .setSound(SoundEvents.BLOCK_SAND_PLACE);

    // Meta Tile Entities

    public static MetaTileEntitySteamSieve STEAM_SIEVE_BRONZE;
    public static MetaTileEntitySteamSieve STEAM_SIEVE_STEEL;
    public static MetaTileEntitySieve[] SIEVES = new MetaTileEntitySieve[GTValues.V.length - 1];

    // Textures

    public static final SteamTexture PROGRESS_BAR_SIFTER_STEAM = SteamTexture.fullImage("textures/gui/progress_bar/progress_bar_sift_%s.png");


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Registering Ex Nihilo Compat Items, Blocks, and Machines");
        gtPebble = new ExNihiloPebble();
        SieveDrops.readSieveDropsFromConfig();
        ExNihiloRegistryManager.registerSieveDefaultRecipeHandler(new SieveDrops());
        registerMetaTileEntities();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ExNihiloRecipes.registerExNihiloRecipes();
        MeshRecipes.init();
    }

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        logger.info("Registering Ex Nihilo Compat Recipes");
        ExNihiloRecipes.registerHandlers();
        ExNihiloRecipes.registerGTRecipes();
    }

    @Override
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
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

    private void registerMetaTileEntities() {
        STEAM_SIEVE_BRONZE = MetaTileEntities.registerMetaTileEntity(4000, new MetaTileEntitySteamSieve(BaseUtility.gregificationId("sieve.steam"), false));
        STEAM_SIEVE_STEEL = MetaTileEntities.registerMetaTileEntity(4001, new MetaTileEntitySteamSieve(BaseUtility.gregificationId("steam_sieve_steel"), true));

        SIEVES[0] = MetaTileEntities.registerMetaTileEntity(4002, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.lv"), 1));
        SIEVES[1] = MetaTileEntities.registerMetaTileEntity(4003, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.mv"), 2));
        SIEVES[2] = MetaTileEntities.registerMetaTileEntity(4004, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.hv"), 3));
        SIEVES[3] = MetaTileEntities.registerMetaTileEntity(4005, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.ev"), 4));
        SIEVES[4] = MetaTileEntities.registerMetaTileEntity(4006, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.iv"), 5));
        if (getMidTier("sieve")) {
            SIEVES[5] = MetaTileEntities.registerMetaTileEntity(4007, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.luv"), 6));
            SIEVES[6] = MetaTileEntities.registerMetaTileEntity(4008, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.zpm"), 7));
            SIEVES[7] = MetaTileEntities.registerMetaTileEntity(4009, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.uv"), 8));
        }
        // TODO this config should ideally use the HIGH_TIER map instead of direct checking it, if the cfg is kept
        if (getHighTier("sieve") && BaseConfig.exNihilo.highTierSieve) {
            SIEVES[8] = MetaTileEntities.registerMetaTileEntity(4010, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.uhv"), 9));
            SIEVES[9] = MetaTileEntities.registerMetaTileEntity(4011, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.uev"), 10));
            SIEVES[10] = MetaTileEntities.registerMetaTileEntity(4012, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.uiv"), 11));
            SIEVES[11] = MetaTileEntities.registerMetaTileEntity(4013, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.uxv"), 12));
            SIEVES[12] = MetaTileEntities.registerMetaTileEntity(4014, new MetaTileEntitySieve(BaseUtility.gregificationId("sieve.opv"), 13));
        }
    }
}

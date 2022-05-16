package gregification;

import gregification.base.BaseConfig;
import gregification.base.IModule;
import gregification.base.ModIDs;
import gregtech.api.GregTechAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static gregification.Gregification.*;

@Mod(modid = MODID, name = MOD_NAME, version = VERSION,
        dependencies = "required-after:gregtech@[2.0-beta,);" + "required-after:mixinbooter;" +
                "after:gregicality@[1.0,);" +
                "after:forestry;" +
                "after:tconstruct;" +
                "after:exnihilocreatio;" +
                "after:mysticalagradditions;" +
                "after:binniecore;" +
                "after:binniedesign;" +
                "after:extrabees;" +
                "after:extratrees;" +
                "after:magicbees;" +
                "after:botany;" +
                "after:genetics;" +
                "after:gendustry")
public class Gregification {

    public static final String MODID = "gregification";
    public static final String MOD_NAME = "Gregification";
    public static final String VERSION = "@VERSION@";

    // Modules

    @SidedProxy(modId = MODID, serverSide = "gregification.base.BaseModule", clientSide = "gregification.base.BaseModule")
    private static IModule BASE;

    @SidedProxy(modId = MODID, serverSide = "gregification.forestry.ForestryModule", clientSide = "gregification.forestry.ForestryClientModule")
    private static IModule FORESTRY;

    @SidedProxy(modId = MODID, serverSide = "gregification.exnihilo.ExNihiloModule", clientSide = "gregification.exnihilo.ExNihiloModule")
    private static IModule EXNIHILO;

    @SidedProxy(modId = MODID, serverSide = "gregification.opencomputers.OpenComputersModule", clientSide = "gregification.opencomputers.OpenComputersModule")
    private static IModule OPENCOMPUTERS;

    // Module Map
    private static final Map<IModule, Boolean> MODULE_MAP = new HashMap<>();


    // Module acquisition

    private Map<IModule, Boolean> getModules() {
        if (MODULE_MAP.isEmpty()) {
            MODULE_MAP.put(BASE, true);
            MODULE_MAP.put(FORESTRY, BaseConfig.enableForestryModule && Loader.isModLoaded(ModIDs.MODID_FR));
            MODULE_MAP.put(EXNIHILO, BaseConfig.enableExNihiloModule && Loader.isModLoaded(ModIDs.MODID_EXNI));
            MODULE_MAP.put(OPENCOMPUTERS, BaseConfig.enableOpenComputersModule && Loader.isModLoaded(ModIDs.MODID_OC));
            // Add your new module here
        }
        return MODULE_MAP;
    }


    /////////////////////////////////////////////////
    // Below here only needs to be modified if you //
    // add a new event to the IModule interface.   //
    /////////////////////////////////////////////////


    // Event Handlers

    @EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        getModules().forEach((k, v) -> {
            if (v) k.preInit(event);
        });
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        getModules().forEach((k, v) -> {
            if (v) k.init(event);
        });
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        getModules().forEach((k, v) -> {
            if (v) k.postInit(event);
        });
    }


    // Subscribe Event listeners, only 1 set of listeners for the mod

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        getModules().forEach((k, v) -> {
            if (v) k.registerItems(event);
        });
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        getModules().forEach((k, v) -> {
            if (v) k.registerBlocks(event);
        });
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        getModules().forEach((k, v) -> {
            if (v) k.registerRecipes(event);
        });
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        getModules().forEach((k, v) -> {
            if (v) k.registerModels(event);
        });
    }

    @SubscribeEvent
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
        getModules().forEach((k, v) -> {
            if (v) k.registerMaterials(event);
        });
    }
}

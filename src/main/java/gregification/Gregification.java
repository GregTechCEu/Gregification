package gregification;

import gregification.base.Module;
import gregtech.api.GregTechAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

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
    private static Module _BASE;

    @SidedProxy(modId = MODID, serverSide = "gregification.forestry.ForestryModule", clientSide = "gregification.forestry.ForestryClientModule")
    private static Module _FORESTRY;

    @SidedProxy(modId = MODID, serverSide = "gregification.exnihilo.ExNihiloModule", clientSide = "gregification.exnihilo.ExNihiloModule")
    private static Module _EXNIHILO;

    @SidedProxy(modId = MODID, serverSide = "gregification.opencomputers.OpenComputersModule", clientSide = "gregification.opencomputers.OpenComputersModule")
    private static Module _OPENCOMPUTERS;


    /*----------------------------------------------------------------
    | To add a new Module, register it above with the @SidedProxy    |
    | annotation and list the class that implements Module.class     |
    | under its serverSide. If you need a clientSide as well, have   |
    | that class extend your serverSide class. No further            |
    | registration beyond that is needed.                            |
    ----------------------------------------------------------------*/


    //////////////////////////////////////////////
    // !!   Below here does not need to be   !! //
    // !!    modified to add a new module    !! //
    //////////////////////////////////////////////


    // Module List
    // Contains only active modules
    private static final List<Module> MODULE_LIST = new ArrayList<>();


    // Module acquisition

    private List<Module> getModules() {
        if (MODULE_LIST.isEmpty()) {
            Module.getModules().forEach(module -> {
                if (module.isModuleActive()) MODULE_LIST.add(module);
            });
        }
        return MODULE_LIST;
    }


    // Event Handlers

    @EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        getModules().forEach(m -> {
            List<Class<?>> listeners = m.getEventBusListeners();
            if (listeners != null) {
                listeners.forEach(MinecraftForge.EVENT_BUS::register);
            }
        });
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        getModules().forEach(m -> m.preInit(event));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        getModules().forEach(m -> m.init(event));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        getModules().forEach(m -> m.postInit(event));
    }


    // Subscribe Event listeners, only 1 set of listeners for the mod

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        getModules().forEach(m -> m.registerItems(event));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        getModules().forEach(m -> m.registerBlocks(event));
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        getModules().forEach(m -> m.registerRecipes(event));
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        getModules().forEach(m -> m.registerModels(event));
    }

    @SubscribeEvent
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
        getModules().forEach(m -> m.registerMaterials(event));
    }
}

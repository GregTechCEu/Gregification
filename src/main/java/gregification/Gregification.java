package gregification;

import gregification.base.Module;
import gregification.core.AnnotationProcessor;
import gregtech.api.GregTechAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


    //////////////////////////////////////////////
    // !!   This class does not need to be   !! //
    // !!    modified to add a new module    !! //
    //////////////////////////////////////////////


    public static final String MODID = "gregification";
    public static final String MOD_NAME = "Gregification";
    public static final String VERSION = GFInternalTags.VERSION;

    // Root logger
    public static final Logger logger = LogManager.getLogger("Gregification");


    // Module List
    // Contains only active modules
    private static final List<Module> MODULE_LIST = new ArrayList<>();

    public static void registerModule(Module module) {
        MODULE_LIST.add(module);
    }


    @EventHandler
    public void onConstruction(FMLConstructionEvent event) {
        AnnotationProcessor.loadModules(event.getASMHarvestedData());
        MinecraftForge.EVENT_BUS.register(this);
        MODULE_LIST.forEach(m -> {
            List<Class<?>> listeners = m.getEventBusListeners();
            if (listeners != null) {
                listeners.forEach(MinecraftForge.EVENT_BUS::register);
            }
        });
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MODULE_LIST.forEach(m -> m.preInit(event));
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MODULE_LIST.forEach(m -> m.init(event));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MODULE_LIST.forEach(m -> m.postInit(event));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        MODULE_LIST.forEach(m -> m.registerItems(event));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        MODULE_LIST.forEach(m -> m.registerBlocks(event));
    }

    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        MODULE_LIST.forEach(m -> m.registerRecipes(event));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        MODULE_LIST.forEach(m -> m.registerModels(event));
    }

    @SubscribeEvent
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
        MODULE_LIST.forEach(m -> m.registerMaterials(event));
    }

    @EventHandler
    public void respondIMC(FMLInterModComms.IMCEvent event) {
        MODULE_LIST.forEach(m -> m.respondIMC(event));
    }

}

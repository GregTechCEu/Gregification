package gregification.sample;

import gregification.base.Module;
import gregtech.api.GregTechAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * Declare your module with the {@link Module.Root} annotation,
 * passing a unique name as the module name.<br><br>
 *
 * You must also have your module class implement {@link Module}.<br><br>
 *
 * This name will be used in a few places as an identifier,
 * and will be used by any {@link Module.Log} annotated Loggers.<br><br>
 *
 * Nothing else is needed to register and initialize your module; it is all handled automatically.
 */
@Module.Root(name = "Sample Module")
public class SampleModule implements Module {

    /**
     * Annotate a public static {@link org.apache.logging.log4j.Logger} field
     * with {@link Module.Log} annotation to automatically populate the logger.
     */
    @Module.Log
    public static Logger logger;

    /**
     * Implement the {@link Module#isModuleActive()} method at a minimum to make a valid module.<br><br>
     *
     * This method should at least check if the module config is enabled, and if the required mods are loaded.
     *
     * @return true if this module should be initialized, false otherwise.
     *         This is only checked once during {@link net.minecraftforge.fml.common.event.FMLConstructionEvent},
     *         and if this returns false during that phase, this module will not be initialized.
     */
    @Override
    public boolean isModuleActive() {
        return false; // returning false so that this sample module isn't actually used
    }

    /**
     * This method is used to register custom classes to the event bus, when the default events
     * passed through by the {@link Module} interface are not enough. Typically this will either be
     * this module class, or some utility event handler class.<br><br>
     *
     * In this example, I am simply returning a singleton list with this class in it.
     *
     * @return A list of classes that should be registered to {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS}.
     */
    @Override
    public List<Class<?>> getEventBusListeners() {
        return Collections.singletonList(this.getClass());
    }

    /**
     * Simple pass-through for the Pre-Init, Init, and Post-Init event phases.
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    /**
     * Simple pass-throughs for the Item, Block, IRecipe, Model, and Material registry events.
     */
    @Override
    public void registerItems(RegistryEvent.Register<Item> event) {
    }

    @Override
    public void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @Override
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    }

    @Override
    public void registerModels(ModelRegistryEvent event) {
    }

    @Override
    public void registerMaterials(GregTechAPI.MaterialEvent event) {
    }
}

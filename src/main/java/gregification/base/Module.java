package gregification.base;

import gregtech.api.GregTechAPI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.annotation.*;
import java.util.List;

public interface Module {

    /**
     * Should return true if the required mod is loaded, and if
     * the required config settings are enabled.
     */
    boolean isModuleActive();

    /**
     * Override this and return a List of Classes that should be registered to the Event Bus.
     */
    default List<Class<?>> getEventBusListeners() {
        return null;
    }


    // Event stages

    default void preInit(FMLPreInitializationEvent event) {
    }

    default void init(FMLInitializationEvent event) {
    }

    default void postInit(FMLPostInitializationEvent event) {
    }


    // Common events

    default void registerItems(RegistryEvent.Register<Item> event) {
    }

    default void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    default void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    }

    default void registerModels(ModelRegistryEvent event) {
    }

    default void registerMaterials(GregTechAPI.MaterialEvent event) {
    }

    /**
     * Annotate your module class with this to automatically create and initialize your Module.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Root {
        String name();
    }

    /**
     * Annotate a static field of type {@link org.apache.logging.log4j.Logger} to automatically populate the Logger.
     *
     * MUST be on a class annotated with {@link Root}, and will automatically use {@link Root#name()} as the Logger name.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Log {
    }
}

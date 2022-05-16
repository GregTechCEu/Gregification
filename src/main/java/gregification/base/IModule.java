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

/**
 * A basic interface for modules to describe the main stages of the game launch and common events.
 * Add new default methods here if they need to be called on any module from the main mod class.
 */
public interface IModule {

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
}

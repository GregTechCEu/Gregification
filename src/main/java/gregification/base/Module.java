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

import java.util.ArrayList;
import java.util.List;

public abstract class Module {

    private static final List<Module> MODULES = new ArrayList<>();

    public Module() {
        MODULES.add(this);
    }

    public static List<Module> getModules() {
        return MODULES;
    }

    /**
     * Should return true if the required mod is loaded, and if
     * the required config settings are enabled.
     */
    public abstract boolean isModuleActive();

    /**
     * Override this and return a List of Classes that should be registered to the Event Bus.
     */
    public List<Class<?>> getEventBusListeners() {
        return null;
    }


    // Event stages

    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
    }

    public void postInit(FMLPostInitializationEvent event) {
    }


    // Common events

    public void registerItems(RegistryEvent.Register<Item> event) {
    }

    public void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    }

    public void registerModels(ModelRegistryEvent event) {
    }

    public void registerMaterials(GregTechAPI.MaterialEvent event) {
    }
}

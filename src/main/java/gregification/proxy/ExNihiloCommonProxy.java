package gregification.proxy;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import gregification.common.GFMetaTileEntities;
import gregification.config.GFConfig;
import gregification.exnihilo.*;
import gregification.common.GFLog;
import gregification.common.GFValues;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GFValues.MODID)
public class ExNihiloCommonProxy {

    @Optional.Method(modid = GFValues.MODID_EXNI)
    public void preInit() {
        if (GFConfig.exNihilo.enableExNihilo) {
            GFLog.exNihiloLogger.info("Registering Ex Nihilo Compat Items, Blocks, and Machines");
            ExNihiloPebble.register();
            SieveDrops.readSieveDropsFromConfig();
            ExNihiloRegistryManager.registerSieveDefaultRecipeHandler(new SieveDrops());
            GFMetaTileEntities.registerExNihilo();
        }
    }

    @Optional.Method(modid = GFValues.MODID_EXNI)
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GFConfig.exNihilo.enableExNihilo) {
            GFLog.exNihiloLogger.info("Registering Ex Nihilo Compat Recipes");
            ExNihiloRecipes.registerHandlers();
            ExNihiloRecipes.registerGTRecipes();
        }
    }

    @Optional.Method(modid = GFValues.MODID_EXNI)
    public void init() {
        if (GFConfig.exNihilo.enableExNihilo) {
            ExNihiloRecipes.registerExNihiloRecipes();
        }
    }
}

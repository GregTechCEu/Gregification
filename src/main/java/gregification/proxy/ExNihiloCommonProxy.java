package gregification.proxy;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import gregification.config.GFConfig;
import gregification.exnihilo.*;
import gregification.exnihilo.metatileentities.ExNihiloMetaTileEntities;
import gregification.util.GFLog;
import gregification.util.GFValues;
import gregtech.api.GTValues;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = GFValues.MODID)
public class ExNihiloCommonProxy {

    @Optional.Method(modid = GFValues.MODID_EXNI)
    @EventHandler
    public void preInit() {
        if (GFConfig.exNihilo.enableExNihilo && GTValues.isModLoaded(GFValues.MODID_EXNI)) {
            GFLog.exNihiloLogger.info("Registering Ex Nihilo Compat Items, Blocks, and Machines");
            ExNihiloEnums.preInit();
            ExNihiloPebble.register();
            SieveDrops.readSieveDropsFromConfig();
            ExNihiloRegistryManager.registerSieveDefaultRecipeHandler(new SieveDrops());
            ExNihiloMetaTileEntities.register();
        }
    }

    @Optional.Method(modid = GFValues.MODID_EXNI)
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        if (GFConfig.exNihilo.enableExNihilo && GTValues.isModLoaded(GFValues.MODID_EXNI)) {
            GFLog.exNihiloLogger.info("Registering Ex Nihilo Compat Recipes");
            ExNihiloRecipes.registerHandlers();
            ExNihiloRecipes.registerRecipes();
        }
    }
}

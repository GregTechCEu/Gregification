package gregification.opencomputers;

import gregification.base.BaseConfig;
import gregification.base.BaseUtility;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.opencomputers.drivers.*;
import gregification.opencomputers.drivers.specific.*;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenComputersModule extends Module {

    public static final Logger logger = LogManager.getLogger("Gregification: Open Computers");

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableOpenComputersModule && Loader.isModLoaded(ModIDs.MODID_OC);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        // Incompatible with this module (probably doesn't work with GTCEu anyway...)
        BaseUtility.throwIncompatibilityIfLoaded("gtce2oc",
                "All functionality from this mod has been implemented in Gregification.");

        logger.info("Registering Open Computers Drivers...");
        Driver.add(new DriverEnergyContainer());
        Driver.add(new DriverWorkable());
        Driver.add(new DriverAbstractRecipeLogic());
        Driver.add(new DriverRecipeMapMultiblockController());
        Driver.add(new DriverICoverable());
        Driver.add(new DriverSimpleMachine());
        Driver.add(new DriverFusionReactor());
        Driver.add(new DriverWorldAccelerator());
        Driver.add(new DriverConverter());
        //Driver.add(new DriverBatteryTower());

        // TODO Waiting on Gregicality Science
        if (BaseConfig.openComputers.enableGregicalityOC && Loader.isModLoaded(ModIDs.MODID_GCYS)) {
            //Driver.add(new DriverNuclearReactor());
            //Driver.add(new DriverVoidMiner());
            //Driver.add(new DriverQubitMultiblockController());
        }
    }
}

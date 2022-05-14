package gregification.proxy;

import gregification.config.GFConfig;
import gregification.opencomputers.drivers.*;
import gregification.opencomputers.drivers.specific.*;
import gregification.common.GFLog;
import gregification.common.GFValues;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;

public class OCCommonProxy {

    @Optional.Method(modid = GFValues.MODID_OC)
    public void init() {
        if (GFConfig.openComputers.enableOpenComputers) {
            GFLog.ocLogger.info("Registering Open Computers Drivers...");

            if (!Loader.isModLoaded(GFValues.MODID_GTOC)) { // try to avoid colliding with GTCE2OC driver
                Driver.add(new DriverEnergyContainer());
                Driver.add(new DriverWorkable());
            } else GFLog.ocLogger.warn("GTCE2OC mod detected, problems may occur!");

            Driver.add(new DriverAbstractRecipeLogic());
            Driver.add(new DriverRecipeMapMultiblockController());
            Driver.add(new DriverICoverable());
            Driver.add(new DriverSimpleMachine());
            Driver.add(new DriverFusionReactor());
            Driver.add(new DriverWorldAccelerator());
            //Driver.add(new DriverBatteryTower());

            // TODO Waiting on Gregicality Science
            //if (GFConfig.openComputers.enableGregicalityOC && Loader.isModLoaded(GFValues.MODID_GCYS)) {
                //Driver.add(new DriverNuclearReactor());
                //Driver.add(new DriverVoidMiner());
                //Driver.add(new DriverQubitMultiblockController());
            //}
        }
    }
}

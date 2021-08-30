package gregification.proxy;

import gregification.config.GFConfig;
import gregification.opencomputers.drivers.*;
import gregification.opencomputers.drivers.specific.*;
import gregification.common.GFLog;
import gregification.common.GFValues;
import gregtech.api.GTValues;
import li.cil.oc.api.Driver;

public class OCCommonProxy {

    public void init() {
        if (GFConfig.openComputers.enableOpenComputers) {
            GFLog.ocLogger.info("Registering Open Computers Drivers...");

            if (!GTValues.isModLoaded(GFValues.MODID_GTOC)) { // try to avoid colliding with GTCE2OC driver
                Driver.add(new DriverEnergyContainer());
                Driver.add(new DriverWorkable());
            } else GFLog.ocLogger.warn("GTCE2OC mod detected, problems may occur!");

            Driver.add(new DriverAbstractRecipeLogic());
            Driver.add(new DriverMultiblockRecipeLogic());
            Driver.add(new DriverICoverable());
            Driver.add(new DriverSimpleMachine());
            Driver.add(new DriverFusionReactor());

            // TODO Waiting on Gregicality to be at least functional with CEu
            //if (GFConfig.openComputers.enableGregicalityOC && GTValues.isModLoaded(GFValues.MODID_GCY)) {
                //Driver.add(new DriverNuclearReactor());
                //Driver.add(new DriverVoidMiner());
                //Driver.add(new DriverWorldAccelerator()); // todo this may move to CEu
                //Driver.add(new DriverQubitMultiblockController());
                //Driver.add(new DriverBatteryTower()); // todo this will move to CEu
            //}
        }
    }
}

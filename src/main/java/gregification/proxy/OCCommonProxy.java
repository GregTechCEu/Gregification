package gregification.proxy;

import gregification.config.GFConfig;
import gregification.opencomputers.drivers.*;
import gregification.opencomputers.drivers.specific.*;
import gregification.util.GFLog;
import gregification.util.GFValues;
import gregtech.api.GTValues;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Optional;

public class OCCommonProxy {

    @Optional.Method(modid = GFValues.MODID_OC)
    public void init() {
        if (GFConfig.openComputers.enableOpenComputers && GTValues.isModLoaded(GFValues.MODID_OC)) {
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
            //if (GTValues.isModLoaded(GFValues.MODID_GCY)) {
                //Driver.add(new DriverMTENuclearReactor());
                //Driver.add(new DriverMTEVoidMiner());
                //Driver.add(new DriverTEWorldAccelerator()); // todo this may move to CEu
                //Driver.add(new DriverQubitMultiblockController());
                //Driver.add(new DriverMTEBatteryTower()); // todo this will move to CEu
            //}
        }
    }
}

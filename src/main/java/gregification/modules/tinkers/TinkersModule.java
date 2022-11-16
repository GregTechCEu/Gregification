package gregification.modules.tinkers;

import gregification.base.BaseConfig;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.modules.tinkers.material.MaterialAdditions;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@Module.Root(name = "Gregification: Tinkers' Construct")
public class TinkersModule implements Module {

    @Module.Log
    public static Logger logger;

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableTinkersConstructModule && Loader.isModLoaded(ModIDs.MODID_TCON);
    }

    @Override
    public List<Class<?>> getEventBusListeners() {
        return Collections.singletonList(TinkersEvents.class);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        SmelteryProcessing.integrateMaterials();
        MaterialAdditions.init();
    }
}

package gregification.modules.tinkers;

import gregification.base.BaseConfig;
import gregification.base.ModIDs;
import gregification.base.Module;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Logger;

@Module.Root(name = "Gregification: Tinkers' Construct")
public class TinkersModule implements Module {

    @Module.Log
    public static Logger logger;

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableTinkersConstructModule && Loader.isModLoaded(ModIDs.MODID_TCON);
    }
}

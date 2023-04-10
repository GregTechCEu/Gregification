package gregification.modules.tinkers;

import com.google.common.collect.ImmutableList;
import gregification.base.BaseConfig;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.modules.tinkers.book.GregificationBook;
import gregification.modules.tinkers.material.MaterialProcessing;
import gregification.modules.tinkers.traits.ModifierSalty;
import gregification.modules.tinkers.traits.TinkersTraits;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

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
        return ImmutableList.of(TinkersEvents.class, ModifierSalty.class);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MaterialProcessing.init();
        TinkersTraits.registerModifiers();
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            GregificationBook.register();
        }
    }
}

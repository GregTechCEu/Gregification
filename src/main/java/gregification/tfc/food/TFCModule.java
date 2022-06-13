package gregification.tfc.food;

import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.tfc.TFCFoodComponent;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@Module.Root(name = "Gregification: Ex TerraFirmaCraft")
public class TFCModule implements Module {
    @Module.Log
    public static Logger logger;

    @Override
    public boolean isModuleActive() {
        return Loader.isModLoaded(ModIDs.MODID_TFC);
    }

    @Override
    public List<Class<?>> getEventBusListeners() {
        return Collections.singletonList(this.getClass());
    }

    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof MetaItem
                && ((MetaItem<?>) event.getObject().getItem()).getItem(event.getObject()) != null) {
            for (IItemComponent component : ((MetaItem<?>) event.getObject().getItem()).getItem(event.getObject()).getAllStats()) {
                if (component instanceof TFCFoodComponent) {
                    ICapabilityProvider foodHandler = ((TFCFoodComponent) component).getTFCFoodHandler();
                    if (foodHandler != null)
                        event.addCapability(CapabilityFood.KEY, foodHandler);
                    break;
                }
            }
        }
    }
}

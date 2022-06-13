package gregification.proxy;

import gregification.common.GFValues;
import gregification.terrafirmacraft.TFCFoodComponent;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.items.metaitem.stats.IItemComponent;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = GFValues.MODID)
public class TFCCommonProxy {
    @SubscribeEvent
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

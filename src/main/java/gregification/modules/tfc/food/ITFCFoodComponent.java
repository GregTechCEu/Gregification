package gregification.modules.tfc.food;

import gregtech.api.items.metaitem.stats.IFoodBehavior;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 Along with this, it is recommended that one calls player.getFoodStats().addStats(null, stack)
 in onFoodEaten, so that TFC actually adds nutrients and hunger correctly.
 */
public interface ITFCFoodComponent extends IFoodBehavior {

    ICapabilityProvider getTFCFoodHandler();
}

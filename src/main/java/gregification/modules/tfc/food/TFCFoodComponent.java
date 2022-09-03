package gregification.modules.tfc.food;

import gregification.base.ModIDs;
import gregification.modules.tfc.TFCConfig;
import gregification.modules.tfc.TFCModule;
import gregtech.api.items.metaitem.FoodStats;
import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.util.RandomPotionEffect;
import gregtechfoodoption.item.GTFOFoodStats;
import net.dries007.tfc.api.capability.food.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;

public class TFCFoodComponent extends FoodStats implements ITFCFoodComponent {

    protected FoodData foodData = new FoodData();
    protected boolean isHeatable = false;
    protected int heatCapacity;
    protected int meltTemp;
    protected IFoodBehavior oldBehavior;

    public TFCFoodComponent(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible, ItemStack containerItem, RandomPotionEffect... potionEffects) {
        super(foodLevel, saturation, isDrink, alwaysEdible, containerItem, potionEffects);
        if (TFCConfig.allowGregTechFoodToHaveStatsInTFC)
            this.foodData = new FoodData(foodLevel, 0, saturation, 0, 0, 0, 0, 0, 1);
    }

    public TFCFoodComponent(int foodLevel, float saturation, boolean isDrink) {
        this(foodLevel, saturation, isDrink, false, null);
    }

    public TFCFoodComponent(int foodLevel, float saturation) {
        this(foodLevel, saturation, false);
    }

    private TFCFoodComponent(IFoodBehavior oldBehavior) {
        this(oldBehavior.getFoodLevel(null, null), oldBehavior.getSaturation(null, null), oldBehavior.getFoodAction(null) == EnumAction.DRINK,
                oldBehavior.alwaysEdible(null, null),
                oldBehavior instanceof FoodStats ? ((FoodStats) oldBehavior).containerItem
                        : Loader.isModLoaded(ModIDs.MODID_GTFO) && oldBehavior instanceof GTFOFoodStats ? ((GTFOFoodStats) oldBehavior).stackSupplier.get() : null,
                oldBehavior instanceof FoodStats ? ((FoodStats) oldBehavior).potionEffects
                        : Loader.isModLoaded(ModIDs.MODID_GTFO) && oldBehavior instanceof GTFOFoodStats ? ((GTFOFoodStats) oldBehavior).potionEffects : null);
        // Although technically the above weirdness should not be needed, it is nice for accessing these values without needing to jump through several hurdles.
        this.oldBehavior = oldBehavior;
    }

    public static TFCFoodComponent createFromFoodStats(MetaItem.MetaValueItem metaValueItem) {
        if (metaValueItem.getUseManager() instanceof FoodUseManager) {
            try {
                return new TFCFoodComponent(((FoodUseManager) metaValueItem.getUseManager()).getFoodStats());
            } catch (Exception e) {
                TFCModule.logger.error("FoodStats of item " + metaValueItem.unlocalizedName + "cannot be converted into a TFCFoodComponent!");
                e.printStackTrace();
            }
        }
        TFCModule.logger.warn("Item " + metaValueItem + " is not meant to be food, and yet TFC FoodStats were attempted to be made from it.");
        return new TFCFoodComponent(0, 0);
    }

    public TFCFoodComponent setFoodData(float water, float grain, float fruit, float veg, float meat, float dairy) {
        if (TFCConfig.allowGregTechFoodToHaveStatsInTFC) {
            if (TFCConfig.allowGregTechFoodToHaveNutrientsInTFC)
                this.foodData = new FoodData(this.foodLevel, water, this.saturation, grain, fruit, veg, meat, dairy, 1);
            // Otherwise, it should be set from the constructor already.
        }
        return this;
    }

    public TFCFoodComponent setHeatProperties(int heatCapacity, int meltTemp) {
        this.isHeatable = true;
        this.heatCapacity = heatCapacity;
        this.meltTemp = meltTemp;
        return this;
    }

    @Override
    public ItemStack onFoodEaten(ItemStack stack, EntityPlayer player) {
        if (Loader.isModLoaded(ModIDs.MODID_TFC)) {
            int actualSize = stack.getCount();
            stack.setCount(1); // In case the stack had only one item and is empty; this allows the capability to be accessed
            player.getFoodStats().addStats(null, stack);
            stack.setCount(actualSize);
        }
        if (oldBehavior != null)
            return oldBehavior.onFoodEaten(stack, player);
        return super.onFoodEaten(stack, player);
    }

    public ICapabilityProvider getTFCFoodHandler() {
        return this.isHeatable ? new FoodHeatHandler(null, foodData, heatCapacity, meltTemp) : new FoodHandler(null, foodData);
    }

}

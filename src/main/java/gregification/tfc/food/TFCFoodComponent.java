package gregification.tfc.food;

import gregification.base.ModIDs;
import gregification.tfc.TFCConfig;
import gregtech.api.items.metaitem.FoodStats;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.util.RandomPotionEffect;
import net.dries007.tfc.api.capability.food.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Loader;

public class TFCFoodComponent extends FoodStats implements ITFCFoodComponent {

    protected FoodData foodData = new FoodData();
    protected boolean isHeatable = false;
    protected int heatCapacity;
    protected int meltTemp;

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
            player.getFoodStats().addStats(null, stack);
        }
        return super.onFoodEaten(stack, player);
    }

    public ICapabilityProvider getTFCFoodHandler() {
        return this.isHeatable ? new FoodHeatHandler(null, foodData, heatCapacity, meltTemp) : new FoodHandler(null, foodData);
    }

}

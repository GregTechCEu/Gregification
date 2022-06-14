package gregification.tfc;

import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.tfc.food.TFCFoodComponent;
import gregtech.api.items.metaitem.FoodStats;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.RandomPotionEffect;
import gregtech.common.items.MetaItems;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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

    @Override
    public void init(FMLInitializationEvent event) {
        MetaItems.COIN_CHOCOLATE.addComponents(new TFCFoodComponent(1, 0.1F, false, true, OreDictUnifier.get(OrePrefix.foil, Materials.Gold), new RandomPotionEffect(MobEffects.SPEED, 200, 1, 10))
                .setFoodData(0, 0, 0, 0.2f, 0, 0).setHeatProperties(0, 0));
        MetaItems.BOTTLE_PURPLE_DRINK.addComponents(new TFCFoodComponent(8, 0.2F, true, true, new ItemStack(Items.GLASS_BOTTLE), new RandomPotionEffect(MobEffects.HASTE, 800, 1, 90))
                .setFoodData(3, 0, 1, 0, 0, 0).setHeatProperties(10, 240));
    }
}

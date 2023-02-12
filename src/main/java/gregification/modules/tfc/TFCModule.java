package gregification.modules.tfc;

import gregification.base.BaseConfig;
import gregification.base.ModIDs;
import gregification.base.Module;
import gregification.modules.tfc.food.ITFCFoodComponent;
import gregification.modules.tfc.food.TFCFoodComponent;
import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@Module.Root(name = "Gregification: TerraFirmaCraft")
public class TFCModule implements Module {
    @Module.Log
    public static Logger logger;

    @Override
    public boolean isModuleActive() {
        return BaseConfig.enableTerraFirmaCraftModule && Loader.isModLoaded(ModIDs.MODID_TFC);
    }

    @Override
    public List<Class<?>> getEventBusListeners() {
        return Collections.singletonList(this.getClass());
    }

    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof MetaItem && ((MetaItem<?>) event.getObject().getItem()).getItem(event.getObject()) != null) {
            MetaItem.MetaValueItem valueItem = ((MetaItem<?>) event.getObject().getItem()).getItem(event.getObject());
            if (valueItem.getUseManager() instanceof FoodUseManager && ((FoodUseManager) valueItem.getUseManager()).getFoodStats() != null) {
                IFoodBehavior gtFoodStats = ((FoodUseManager) valueItem.getUseManager()).getFoodStats();
                if (gtFoodStats instanceof ITFCFoodComponent) {
                    ICapabilityProvider foodHandler = ((ITFCFoodComponent) gtFoodStats).getTFCFoodHandler();
                    if (foodHandler != null) event.addCapability(CapabilityFood.KEY, foodHandler);
                    // If the food behavior is not a TFCFoodComponent, one can't really
                }
            }
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        MetaItems.COIN_CHOCOLATE.addComponents(new TFCFoodComponent(1, 0.1F, false, true, OreDictUnifier.get(OrePrefix.foil, Materials.Gold), new RandomPotionEffect(MobEffects.SPEED, 200, 1, 10)).setFoodData(0, 0, 0, 0.2f, 0, 0).setHeatProperties(0, 0));
        MetaItems.BOTTLE_PURPLE_DRINK.addComponents(new TFCFoodComponent(8, 0.2F, true, true, new ItemStack(Items.GLASS_BOTTLE), new RandomPotionEffect(MobEffects.HASTE, 800, 1, 90)).setFoodData(3, 0, 1, 0, 0, 0).setHeatProperties(10, 240));
    }


    /**
     * For addons attempting to use this event, you must send an IMC message to Gregification labelled "setTfcFoodStats" with an NBT compound consisting of:
     *   an int "item" (identifies the metaitem, done with Item.getIdFromItem(Item)
     *   an int "damage" (or metavalue, can be accessed with metaItem.getMetaValue()
     *   an NBT compound "tfcFoodStats" consisting of:
     *     a float "water"
     *     a float "grain"
     *     a float "fruit"
     *     a float "veg"
     *     a float "meat"
     *     a float "dairy"
     *   optionally, an NBT compound "tfcHeatingStats" consisting of:
     *     an int "heatCapacity"
     *     an int "meltTemp"
     */
    @Override
    public void respondIMC(FMLInterModComms.IMCEvent event) {
        for (final FMLInterModComms.IMCMessage imcMessage : event.getMessages()) {
            if (imcMessage.key.equalsIgnoreCase("setTfcFoodStats")) {
                if (imcMessage.isNBTMessage()) {
                    NBTTagCompound tag = imcMessage.getNBTValue();
                    if (tag.hasKey("item", 3) && Item.getItemById(tag.getInteger("item")) instanceof MetaItem) {
                        MetaItem metaItem = (MetaItem) Item.getItemById(tag.getInteger("item"));
                        if (tag.hasKey("damage", 3)) {
                            MetaItem.MetaValueItem item = metaItem.getItem(tag.getShort("damage"));
                            if (item != null) {
                                TFCFoodComponent comp = TFCFoodComponent.createFromFoodStats(item);
                                if (tag.hasKey("tfcFoodStats", 10)) {
                                    NBTTagCompound tfcFoodStats = tag.getCompoundTag("tfcFoodStats");
                                    comp.setFoodData(tfcFoodStats.getFloat("water"), tfcFoodStats.getFloat("grain"),
                                            tfcFoodStats.getFloat("fruit"), tfcFoodStats.getFloat("veg"),
                                            tfcFoodStats.getFloat("meat"), tfcFoodStats.getFloat("dairy"));
                                }
                                if (tag.hasKey("tfcHeatingStats", 10)) {
                                    NBTTagCompound tfcHeatingStats = tag.getCompoundTag("tfcHeatingStats");
                                    comp.setHeatProperties(tfcHeatingStats.getInteger("heatCapacity"), tfcHeatingStats.getInteger("meltTemp"));
                                }
                                item.addComponents(comp);
                            }
                        }
                    }
                }
            }
        }
    }
}

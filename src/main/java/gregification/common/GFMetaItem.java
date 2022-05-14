package gregification.common;

import gregification.config.GFConfig;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;

public class GFMetaItem extends StandardMetaItem {

    public static MetaItem<?>.MetaValueItem ELECTRODE_APATITE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BLAZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_BRONZE;
    public static MetaItem<?>.MetaValueItem ELECTRODE_COPPER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_DIAMOND;
    public static MetaItem<?>.MetaValueItem ELECTRODE_EMERALD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ENDER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_GOLD;
    public static MetaItem<?>.MetaValueItem ELECTRODE_IRON;
    public static MetaItem<?>.MetaValueItem ELECTRODE_LAPIS;
    public static MetaItem<?>.MetaValueItem ELECTRODE_OBSIDIAN;
    public static MetaItem<?>.MetaValueItem ELECTRODE_ORCHID;
    public static MetaItem<?>.MetaValueItem ELECTRODE_RUBBER;
    public static MetaItem<?>.MetaValueItem ELECTRODE_TIN;

    public static MetaItem<?>.MetaValueItem HYPERIUM_ESSENCE;
    public static MetaItem<?>.MetaValueItem HYPERIUM_SEED;
    public static MetaItem<?>.MetaValueItem LUDICIUM_ESSENCE;
    public static MetaItem<?>.MetaValueItem LUDICIUM_SEED;

    public static void init() {
        GFMetaItem item = new GFMetaItem();
        item.setRegistryName("gf_meta_item");
    }

    @Override
    public void registerSubItems() {
        if (Loader.isModLoaded(GFValues.FORESTRY) && GFConfig.forestry.enableForestry && GFConfig.forestry.gtElectrodes) {
            registerForestryItems();
        }
        if (Loader.isModLoaded(GFValues.MODID_MA) && GFConfig.mysticalAgriculture.enableMysticalAgriculture) {
            registerMysticalAgricultureItems();
        }
    }

    private void registerForestryItems() {

        // Electrodes
        ELECTRODE_APATITE = addItem(1, "electrode.apatite");
        ELECTRODE_BLAZE = addItem(2, "electrode.blaze");
        ELECTRODE_BRONZE = addItem(3, "electrode.bronze");
        ELECTRODE_COPPER = addItem(4, "electrode.copper");
        ELECTRODE_DIAMOND = addItem(5, "electrode.diamond");
        ELECTRODE_EMERALD = addItem(6, "electrode.emerald");
        ELECTRODE_ENDER = addItem(7, "electrode.ender");
        ELECTRODE_GOLD = addItem(8, "electrode.gold");
        ELECTRODE_LAPIS = addItem(9, "electrode.lapis");
        ELECTRODE_OBSIDIAN = addItem(10, "electrode.obsidian");
        ELECTRODE_TIN = addItem(11, "electrode.tin");


        if (Loader.isModLoaded(GFValues.MODID_IC2) || Loader.isModLoaded(GFValues.MODID_BINNIE)) {
            ELECTRODE_IRON = addItem(12, "electrode.iron");
        }

        if (Loader.isModLoaded(GFValues.MODID_XU2)) {
            ELECTRODE_ORCHID = addItem(13, "electrode.orchid");
        }

        if (Loader.isModLoaded(GFValues.MODID_IC2) || Loader.isModLoaded(GFValues.MODID_TR) || Loader.isModLoaded(GFValues.MODID_BINNIE)) {
            ELECTRODE_RUBBER = addItem(14, "electrode.rubber");
        }
    }

    private void registerMysticalAgricultureItems() {
        HYPERIUM_ESSENCE = addItem(15, "hyperium_essence");
        LUDICIUM_ESSENCE = addItem(16, "ludicium_essence");
        HYPERIUM_SEED = addItem(17, "hyperium_crafting_seed");
        LUDICIUM_SEED = addItem(18, "ludicium_crafting_seed");
    }

    // Override for a small optimization
    @Override
    @Nonnull
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }
}

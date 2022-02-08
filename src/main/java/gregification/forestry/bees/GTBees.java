package gregification.forestry.bees;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleFlowers;
import forestry.modules.ModuleHelper;
import gregification.common.GFLog;
import gregification.common.GFValues;
import gregification.config.GFConfig;
import gregification.forestry.frames.GTFrameType;
import gregification.forestry.frames.GTItemFrame;
import gregtech.api.GTValues;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class GTBees {

    // Frequently used booleans for enabling some features
    public static final boolean TWILIGHT_BEES = GFConfig.forestry.twilightBees && GTValues.isModLoaded(GFValues.MODID_TF) && GTValues.isModLoaded(GFValues.MODID_MB);
    public static final boolean THAUMIC_BEES = GFConfig.forestry.thaumicBees && GTValues.isModLoaded(GFValues.MODID_THAUM) && GTValues.isModLoaded(GFValues.MODID_MB);

    // New Items
    public static GTItemComb combs;
    public static GTItemDrop drops;
    public static Map<GTFrameType, GTItemFrame> frames = new HashMap<>();

    public static ItemStack getCombStack(GTCombType type) {
        return getCombStack(type, 1);
    }

    public static ItemStack getCombStack(GTCombType type, int amount) {
        if (!ModuleHelper.isEnabled("apiculture")) {
            GFLog.forestryLogger.error("Tried to get GT Comb stack, but Apiculture module is not enabled!");
        }
        return new ItemStack(combs, amount, type.ordinal());
    }

    public static ItemStack getDropStack(GTDropType type) {
        return getDropStack(type, 1);
    }

    public static ItemStack getDropStack(GTDropType type, int amount) {
        if (!ModuleHelper.isEnabled("apiculture")) {
            GFLog.forestryLogger.error("Tried to get GT Drop stack, but Apiculture module is not enabled!");
        }
        return new ItemStack(drops, amount, type.ordinal());
    }

    public static ItemStack getFrameStack(GTFrameType type) {
        if (!ModuleHelper.isEnabled("apiculture")) {
            GFLog.forestryLogger.error("Tried to get GT Frame stack, but Apiculture module is not enabled!");
        }
        return new ItemStack(frames.get(type), 1);
    }

    public static IAlleleBeeEffect getEffect(String modid, String name) {
        String s;
        switch (modid) {
            case GFValues.MODID_EB:
                s = "extrabees.effect." + name;
                break;
            case GFValues.MODID_MB:
                s = "magicbees.effect" + name;
                break;
            case GTValues.MODID:
                s = "gregtech.effect." + name;
                break;
            default:
                s = "forestry.effect" + name;
                break;
        }
        return (IAlleleBeeEffect) AlleleManager.alleleRegistry.getAllele(s);
    }

    public static IAlleleFlowers getFlowers(String modid, String name) {
        String s;
        switch (modid) {
            case GFValues.MODID_EB:
                s = "extrabees.flower." + name;
                break;
            case GFValues.MODID_MB:
                s = "magicbees.flower" + name;
                break;
            case GTValues.MODID:
                s = "gregtech.flower." + name;
                break;
            default:
                s = "forestry.flowers" + name;
                break;
        }
        return (IAlleleFlowers) AlleleManager.alleleRegistry.getAllele(s);
    }

    public static IAlleleBeeSpecies getSpecies(String modid, String name) {
        String s;
        switch (modid) {
            case GFValues.MODID_EB:
                s = "extrabees.species." + name;
                break;
            case GFValues.MODID_MB:
                s = "magicbees.species" + name;
                break;
            case GTValues.MODID:
                s = "gregtech.species." + name;
                break;
            default:
                s = "forestry.species" + name;
                break;
        }
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele(s);
    }
}

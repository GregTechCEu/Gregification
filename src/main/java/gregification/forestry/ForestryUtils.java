package gregification.forestry;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleFlowers;
import forestry.api.recipes.RecipeManagers;
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import forestry.factory.recipes.CarpenterRecipeManager;
import forestry.modules.ModuleHelper;
import gregification.base.ModIDs;
import gregification.forestry.bees.GTCombType;
import gregification.forestry.bees.GTDropType;
import gregification.forestry.frames.GTFrameType;
import net.minecraft.item.ItemStack;

public class ForestryUtils {

    public static ItemStack getCombStack(GTCombType type) {
        return getCombStack(type, 1);
    }

    public static ItemStack getCombStack(GTCombType type, int amount) {
        if (!apicultureEnabled()) {
            ForestryModule.logger.error("Tried to get GT Comb stack, but Apiculture module is not enabled!");
            return ItemStack.EMPTY;
        }
        return new ItemStack(ForestryModule.gtCombs, amount, type.ordinal());
    }

    public static ItemStack getDropStack(GTDropType type) {
        return getDropStack(type, 1);
    }

    public static ItemStack getDropStack(GTDropType type, int amount) {
        if (!apicultureEnabled()) {
            ForestryModule.logger.error("Tried to get GT Drop stack, but Apiculture module is not enabled!");
            return ItemStack.EMPTY;
        }
        return new ItemStack(ForestryModule.gtDrops, amount, type.ordinal());
    }

    public static ItemStack getFrameStack(GTFrameType type) {
        if (!apicultureEnabled()) {
            ForestryModule.logger.error("Tried to get GT Frame stack, but Apiculture module is not enabled!");
            return ItemStack.EMPTY;
        }
        return new ItemStack(ForestryModule.gtFrames.get(type), 1);
    }

    public static IAlleleBeeEffect getEffect(String modid, String name) {
        String s;
        switch (modid) {
            case ModIDs.MODID_EB:
                s = "extrabees.effect." + name;
                break;
            case ModIDs.MODID_MB:
                s = "magicbees.effect" + name;
                break;
            case ModIDs.MODID_GT:
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
            case ModIDs.MODID_EB:
                s = "extrabees.flower." + name;
                break;
            case ModIDs.MODID_MB:
                s = "magicbees.flower" + name;
                break;
            case ModIDs.MODID_GT:
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
            case ModIDs.MODID_EB:
                s = "extrabees.species." + name;
                break;
            case ModIDs.MODID_MB:
                s = "magicbees.species" + name;
                break;
            case ModIDs.MODID_GT:
                s = "gregtech.species." + name;
                break;
            default:
                s = "forestry.species" + name;
                break;
        }
        return (IAlleleBeeSpecies) AlleleManager.alleleRegistry.getAllele(s);
    }

    public static void removeCarpenterRecipe(ItemStack output) {
        if (ModuleFactory.machineEnabled(MachineUIDs.CARPENTER)) {
            CarpenterRecipeManager.getRecipes(output).forEach(r -> RecipeManagers.carpenterManager.removeRecipe(r));
        }
    }

    public static boolean apicultureEnabled() {
        return ModuleHelper.isEnabled("apiculture");
    }
}

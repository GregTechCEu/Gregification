package gregification.forestry.recipes;

import forestry.api.recipes.RecipeManagers;
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import forestry.factory.recipes.CarpenterRecipeManager;
import net.minecraft.item.ItemStack;

public class ForestryRecipeUtil {

    public static void removeCarpenterRecipe(ItemStack output) {
        if (ModuleFactory.machineEnabled(MachineUIDs.CARPENTER)) {
            CarpenterRecipeManager.getRecipes(output).forEach(r -> RecipeManagers.carpenterManager.removeRecipe(r));
        }
    }
}

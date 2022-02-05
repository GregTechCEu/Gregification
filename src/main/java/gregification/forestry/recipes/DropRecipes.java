/*
    Copyright 2022, GregoriusT, dan
    GregTech5

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gregification.forestry.recipes;

import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.ModuleApiculture;
import forestry.apiculture.items.EnumPropolis;
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import gregification.common.GFUtility;
import gregification.common.GFValues;
import gregification.forestry.bees.GTDropType;
import gregification.proxy.ForestryCommonProxy;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import net.minecraft.item.ItemStack;

public class DropRecipes {

    public static void init() {

        // Oil Drop
        ItemStack dropStack = getStackForType(GTDropType.OIL);
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1), 3000, 0)
                .fluidOutputs(Materials.OilHeavy.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.OilHeavy.getFluid(100), ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1), 30);
        }

        // Biomass Drop
        dropStack = getStackForType(GTDropType.BIOMASS);
        ItemStack propolisStack = ModuleApiculture.getItems().propolis.get(EnumPropolis.NORMAL, 1);
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            propolisStack = GFUtility.getModItem(GFValues.MODID_EB, "propolis", 7);
        }
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(propolisStack, 3000, 0)
                .fluidOutputs(Materials.Biomass.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.Biomass.getFluid(100), propolisStack, 30);
        }

        // Ethanol Drop
        dropStack = getStackForType(GTDropType.ETHANOL);
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(dropStack)
                .chancedOutput(propolisStack, 3000, 0)
                .fluidOutputs(Materials.Ethanol.getFluid(100))
                .duration(32).EUt(8).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.SQUEEZER)) {
            RecipeManagers.squeezerManager.addRecipe(40, dropStack, Materials.Ethanol.getFluid(100), propolisStack, 30);
        }

        // TODO Other drops
    }

    private static ItemStack getStackForType(GTDropType type) {
        return ForestryCommonProxy.drops.get(type);
    }
}

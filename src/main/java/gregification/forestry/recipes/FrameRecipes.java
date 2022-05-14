/*
    Copyright 2022, Alkalus, dan
    GregTech++

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
import forestry.factory.MachineUIDs;
import forestry.factory.ModuleFactory;
import gregification.forestry.bees.GTBees;
import gregification.forestry.frames.GTFrameType;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FrameRecipes {

    public static void init() {
        registerRecipe("stickLongElectrum", "stickElectrum", "foilElectrum",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.ACCELERATED),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongUranium235", "stickPlutonium241", "foilPlutonium241",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.MUTAGENIC),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongBlueSteel", "stickBlueSteel", "gemNetherStar",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.WORKING),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongWroughtIron", "stickWroughtIron", "foilWroughtIron",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.DECAYING),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongPotin", "stickPotin", "foilElectrum",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.SLOWING),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongOsmiridium", "stickOsmiridium", "foilOsmiridium",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.STABILIZING),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());

        registerRecipe("stickLongTreatedWood", "stickTreatedWood", "platePaper",
                Materials.Redstone.getFluid(576), GTBees.getFrameStack(GTFrameType.ARBORIST),
                ModuleApiculture.getItems().frameImpregnated.getItemStack());
    }

    private static void registerRecipe(String cornerItem, String edgeItem, String centerItem, FluidStack fluid, ItemStack output, ItemStack frame) {
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTUtility.copyAmount(4, OreDictUnifier.get(cornerItem)))
                .inputs(GTUtility.copyAmount(4, OreDictUnifier.get(edgeItem)))
                .inputs(GTUtility.copyAmount(1, OreDictUnifier.get(centerItem)))
                .fluidInputs(fluid.copy())
                .outputs(GTUtility.copyAmount(1, output))
                .duration(300).EUt(GTValues.VA[GTValues.LV]).buildAndRegister();

        if (ModuleFactory.machineEnabled(MachineUIDs.CARPENTER)) {
            RecipeManagers.carpenterManager.addRecipe(15, fluid.copy(), frame.copy(), output.copy(),
                    "CEC", "E#E", "CEC",
                    'C', cornerItem,
                    'E', edgeItem,
                    '#', centerItem);
        }
    }
}

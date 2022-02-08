package gregification.forestry.recipes;

import gregification.common.GFUtility;
import gregification.common.GFValues;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;

import static gregification.common.GFValues.*;

public class PropolisRecipes {

    public static void init() {

        // Extra Bees Propolis
        if (GTValues.isModLoaded(GFValues.MODID_EB)) {
            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(GFUtility.getModItem(MODID_EB, "propolis", 0))
                    .fluidOutputs(Materials.Water.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();

            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(GFUtility.getModItem(MODID_EB, "propolis", 1))
                    .fluidOutputs(Materials.Oil.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();

            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder()
                    .inputs(GFUtility.getModItem(MODID_EB, "propolis", 7))
                    .fluidOutputs(Materials.Creosote.getFluid(500))
                    .duration(32).EUt(7).buildAndRegister();
        }
    }
}

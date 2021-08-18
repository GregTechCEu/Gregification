package gregification;

import crafttweaker.annotations.ZenRegister;
import gregification.exnihilo.SieveRecipeMap;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gregification.recipe.GFRecipeMaps")
@ZenRegister
public class GFRecipeMaps {

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SIEVE_RECIPES = new SieveRecipeMap("electric_sieve", 2, 2, 1, 24, 0, 0, 0, 0, new SimpleRecipeBuilder().duration(100).EUt(4), false)
            .setProgressBar(GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.HORIZONTAL);
}

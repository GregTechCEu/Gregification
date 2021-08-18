/*
    Copyright 2019, TheLimePixel, dan
    GregBlock Utilities

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
package gregification.exnihilo;

import exnihilocreatio.ModBlocks;
import exnihilocreatio.compatibility.jei.sieve.SieveRecipe;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.Siftable;
import gregification.common.GFOrePrefix;
import gregtech.api.recipes.MatchingMode;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import gregtech.loaders.recipe.MetaTileEntityLoader;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;

import static gregification.common.GFRecipeMaps.SIEVE_RECIPES;
import static gregification.common.GFMetaTileEntities.SIEVES;
import static gregification.common.GFMetaTileEntities.STEAM_SIEVE;
import static gregtech.common.blocks.BlockGranite.GraniteVariant.BLACK_GRANITE;
import static gregtech.common.blocks.BlockGranite.GraniteVariant.RED_GRANITE;
import static gregtech.common.blocks.BlockMineral.MineralVariant.BASALT;
import static gregtech.common.blocks.BlockMineral.MineralVariant.MARBLE;
import static gregtech.common.blocks.BlockSteamCasing.SteamCasingType.BRONZE_HULL;
import static gregtech.common.blocks.StoneBlock.ChiselingVariant.CRACKED;
import static gregtech.common.blocks.StoneBlock.ChiselingVariant.NORMAL;
import static gregtech.loaders.recipe.CraftingComponent.*;

public class ExNihiloRecipes {

    public static void registerHandlers() {
        GFOrePrefix.oreChunk.addProcessingHandler(PropertyKey.ORE, ExNihiloRecipes::processChunk);
        GFOrePrefix.oreEnderChunk.addProcessingHandler(PropertyKey.ORE, ExNihiloRecipes::processChunk);
        GFOrePrefix.oreNetherChunk.addProcessingHandler(PropertyKey.ORE, ExNihiloRecipes::processChunk);
        GFOrePrefix.oreSandyChunk.addProcessingHandler(PropertyKey.ORE, ExNihiloRecipes::processChunk);
    }

    private static void processChunk(OrePrefix orePrefix, Material material, OreProperty oreProperty) {
        Material smeltingMaterial = material;
        ItemStack smeltStack = ItemStack.EMPTY;
        if (oreProperty.getDirectSmeltResult() != null) {
            smeltingMaterial = oreProperty.getDirectSmeltResult();
        }

        if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
            smeltStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        } else if (smeltingMaterial.hasProperty(PropertyKey.GEM)) {
            smeltStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);
        }
        if (!smeltStack.isEmpty() && !material.hasProperty(PropertyKey.BLAST)) {
            ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), smeltStack);
        }
    }

    public static void registerGTRecipes() {
        // Machine Recipes
        MetaTileEntityLoader.registerMachineRecipe(SIEVES, "CPC", "FMF", "OSO", 'M', HULL, 'C', CIRCUIT, 'O', CABLE, 'F', CONVEYOR, 'S', new ItemStack(ModBlocks.sieve), 'P', PISTON);
        ModHandler.addShapedRecipe("steam_sieve", STEAM_SIEVE.getStackForm(), "BPB", "BMB", "BSB", 'B', new UnificationEntry(OrePrefix.pipeSmallFluid, Materials.Bronze), 'M', MetaBlocks.STEAM_CASING.getItemVariant(BRONZE_HULL), 'S', new ItemStack(ModBlocks.sieve), 'P', Blocks.PISTON);

        // Basalt and Granite Pebble recipes
        ModHandler.addShapedRecipe("pebbles_to_basalt",        MetaBlocks.MINERAL.getItemVariant(BASALT, CRACKED),        "PP", "PP", 'P', ExNihiloPebble.getPebbleStack(ExNihiloPebble.GTPebbles.BASALT));
        ModHandler.addShapedRecipe("pebbles_to_black_granite", MetaBlocks.GRANITE.getItemVariant(BLACK_GRANITE, CRACKED), "PP", "PP", 'P', ExNihiloPebble.getPebbleStack(ExNihiloPebble.GTPebbles.BLACK_GRANITE));
        ModHandler.addShapedRecipe("pebbles_to_marble",        MetaBlocks.MINERAL.getItemVariant(MARBLE, CRACKED),        "PP", "PP", 'P', ExNihiloPebble.getPebbleStack(ExNihiloPebble.GTPebbles.MARBLE));
        ModHandler.addShapedRecipe("pebbles_to_red_granite",   MetaBlocks.GRANITE.getItemVariant(RED_GRANITE, CRACKED),   "PP", "PP", 'P', ExNihiloPebble.getPebbleStack(ExNihiloPebble.GTPebbles.RED_GRANITE));

        ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(BASALT, CRACKED),        MetaBlocks.MINERAL.getItemVariant(BASALT, NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(BLACK_GRANITE, CRACKED), MetaBlocks.GRANITE.getItemVariant(BLACK_GRANITE, NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.MINERAL.getItemVariant(MARBLE, CRACKED),        MetaBlocks.MINERAL.getItemVariant(MARBLE, NORMAL));
        ModHandler.addSmeltingRecipe(MetaBlocks.GRANITE.getItemVariant(RED_GRANITE, CRACKED),   MetaBlocks.GRANITE.getItemVariant(RED_GRANITE, NORMAL));
    }

    // Has to be done in init phase because of ExNi registering outside of the Registry event
    public static void registerExNihiloRecipes() {
        // Mirror Ex Nihilo Sifter recipes to Sifter RecipeMap
        for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
            for (ItemStack stack : recipe.getSievables()) {
                // todo do this check better
                //noinspection unchecked
                if (SIEVE_RECIPES.findRecipe(4, Arrays.asList(new ItemStack[]{stack, recipe.getMesh()}), new ArrayList<>(), 0, MatchingMode.DEFAULT) != null) continue;
                SimpleRecipeBuilder builder = SIEVE_RECIPES.recipeBuilder().notConsumable(recipe.getMesh()).inputs(stack);

                for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                    if (siftable.getMeshLevel() == recipe.getMesh().getMetadata())
                        builder.chancedOutput(siftable.getDrop().getItemStack(), (int) (siftable.getChance() * (float) Recipe.getMaxChancedValue()), 500);
                }
                builder.buildAndRegister();
            }
        }
    }
}

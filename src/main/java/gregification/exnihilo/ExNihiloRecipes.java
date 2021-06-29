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
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.GemMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregification.GFRecipeMaps.SIEVE_RECIPES;
import static gregification.exnihilo.metatileentities.ExNihiloMetaTileEntities.SIEVES;
import static gregification.exnihilo.metatileentities.ExNihiloMetaTileEntities.STEAM_SIEVE;
import static gregtech.common.blocks.BlockGranite.GraniteVariant.BLACK_GRANITE;
import static gregtech.common.blocks.BlockGranite.GraniteVariant.RED_GRANITE;
import static gregtech.common.blocks.BlockMachineCasing.MachineCasingType.BRONZE_HULL;
import static gregtech.common.blocks.BlockMineral.MineralVariant.BASALT;
import static gregtech.common.blocks.BlockMineral.MineralVariant.MARBLE;
import static gregtech.common.blocks.StoneBlock.ChiselingVariant.CRACKED;
import static gregtech.common.blocks.StoneBlock.ChiselingVariant.NORMAL;
import static gregtech.common.pipelike.cable.Insulation.CABLE_SINGLE;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class ExNihiloRecipes {

    // TODO Switch from valueOf with GTCEu
    public static void registerHandlers() {
        OrePrefix.valueOf("oreChunk").addProcessingHandler(DustMaterial.class, ExNihiloRecipes::processChunk);
        OrePrefix.valueOf("oreEnderChunk").addProcessingHandler(DustMaterial.class, ExNihiloRecipes::processChunk);
        OrePrefix.valueOf("oreNetherChunk").addProcessingHandler(DustMaterial.class, ExNihiloRecipes::processChunk);
        OrePrefix.valueOf("oreSandyChunk").addProcessingHandler(DustMaterial.class, ExNihiloRecipes::processChunk);
    }

    private static void processChunk(OrePrefix orePrefix, DustMaterial material) {
        ItemStack ingotStack = null;
        DustMaterial smeltingMaterial = material;
        if (material.directSmelting != null) {
            smeltingMaterial = material.directSmelting;
        }
        if (smeltingMaterial instanceof IngotMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        else if (smeltingMaterial instanceof GemMaterial)
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);

        if (ingotStack != null && !ingotStack.isEmpty()) {
            ingotStack.setCount(material.oreMultiplier);

            if (!ingotStack.isEmpty() && isFurnaceMaterial(material)) {
                ModHandler.addSmeltingRecipe(new UnificationEntry(orePrefix, material), ingotStack);
            }
        }
    }

    private static boolean isFurnaceMaterial(Material material) {
        return !(material instanceof IngotMaterial) ||
                ((IngotMaterial) material).blastFurnaceTemperature <= 0;
    }

    public static void registerRecipes() {

        // Machines TODO Re-enable sieve recipe when move to GTCEu
        // registerMachineRecipe(SIEVES, "CPC", "FMF", "OSO", 'M', HULL, 'C', CIRCUIT, 'O', CABLE_SINGLE, 'F', CONVEYOR, 'S', new ItemStack(ModBlocks.sieve), 'P', PISTON);
        ModHandler.addShapedRecipe("steam_sieve", STEAM_SIEVE.getStackForm(), "BPB", "BMB", "BSB", 'B', new UnificationEntry(OrePrefix.pipeSmall, Materials.Bronze), 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BRONZE_HULL), 'S', new ItemStack(ModBlocks.sieve), 'P', new ItemStack(Blocks.PISTON));

        // Mirror Ex Nihilo Sifter recipes to Sifter RecipeMap
        for (SieveRecipe recipe : ExNihiloRegistryManager.SIEVE_REGISTRY.getRecipeList()) {
            for (ItemStack stack : recipe.getSievables()) {
                SimpleRecipeBuilder builder = SIEVE_RECIPES.recipeBuilder().duration(100).EUt(4)
                        .notConsumable(recipe.getMesh())
                        .inputs(stack);

                for (Siftable siftable : ExNihiloRegistryManager.SIEVE_REGISTRY.getDrops(stack)) {
                    if (siftable.getMeshLevel() == recipe.getMesh().getMetadata())
                        builder.chancedOutput(siftable.getDrop().getItemStack(), (int) (siftable.getChance() * (float) Recipe.getMaxChancedValue()), 500);
                }
                builder.buildAndRegister();
            }
        }

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
}

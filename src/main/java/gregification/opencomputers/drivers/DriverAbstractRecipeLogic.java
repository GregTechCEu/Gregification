/*
    Copyright 2020, decal06, dan
    Gregicality

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
package gregification.opencomputers.drivers;

import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class DriverAbstractRecipeLogic extends DriverSidedTileEntity {

    @Override
    public Class<?> getTileEntityClass() {
        return AbstractRecipeLogic.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IGregTechTileEntity) {
            return tileEntity.hasCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, side);
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IGregTechTileEntity) {
            IWorkable capability = tileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_WORKABLE, side);
            if (capability instanceof AbstractRecipeLogic)
                return new EnvironmentAbstractRecipeLogic((IGregTechTileEntity) tileEntity,
                        (AbstractRecipeLogic) capability);
        }
        return null;
    }

    public final static class EnvironmentAbstractRecipeLogic extends EnvironmentMetaTileEntity<AbstractRecipeLogic> {

        public EnvironmentAbstractRecipeLogic(IGregTechTileEntity holder, AbstractRecipeLogic capability) {
            super(holder, capability, "gt_recipeLogic");
        }

        @Callback(doc = "function():table -- Returns previous recipe.")
        public Object[] getCurrentRecipe(final Context context, final Arguments args) {
            Recipe previousRecipe = tileEntity.getPreviousRecipe();
            if (previousRecipe != null && tileEntity.isActive()) {
                HashMap<String, Object> recipe = new HashMap<>();
                recipe.put("duration", previousRecipe.getDuration());
                recipe.put("EUt", previousRecipe.getEUt());

                List<Map<String, Object>> itemInput = new ArrayList<>();
                List<CountableIngredient> inputs = previousRecipe.getInputs();
                inputs.forEach(iR -> {
                    for (ItemStack itemStack : iR.getIngredient().getMatchingStacks()) {
                        Map<String, Object> input = new HashMap<>();
                        input.put("count", iR.getCount());
                        input.put("name", itemStack.getDisplayName());
                        itemInput.add(input);
                    }
                });
                if (!itemInput.isEmpty()) {
                    recipe.put("itemInputs", itemInput);
                }

                List<Map<String, Object>> fluidInput = new ArrayList<>();
                List<FluidStack> fluidInputs = previousRecipe.getFluidInputs();
                fluidInputs.forEach(iR -> {
                    Map<String, Object> input = new HashMap<>();
                    input.put("amount", iR.amount);
                    input.put("name", iR.getFluid().getName());
                    fluidInput.add(input);
                });
                if (!fluidInput.isEmpty()) {
                    recipe.put("fluidInputs", fluidInput);
                }

                List<Map<String, Object>> itemOutput = new ArrayList<>();
                List<ItemStack> outputs = previousRecipe.getOutputs();
                outputs.forEach(iR -> {
                    Map<String, Object> output = new HashMap<>();
                    output.put("count", iR.getCount());
                    output.put("name", iR.getDisplayName());
                    itemOutput.add(output);
                });
                if (!itemOutput.isEmpty()) {
                    recipe.put("itemOutputs", itemOutput);
                }

                List<Map<String, Object>> chancedItemOutput = new ArrayList<>();
                List<Recipe.ChanceEntry> chancedOutputs = previousRecipe.getChancedOutputs();
                chancedOutputs.forEach(iR -> {
                    Map<String, Object> output = new HashMap<>();
                    output.put("chance", iR.getChance());
                    output.put("boostPerTier", iR.getBoostPerTier());
                    output.put("count", iR.getItemStack().getCount());
                    output.put("name", iR.getItemStack().getDisplayName());
                    chancedItemOutput.add(output);
                });
                if (!chancedItemOutput.isEmpty()) {
                    recipe.put("chancedItemOutput", chancedItemOutput);
                }

                List<Map<String, Object>> fluidOutput = new ArrayList<>();
                List<FluidStack> fluidOutputs = previousRecipe.getFluidOutputs();
                fluidOutputs.forEach(iR -> {
                    Map<String, Object> output = new HashMap<>();
                    output.put("amount", iR.amount);
                    output.put("name", iR.getFluid().getName());
                    fluidOutput.add(output);
                });
                if (!fluidOutput.isEmpty()) {
                    recipe.put("fluidOutputs", fluidOutput);
                }
                return new Object[] {recipe};
            }
            return new Object[] {null};
        }
    }
}

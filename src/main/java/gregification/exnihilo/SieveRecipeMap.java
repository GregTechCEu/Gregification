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

import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import java.util.function.DoubleSupplier;

public class SieveRecipeMap extends RecipeMap<SimpleRecipeBuilder> {

    private static final int PROGRESS_LEFT = 51;
    private static final int PROGRESS_TOP = 24;
    private static final int ITEM_SLOT_WITH_EDGE_DIMENSION = 18;
    private static final int OUTPUT_TOP = 2;
    private static final int OUTPUT_LEFT = 79;
    private static final int MESH_TOP = 26;
    private static final int MESH_LEFT = 7;
    private static final int INPUT_TOP = 26;
    private static final int INPUT_LEFT = 25;

    public SieveRecipeMap(String unlocalizedName,
                          int minInputs, int maxInputs, int minOutputs, int maxOutputs,
                          int minFluidInputs, int maxFluidInputs, int minFluidOutputs, int maxFluidOutputs,
                          SimpleRecipeBuilder defaultRecipe, boolean isHidden) {
        super(unlocalizedName, minInputs, maxInputs, minOutputs, maxOutputs, minFluidInputs, maxFluidInputs, minFluidOutputs, maxFluidOutputs, defaultRecipe, isHidden);
    }

    @Override
    @Nonnull
    public ModularUI.Builder createUITemplate(DoubleSupplier progressSupplier, IItemHandlerModifiable importItems,
                                              IItemHandlerModifiable exportItems, FluidTankList importFluids,
                                              FluidTankList exportFluids, int yOffset) {
        ModularUI.Builder builder = ModularUI.defaultBuilder(yOffset);
        builder.widget(new ProgressWidget(progressSupplier, PROGRESS_LEFT, PROGRESS_TOP, 20, 20, this.progressBarTexture, this.moveType));
        this.addInventorySlotGroup(builder, importItems, importFluids, false, yOffset);
        this.addInventorySlotGroup(builder, exportItems, exportFluids, true, yOffset);
        return builder;
    }

    @Override
    protected void addInventorySlotGroup(ModularUI.Builder builder, IItemHandlerModifiable itemHandler, FluidTankList fluidHandler, boolean isOutputs, int yOffset) {
        if (isOutputs) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {
                    int slotIndex = i * 5 + j;
                    addSlot(builder, OUTPUT_LEFT + ITEM_SLOT_WITH_EDGE_DIMENSION * j, OUTPUT_TOP + ITEM_SLOT_WITH_EDGE_DIMENSION * i, slotIndex, itemHandler, fluidHandler, false, true);
                }
            }
        } else {
            addSlot(builder, INPUT_LEFT, INPUT_TOP, 0, itemHandler, fluidHandler, false, false);
            addSlot(builder, MESH_LEFT, MESH_TOP, 1, itemHandler, fluidHandler, false, false);
        }
    }
}

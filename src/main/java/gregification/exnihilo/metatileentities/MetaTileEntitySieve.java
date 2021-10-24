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
package gregification.exnihilo.metatileentities;

import gregification.common.GFRecipeMaps;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.LabelWidget;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.gui.widgets.ToggleButtonWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.render.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntitySieve extends SimpleMachineMetaTileEntity {

    public MetaTileEntitySieve(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, GFRecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, tier, false);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.extendedBuilder()
                .widget(new LabelWidget(6, 6, getMetaFullName()))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT, 8, 134)
                .widget(new SlotWidget(this.importItems, 0, 35, 25).setBackgroundTexture(GuiTextures.SLOT))
                .widget(new SlotWidget(this.importItems, 1, 53, 25).setBackgroundTexture(GuiTextures.SLOT))
                .widget(new ProgressWidget(workable::getProgressPercent, 78, 24, 20, 18, GuiTextures.PROGRESS_BAR_SIFT, ProgressWidget.MoveType.VERTICAL_INVERTED));

        int leftButtonStartX = 7;
        if (exportItems.getSlots() > 0) {
            builder.widget(new ToggleButtonWidget(leftButtonStartX, 62, 18, 18,
                    GuiTextures.BUTTON_ITEM_OUTPUT, this::isAutoOutputItems, this::setAutoOutputItems)
                    .setTooltipText("gregtech.gui.item_auto_output.tooltip"));
        }

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 4; x++) {
                builder.widget(new SlotWidget(this.exportItems, y * 4 + x, 98 + x * 18, 14 + y * 18, true, false).setBackgroundTexture(GuiTextures.SLOT));
            }
        }

        return builder.build(getHolder(), player);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntitySieve(metaTileEntityId, getTier());
    }
}

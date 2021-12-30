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
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SteamMetaTileEntity;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;


public class MetaTileEntitySteamSieve extends SteamMetaTileEntity {

    public MetaTileEntitySteamSieve(ResourceLocation metaTileEntityId, boolean isHighPressure) {
        super(metaTileEntityId, GFRecipeMaps.SIEVE_RECIPES, Textures.SIFTER_OVERLAY, isHighPressure);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder metaTileEntityHolder) {
        return new MetaTileEntitySteamSieve(metaTileEntityId, isHighPressure);
    }
    
    @Override
    public IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(2);
    }

    @Override
    public IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(30);
    }

    @Override
    protected ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND_STEAM.get(isHighPressure), 180, 220)
                .slot(this.importItems, 0, 15, 30, GuiTextures.SLOT_STEAM.get(isHighPressure))
                .slot(this.importItems, 1, 33, 30, GuiTextures.SLOT_STEAM.get(isHighPressure))
                .bindPlayerInventory(player.inventory, GuiTextures.SLOT_STEAM.get(isHighPressure), 8, 134)
                .progressBar(workableHandler::getProgressPercent, 54, 29, 20, 18, GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL);

        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 5; x++) {
                builder.slot(this.exportItems, y * 4 + x, 78 + x * 18, 12 + y * 18, true, false, GuiTextures.SLOT_STEAM.get(isHighPressure));
            }
        }

        return builder.build(getHolder(), player);
    }
}

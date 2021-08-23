/*
    Copyright 2019, TheLimePixel, dan
    Gregic Additions

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

import forestry.core.ModuleCore;
import forestry.core.items.EnumElectronTube;
import gregification.common.GFValues;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import static gregification.common.GFMetaItem.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class ElectrodeRecipes {

    public static void init() {

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .inputs(ELECTRODE_APATITE.getStackForm())
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.APATITE, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Apatite, 2)
                .input(bolt, Apatite)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_APATITE)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Apatite, 4)
                .input(bolt, Apatite, 2)
                .input(dust, Redstone)
                .outputs(ELECTRODE_APATITE.getStackForm(2))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_BLAZE)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BLAZE, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(dust, Blaze, 2)
                .input(dustSmall, Blaze, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_BLAZE, 2)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                .input(dust, Blaze, 5)
                .input(dust, Redstone, 2)
                .output(ELECTRODE_BLAZE, 4)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_BRONZE)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BRONZE, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Bronze, 2).input(bolt, Bronze)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_BRONZE)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Bronze, 4).input(bolt, Bronze, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_BRONZE, 2)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_COPPER)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.COPPER, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Copper, 2)
                .input(bolt, Copper)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_COPPER)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Copper, 4)
                .input(bolt, Copper, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_COPPER, 2)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_DIAMOND)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.DIAMOND, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Diamond, 2)
                .input(bolt, Diamond)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_DIAMOND)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Diamond, 4)
                .input(bolt, Diamond, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_DIAMOND, 2)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_EMERALD)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.EMERALD, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Emerald, 2)
                .input(bolt, Emerald)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_EMERALD)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Emerald, 4)
                .input(bolt, Emerald, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_EMERALD, 2)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_ENDER)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ENDER, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(dust, Endstone, 2)
                .input(dustSmall, Endstone, 2)
                .input(dust, EnderEye)
                .output(ELECTRODE_ENDER, 2)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                .input(dust, Endstone, 5)
                .input(dust, EnderEye, 2)
                .output(ELECTRODE_ENDER, 4)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_GOLD)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.GOLD, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Gold, 2)
                .input(bolt, Gold)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_GOLD)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Gold, 4)
                .input(bolt, Gold, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_GOLD, 2)
                .buildAndRegister();

        if (Loader.isModLoaded(GFValues.MODID_IC2) || Loader.isModLoaded(GFValues.MODID_BINNIE)) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .input(ELECTRODE_IRON)
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.IRON, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Iron, 2)
                    .input(bolt, Iron)
                    .input(dustSmall, Redstone, 2)
                    .output(ELECTRODE_IRON)
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Iron, 4).input(bolt, Iron, 2)
                    .input(dust, Redstone)
                    .output(ELECTRODE_IRON, 2)
                    .buildAndRegister();

        }
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_LAPIS)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.LAPIS, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Lapis, 2)
                .input(bolt, Lapis)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_LAPIS)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Lapis, 4)
                .input(bolt, Lapis, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_LAPIS, 2)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_OBSIDIAN)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.OBSIDIAN, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(dust, Obsidian, 2)
                .input(dustSmall, Obsidian, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_OBSIDIAN, 2)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                .input(dust, Obsidian, 5)
                .input(dust, Redstone, 2)
                .output(ELECTRODE_OBSIDIAN, 4)
                .buildAndRegister();

        if (Loader.isModLoaded(GFValues.MODID_XU2)) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .input(ELECTRODE_ORCHID)
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ORCHID, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24)
                    .inputs(new ItemStack(Blocks.REDSTONE_ORE, 5))
                    .input(dust, Redstone)
                    .output(ELECTRODE_ORCHID, 4)
                    .buildAndRegister();
        }
        if (Loader.isModLoaded(GFValues.MODID_IC2) || Loader.isModLoaded(GFValues.MODID_TR)) {
            ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                    .input(ELECTRODE_RUBBER)
                    .input(plate, Glass)
                    .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.RUBBER, 1))
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                    .input(stick, Rubber, 2)
                    .input(bolt, Rubber)
                    .input(dustSmall, Redstone, 2)
                    .output(ELECTRODE_RUBBER)
                    .buildAndRegister();

            FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                    .input(stick, Rubber, 4)
                    .input(bolt, Rubber, 2)
                    .input(dust, Redstone)
                    .output(ELECTRODE_RUBBER, 2)
                    .buildAndRegister();
        }
        ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16)
                .input(ELECTRODE_TIN)
                .input(plate, Glass)
                .outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.TIN, 1))
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24)
                .input(stick, Tin, 2)
                .input(bolt, Tin)
                .input(dustSmall, Redstone, 2)
                .output(ELECTRODE_TIN)
                .buildAndRegister();

        FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24)
                .input(stick, Tin, 4)
                .input(bolt, Tin, 2)
                .input(dust, Redstone)
                .output(ELECTRODE_TIN, 2)
                .buildAndRegister();
    }
}

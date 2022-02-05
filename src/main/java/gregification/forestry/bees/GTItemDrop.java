/*
    Copyright 2022, GregoriusT, dan
    GregTech5

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
package gregification.forestry.bees;

import forestry.api.apiculture.BeeManager;
import forestry.api.arboriculture.TreeManager;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.ISpeciesRoot;
import forestry.api.lepidopterology.ButterflyManager;
import forestry.core.items.IColoredItem;
import gregification.common.GFValues;
import gregtech.api.GTValues;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GTItemDrop extends Item implements IColoredItem, IItemModelRegister {

    public GTItemDrop() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setTranslationKey("gt.honey_drop");
        setRegistryName(GTValues.MODID, "gt.honey_drop");
        setResearchSuitability(BeeManager.beeRoot);
        setResearchSuitability(TreeManager.treeRoot);
        setResearchSuitability(ButterflyManager.butterflyRoot);
        setResearchSuitability(AlleleManager.alleleRegistry.getSpeciesRoot("rootFlowers"));
    }

    private void setResearchSuitability(@Nullable ISpeciesRoot speciesRoot) {
        if (speciesRoot != null) {
            speciesRoot.setResearchSuitability(new ItemStack(this, 1, GTValues.W), 0.5f);
        }
    }

    @Override
    public void registerModel(@Nonnull Item item, @Nonnull IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < GTCombType.VALUES.length; i++) {
            manager.registerItemModel(item, i, GFValues.FORESTRY, "gt.honey_drop");
        }
    }

    @Override
    @Nonnull
    public String getTranslationKey(@Nonnull ItemStack stack) {
        GTDropType type = GTDropType.getDrop(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.getName();
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (int i = 0; i < GTDropType.VALUES.length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int i) {
        GTDropType type = GTDropType.getDrop(itemStack.getItemDamage());
        return type.getColors()[i == 0 ? 0 : 1];
    }

    public ItemStack get(GTDropType type) {
        return new ItemStack(this, 1, type.ordinal());
    }
}

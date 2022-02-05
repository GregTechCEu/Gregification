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

import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import forestry.core.items.IColoredItem;
import forestry.core.utils.ItemTooltipUtil;
import gregification.common.GFValues;
import gregtech.api.GTValues;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("ALL")
public class GTItemComb extends Item implements IColoredItem, IItemModelRegister {

    public GTItemComb() {
        super();
        setHasSubtypes(true);
        setCreativeTab(Tabs.tabApiculture);
        setRegistryName(GTValues.MODID, "gt.comb");
        setTranslationKey("gt.comb");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel(Item item, IModelManager manager) {
        manager.registerItemModel(item, 0);
        for (int i = 0; i < GTCombType.values().length; i++) {
            manager.registerItemModel(item, i, GFValues.FORESTRY, "gt.comb");
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        GTCombType type = GTCombType.get(stack.getItemDamage());
        return super.getTranslationKey(stack) + "." + type.name;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == Tabs.tabApiculture) {
            for (int i = 0; i < GTCombType.VALUES.length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getColorFromItemstack(ItemStack itemStack, int tintindex) {
        GTCombType type = GTCombType.get(itemStack.getItemDamage());
        return type.getColors()[tintindex >= 1 ? 1 : 0];
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ItemTooltipUtil.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public ItemStack get(GTCombType type, int amount) {
        return new ItemStack(this, 1, type.ordinal());
    }
}

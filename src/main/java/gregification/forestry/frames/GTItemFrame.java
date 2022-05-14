/*
    Copyright 2022, Alkalus, dan
    GregTech++

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
package gregification.forestry.frames;

import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import forestry.api.core.IItemModelRegister;
import forestry.api.core.IModelManager;
import forestry.api.core.Tabs;
import gregification.common.GFValues;
import gregtech.api.GTValues;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class GTItemFrame extends Item implements IHiveFrame, IItemModelRegister {

    private final GTFrameType type;

    public GTItemFrame(GTFrameType type) {
        super();
        this.type = type;
        this.setMaxDamage(this.type.maxDamage);
        this.setMaxStackSize(1);
        this.setCreativeTab(Tabs.tabApiculture);
        this.setRegistryName(GTValues.MODID, "gt.frame_" + type.getName());
        this.setTranslationKey("gt.frame_" + type.getName().toLowerCase());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World worldIn, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flagIn) {
        // TODO
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @Nonnull
    public ItemStack frameUsed(@Nonnull IBeeHousing housing, ItemStack frame, @Nonnull IBee bee, int wear) {
        frame.setItemDamage(frame.getItemDamage() + wear);
        if (frame.getItemDamage() >= frame.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return frame;
    }

    @Nonnull
    @Override
    public IBeeModifier getBeeModifier() {
        return this.type;
    }

    @Override
    public void registerModel(@Nonnull Item item, @Nonnull IModelManager manager) {
        manager.registerItemModel(item, 0, GFValues.FORESTRY, "gt.frame_" + type.getName().toLowerCase());
    }
}

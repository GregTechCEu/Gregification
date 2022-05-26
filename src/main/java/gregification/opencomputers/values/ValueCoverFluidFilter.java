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
package gregification.opencomputers.values;

import gregification.opencomputers.InputValidator;
import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverFluidFilter;
import gregtech.common.covers.FluidFilterMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverFluidFilter extends ValueCoverBehavior {

    public ValueCoverFluidFilter(CoverFluidFilter coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverFluidFilter");
    }

    @Override
    protected CoverFluidFilter getCoverBehavior() {
        CoverBehavior cover = super.getCoverBehavior();
        return cover instanceof CoverFluidFilter ? (CoverFluidFilter) cover : null;
    }

    @Callback(doc = "function(mode:number) --  Sets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] setFilterMode(final Context context, final Arguments args) {
        CoverFluidFilter cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        FluidFilterMode mode = InputValidator.getEnumArrayIndex(args, 0, FluidFilterMode.values());
        cover.setFilterMode(mode);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] getFilterMode(final Context context, final Arguments args) {
        CoverFluidFilter cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{cover.getFilterMode().ordinal()};
    }
}
